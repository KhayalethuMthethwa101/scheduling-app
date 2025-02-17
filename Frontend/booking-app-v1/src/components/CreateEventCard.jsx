import React from 'react';
import { useNavigate } from 'react-router-dom';
import { useUser } from '../context/UserContext';
import { assets } from '../assets/assets';

const CreateEventCard = () => {
  const navigate = useNavigate();
  const { profileData } = useUser();

  if (!profileData ||profileData.role !== 'Admin') {
    return null;
  }

  return (
    <div className='border border-blue-200 rounded-xl overflow-hidden cursor-pointer hover:translate-y-[-10px] transition-all duration-500 max-w-md mx-auto mt-10'>
      <div 
        className='border border-dashed border-gray-400 rounded-lg p-6 text-center cursor-pointer hover:border-solid hover:border-gray-600 transition-all'
        onClick={() => navigate('/create-event')}>
        <img className='mx-auto mb-4 w-12 bg-blue-50' src={assets.add_icon} alt="" />
        <p className='text-lg font-semibold text-gray-700'>Create New Event</p>
      </div>
    </div>
  );
};

export default CreateEventCard;
