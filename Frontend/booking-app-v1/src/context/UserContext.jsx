// src/context/UserContext.jsx
import React, { createContext, useContext, useState, useEffect } from 'react';

const UserContext = createContext();

export const UserProvider = ({ children }) => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [profileData, setProfileData] = useState(() => {
        const storedUser = localStorage.getItem("user");
        return storedUser ? JSON.parse(storedUser) : null;
    });

   useEffect(() => {
        if(profileData){
            localStorage.setItem("user", JSON.stringify(profileData));
        } else {
            localStorage.removeItem("user");
        }
   }, [profileData]);


    return (
        <UserContext.Provider value={{  isLoggedIn, setIsLoggedIn, profileData, setProfileData }}>
            {children}
        </UserContext.Provider>
    );
};

export const useUser = () => useContext(UserContext);
