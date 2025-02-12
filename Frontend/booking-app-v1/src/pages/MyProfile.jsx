import React, { useState } from 'react'
import { assets } from '../assets/assets'

const MyProfile = () => {

  const [userData, setUserData] = useState({
    name:'Sam Altman',
    image: assets.contact_us,
    email: 'SamAltman@openai.com',
    phoneNumber: 'Classified',
    address:{
      line1: 'Chicago, Illinois, U.S.',
      line2: '-'
    },
    gender: "Male",
    dob: '1985/04/22'
  })

  const [isEdit, setIsEdit] = useState(true)

  return (
    <div>
        <img src={userData.image} />

        {
          isEdit
          ? <input type="text" value={userData.name}  onChange={e=> setUserData(prev => ({...prev,name:e.target.value}))}/>
          :<p>{userData.name}</p>
        }

        <hr />
        <div>
          <p>CONTACT INFORMATION</p>
          <div>
            <p>Email id:</p>
            <p>{userData.email}</p>
            <p>Phone:</p>
            {
              isEdit
              ? <input type="text" value={userData.phoneNumber}  onChange={e=> setUserData(prev => ({...prev,phoneNumber:e.target.value}))}/>
              :<p>{userData.phoneNumber}</p>
            }
            <p>Address</p>
            {
              isEdit
              ? <p>
                  <input type="text" value={userData.address.line1}  onChange={(e)=> setUserData(prev => ({...prev,address:{...prev.address, line1: e.target.value}}))}/>
                  <br />
                  <input type="text" value={userData.address.line2}  onChange={(e)=> setUserData(prev => ({...prev,address:{...prev.address, line2: e.target.value}}))}/>
                </p>
              :<p>
                {userData.address.line1}
                <br />
                {userData.address.line2}
              </p>
            }
          </div>
        </div>
    </div>
  )
}

export default MyProfile