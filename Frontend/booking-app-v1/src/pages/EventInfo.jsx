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
import { GoogleMap, LoadScript, Marker } from "@react-google-maps/api";


const GOOGLE_MAPS_API_KEY = import.meta.env.VITE_GOOGLE_MAPS_API_KEY;
const WEATHER_API_KEY = import.meta.env.VITE_OPENWEATHER_API_KEY;

const mapContainerStyle = {
  width: "100%",
  height: "400px",
  borderRadius: "10px",
  marginTop: "20px",
};

const EventInfo = () => {

  const { eventId } = useParams()
  const { events, currencySymbol } = useContext(AppContext)
  const [eventInfo, setEventInfo] = useState(null)
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(null);
  const { profileData } = useUser();
  const [reviews, setReviews] = useState([]);
  const [weather, setWeather] = useState(null);
  const [loading, setLoading] = useState(true);
  const [weatherError, setWeatherError] = useState(null);
  const navigate = useNavigate()

  const fetchEvent = async () => {
    const eventInfo = events.find(event => event.eventId === eventId)
    setEventInfo(eventInfo)   
  }

  useEffect(()=>{
    fetchEvent();
    fetchReviews();
  }, [events, eventId])

  useEffect(() => {
    if (!eventInfo) return;

    const fetchWeather = async () => {
      try {
        const lat = parseFloat(eventInfo?.latitude) //|| -33.9249; // Default: Cape Town
        const lon = parseFloat(eventInfo?.longitude) //|| 18.4241; // Default: Cape Town
        console.log(`Fetching weather for lat: ${lat}, lon: ${lon}`);

        const response = await fetch(
          `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${WEATHER_API_KEY}&units=metric`
        );
        const data = await response.json();

        if (data.cod !== 200) throw new Error(data.message);
        setWeather(data);
      } catch (err) {
        setWeatherError('Failed to fetch weather data');
      } finally {
        setLoading(false);
      }
    };

    fetchWeather();
  }, [eventInfo])

  const handleRsvp = async () => {
    if (!profileData || !profileData.email) {
      setError('User profile data is not available.');
      return;
    }

    try {
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

  const handleDeleteEvent = async () => {
    if (!window.confirm("Are you sure you want to delete this event?")) return;
  
    try {
      await axios.delete(`${import.meta.env.VITE_APP_API_URL}/events/${eventInfo.eventId}`);
      setSuccess('Event deleted successfully!');
      setError(null);
      navigate("/events"); // Redirect after deletion
    } catch (error) {
      setError('Failed to delete event. Please try again.');
      console.error('Error deleting event:', error);
    }
  };

  const handleCloseModal = () => {
    setSuccess(null);
    setError(null);
    setWeather(null);
    navigate("/events")
  };

  const fetchReviews = async () => {
    try {
      const response = await axios.get(`${import.meta.env.VITE_APP_API_URL}/feedback/event/${eventId}`);
      setReviews(response.data);
    } catch (error) {
      console.error('Error fetching reviews:', error);
    }
  };


  return eventInfo && (
    <div>
      {/* ----------Weather Info Section ----------- */}
      <div className="bg-white p-4 rounded-lg shadow-md my-4">
        {loading ? (
          <p>Loading weather...</p>
        ) : weatherError ? (
          <p>{weatherError}</p>
        ) : (
          weather && (
            <div>
              <h3 className="text-xl font-semibold">Weather Forecast for {eventInfo.location}</h3>
              <p>Temperature: {weather.main.temp}°C</p>
              <p>Conditions: {weather.weather[0].description}</p>
              <p>Wind Speed: {weather.wind.speed} m/s</p>
            </div>
          )
        )}
      </div>
        {/* ----------Event Details ----------- */}
        <div className='flex flex-col sm:flex-row gap-4'>
          <div>
            <img className='bg-primary w-full sm:max-w-72 rounded-lg' src={eventInfo.imageUrl} alt="" />
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
              <p className='flex items-center gap-1 text-sm font-medium text-gray-900 mt-3'>
                Address
              </p>
              <p className='text-sm text-gray-500 max-w-[700px] mt-1'>{eventInfo.location}</p>
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
          {/* Only show delete button if user is an admin */}
          {profileData?.role === "Admin" && (
            <button 
              onClick={handleDeleteEvent} 
              className='bg-red-600 text-white text-sm font-light px-20 py-3 rounded-full ml-4'
            >
              Delete Event
            </button>
          )}
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

        {/* Google Map Section */}
        {eventInfo.latitude && eventInfo.longitude && (
          <div className="mt-8">
            <LoadScript googleMapsApiKey={GOOGLE_MAPS_API_KEY}>
              <GoogleMap
                mapContainerStyle={mapContainerStyle}
                center={{ lat: parseFloat(eventInfo.latitude), lng: parseFloat(eventInfo.longitude) }}
        
                zoom={15}
              >
                <Marker 
                  position={{ lat: parseFloat(eventInfo.latitude), lng: parseFloat(eventInfo.longitude) }}
                 
                />
              </GoogleMap>
            </LoadScript>
          </div>
        )}
        
        {/* Review Section */}
        <div className="mt-8">
          <h2 className="text-xl font-semibold text-gray-900">Reviews</h2>
          
          {/* Review Summary */}
          <div className="mt-8 p-4 bg-gray-100 rounded-lg">
            {reviews.length > 0 ? (
              <>
                <h2 className="text-2xl font-bold text-gray-900">Customer Reviews</h2>
                <div className="flex items-center gap-6 mt-3">
                  {/* Average Rating */}
                  <div className="flex flex-col items-center">
                    <p className="text-4xl font-bold text-yellow-500">
                      {reviews.reduce((sum, review) => sum + review.rating, 0) / reviews.length || 0} ⭐
                    </p>
                    <p className="text-sm text-gray-600">Average Rating</p>
                  </div>

                  {/* Recommendation Percentage */}
                  <div className="flex flex-col items-center">
                    <p className="text-4xl font-bold text-green-500">
                    {reviews.reduce((sum, review) => sum + review.recommendation, 0) / reviews.length || 0} ⭐
                    </p>
                    <p className="text-sm text-gray-600">Would Recommend</p>
                  </div>
                </div>
              </>
            ) : (
              <p className="text-gray-500 text-lg">No reviews yet. Be the first to leave one!</p>
            )}
          </div>

          {/* Display Reviews */}
          <div className="mt-4">
            {reviews.map(review => (
              <div key={review.reviewId} className="border-b py-3">
                <p className="text-sm font-medium text-gray-800">{review.email}</p>
                <p className="text-sm text-gray-600">Rating: {review.rating} ⭐</p>
                <p className="text-sm text-gray-600">Recommendation: {review.recommendation}</p>
                <p className="text-sm text-gray-700">{review.comment}</p>
              </div>
            ))}
          </div>

        </div>


        {/* listing related events */}
        <RelatedEvents eventId={eventId} category={eventInfo.category} />
    </div>
  )
}

export default EventInfo
