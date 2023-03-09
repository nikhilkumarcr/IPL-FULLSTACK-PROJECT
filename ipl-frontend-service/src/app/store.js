import { configureStore } from '@reduxjs/toolkit';
import authReducer from '../services/authService/authSlice';
import messageReducer from '../services/messageService/message';
import teamReducer from '../services/adminService/team/teamSlice';
import playerReducer from '../services/adminService/player/playerSlice'

export const store = configureStore({
  
  reducer: {
    auth : authReducer,
    message : messageReducer,
    team : teamReducer,
    player : playerReducer
  },
});
