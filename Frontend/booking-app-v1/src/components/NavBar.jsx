import React, {useState} from 'react'
import {assets} from '../assets/assets'
import { NavLink, useNavigate } from 'react-router-dom'
import { useUser } from '../context/UserContext'

const NavBar = () => {

    const navigate = useNavigate();
    const [showMenu, setShowMenu] = useState(false)
    const { isLoggedIn, setIsLoggedIn } = useUser();
    const { profileData, setProfileData } = useUser();

    const handleLogout = () => {
        setProfileData(null); // Update login state
        navigate('/login'); // Redirect to login page
    };

  return (
    <div className='flex items-center justify-between text-sm py-4 mb-5 border-b border-b-gray-400 px-4 sm:px-6 md:px-8'>
        <img onClick={()=>navigate('/')} className='w-44 cursor-pointer' src={assets.logo} alt="" />
        <ul className='hidden md:flex item-start gap-5 font-medium'>
            <NavLink to='/'>
                <li className='py-10'>HOME</li>
                <hr className='border-none outline-none h-0.5 bg-primary w-3/5 m-auto hidden' />
            </NavLink>
            <NavLink to='/events'>
                <li className='py-10'>ALL EVENTS</li>
                <hr className='border-none outline-none h-0.5 bg-primary w-3/5 m-auto hidden' />
            </NavLink>
            <NavLink to='/about'>
                <li className='py-10'>ABOUT US</li>
                <hr className='border-none outline-none h-0.5 bg-primary w-3/5 m-auto hidden' />
            </NavLink>
            <NavLink to='/contact'>
                <li className='py-10'>CONTACT US</li>
                <hr className='border-none outline-none h-0.5 bg-primary w-3/5 m-auto hidden' />
            </NavLink>
        </ul>
        <div className='flex items-center'>
            {
                profileData 
                ? (
                    <div className='flex items-center gap-2 cursor-pointer group relative'>
                        <img className='w-12 rounded-full' src={profileData.image || assets.profile_pic} alt="" />
                        <img className='w-2.5' src={assets.dropdown_icon} alt='' />
                        <div className='absolute top-0 right-0 pt-14 text-base font-medium text-gray-600 x-20 hidden group-hover:block'>
                            <div className='min-w-48 bg-stone-100 rounded flex flex-col gap-4 p-4'>
                                <p onClick={()=>navigate('myprofile')} className='hover:text-black cursor-pointer'>My Profile</p>
                                <p onClick={()=>navigate('mybookings')} className='hover:text-black cursor-pointer'>My Bookings</p>
                                {profileData.role === 'Admin' && (<p onClick={()=>navigate('dashboard')} className='hover:text-black cursor-pointer'>Admin Dashboard</p>)}
                                {profileData.role === 'Admin' && (<p onClick={()=>navigate('allbookings')} className='hover:text-black cursor-pointer'>All Bookings</p>)}
                                {profileData.role === 'Admin' && (<p onClick={()=>navigate('allusers')} className='hover:text-black cursor-pointer'>All Users</p>)}
                                <p onClick={handleLogout} className='hover:text-black cursor-pointer'>Logout</p>
                            </div>
                        </div>
                    </div>
                )
                : (<button onClick={()=>navigate('/login')} className='bg-primary text-white px-8 py-3 rounded-full font-light hidden md:block'>Login/SignUp</button>)
            }
            <img onClick={()=>setShowMenu(true)} className='w-10 md:hidden' src={assets.menu_icon} />
            {/* Mobile menu */}
            <div className={`${showMenu ? 'fixed w-full' : 'h-0 w-0'} md:hidden right-0 top-0 bottom-0 z-20 overflow-hidden bg-white transition-all`}>
                <div className='flex items-center justify-between px-5 py-6'>
                    <img className='w-36' src={assets.logo} />
                    <img className='w-7' onClick={()=>setShowMenu(false)} src={assets.cross_icon} />
                </div>
                <ul className='flex flex-col items-center gap-2 mt-5 px-5 text-lg font-medium'>
                    <NavLink onClick={()=>setShowMenu(false)} to='/'><p className='px-4 py-2 rounded inline-block'>HOME</p></NavLink>
                    <NavLink onClick={()=>setShowMenu(false)} to='/events'><p className='px-4 py-2 rounded inline-block'>ALL EVENTS</p></NavLink>
                    <NavLink onClick={()=>setShowMenu(false)} to='/about'><p className='px-4 py-2 rounded inline-block'>ABOUT US</p></NavLink>
                    <NavLink onClick={()=>setShowMenu(false)} to='/contact'><p className='px-4 py-2 rounded inline-block'>CONTACT US</p></NavLink>
                </ul>
            </div>
        </div>
    </div>
  );
};

export default NavBar