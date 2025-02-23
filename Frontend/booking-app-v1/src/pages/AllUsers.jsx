import React, { useEffect, useState } from 'react';
import axios from 'axios';

const UsersTable = () => {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [editingUser, setEditingUser] = useState(null);
  const [updatedUsers, setUpdatedUsers] = useState({});

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const response = await axios.get(`${import.meta.env.VITE_APP_API_URL}/users`);
        setUsers(response.data);
      } catch (error) {
        console.error('Error fetching users:', error);
        setError('Failed to fetch users');
      } finally {
        setLoading(false);
      }
    };
    fetchUsers();
  }, []);

  // Handle input change for user edits
  const handleChange = (email, field, value) => {
    setUpdatedUsers(prev => ({
      ...prev,
      [email]: { ...prev[email], [field]: value },
    }));
  };

  // Save updated user data
  const handleSave = async (email) => {
    const userToUpdate = users.find(user => user.email === email);
    if (!userToUpdate) return;

    const updatedUser = {
      ...userToUpdate, // Keep all existing fields
      ...updatedUsers[email], // Override with updated fields
    };

    try {
      await axios.put(`${import.meta.env.VITE_APP_API_URL}/users/update`, updatedUser);
      setUsers(users.map(user => (user.email === email ? updatedUser : user)));
      setEditingUser(null); // Exit edit mode
    } catch (error) {
      console.error('Error updating user:', error);
      setError('Failed to update user');
    }
  };

  if (loading) return <div>Loading...</div>;
  if (error) return <div className="text-red-500">{error}</div>;

  return (
    <div>
      <p className='pb-3 mt-12 font-medium text-zinc-700 border-b'>All Users</p>
      <p className='text-sm text-gray-500 mt-1'>Total Users: {users.length}</p>

      <table className='w-full mt-2 border-collapse border border-gray-300'>
        <thead>
          <tr className='bg-gray-100'>
            <th className='border border-gray-300 p-2 text-left'>Email (User ID)</th>
            <th className='border border-gray-300 p-2 text-left'>Name</th>
            <th className='border border-gray-300 p-2 text-left'>Role</th>
            <th className='border border-gray-300 p-2 text-left'>Gender</th>
            <th className='border border-gray-300 p-2 text-left'>Date of Birth</th>
            <th className='border border-gray-300 p-2 text-left'>Actions</th>
          </tr>
        </thead>
        <tbody>
          {users.map(user => (
            <tr key={user.email} className='border-t'>
              <td className='border border-gray-300 p-2'>{user.email}</td>

              {/* Editable Name */}
              <td className='border border-gray-300 p-2'>
                {editingUser === user.email ? (
                  <input 
                    type="text" 
                    value={updatedUsers[user.email]?.userName ?? user.userName} 
                    onChange={(e) => handleChange(user.email, "userName", e.target.value)} 
                    className="border px-2 py-1 w-full"
                  />
                ) : (
                  user.userName
                )}
              </td>

              {/* Editable Role */}
              <td className='border border-gray-300 p-2'>
                {editingUser === user.email ? (
                  <select
                    value={updatedUsers[user.email]?.userRole ?? user.userRole}
                    onChange={(e) => handleChange(user.email, "userRole", e.target.value)}
                    className="border px-2 py-1 w-full"
                  >
                    <option value="visitor">Visitor</option>
                    <option value="admin">Admin</option>
                  </select>
                ) : (
                  user.userRole
                )}
              </td>

              <td className='border border-gray-300 p-2'>{user.gender}</td>
              <td className='border border-gray-300 p-2'>{user.dob}</td>

              {/* Action Buttons */}
              <td className='border border-gray-300 p-2'>
                {editingUser === user.email ? (
                  <button 
                    onClick={() => handleSave(user.email)} 
                    className="bg-green-500 text-white px-3 py-1 rounded mr-2"
                  >
                    Save
                  </button>
                ) : (
                  <button 
                    onClick={() => setEditingUser(user.email)} 
                    className="bg-blue-500 text-white px-3 py-1 rounded"
                  >
                    Edit
                  </button>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default UsersTable;
