import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import Url from "../../components/ApiUrl";




export const viewPlayersByTeamId = createAsyncThunk(
    "owner/view-players",
    async (teamId)=>{
        const response = await axios.get(Url.ownerUrl + `view-players/${teamId}`);
        console.log(response.data);
        return  response.data;
    }
);


export const addPlayerToTeam = createAsyncThunk(
    "owner/add-player",

    async ({teamId,playerId})=>{

        const response = await axios.post(Url.ownerUrl + `add-player/${teamId}/${playerId}`);
        console.log(response)
        return response.data;

    }
);


export const deletePlayerFromTeam = createAsyncThunk(
    "owner/delete-player",
    async (playerId)=>{
        await axios.delete(Url.ownerUrl + `delete-player/${playerId}`);
        return playerId;
    }

);

const initialState = {
    ownerStatus: "",
    ownerTeamList: []
  };


const ownerSlice = createSlice({
    name :"owner",
    initialState,
    reducers : {},
    extraReducers : (builder) =>  {

        builder.addCase(viewPlayersByTeamId.pending, (state, action) => {
            state.ownerStatus = "pending";
          });

        builder.addCase(viewPlayersByTeamId.fulfilled, (state, action) => {
            state.ownerStatus = "idle";
            state.ownerTeamList = action.payload;
          });
   
          builder.addCase(addPlayerToTeam.pending, (state, action) => {
            state.ownerStatus = "pending";
          });
      
          builder.addCase(addPlayerToTeam.fulfilled, (state, action) => {
            state.ownerStatus = "idle";
            state.ownerTeamList.push(state.action.payload);
          });

          builder.addCase(deletePlayerFromTeam.pending, (state) => {
            state.ownerStatus = "pending";
          });
      
          builder.addCase(deletePlayerFromTeam.fulfilled, (state, action) => {
            state.ownerStatus = "idle";
            state.ownerTeamList = state.ownerTeamList.filter((player) => player.id !== action.payload);
          });

      
         

    }


})

export default ownerSlice.reducer;
