import axios from 'axios'


const EVENT_REST_API_URL ='http://localhost:8081/api/v1/events';


async function fetchData() {
    try {
        const response = await axios.get(EVENT_REST_API_URL);
        console.log('Data from Spring Boot:', response.data);
    } catch (error) {
        console.error('Error fetching data:', error);
    }
}

fetchData();

const data = {
    eventName: 'Weekend To Remember',
    eventDescription: 'event for kids who want to take a break from academics and channel their creative juices',
    location: '81 Lower Main rd',
    status: 'Active',
    attendees: {}
};

// Function to push data to the API
async function pushData() {
    try {
        const response = await axios.post(EVENT_REST_API_URL, data);
        console.log('Data pushed successfully:', response.data);
    } catch (error) {
        console.error('Error pushing data:', error);
    }
}

// Call the function
pushData();

// const userservice = new UserService();
// userservice.getUsers();

// export default new UserService();