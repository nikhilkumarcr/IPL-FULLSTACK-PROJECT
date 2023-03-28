import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import Url from "../../../components/ApiUrl";


export const viewAllTeams = createAsyncThunk(
  "team/view-teams",
  async () => {
    const response = await axios.get(Url.adminUrl + "view-teams");
    return response.data;
  }
);

export const addTeam = createAsyncThunk(
  "team/add-team",
  async (payload) => {
    const response = await axios.post(Url.adminUrl + "add-team", payload);
    return response.data;

  }

);

export const updateTeam = createAsyncThunk(
  "team/update-team",
  async (payload) => {
    const response = await axios.post(Url.adminUrl + `update-team/${payload.teamId}`, payload);
    return response.data;
  }
);

export const deleteTeam = createAsyncThunk(
  "team/delete-team",
  async (teamId) => {
    await axios.delete(Url.adminUrl + `delete-team/${teamId}`)
    return teamId;
  }
);

export const getTeamByTeamId = createAsyncThunk(
  "team/get-team",
  async (teamId) => {
    const response = await axios.get(Url.adminUrl + `get-team/${teamId}`);
    return response.data;
  }
);


export const getTeamIdByOwnername = createAsyncThunk(
  "team/get-teamId",
  async (ownerName)=>{
    const response = await axios.get(Url.adminUrl + `get-teamId/${ownerName}`);
    return response.data;
  }
);


const initialState = {
  teamStatus: "",
  teamsList: [],
  team : {},
};


const teamSlice = createSlice({
  name: "team",
  initialState,
  reducers: {},
  extraReducers: (builder) => {

    builder.addCase(viewAllTeams.fulfilled, (state, action) => {
      state.teamStatus = "idle";
      state.teamsList = action.payload;
    });

    builder.addCase(viewAllTeams.pending, (state, action) => {
      state.teamStatus = "pending";
    });

    builder.addCase(addTeam.pending, (state, action) => {
      state.teamStatus = "pending";
    });

    builder.addCase(addTeam.fulfilled, (state, action) => {
      state.teamStatus = "idle";
      state.teamsList.push(action.payload);
    });

    builder.addCase(updateTeam.pending, (state) => {
      state.teamStatus = "pending";
    });
    
    builder.addCase(updateTeam.fulfilled, (state, action) => {
      state.teamStatus = "idle";
      state.teamsList = state.teamsList.filter((_) => _.id !== action.payload.id);
      state.teamsList = state.action.payload
    });

    builder.addCase(deleteTeam.pending, (state) => {
      state.teamStatus = "pending";
    });

    builder.addCase(deleteTeam.fulfilled, (state, action) => {
      state.teamStatus = "idle";
      state.teamsList = state.teamsList.filter((team) => team.id !== action.payload);
    });

    builder.addCase(getTeamByTeamId.pending, (state) => {
      state.teamStatus = "pending";
    });

    builder.addCase(getTeamByTeamId.fulfilled, (state, action) => {
      state.teamStatus = "idle";
      state.team = action.payload;
    });

    builder.addCase(getTeamIdByOwnername.pending, (state) => {
      state.teamStatus = "pending";
    });

    builder.addCase(getTeamIdByOwnername.fulfilled, (state, action) => {
      state.teamStatus = "idle";
      state.team = action.payload;
    });

  }
});


export default teamSlice.reducer;

