import { configureStore } from '@reduxjs/toolkit';
import authReducer from '../services/authService/authSlice';
import teamReducer from '../services/adminService/team/teamSlice';
import playerReducer from '../services/adminService/player/playerSlice';
import ownerReducer from '../services/ownerService/ownerSlice';

export const store = configureStore({
  
  reducer: {
    auth : authReducer,
    team : teamReducer,
    player : playerReducer,
    owner : ownerReducer
  },
});
