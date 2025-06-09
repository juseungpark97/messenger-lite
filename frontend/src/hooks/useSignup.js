// hooks/useSignup.js
import { useState } from 'react';
import { signup } from '../services/authService';

export function useSignup() {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const handleSignup = async (name, email, password) => {
    setLoading(true);
    try {
      const data = await signup(name, email, password);
      return data;
    } catch (err) {
      setError(err);
      throw err;
    } finally {
      setLoading(false);
    }
  };

  return { handleSignup, loading, error };
}