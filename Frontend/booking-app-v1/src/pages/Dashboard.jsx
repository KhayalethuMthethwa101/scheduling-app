import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useUser } from '../context/UserContext';
import { useNavigate } from 'react-router-dom';
import { PieChart, Pie, Cell, Tooltip as RechartsTooltip, Legend } from 'recharts';
import { Bar } from 'react-chartjs-2';
import { Chart as ChartJS, CategoryScale, LinearScale, BarElement, Title, Tooltip as ChartJSTooltip, Legend as ChartJSLegend } from 'chart.js';
import { ResponsiveContainer } from 'recharts';

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  ChartJSTooltip,
  ChartJSLegend
);

const Dashboard = () => {
  const { profileData } = useUser();
  const navigate = useNavigate();
  const [bookings, setBookings] = useState([]);
  const [genderDistribution, setGenderDistribution] = useState([]);
  const [ageDistribution, setAgeDistribution] = useState([]);
  const [filter, setFilter] = useState([]);
  const [selectedFilter, setSelectedFilter] = useState('All');


  useEffect(() => {
    if (profileData.role !== 'Admin') {
      navigate('/home');
    } else {
      fetchDashboardData();
    }
  }, [profileData, navigate]);

  const fetchDashboardData = async () => {
    try {
      const bookingsResponse = await axios.get(`${import.meta.env.VITE_APP_API_URL}/bookings/admin/bookings`);
      const genderResponse = await axios.get(`${import.meta.env.VITE_APP_API_URL}/bookings/admin/gender-distribution`);
      const ageResponse = await axios.get(`${import.meta.env.VITE_APP_API_URL}/bookings/admin/age-distribution`);
      
      setBookings(bookingsResponse.data);
      setGenderDistribution(genderResponse.data);
      setAgeDistribution(ageResponse.data);

      const uniqueFilter = ['All', ...new Set(bookingsResponse.data.map(booking => booking.eventName))];
      setFilter(uniqueFilter);
    } catch (error) {
      console.error('Error fetching dashboard data:', error);
    }
  };

  const COLORS = ['#0088FE', '#00C49F', '#FFBB28', '#FF8042'];

  const filteredBookings = selectedFilter === 'All'
    ? bookings
    : bookings.filter(booking => booking.eventName === selectedFilter);

  const bookingData = {
    labels: filteredBookings.map(booking => booking.eventName),
    datasets: [
      {
        label: 'Bookings',
        data: bookings.map(booking => booking.bookingCount),
        backgroundColor: 'rgba(75, 192, 192, 0.6)',
        borderColor: 'rgba(75, 192, 192, 1)',
        borderWidth: 1,
      },
    ],
  };

  const bookingOptions = {
    responsive: true,
    plugins: {
      legend: {
        position: 'top',
      },
      title: {
        display: true,
        text: 'Event Bookings',
      },
    },
  };

  return (
    <div className='p-4 text-center'>
      <h2 className='text-2xl font-semibold mb-4'>Admin Dashboard</h2>
      <div className='mt-6'>
        <h3 className='text-xl font-semibold mb-2'>Event Bookings</h3>
        <div className='mb-4'>
          <label htmlFor='categoryFilter'>Filter by Event:</label>
          <select id='categoryFilter' value={selectedFilter} onChange={(e) => setSelectedFilter(e.target.value)}>
            {filter.map(category => (
              <option key={category} value={category}>{category}</option>
            ))}
          </select>
        </div>
        <Bar data={bookingData} options={bookingOptions} />
      </div>
      <div className='mt-6'>
        <h3 className='text-xl font-semibold mb-2'>Gender Distribution (users)</h3>
        <div style={{ display: 'flex', justifyContent: 'center' }}>
          <ResponsiveContainer  width="100%" height={600}>
              <PieChart>
              <Pie
                data={genderDistribution}
                cx="50%"
                cy="50%"
                labelLine={false}
                label={({ name, percent }) => `${name} ${(percent * 100).toFixed(0)}%`}
                outerRadius="80%"
                fill="#8884d8"
                dataKey="value"
              >
                {genderDistribution.map((entry, index) => (
                  <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
                ))}
              </Pie>
              <RechartsTooltip />
              <Legend />
            </PieChart>
          </ResponsiveContainer>
        </div>
      </div>
      <div className='mt-6'>
        <h3 className='text-xl font-semibold mb-2'>Age Distribution</h3>
        <div style={{ display: 'flex', justifyContent: 'center' }}>
          <ResponsiveContainer  width="100%" height={600}>
            <PieChart>
              <Pie
                data={ageDistribution}
                cx="50%"
                cy="50%"
                labelLine={false}
                label={({ name, percent }) => `${name} (${(percent * 100).toFixed(0)}%)`}
                outerRadius="80%"
                fill="#8884d8"
                dataKey="value"
              >
                {ageDistribution.map((entry, index) => (
                  <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
                ))}
              </Pie>
              <RechartsTooltip />
              <Legend />
            </PieChart>
          </ResponsiveContainer>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
