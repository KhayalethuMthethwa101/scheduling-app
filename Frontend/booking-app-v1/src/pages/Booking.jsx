import React, { useContext, useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import { AppContext } from '../context/AppContext'

// All events page
const Booking = () => {

  const { category } = useParams()
  const { events } = useContext(AppContext)
  const [filterEvent, setFilterEvent] = useState([])
  const [showFilter, setShowFilter] = useState(false)
  const navigate = useNavigate()


  //Something is not working here
  const applyFilter = () =>{
    if(category){
      setFilterEvent(events.filter(event => event.category === category))
    } else {
      setFilterEvent(events)
    }
  }

  useEffect(()=>{
    applyFilter()
  },[events, category])

  return (
    <div>
        <p className='text-gray-600'>Browse through you favourite events.</p>
        <div className='flex flex-col sm:flex-row items-start gap-5 mt-5'>
          <button className={`py-1 px-3 border rounded text-sm transition-all sm:hidden ${showFilter ? 'bg-primary text-white' : ''}`} onClick={()=>setShowFilter(prev=> !prev)}>Filters</button>
          <div className={`flex-col gap-4 text-sm text-gray-600 ${showFilter ? 'flex': 'hidden sm:flex'}`}>
            <p onClick={()=> category ==='Academic' ? navigate('/events'):navigate('/events/Academic')} className={`w-[94vw] sm:w-auto pl-3 py-1.5 pr-16 border border-gray-300 rounded transition-all cursor-pointer ${category ==="Academic" ? "bg-indigo-100 text-black": ""}`}>Academic</p>
            <p onClick={()=> category ==='Art' ? navigate('/events'):navigate('/events/Art')} className={`w-[94vw] sm:w-auto pl-3 py-1.5 pr-16 border border-gray-300 rounded transition-all cursor-pointer ${category ==="Art" ? "bg-indigo-100 text-black": ""}`}>Art</p>
            <p onClick={()=> category ==='Motorsport' ? navigate('/events'):navigate('/events/Motorsport')} className={`w-[94vw] sm:w-auto pl-3 py-1.5 pr-16 border border-gray-300 rounded transition-all cursor-pointer ${category ==="Motorsport" ? "bg-indigo-100 text-black": ""}`}>Motorsport</p>
            <p onClick={()=> category ==='Music' ? navigate('/events'):navigate('/events/Music')} className={`w-[94vw] sm:w-auto pl-3 py-1.5 pr-16 border border-gray-300 rounded transition-all cursor-pointer ${category ==="Music" ? "bg-indigo-100 text-black": ""}`}>Music</p>
            <p onClick={()=> category ==='Sport' ? navigate('/events'):navigate('/events/Sport')} className={`w-[94vw] sm:w-auto pl-3 py-1.5 pr-16 border border-gray-300 rounded transition-all cursor-pointer ${category ==="Sport" ? "bg-indigo-100 text-black": ""}`}>Sport</p>
            <p onClick={()=> category ==='Other' ? navigate('/events'):navigate('/events/Other')} className={`w-[94vw] sm:w-auto pl-3 py-1.5 pr-16 border border-gray-300 rounded transition-all cursor-pointer ${category ==="Other" ? "bg-indigo-100 text-black": ""}`}>Other</p>
          </div>
          <div className='w-full grid grid-cols-auto gap-4 gap-y-6'>
          {
            filterEvent.map((item, index)=>(
              <div onClick={()=>navigate(`/booking/${item._id}`)} className='border border-blue-200 rounded-xl overflow-hidden cursor-pointer hover:translate-y-[-10px] transition-all duration-500' key={index}>
                <img className='bg-blue-50' src={item.image} alt="" /> 
                <div className='p-4'>
                  <div className='flex items-center gap-2 text-sm text-center text-green-500'>
                    <p className='w-2 h-2 bg-green-500 rounded-full'></p><p>Event Status</p>
                  </div>
                  <p className='text-gray-900 text-lg font-medium'>{item.name}</p>
                  <p className='text-gray-600 text-sm'>{item.category}</p>
                </div>
              </div>
            ))
          }
          </div>
        </div>  
    </div>
  )
}

export default Booking