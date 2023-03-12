import React from 'react';
import "bootstrap/dist/css/bootstrap.min.css";
import { Route, Routes } from 'react-router-dom';
import './App.css';
import Home from './components/Home';
import Login from './components/Login';
import { NavBar } from './components/NavBar';
// import Admin from './services/adminService/Admin';
import Owner from './services/ownerService/owner';
import Team from './services/adminService/team/team';
import AddTeam from './services/adminService/team/addTeam';
import EditTeam from './services/adminService/team/editTeam';
import Player from './services/adminService/player/player';
import AddPlayer from './services/adminService/player/addPlayer';
import EditPlayer from './services/adminService/player/editPlayer';
import OwnerTeam from './services/ownerService/ownerTeam';
import OwnerAddPlayer from './services/ownerService/ownerAddPlayer';
import AnotherTeamView from './services/ownerService/anotherTeamView';
// import OwnerId from './services/ownerService/OwnerId';
import AdminRoutes from './services/adminService/adminRoutes';
import OwnerRoutes from './services/ownerService/ownerRoutes';


function App() {
  return (
    <div className="App">
      
      <NavBar />


      <div className='container-fluid'>
        <Routes>
          
          <Route path="/" element={<Home />} />

          <Route path="/home" element={<Home />} />
          <Route path="/login" element={<Login />} />

          <Route path="/admin" element={<AdminRoutes />}>
            <Route path="team" element={<Team />} />
            <Route path="add-team" element={<AddTeam />} />
            <Route path="edit-team/:teamId" element={<EditTeam />} />
            <Route path="player" element={<Player />} />
            <Route path="add-player" element={<AddPlayer />} />
            <Route path="edit-player/:playerId" element={<EditPlayer />} />
          </Route>

          <Route path="/owner" element={<OwnerRoutes />}>
            <Route path="owner-page" element={<Owner />} />
            <Route path="owner-team/:teamId" element={<OwnerTeam />} />
            <Route path="view-new-player/:teamId" element={<OwnerAddPlayer />} />
            <Route path="other-team-players/:teamId" element={<AnotherTeamView />} />
          </Route>

        </Routes>
      </div>

    </div>
  );
}

export default App;
