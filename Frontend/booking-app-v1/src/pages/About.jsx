import React from 'react'
import { assets } from '../assets/assets'

const About = () => {
  return (
    <div>
        <div className='text-center text-2xl pt-10 text-gray-500'>
          <p>ABOUT <span className='text-gray-700 font-medium'>US</span></p>
        </div>
        
        <div className='my-10 flex flex-col md:flex-row gap-12'>
          <img className='w-full md:max-w-[360px]' src={assets.about_us} alt=""/>
          <div className='flex flex-col justify-center gap-6 md:w-2/4 text-sm text-gray-600'>
            <p>Welcome to Cape Town Art Festival, where we turn your event dreams into reality! With a passion for creativity and a commitment to excellence, we specialize in crafting unforgettable experiences that leave a lasting impression.</p>
            <b className='text-gray-800'>Our Objective</b>
            <p>Our primary objective is to deliver exceptional event planning and management services that exceed our clients' expectations. Whether it's a corporate gathering, a lavish wedding, or a themed party, we ensure every detail is meticulously planned and flawlessly executed. Our team of experienced professionals works closely with clients to understand their vision and transform it into a stunning reality.</p>
            <b className='text-gray-800'>Our Motivation</b>
            <p>At Cape Town Art Festival, we believe that every event tells a unique story. Our motivation stems from our love for creating memorable moments that bring joy and happiness to our clients and their guests. We take pride in our ability to design and execute events that are not only visually stunning but also seamlessly organized.</p>
          </div>
        </div>

        <div className='text-xl my-4'>
          <p>WHY <span className='tect-gray-700 font-semibold'>CHOOSE US</span></p>
        </div>

        <div className='flex flex-col md:flex-row mb-20'>
          <div className='boarder px-10 md:px-16 py-8 sm:py-16 flex flex-col gap-5 text-[15px] hover:bg-primary hover:text-white transition-all duration-300 text-gray-600 cursor-pointer'>
            <b>Creativity and Innovation:</b>
            <p>Our team is constantly exploring new ideas and trends to ensure your event stands out. We bring fresh and innovative concepts to the table, making each event unique and personalized.</p>
          </div>
          <div className='boarder px-10 md:px-16 py-8 sm:py-16 flex flex-col gap-5 text-[15px] hover:bg-primary hover:text-white transition-all duration-300 text-gray-600 cursor-pointer'>
            <b>Attention to Detail</b>
            <p>We understand that the little things make a big difference. From the décor to the logistics, we pay close attention to every aspect of the event to ensure nothing is overlooked.</p>
          </div>
          <div className='boarder px-10 md:px-16 py-8 sm:py-16 flex flex-col gap-5 text-[15px] hover:bg-primary hover:text-white transition-all duration-300 text-gray-600 cursor-pointer'>
            <b>Professional Expertise</b>
            <p>With years of experience in the events industry, our team has the knowledge and skills to handle events of any scale and complexity. We are dedicated to delivering top-notch service and ensuring a smooth and stress-free experience for our clients.</p>
          </div>
        </div>
    </div>
  )
}

export default About