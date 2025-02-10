import { createContext } from "react";
import { events } from "../assets/assets";

export const AppContext = createContext()

const AppContextProvider = (props) => {


    const value = {
        events
    }

    return(
        <AppContext.Provider value={value}>
            {props.children}
        </AppContext.Provider>
    )
}

export default AppContextProvider