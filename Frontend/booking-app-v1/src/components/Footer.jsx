import React from 'react'
import { assets } from '../assets/assets'
import { NavLink } from 'react-router-dom'

const Footer = () => {

  return (
    <div className='md:mx-10 px-4 sm:px-6 md:px-8'>
        <div className='flex flex-col sm:grid grid-cols-[3fr_1fr_1fr] gap-14 my-10 mt-40 text-sm'>
            {/* -------Left Section --------- */}
            <div>
                <img className='mb-15  w-40 bg-primary' src={assets.logo} alt=''/>
                <p className='w-full md:w-2/3 text-gray-600 leading-6'>This was created by Khayalethu Mthethwa, with a special thank you to fellow Youtuber GreatStack, for the template and design of the UI.</p>
                <p className='w-full md:w-2/3 text-gray-600 leading-6'> I hope my efforts were not in vain.</p>
            </div>
            {/* -------Center Section --------- */}
            <div>
                <p className='text-xl font-medium mb-5'>CAPE TOWN ART FESTIVAL</p>
                <ul className='flex flex-col gap-2 text-gray-600'>
                    <NavLink to='/'>
                        <li>Home</li>
                    </NavLink>
                    <NavLink to='/about'>
                        <li>About Us</li>
                    </NavLink>
                    <NavLink to='/contact'>
                        <li>Contact Us</li>
                    </NavLink>
                    <li>Privacy Policy</li>
                </ul>
            </div>
            {/* -------Right Section --------- */}
            <div>
                <p className='text-xl font-medium mb-5'>GET IN TOUCH</p>
                <ul className='flex flex-col gap-2 text-gray-600'>
                    <li>+27 76 873 8389</li>
                    <li>khaylethumthethwa16@gmail.com</li>
                </ul>
            </div>
        </div>
        {/* ----------------Copyright Text------------ */}
        <div>
            <hr />
            <p className='py-5 text-sm text-center'>Copyright 2024@ EventMaster - All Rights Reserved.</p>
        </div>
    </div>
  )
}

export default Footer