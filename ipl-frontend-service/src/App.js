import React, { useEffect, useState } from 'react';
import { Link, Route, Routes } from 'react-router-dom';
import "bootstrap/dist/css/bootstrap.min.css"
import './App.css';

import Home from './components/Home';
import Login from './components/Login';
import EventBus from './eventBus/EventBus';
import AuthService from './services/authService/AuthService';
import Admin from './services/adminService/Admin';
import Owner from './services/ownerService/Owner';
import Team from './services/adminService/team.js/Team';
import AddTeam from './services/adminService/team.js/AddTeam';
import EditTeam from './services/adminService/team.js/EditTeam';
import Player from './services/adminService/player.js/Player';
import AddPlayer from './services/adminService/player.js/AddPlayer';
import EditPlayer from './services/adminService/player.js/EditPlayer';
import OwnerTeam from './services/ownerService/OwnerTeam';
import OwnerAddPlayer from './services/ownerService/OwnerAddPlayer';
import AnotherTeamView from './services/ownerService/AnotherTeamView';

import OwnerId from './services/ownerService/OwnerId';



function App() {

  const [currentUser, setCurrentUser] = useState(undefined);

  useEffect(() => {
    const user = AuthService.getCurrentUser();

    if (user) {
      setCurrentUser(user);
    }

    EventBus.on("logout", () => {
      logOut();
    });

    return () => {
      EventBus.remove("logout");
    };
  }, []);

  const logOut = () => {
    AuthService.logout();
    setCurrentUser(undefined);
  };
  return (
    <div >
      <nav className="navbar navbar-expand navbar-dark " style={{ backgroundColor: '#6abdea' }}>

        <Link to={"/home"} className="navbar-brand">
          <img src='../images/ipl_logo.png' alt='ipl-logo' id='img' />
        </Link>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">

          <ul class="navbar-nav mr-auto">
              <Link to={"/home"} className="navbar-brand">
                <b>Indian Premier League</b>
              </Link>
          </ul>

          {currentUser ? (
            <div className="navbar-nav ml-auto">
              <li className="nav-item">
                <Link className="nav-link">
                  <div style={{ textTransform: 'uppercase' }}>{currentUser.username}</div>
                </Link>
              </li>
              <li className="nav-item">
                <a href="/home" className="nav-link" onClick={logOut} id='txt'>
                  <b> Log-Out</b>
                </a>
              </li>
            </div>
          ) : (
            <div className="navbar-nav ml-auto">
              <li className="nav-item">
                <Link to={"/login"} className="nav-link" id='txt'>
                  <b>Log-In</b>
                </Link>
              </li>
            </div>
          )}
        </div>
      </nav>

      <div className='container'>
        <Routes>
          <Route path="/" element={<Home />} />

          <Route path="/home" element={<Home />} />
          <Route path="/login" element={<Login />} />

          <Route path="/admin" element={<Admin />} />
          <Route path="/team" element={<Team />} />
          <Route path="/add-team" element={<AddTeam />} />
          <Route path="/edit-team/:teamId" element={<EditTeam />} />
          <Route path="/player" element={<Player />} />
          <Route path="/add-player" element={<AddPlayer />} />
          <Route path="/edit-player/:playerId" element={<EditPlayer />} />

          <Route path="/owner" element={<OwnerId />} />

          <Route path="/owner-page/:teamId" element={<Owner />} />
          <Route path="/owner-team/:teamId" element={<OwnerTeam />} />
          <Route path="/view-new-player/:teamId" element={<OwnerAddPlayer />} />
          <Route path="/other-team-players/:teamId" element={<AnotherTeamView />} />
        </Routes>
      </div>
    </div>

  );
}

export default App;
