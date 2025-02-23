import React, { useContext, useState } from 'react';
import { v4 as uuidv4 } from 'uuid';
import axios from 'axios';


const FeedbackForm = ({ eventId, email }) => {
  const [comment, setComment] = useState('');
  const [rating, setRating] = useState(0);
  const [recommendation, setRecommendation] = useState(0);
  const [success, setSuccess] = useState(null);
  const [error, setError] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();
    const feedbackData = {
      feedbackId: uuidv4(),
      eventId,
      comment,
      rating,
      recommendation,
      email
    };
    console.log(feedbackData)
    try {
      await axios.post(`${import.meta.env.VITE_APP_API_URL}/feedback`, feedbackData);
      setSuccess('Feedback submitted successfully!');
      setError(null);
      setComment('');
      setRating(0);
      setRecommendation(0);
    } catch (error) {
      setError('Error submitting feedback. Please try again.');
      setSuccess(null);
      console.error('Error submitting feedback:', error);
    }
  };

  return (
    <form onSubmit={handleSubmit} className='feedback-form'>
      <h2 className='text-2xl font-semibold mb-4'>Submit Feedback</h2>
      {error && <p className='text-red-500'>{error}</p>}
      {success && <p className='text-green-500'>{success}</p>}
      <div className='mb-2'>
        <label className='block mb-1'>Comment:</label>
        <textarea
          className='w-full p-2 border rounded'
          value={comment}
          onChange={(e) => setComment(e.target.value)}
          required
        />
      </div>
      <div className='mb-2'>
        <label className='block mb-1'>Rating (out of 5):</label>
        <input
          type='number'
          className='w-full p-2 border rounded'
          value={rating}
          onChange={(e) => setRating(parseInt(e.target.value))}
          min='0'
          max='5'
          required
        />
      </div>
      <div className='mb-2'>
        <label className='block mb-1'>Recommendation (out of 5):</label>
        <input
          type='number'
          className='w-full p-2 border rounded'
          value={recommendation}
          onChange={(e) => setRecommendation(parseInt(e.target.value))}
          min='0'
          max='5'
          required
        />
      </div>
      <button type='submit' className='bg-blue-500 text-white py-2 px-4 rounded'>
        Submit
      </button>
    </form>
  );
};

export default FeedbackForm;
