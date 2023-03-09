import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import Url from "../../../components/ApiUrl";


export const viewAllPlayers = createAsyncThunk(
    "player/view-players",

    async () => {
        const response = await axios.get(Url.adminUrl + "view-players");
        return response.data;
    }
);

export const addPlayer = createAsyncThunk(
    "player/add-player",

    async (payload) => {
        const response = await axios.post(Url.adminUrl + "add-player", payload);
        console.log(response.data)
        return response.data;

    }

);

export const updatePlayer = createAsyncThunk(
    "player/update-player",

    async (payload) => {

        const response = await axios.post(Url.adminUrl + `update-player/${payload.id}`, payload);
        return response.data;

    }

)

export const deletePlayer = createAsyncThunk(
    "player/delete-player",

    async (playerId) => {
        await axios.delete(Url.adminUrl + `delete-player/${playerId}`)
        return playerId;

    }
);


const initialState = {

    playerStatus: "",
    playersList: []
  };

  const playerSlice = createSlice({

    name: "team",
    initialState,
    reducers: {},

    extraReducers: (builder) => {
  
      builder.addCase(viewAllPlayers.fulfilled, (state, action) => {
        state.playerStatus = "idle";
        state.playersList = action.payload;
      });
  
      builder.addCase(viewAllPlayers.pending, (state, action) => {
        state.playerStatus = "pending";
      });

      builder.addCase(addPlayer.pending, (state, action) => {
        state.playerStatus = "pending";
      });
      builder.addCase(addPlayer.fulfilled, (state, action) => {
        state.playerStatus = "idle";
        state.playersList.push(action.payload);
      });
  
      builder.addCase(deletePlayer.pending, (state) => {
        state.playerStatus = "pending";
      });
  
      builder.addCase(deletePlayer.fulfilled, (state, action) => {
        state.playerStatus = "idle";
        state.playersList = state.playersList.filter((player) => player.id !== action.payload);
      });
      
    }
  });
  
  
  export default playerSlice.reducer;

