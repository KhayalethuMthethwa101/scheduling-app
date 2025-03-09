import React, { useContext, useEffect, useState } from 'react';
import { AppContext } from '../context/AppContext';
import axios from 'axios';
import { useUser } from '../context/UserContext';

const BookingsTable = () => {
  const { events } = useContext(AppContext);
  const { users } = useContext(AppContext);
  const [bookings, setBookings] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchBookings = async () => {
      try {
        const response = await axios.get(`${import.meta.env.VITE_APP_API_URL}/bookings`);
        setBookings(response.data);
        setLoading(false);
      } catch (error) {
        console.error('Error fetching bookings:', error);
        setError('Failed to fetch bookings');
        setLoading(false);
      }
    };
    fetchBookings();
  }, []);

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div className="text-red-500">{error}</div>;
  }

  // Group bookings by event
  const groupedBookings = bookings.reduce((acc, booking) => {
    const event = events.find(event => event.eventId === booking.eventId);
    const user = users.find(user => user.email === booking.email);
    if (!event) return acc;
    if (!acc[event.eventName]) {
      acc[event.eventName] = { event, users: [] };
    }
    acc[event.eventName].users.push({
      name: user ? user.userName : 'Unknown',
      email: booking.email,
      timestamp: booking.timestamp
    });
    return acc;
  }, {});

  return (
    <div>
      <p className='pb-3 mt-12 font-medium text-zinc-700 border-b'>All Bookings</p>
      <p className='text-sm text-gray-500 mt-1'>Total Events with Bookings: {Object.keys(groupedBookings).length}</p>
      <div>
        {Object.entries(groupedBookings).map(([eventName, { event, users }], index) => (
          <div key={index} className='border-b py-4'>
            <h3 className='text-lg font-semibold text-neutral-800'>{eventName}</h3>
            <p className='text-sm text-gray-600'>{event.category}</p>
            <p className='text-sm text-gray-700'>Address: {event.location}</p>
            <p className='text-sm text-gray-700'>Date & Time: {event.dateOfEvent} | {event.timeOfEvent}</p>
            <table className='w-full mt-2 border-collapse border border-gray-300'>
              <thead>
                <tr className='bg-gray-100'>
                  <th className='border border-gray-300 p-2 text-left'>Name</th>
                  <th className='border border-gray-300 p-2 text-left'>Email</th>
                  <th className='border border-gray-300 p-2 text-left'>Booking Timestamp</th>
                </tr>
              </thead>
              <tbody>
                {users.map((user, userIndex) => (
                  <tr key={userIndex} className='border-t'>
                    <td className='border border-gray-300 p-2'>{user.name}</td>
                    <td className='border border-gray-300 p-2'>{user.email}</td>
                    <td className='border border-gray-300 p-2'>{new Date(user.timestamp).toLocaleString()}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        ))}
      </div>
    </div>
  );
};

export default BookingsTable;
