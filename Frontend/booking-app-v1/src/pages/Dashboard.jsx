import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useUser } from '../context/UserContext';
import { useNavigate } from 'react-router-dom';
import { PieChart, Pie, Cell, Tooltip, Legend } from 'recharts';


const Dashboard = () => {
  const { profileData } = useUser();
  const navigate = useNavigate();
  const [bookings, setBookings] = useState([]);
  const [genderDistribution, setGenderDistribution] = useState([]);
  const [ageDistribution, setAgeDistribution] = useState([]);


  useEffect(() => {
    if (profileData.role !== 'Admin') {
      navigate('/home');
    } else {
      fetchDashboardData();
    }
  }, [profileData, navigate]);

  const fetchDashboardData = async () => {
    try {
      const bookingsResponse = await axios.get(`${import.meta.env.VITE_APP_API_URL}/admin/bookings`);
      const genderResponse = await axios.get(`${import.meta.env.VITE_APP_API_URL}/admin/gender-distribution`);
      const ageResponse = await axios.get(`${import.meta.env.VITE_APP_API_URL}/admin/age-distribution`);

      setBookings(bookingsResponse.data);
      setGenderDistribution(genderResponse.data);
      setAgeDistribution(ageResponse.data);
    } catch (error) {
      console.error('Error fetching dashboard data:', error);
    }
  };

  const COLORS = ['#0088FE', '#00C49F', '#FFBB28', '#FF8042'];

  return (
    <div className='p-4'>
      <h2 className='text-2xl font-semibold mb-4'>Admin Dashboard</h2>
      <div>
        <h3 className='text-xl font-semibold mb-2'>Event Bookings</h3>
        <ul>
          {bookings.map((booking, index) => (
            <li key={index} className='mb-2'>
              <strong>Event:</strong> {booking.eventName} | <strong>Bookings:</strong> {booking.bookingCount}
            </li>
          ))}
        </ul>
      </div>
      <div className='mt-6'>
        <h3 className='text-xl font-semibold mb-2'>Gender Distribution</h3>
        <PieChart width={400} height={400}>
          <Pie
            data={genderDistribution}
            cx={200}
            cy={200}
            labelLine={false}
            label={({ name, percent }) => `${name} ${(percent * 100).toFixed(0)}%`}
            outerRadius={80}
            fill="#8884d8"
            dataKey="value"
          >
            {genderDistribution.map((entry, index) => (
              <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
            ))}
          </Pie>
          <Tooltip />
          <Legend />
        </PieChart>
      </div>
      <div className='mt-6'>
        <h3 className='text-xl font-semibold mb-2'>Age Distribution</h3>
        <PieChart width={400} height={400}>
          <Pie
            data={ageDistribution}
            cx={200}
            cy={200}
            labelLine={false}
            label={({ name, percent }) => `${name} ${(percent * 100).toFixed(0)}%`}
            outerRadius={80}
            fill="#8884d8"
            dataKey="value"
          >
            {ageDistribution.map((entry, index) => (
              <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
            ))}
          </Pie>
          <Tooltip />
          <Legend />
        </PieChart>
      </div>
    </div>
  );
};

export default Dashboard;
