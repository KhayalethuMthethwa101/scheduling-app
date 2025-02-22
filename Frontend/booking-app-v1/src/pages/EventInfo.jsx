import React, { useContext, useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import { AppContext } from '../context/AppContext'
import { assets } from '../assets/assets'
import RelatedEvents from '../components/relatedEvents'
import axios from 'axios';
import { useUser } from '../context/UserContext';
import { v4 as uuidv4 } from 'uuid';
import { useNavigate } from 'react-router-dom'
import Modal from '../components/modal';


const EventInfo = () => {

  const { eventId } = useParams()
  const { events, currencySymbol } = useContext(AppContext)
  const [eventInfo, setEventInfo] = useState(null)
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(null);
  const { profileData } = useUser();
  const navigate = useNavigate()

  const fetchEvent = async () => {
    const eventInfo = events.find(event => event.eventId === eventId)
    setEventInfo(eventInfo)   
  }


  useEffect(()=>{
    fetchEvent()
  }, [events, eventId])

  const handleRsvp = async () => {
    if (!profileData || !profileData.email) {
      setError('User profile data is not available.');
      return;
    }

    try {
      console.log(eventInfo);
      const bookingData = {
        bookingId: uuidv4(),
        email: profileData.email,
        eventId: eventInfo.eventId,
        eventName: eventInfo.eventName,
        bookingDate: new Date().toISOString().split('T')[0], // Format as YYYY-MM-DD
        timestamp: new Date().toISOString(),
      };

      await axios.post(`${import.meta.env.VITE_APP_API_URL}/bookings`, bookingData);
      setSuccess('RSVP successful!');
      setError(null);
    } catch (error) {
      setError('RSVP failed. Please try again.');
      setSuccess(null);
      console.error('Error during RSVP:', error);
    }
  }

  const handleCloseModal = () => {
    setSuccess(null);
    setError(null);
    navigate("/events")
  };

  return eventInfo && (
    <div>
        {/* ----------Event Details ----------- */}
        <div className='flex flex-col sm:flex-row gap-4'>
          <div>
            <img className='bg-primary w-full sm:max-w-72 rounded-lg' src={eventInfo.image} alt="" />
          </div>
          <div className='flex-1 border border-gray-400 rounded-lg p-8 py-7 bg-white mx-2 sm:mx-0 mt-[-80px] sm:mt-0'>
            {/* -------------Event Info: Event name, Location, Date -------- */}
            <p className='flex items-center gap-2 text-2xl font-medium text-gray-900'>
              {eventInfo.name}
              <img className='w-7' src={assets.verified_icon} alt="" />
            </p>
            <div className='flex items-center gap-2 text-sm mt-1 text-gray-600'>
              <p>{eventInfo.category}</p>
              <button className='py-0.5 px-2 border text-xs rounded-full'>Event capacity {eventInfo.capacity}</button>
            </div>
            {/* ------- Event Details -------- */}
            <div>
              <p className='flex items-center gap-1 text-sm font-medium text-gray-900 mt-3'>
                Description <img className='w-3' src={assets.info_icon} alt="" />
              </p>
              <p className='text-sm text-gray-500 max-w-[700px] mt-1'>{eventInfo.eventDescription}</p>
            </div>
            <div>
              <p className='flex items-center gap-1 text-sm font-medium text-gray-900 mt-3'>Date</p>
              <p className='text-sm text-gray-500 max-w-[700px] mt-1'>{eventInfo.dateOfEvent}</p>
            </div>
            <div>
              <p className='flex items-center gap-1 text-sm font-medium text-gray-900 mt-3'>Time</p>
              <p className='text-sm text-gray-500 max-w-[700px] mt-1'>{eventInfo.timeOfEvent}</p>
            </div>
            <p className='text-gray-500 font-medium mt-4'>
              Reservation Fee: <span className='text-gray-600'>{currencySymbol}{eventInfo.fee}</span>
            </p>
          </div>
          
        </div> 
        
        <div className='sm:ml-72 sm:pl-4 mt-4 font-medium text-gray-700'> 
          {/* RSVP Section */}
          <button onClick={handleRsvp} className='bg-primary text-white text-sm font-light px-20 py-3 rounded-full my-6'>RSVP</button>
          { error && 
              <Modal onClose={handleCloseModal}>
                <p className="text-red-600">{error}</p>
              </Modal>
          }
          { success && 
              <Modal onClose={handleCloseModal}>
                <p className="text-green-600">{success}</p>  
              </Modal>
            }
        </div>

        {/* listing related events */}
        <RelatedEvents eventId={eventId} category={eventInfo.category} />
    </div>
  )
}

export default EventInfo