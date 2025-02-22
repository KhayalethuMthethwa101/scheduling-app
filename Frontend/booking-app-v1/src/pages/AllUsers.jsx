import React, { useEffect, useState } from 'react';
import axios from 'axios';

const UsersTable = () => {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const response = await axios.get(`${import.meta.env.VITE_APP_API_URL}/users`);
        setUsers(response.data);
        setLoading(false);
      } catch (error) {
        console.error('Error fetching users:', error);
        setError('Failed to fetch users');
        setLoading(false);
      }
    };
    fetchUsers();
  }, []);

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div className="text-red-500">{error}</div>;
  }

  return (
    <div>
      <p className='pb-3 mt-12 font-medium text-zinc-700 border-b'>All Users</p>
      <p className='text-sm text-gray-500 mt-1'>Total Users: {users.length}</p>
      <table className='w-full mt-2 border-collapse border border-gray-300'>
        <thead>
          <tr className='bg-gray-100'>
            <th className='border border-gray-300 p-2 text-left'>Email</th>
            <th className='border border-gray-300 p-2 text-left'>Name</th>
            <th className='border border-gray-300 p-2 text-left'>Role</th>
            <th className='border border-gray-300 p-2 text-left'>Gender</th>
            <th className='border border-gray-300 p-2 text-left'>Date of Birth</th>
          </tr>
        </thead>
        <tbody>
          {users.map((user, index) => (
            <tr key={index} className='border-t'> 
            <td className='border border-gray-300 p-2'>{user.email}</td>
              <td className='border border-gray-300 p-2'>{user.userName}</td> 
              <td className='border border-gray-300 p-2'>{user.userRole}</td>
              <td className='border border-gray-300 p-2'>{user.gender}</td>
              <td className='border border-gray-300 p-2'>{user.dob}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default UsersTable;
