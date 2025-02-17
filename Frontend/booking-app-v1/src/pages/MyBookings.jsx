import React, { useContext, useEffect, useState } from 'react';
import {AppContext} from '../context/AppContext'
import axios from 'axios';
import { useUser } from '../context/UserContext'
import Modal from '../components/modal';
import FeedbackForm from './FeedbackForm';
import { useNavigate } from 'react-router-dom';

const REST_API_URL =  'http://localhost:8081/api/v1'

const MyBookings = () => {

  const { profileData } = useUser(); // Get the logged-in user's profile data
  const { events } = useContext(AppContext);
  const [bookings, setBookings] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(null);
  const [showFeedbackForm, setShowFeedbackForm] = useState(false);
  const [selectedEventId, setSelectedEventId] = useState(null);
  const navigate = useNavigate()


  useEffect(() => {
    const fetchBookings = async () => {
      try {
        const response = await axios.get(`${REST_API_URL}/bookings/user/${profileData.email}`);
        setBookings(response.data);
        setLoading(false);
      } catch (error) {
        console.error('Error fetching bookings:', error);
        setLoading(false);
      }
    };
    if (profileData.email) {
      fetchBookings();
    }
  }, [profileData.email]);

  if (loading) {
    return <div>Loading...</div>;
  }

  const handleCancel = async (bookingId) => {
    try {
      await axios.delete(`${REST_API_URL}/bookings/delete/${bookingId}`);
      setBookings(bookings.filter(booking => booking.bookingId !== bookingId));
    } catch (error) {
      console.error('Error cancelling booking:', error);
    }
  }

  const handleFeedback = (eventId) => {
    setSelectedEventId(eventId);
    setShowFeedbackForm(true);
  }

  const handleCloseModal = () => {
    setShowFeedbackForm(false);
    setSuccess(null);
    setError(null);
  }


return (
    <div>
        <p className='pb-3 mt-12 font-medium text-zinc-700 border-b'>My Appointments</p>
        <p className='text-sm text-gray-500 mt-1'>Total Events RSVP-ed: {bookings.length}</p>
        <div>
          {bookings.map((booking, index) => {
            const event = events.find(event => event.eventId === booking.eventId);
            return (
              <div className='grid grid-cols-[1fr_3fr] gap-4 sm:flex sm:gap-6 py-2 border-b' key={index}>
                <div>
                  <img className='w-32 bg-indigo-50' src={event ? event.image : ''} alt="" />
                </div>
                <div className='flex-1 text-sm text-zinc-600'>
                  <p className='text-neutral-800 font-semibold'>{event ? event.eventName : ''}</p>
                  <p>{event ? event.category : ''}</p>
                  <p className='text-zinc-700 font-medium mt-1'>Address:</p>
                  <p className='text-xs'>{event ? event.address : ''}</p>
                  <p className='text-xs mt-1'><span className='text-sm text-neutral-700 font-medium'>Date & Time</span> {booking.bookingDate} | {event ? event.timeOfEvent : ''}</p>
                </div>
                <div></div>
                <div className='flex flex-col gap-2 justify-end'>
                  <button className='text-sm text-stone-500 text-center sm:min-w-48 py-2 border rounded hover:bg-blue-600 hover:text-white transition-all duration-300' onClick={() => handleFeedback(booking.eventId)}>Give Feedback</button>
                  <button className='text-sm text-stone-500 text-center sm:min-w-48 py-2 border rounded hover:bg-red-600 hover:text-white transition-all duration-300' onClick={() => handleCancel(booking.bookingId)}>Cancel Appointment</button>
                </div>
              </div>
            );
          })}
        </div>
        {showFeedbackForm && (
          <Modal onClose={() => setShowFeedbackForm(false)}>
            <FeedbackForm eventId={selectedEventId} email={profileData.email}/>
          </Modal>
        )}
        {success && <Modal message={success} onClose={handleCloseModal} />}
        {error && <Modal message={error} onClose={handleCloseModal} />}
    </div>
  );
};

export default MyBookings;