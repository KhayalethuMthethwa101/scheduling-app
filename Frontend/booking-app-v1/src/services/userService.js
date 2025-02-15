import axios, { Axios } from 'axios'

const REACT_APP_API_URL='http://localhost:8081'

async function fetchEvents() {
        const response = await fetch(`${REACT_APP_API_URL}/api/v1/events`,{
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            }
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // Parse the JSON from the response
        })
        .then(data1 => {
            console.log('Data from Spring Boot (fetch):', data1);
            // Do something with the data
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });
    console.log('Data from Spring Boot (fetch):', response.json());
    }

// fetchEvents();

const sendData = async () => {
    const data = {
        eventId: '4',
        eventName: 'Slept on Tuesday',
        eventDescription: 'Test 3',
        location: 'Cape Town, Observatory',
        status: 'Active',
        dateOfEvent: '2025/02/21'
    };

    try {
        const response = await fetch(`${REACT_APP_API_URL}/api/v1/events/addEvent`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        if(!response.ok){
            throw new Error('Network response was not ok');
        }

        console.log('Data from Spring Boot:', response.json());
    } catch (error) {
        console.error('There was a problem with the fetch operation:', error);
    }
};

// Call the function
sendData();
