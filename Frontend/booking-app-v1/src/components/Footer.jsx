import React from 'react'
import { assets } from '../assets/assets'

const Footer = () => {
  return (
    <div>
        <div>
            {/* -------Left Section --------- */}
            <div>
                <img src={assets.logo} alt=''/>
                <p>This was created by Khayalethu Mthethwa, with a special thank you to fellow Youtuber GreatStack, for the template and design of the UI. I out in over 50 hours in for this project just to get it to send dat and post data. I hope my efforts were not in vain.</p>
            </div>
            {/* -------Center Section --------- */}
            <div>
                <p>COMPANY: UNIVERSITY OF CAPE TOWN</p>
                <ul>
                    <li>Home</li>
                    <li>About Us</li>
                    <li>Contact Us</li>
                    <li>Privacy Policy</li>
                </ul>
            </div>
            {/* -------Right Section --------- */}
            <div>
                <p>GET IN TOUCH</p>
                <ul>
                    <li>+27 76 873 8389</li>
                    <li>khaylethumthethwa16@gmail.com</li>
                </ul>
            </div>
        </div>
        {/* ----------------Copyright Text------------ */}
        <div>
            <hr />
            <p>Copyright 2024@ EventMaster - All Rights Reserved.</p>
        </div>
    </div>
  )
}

export default Footer