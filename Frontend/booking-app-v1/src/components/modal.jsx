import React from 'react';

const Modal = ({ children, onClose }) => {
  return (
    <div className="fixed inset-0 bg-gray-800 bg-opacity-50 flex justify-center items-center z-50">
      <div className="bg-white rounded-lg p-4 shadow-lg max-w-sm mx-auto">
        {children}
        <button 
          className="mt-4 bg-blue-500 text-white py-2 px-4 rounded" 
          onClick={onClose}
        >
          Close
        </button>
      </div>
    </div>
  );
};

export default Modal;
