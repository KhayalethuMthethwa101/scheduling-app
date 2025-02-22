import React from 'react'
import Header from '../components/Header'
import Category from '../components/category'
import TopEvents from '../components/TopEvents'
import Banner from '../components/Banner'
import { useUser } from '../context/UserContext'

export const Home = () => {
 const { profileData } = useUser();

  return (
    <div className="px-4 sm:px-6 md:px-8">
        <Header />
        <Category />
        <TopEvents />
        {!profileData && <Banner />}
    </div>
  )
}


export default Home
