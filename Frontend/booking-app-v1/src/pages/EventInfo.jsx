import React, { useContext, useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import { AppContext } from '../context/AppContext'
import { assets } from '../assets/assets'
import RelatedEvents from '../components/relatedEvents'


const EventInfo = () => {

  const { eventId } = useParams()
  const { events, currencySymbol } = useContext(AppContext)

  const [eventInfo, setEventInfo] = useState(null)

  const fetchEvent = async () => {
    const eventInfo = events.find(event => event._id === eventId)
    setEventInfo(eventInfo)   
    console.log(eventInfo);
     
  }

  useEffect(()=>{
    fetchEvent()
  }, [events, eventId])


  return eventInfo && (
    <div>
        {/* ----------Event Details ----------- */}
        <div className='flex flex-col sm:flex-row gap-4'>
          <div>
            <img className='bg-primary w-full sm:max-w-72 rounded-lg' src={eventInfo.image} alt="" />
          </div>
          <div className='flex-1 border border-gray-400 rounded-lg p-8 py-7 bg-white mx-2 sm:mx-0 mt-[-80px] sm:mt-0'>
            {/* -------------Event Info: Event name, Location, Date -------- */}
            <p className='flex items-center gap-2 text-2xl font-medium text-gray-900'>
              {eventInfo.name}
              <img className='w-7' src={assets.verified_icon} alt="" />
            </p>
            <div className='flex items-center gap-2 text-sm mt-1 text-gray-600'>
              <p>{eventInfo.category}</p>
              <button className='py-0.5 px-2 border text-xs rounded-full'>Event capacity {eventInfo.capacity}</button>
            </div>
            {/* ------- Event Details -------- */}
            <div>
              <p className='flex items-center gap-1 text-sm font-medium text-gray-900 mt-3'>
                Description <img className='w-3' src={assets.info_icon} alt="" />
              </p>
              <p className='text-sm text-gray-500 max-w-[700px] mt-1'>{eventInfo.description}</p>
            </div>
            <div>
              <p className='flex items-center gap-1 text-sm font-medium text-gray-900 mt-3'>Date</p>
              <p className='text-sm text-gray-500 max-w-[700px] mt-1'>{eventInfo.date}</p>
            </div>
            <div>
              <p className='flex items-center gap-1 text-sm font-medium text-gray-900 mt-3'>Time</p>
              <p className='text-sm text-gray-500 max-w-[700px] mt-1'>{eventInfo.time}</p>
            </div>
            <p className='text-gray-500 font-medium mt-4'>
              Reservation Fee: <span className='text-gray-600'>{currencySymbol}0.00</span>
            </p>
          </div>
          
        </div> 
        
        <div className='sm:ml-72 sm:pl-4 mt-4 font-medium text-gray-700'> 
          {/* RSVP Section */}
          <button className='bg-primary text-white text-sm font-light px-20 py-3 rounded-full my-6'>RSVP</button>
        </div>

        {/* listing related events */}
        <RelatedEvents eventId={eventId} category={eventInfo.category} />
    </div>
  )
}

export default EventInfo