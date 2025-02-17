import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { v4 as uuidv4 } from 'uuid';


const CreateEvent = () => {
  const [eventDetails, setEventDetails] = useState({
    eventId: uuidv4(),
    eventName: '',
    status: '',
    location: '',
    category: '',
    eventDescription: '',
    dateOfEvent: '',
    timeOfEvent: '',
    capacity: '',
    fee: '',
    imageUrl: ''
  });
  const [selectedFile, setSelectedFile] = useState(null);
  const navigate = useNavigate();

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
    const eventData = { ...eventDetails, capacity: parseInt(eventDetails.capacity, 10)};
    const formData = new FormData();
    formData.append('file', selectedFile);
    formData.append('event', new Blob([JSON.stringify(eventData)], { type: 'application/json' }));

    try {
      await axios.post(`${import.meta.env.VITE_APP_API_URL}/events/create`, formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      });
      navigate('/events');
    } catch (error) {
      console.error('Error creating event:', error);
    }
  };

  return (
    <div className='max-w-lg mx-auto mt-10'>
      <h2 className='text-2xl font-semibold mb-4'>Create New Event</h2>
      <input type="text" name="eventName" placeholder="Event Name" onChange={handleInputChange} className='w-full mb-2 p-2 border rounded' />
      <input type="text" name="status" placeholder="Status" onChange={handleInputChange} className='w-full mb-2 p-2 border rounded' />
      <input type="text" name="location" placeholder="Location" onChange={handleInputChange} className='w-full mb-2 p-2 border rounded' />
      <input type="text" name="category" placeholder="Category" onChange={handleInputChange} className='w-full mb-2 p-2 border rounded' />
      <textarea name="eventDescription" placeholder="Description" onChange={handleInputChange} className='w-full mb-2 p-2 border rounded'></textarea>
      <input type="date" name="dateOfEvent" onChange={handleInputChange} className='w-full mb-2 p-2 border rounded' />
      <input type="time" name="timeOfEvent" onChange={handleInputChange} className='w-full mb-2 p-2 border rounded' />
      <input type="number" name="capacity" placeholder="Capacity" onChange={handleInputChange} className='w-full mb-2 p-2 border rounded' />
      <input type="text" name="fee" placeholder="Fee" onChange={handleInputChange} className='w-full mb-2 p-2 border rounded' />
      <input type="file" onChange={handleFileChange} className='w-full mb-4 p-2 border rounded' />
      <button onClick={handleUpload} className='w-full bg-blue-500 text-white py-2 rounded'>Create Event</button>
    </div>
  );
};

export default CreateEvent;
