import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import authAPI from "./authAPI";


const user = JSON.parse(localStorage.getItem("user"));
//console.log(user.roles)

export const login = createAsyncThunk(
  "auth/login",
  async ({ username, password }) => {
   
      const data = await authAPI.login(username, password);
      return { user: data };
    
  }
);

export const logout = createAsyncThunk("auth/logout", async () => {
  await authAPI.logout();
});

const initialState = user
  ? { isLoggedIn: true, user }
  : { isLoggedIn: false, user: null };

const authSlice = createSlice({

  name: "auth",
  initialState,
  reducers : {},
  extraReducers: {

    [login.fulfilled]: (state, action) => {
      state.isLoggedIn = true;
      state.user = action.payload.user;
    },

    [login.rejected]: (state, action) => {
      state.isLoggedIn = false;
      state.user = null;
    },

    [logout.fulfilled]: (state, action) => {
      state.isLoggedIn = false;
      state.user = null;
    }
    
  },
});
const { reducer } = authSlice;
export default reducer;

