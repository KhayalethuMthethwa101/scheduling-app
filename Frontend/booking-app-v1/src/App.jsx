import React from 'react'
import { Route, Routes } from 'react-router-dom'
import Home from './pages/home'
import Events from './pages/Booking'
import Login from './pages/Login'
import About from './pages/About'
import Contact from './pages/Contacts'
import MyProfile from './pages/MyProfile'
import MyBookings from './pages/MyBookings'
import Booking from './pages/Booking'
import NavBar from './components/NavBar'

const App = () => {
  return (
    <div className="tmx-4 sm:mx-[10%]">
      <NavBar />
      <Routes>
        <Route path='/' element={<Home />} />
        <Route path='/events' element={<Events />} />
        <Route path='/events/:catagory' element={<Events />} />
        <Route path='/login' element={<Login />} />
        <Route path='/about' element={<About />} />
        <Route path='/contact' element={<Contact />} />
        <Route path='/myprofile' element={<MyProfile />} />
        <Route path='/mybookings' element={<MyBookings />} />
        <Route path='/booking/:eventId' element={<Booking />} />
      </Routes>
    </div>
  )
}
export default App