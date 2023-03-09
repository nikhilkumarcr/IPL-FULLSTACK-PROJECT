import React from 'react';
import "bootstrap/dist/css/bootstrap.min.css";
import { Route, Routes } from 'react-router-dom';
import './App.css';
import Home from './components/Home';
import Login from './components/Login';
import { NavBar } from './components/NavBar';

// import Admin from './services/adminService/Admin';
import Owner from './services/ownerService/Owner';
import Team from './services/adminService/team/Team';
import AddTeam from './services/adminService/team/AddTeam';
import EditTeam from './services/adminService/team/EditTeam';
import Player from './services/adminService/player/Player';
import AddPlayer from './services/adminService/player/AddPlayer';
import EditPlayer from './services/adminService/player/EditPlayer';
import OwnerTeam from './services/ownerService/OwnerTeam';
import OwnerAddPlayer from './services/ownerService/OwnerAddPlayer';
import AnotherTeamView from './services/ownerService/AnotherTeamView';

// import OwnerId from './services/ownerService/OwnerId';
import AdminRoutes from './services/adminService/AdminRoutes';
import OwnerRoutes from './services/ownerService/OwnerRoutes';


function App() {
  return (
    <div className="container-fluid">
      
      <NavBar />

      <div>
        <Routes>
          
          <Route path="/" element={<Home />} />

          <Route path="/home" element={<Home />} />
          <Route path="/login" element={<Login />} />

          <Route path="/admin" element={<AdminRoutes />}>
            {/* <Route path="admin-view" element={<Admin />} /> */}
            <Route path="team" element={<Team />} />
            <Route path="add-team" element={<AddTeam />} />
            <Route path="edit-team/:teamId" element={<EditTeam />} />
            <Route path="player" element={<Player />} />
            <Route path="add-player" element={<AddPlayer />} />
            <Route path="edit-player/:playerId" element={<EditPlayer />} />
          </Route>

          <Route path="/owner" element={<OwnerRoutes />}>
            {/* <Route path="owner-view" element={<OwnerId />} /> */}
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
