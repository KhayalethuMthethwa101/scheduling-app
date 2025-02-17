import React, { createContext} from "react";
import { events } from "../assets/assets";

export const AppContext = createContext()
// Create a context for user roles

const AppContextProvider = (props) => {

    const currencySymbol = 'R'

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
