import React from 'react'
import Header from '../components/Header'
import Category from '../components/category'
import TopEvents from '../components/TopEvents'
import Banner from '../components/Banner'

export const Home = () => {
  return (
    <div>
        <Header />
        <Category />
        <TopEvents />
        <Banner />
    </div>
  )
}


export default Home
