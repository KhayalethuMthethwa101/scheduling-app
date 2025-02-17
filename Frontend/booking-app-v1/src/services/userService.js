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
    }

fetchEvents();

const sendData = async () => {
    const data = {
        userName: 'DevSpace101',
        email: 'khayalethumthethwa16@gmail.com',
        address: '81 Lower Main Rd, Observatory, Cape town',
        DOB: '1996-01-01',
        gender: 'Male',
        password: '123456',
        phoneNumber: '0768738389',
        role: 'Admin',
    };

    try {
        const response = await fetch(`${REACT_APP_API_URL}/api/v1/users/adduser`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'

            },
            body: data
        });

        // if(!response.ok){
        //     throw new Error('Network response was not ok');
        // }

        console.log('Data from Spring Boot:', response.json());
    } catch (error) {
        console.error('There was a problem with the fetch operation:', error);
    }
};

// Call the function
// sendData();
