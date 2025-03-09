import React, { useState, useEffect } from 'react'
import { assets } from '../assets/assets'
import axios from 'axios';
import { useUser } from '../context/UserContext'
import { storage } from '../services/firebase';
import { ref, uploadBytesResumable, getDownloadURL } from "firebase/storage";

const MyProfile = () => {
  const { profileData } = useUser();
  const [userData, setUserData] = useState(null);
  const [isEdit, setIsEdit] = useState(false);
  const [selectedFile, setSelectedFile] = useState(null);
  const [uploading, setUploading] = useState(false);

  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const response = await axios.get(`${import.meta.env.VITE_APP_API_URL}/users/${profileData.email}`);
        setUserData(response.data);
      } catch (error) {
        console.error('Error fetching user data:', error);
      }
    };
    if (profileData.email) {
      fetchUserData();
    }
  }, [profileData.email]);

  const handleFileChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      setSelectedFile(file);
  
      // Show the image preview immediately
      const reader = new FileReader();
      reader.onload = () => {
        setUserData((prev) => ({ ...prev, image: reader.result }));
      };
      reader.readAsDataURL(file);
    }
  };
  
  const handleSave = async () => {
    try {
      let imageURL = userData.image; // Default to current image
  
      if (selectedFile) {
        const storageRef = ref(storage, `profilePictures/${profileData.email}-${selectedFile.name}`);
        const uploadTask = uploadBytesResumable(storageRef, selectedFile);
  
        // Wait for the upload to finish
        await new Promise((resolve, reject) => {
          uploadTask.on(
            'state_changed',
            null,
            (error) => reject(error),
            async () => {
              imageURL = await getDownloadURL(uploadTask.snapshot.ref);
              resolve();
            }
          );
        });
      }
  
      const updatedUserData = { 
        ...userData, 
        image: imageURL, // Ensure new image URL is used 
      };
      
      const response = await axios.put(`${import.meta.env.VITE_APP_API_URL}/users/update`, userData);
      console.log(response.data);
      setUserData(updatedUserData);
      setIsEdit(false);
      setSelectedFile(null); // Reset selected file after save
  
    } catch (error) {
      console.error('Error updating user data:', error);
    }
  };
  


  // Ensure userData is available before rendering
  if (!userData) {
    return <div>Loading...</div>;
  }
  

  return (
    <div className='max-w-lg flex flex-col gap-2 text-sm'>
      <label htmlFor="profilePictureInput">
        <img
          className='w-40 rounded cursor-pointer'
          src={userData.image || assets.profile_pic}
          alt="Profile"
        />
      </label>
      {isEdit && (
        <div>
          <input type="file" id="profilePictureInput" onChange={handleFileChange} className="hidden" />
        </div>
      )}

      {isEdit ? (
        <input
          className='bg-gray-50 text-3xl font-medium max-w-60 mt-4'
          type="text"
          value={userData.userName}
          onChange={(e) => setUserData((prev) => ({ ...prev, userName: e.target.value }))}
        />
      ) : (
        <p className='font-medium text-3xl text-neutral-800 mt-4'>{userData.userName}</p>
      )}

      <hr className='bg-zinc-400 h-[1px] border-none' />
      <div>
        <p className='text-neutral-500 underline mt-3'>CONTACT INFORMATION</p>
        <div className='grid grid-cols-[1fr_3fr] gap-y-2.5 mt-3 text-neutral-700'>
          <p className='font-medium'>Email:</p>
          <p className='text-blue-500'>{userData.email}</p>
          <p className='font-medium'>User Role:</p>
          <p className='text-blue-500'>{userData.userRole}</p>
          <p className='font-medium'>Phone:</p>
          {isEdit ? (
            <input className='bg-gray-100 max-w-52' type="text" value={userData.phoneNumber} onChange={e => setUserData(prev => ({ ...prev, phoneNumber: e.target.value }))} />
          ) : (
            <p className='text-blue-400'>{userData.phoneNumber}</p>
          )}
          <p className='font-medium'>Address</p>
          {isEdit ? (
            <input className='bg-gray-50' type="text" value={userData.address} onChange={(e) => setUserData(prev => ({ ...prev, address: e.target.value }))} />
          ) : (
            <p className='text-gray-500'>{userData.address}</p>
          )}
        </div>
      </div>
      <div>
        <p className='text-neutral-500 underline mt-3'>BASIC INFORMATION</p>
        <div className='grid grid-cols-[1fr_3fr] gap-y-2.5 mt-3 text-neutral-700'>
          <p className='font-medium'>Gender</p>
          {isEdit ? (
            <select className='max-w-20 bg-gray-100' onChange={(e) => setUserData(prev => ({ ...prev, gender: e.target.value }))} value={userData.gender}>
              <option value="Male">Male</option>
              <option value="Female">Female</option>
            </select>
          ) : (
            <p className='text-gray-400'>{userData.gender}</p>
          )}
          <p className='font-medium'>Date of Birth:</p>
          {isEdit ? (
            <input className='max-w-28 bg-gray-100' type="date" onChange={(e) => setUserData(prev => ({ ...prev, dob: e.target.value }))} value={userData.dob} />
          ) : (
            <p className='text-gray-400'>{userData.dob}</p>
          )}
        </div>
      </div>

      <div className='mt-10'>
        {isEdit ? (
          <button className='border border-primary px-8 py-2 rounded-full hover:bg-primary hover:text-white transition-all' onClick={handleSave} disabled={uploading}>
            {uploading ? "Saving..." : "Save information"}
          </button>
        ) : (
          <button className='border border-primary px-8 py-2 rounded-full hover:bg-primary hover:text-white transition-all' onClick={() => setIsEdit(true)}>
            Edit
          </button>
        )}
      </div>
    </div>
  );
  
}

export default MyProfile