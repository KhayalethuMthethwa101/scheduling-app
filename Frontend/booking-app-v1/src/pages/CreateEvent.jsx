import React, { useState, useEffect, useRef } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { v4 as uuidv4 } from 'uuid';
import { storage } from '../services/firebase';
import { ref, uploadBytesResumable, getDownloadURL } from "firebase/storage";


const CreateEvent = () => {
  const [eventDetails, setEventDetails] = useState({
    eventId: uuidv4(),
    eventName: '',
    status: '',
    location: '',
    longitute: '',
    latitude: '',
    category: '',
    eventDescription: '',
    dateOfEvent: '',
    timeOfEvent: '',
    capacity: '',
    fee: '',
    imageUrl: ''
  });
  const [selectedFile, setSelectedFile] = useState(null);
  const [uploading, setUploading] = useState(false);
  const navigate = useNavigate();
  const autocompleteRef = useRef(null);

  useEffect(() => {
    loadGooglePlaces();
  }, []);

  const loadGooglePlaces = () => {
    if (!window.google || !window.google.maps || !window.google.maps.places) {
      const script = document.createElement("script");
      script.src = `https://maps.googleapis.com/maps/api/js?key=${import.meta.env.VITE_GOOGLE_MAPS_API_KEY}&libraries=places`;
      script.async = true;
      script.defer = true;
      script.onload = initAutocomplete;
      document.body.appendChild(script);
    } else {
      initAutocomplete();
    }
  };

  const initAutocomplete = () => {
    if (!autocompleteRef.current) return;
    const autocomplete = new window.google.maps.places.Autocomplete(autocompleteRef.current, {
      types: ["geocode"], // Restrict results to addresses
    });

    autocomplete.addListener("place_changed", () => {
      const place = autocomplete.getPlace();
      if (place.geometry) {
        setEventDetails((prevDetails) => ({
          ...prevDetails,
          location: place.formatted_address,
          latitude: place.geometry.location.lat().toString(),
          longitude: place.geometry.location.lng().toString(),
        }));
      }
    });
  };

  const handleFileChange = (e) => {
    setSelectedFile(e.target.files[0]);
  };

  const handleInputChange = (e) => {
    setEventDetails({
      ...eventDetails,
      [e.target.name]: e.target.value
    });
  };

  const handleUpload = async () => {
    if (!selectedFile) {
      alert("Please select an image!");
      return;
    }

    setUploading(true);

    try {
      // Upload image to Firebase
      const storageRef = ref(storage, `events/${selectedFile.name}`);
      const uploadTask = uploadBytesResumable(storageRef, selectedFile);

      uploadTask.on(
        "state_changed",
        null,
        (error) => {
          console.error("Upload error:", error);
          setUploading(false);
        },
        async () => {
          const downloadURL = await getDownloadURL(uploadTask.snapshot.ref);

          // Save event details along with image URL to backend
          const eventData = {
            ...eventDetails,
            capacity: parseInt(eventDetails.capacity, 10),
            imageUrl: downloadURL
          };

          await axios.post(`${import.meta.env.VITE_APP_API_URL}/events/addEvent`, eventData, {
            headers: {
              "Content-Type": "application/json"
            }
          });

          setUploading(false);
          navigate("/events");
        }
      );
    } catch (error) {
      console.error("Error creating event:", error);
      setUploading(false);
    }
  };

  return (
    <div className='max-w-lg mx-auto mt-10'>
      <h2 className='text-2xl font-semibold mb-4'>Create New Event</h2>
      <input type="text" name="eventName" placeholder="Event Name" onChange={handleInputChange} className='w-full mb-2 p-2 border rounded' />
      <input type="text" name="status" placeholder="Status" onChange={handleInputChange} className='w-full mb-2 p-2 border rounded' />

      {/* Google Places Autocomplete */}
      <input ref={autocompleteRef} type="text" name="location" placeholder="Enter Location" className='w-full mb-2 p-2 border rounded' />
      {eventDetails.latitude && eventDetails.longitude && (
        <p className="text-sm text-gray-500">Latitude: {eventDetails.latitude}, Longitude: {eventDetails.longitude}</p>
      )}
      <select 
        name="category" 
        onChange={handleInputChange} 
        className='w-full mb-2 p-2 border rounded'
        defaultValue="Other"
      >
        <option value="" disabled>Select Category</option>
        <option value="Academic">Academic</option>
        <option value="Art">Art</option>
        <option value="Motorsport">Motorsport</option>
        <option value="Music">Music</option>
        <option value="Sport">Sport</option>
        <option value="Other">Other</option>
      </select>
      <textarea name="eventDescription" placeholder="Description" onChange={handleInputChange} className='w-full mb-2 p-2 border rounded'></textarea>
      <input type="date" name="dateOfEvent" onChange={handleInputChange} className='w-full mb-2 p-2 border rounded' />
      <input type="time" name="timeOfEvent" onChange={handleInputChange} className='w-full mb-2 p-2 border rounded' />
      <input type="number" name="capacity" placeholder="Capacity" onChange={handleInputChange} className='w-full mb-2 p-2 border rounded' />
      <input type="text" name="fee" placeholder="Fee" onChange={handleInputChange} className='w-full mb-2 p-2 border rounded' />
      <input type="file" onChange={handleFileChange} className='w-full mb-4 p-2 border rounded' />
      <button onClick={handleUpload} className='w-full bg-blue-500 text-white py-2 rounded' disabled={uploading}>
        {uploading ? "Uploading..." : "Create Event"}
      </button>
    </div>
  );
};

export default CreateEvent;
