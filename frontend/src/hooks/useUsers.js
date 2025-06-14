import { useState, useEffect } from 'react';
import { fetchAllUsers } from '../services/userService';

export function useUsers() {
  const [users, setUsers] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetchAllUsers()
      .then(setUsers)
      .catch(setError);
  }, []);

  return { users, error };
}