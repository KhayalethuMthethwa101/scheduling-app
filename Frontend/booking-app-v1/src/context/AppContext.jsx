import React, { createContext, useState, useEffect } from "react";
import axios from 'axios';

export const AppContext = createContext()
// Create a context for user roles

const AppContextProvider = (props) => {

    const [events, setEvents] = useState([]);
    const currencySymbol = 'R';

    useEffect(() => {
        const fetchEvents = async () => {
            try {
                const response = await axios.get(`${import.meta.env.VITE_APP_API_URL}/events`);
                setEvents(response.data);
            } catch (error) {
                console.error('Error fetching events:', error);
            }
        };

        fetchEvents();
    }, []);

    const value = {
        events, currencySymbol
    }

    return(
        <AppContext.Provider value={value}> 
            {props.children}
        </AppContext.Provider>
    )
}

export default AppContextProvider;
