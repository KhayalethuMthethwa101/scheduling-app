import React from 'react'
import { Route, Routes, Router } from 'react-router-dom'
import Home from './pages/Home'
import Events from './pages/Booking'
import Login from './pages/Login'
import About from './pages/About'
import Contact from './pages/Contacts'
import MyProfile from './pages/MyProfile'
import MyBookings from './pages/MyBookings'
import EventInfo from './pages/EventInfo'
import NavBar from './components/NavBar'
import Footer from './components/Footer'
import CreateEvent from './pages/CreateEvent'
import Dashboard from './pages/Dashboard'
import AllBookings from './pages/AllBookings'
import AllUsers from './pages/AllUsers'

const App = () => {
  return (
    <div className="tmx-4 sm:mx-[10%]">
      <NavBar />
      <Routes>
        <Route path='/' element={<Home />} />
        <Route path='/events' element={<Events />} />
        <Route path='/events/:category' element={<Events />} />
        <Route path='/login' element={<Login />} />
        <Route path='/about' element={<About />} />
        <Route path="/create-event" element={<CreateEvent />} />
        <Route path='/contact' element={<Contact />} />
        <Route path='/myprofile' element={<MyProfile />} />
        <Route path='/mybookings' element={<MyBookings />} />
        <Route path='/booking/:eventId' element={<EventInfo />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/allbookings" element={<AllBookings />} />
        <Route path="/allusers" element={<AllUsers />} />
      </Routes>
      <Footer />
    </div>
  )
}
export default App