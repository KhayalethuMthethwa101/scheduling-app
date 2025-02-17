import React, { useState, useContext } from 'react'
import { useNavigate } from 'react-router-dom'
import { signInWithEmailAndPassword, createUserWithEmailAndPassword} from 'firebase/auth';
import { auth } from '../services/firebase';
import { AppContext } from '../context/AppContext';
import { useUser } from '../context/UserContext';
import axios from 'axios';
import Modal from '../components/modal';

const REST_API_URL =  'http://localhost:8081/api/v1'

const Login = () => {

  const [state, setState] = useState('Sign Up');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [name, setName] = useState('');
  const [phoneNumber, setPhoneNumber] = useState('');
  const [address, setAddress] = useState('');
  const [gender, setGender] = useState('');
  const [dateOfBirth, setDateOfBirth] = useState('');
  const [error, setError] = useState(null)
  const [success, setSuccess] = useState(null)
  const { setIsLoggedIn, setProfileData } = useUser()
  const navigate = useNavigate()

  const handleSignUp = async (e) => {
    e.preventDefault();
    try {
        const userCredential = await createUserWithEmailAndPassword(auth, email, password);
        const user = userCredential.user;
        const userProfile = {
          userName: name,
          email: user.email,
          phoneNumber,
          password,
          address,
          gender,
          dob: dateOfBirth,
          role: 'user',
        };
        // Create user in the Spring Boot backend
        await axios.post(`${REST_API_URL}/users/adduser`, userProfile);
        setProfileData(userProfile);
        setSuccess('Account created successfully!');
        setError(null)
        setIsLoggedIn(true)
        navigate('/')
    } catch (error) {
        setError(error);
        console.error('Error signing up:', error);
    }
  }

  const handleSignIn = async (e) => {
    e.preventDefault();
    try {
        const userCredential = await signInWithEmailAndPassword(auth, email, password);
        const user = userCredential.user;
        const response = await axios.get(`${REST_API_URL}/users/${user.email}`);
        const profileData = response.data;
        setProfileData(profileData);
        console.log(setProfileData)
        setSuccess('Login successful!');
        setIsLoggedIn(true)
        navigate('/')
    } catch (error) {
        setError('Login failed. Please check your email and password.'); 
        console.error('Error signing in:', error);
    }
  };

  const handleSubmit = async (e) => {
    if (state === 'Sign Up') {
        await handleSignUp(e);
    } else {
        await handleSignIn(e);
    }
  };

  const handleCloseModal = () => {
    setSuccess(null);
    setError(null);
  };

  return (
    <form onSubmit={handleSubmit} className='min-h0[80vh] flex items-center'>
      <div className='flex flex-col gap-3 m-auto items-start p-8 min-w-[340px] sm:min-w-96 border rounded-xl text-zinc-600 text-sm shadow-lg'>
        <p className='text-2xl font-semibold'>{state === 'Sign Up' ? "Create Account" : "Log in"}</p>
        <p>Please {state === 'Sign Up' ? "create an account" : "login"} to RSVP to events</p>
        {state === "Sign Up" && (
          <>
            <div className='w-full'>
              <p>Full Name</p>
              <input
                className='border border-zinc-300 rounded w-full p-2 mt-1'
                type='text'
                onChange={(e) => setName(e.target.value)}
                value={name}
                placeholder='Joe Doe'
                required
              />
            </div>
            <div className='w-full'>
              <p>Phone Number</p>
              <input
                className='border border-zinc-300 rounded w-full p-2 mt-1'
                type='text'
                onChange={(e) => setPhoneNumber(e.target.value)}
                value={phoneNumber}
                placeholder='123-456-7890'
                required
              />
            </div>
            <div className='w-full'>
              <p>Address</p>
              <input
                className='border border-zinc-300 rounded w-full p-2 mt-1'
                type='text'
                onChange={(e) => setAddress(e.target.value)}
                value={address}
                placeholder='123 Main St'
                required
              />
            </div>
            <div className='w-full'>
              <p>Gender</p>
              <select
                className='border border-zinc-300 rounded w-full p-2 mt-1'
                onChange={(e) => setGender(e.target.value)}
                value={gender}
                required
              >
                <option value="">Select Gender</option>
                <option value="male">Male</option>
                <option value="female">Female</option>
                <option value="other">Other</option>
              </select>
            </div>
            <div className='w-full'>
              <p>Date of Birth</p>
              <input
                className='border border-zinc-300 rounded w-full p-2 mt-1'
                type='date'
                onChange={(e) => setDateOfBirth(e.target.value)}
                value={dateOfBirth}
                required
              />
            </div>
          </>
        )}
        <div className='w-full'>
          <p>Email</p>
          <input
            className='border border-zinc-300 rounded w-full p-2 mt-1'
            type='email'
            onChange={(e) => setEmail(e.target.value)}
            value={email}
            placeholder='JohnDoe@eventmaster.com'
            required
          />
        </div>
        <div className='w-full'>
          <p>Password</p>
          <input
            className='border border-zinc-300 rounded w-full p-2 mt-1'
            type='password'
            onChange={(e) => setPassword(e.target.value)}
            value={password}
            required
          />
        </div>
        {error && <p className='text-red-500'>{error}</p>}
        {success && <p className='text-green-500'>{success}</p>}
        <button className='bg-primary text-white w-full py-2 rounded-md text-base'>{state === 'Sign Up' ? "Create Account" : "Log in"}</button>
        {state === "Sign Up"
          ? <p>Already have an account? <span onClick={() => setState("Login")} className='text-primary underline cursor-pointer'>Login here</span></p>
          : <p>Create a new account? <span onClick={() => setState("Sign Up")} className='text-primary underline cursor-pointer'>Click here</span></p>
        }
      </div>
      {success && <Modal message={success} onClose={handleCloseModal} />}
      {error && <Modal message={error} onClose={handleCloseModal} />}
    </form>
  );
}

export default Login