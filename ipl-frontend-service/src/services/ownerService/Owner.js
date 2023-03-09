
import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Link} from 'react-router-dom';

import {  getTeamIdByOwnername, viewAllTeams } from '../adminService/team/teamSlice';

import './owner.css';

function Owner() {

    const dispatch = useDispatch();

    const user = JSON.parse(localStorage.getItem("user"));

    const teams = useSelector((state) => state.team.teamsList);

    const teamData = useSelector((state)=>state.team.team);

    useEffect(() => {

        dispatch(viewAllTeams());

    }, [dispatch]);

    useEffect(() => {

       dispatch(getTeamIdByOwnername(user.username));

    }, []);

    return (

        <div>
            <div className='container'>
                <div>
                    <hr />
                    <div className='row'>
                        <div className='col-md-8'>
                            <div>
                                {teams.map((team) => {
                                    if (team.teamId === teamData.teamId) {
                                        return (
                                            <div className='display-4' key={team.teamId}><b>{team.teamName}</b></div>
                                        )
                                    }
                                })}
                            </div>
                        </div>
                        <div className='col-md-4'>
                            <div className='text-right'>
                                <Link type="button" to={`/owner/owner-team/${teamData.teamId}`} className="btn btn-outline-primary m-2">My Team</Link>
                            </div>
                        </div>
                    </div>
                    <hr />
                    <br />
                    <br />
                </div>
            </div>

            <div className='container'>
                <div className='row'>
                    <div className='col-md-1'></div>
                    <div className='col-md-10'>
                        <table className="table shadow">
                            <thead className="thead-dark">
                                <tr className='table-dark'>
                                    <th>Team Images</th>
                                    <th>Team Name</th>
                                    <th>Owner Name</th>
                                    <th>City</th>
                                    <th>State</th>
                                    <th>Button</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    teams.map((team) => {
                                        if (team.ownerId !== teamData.teamId) {
                                            return (
                                                <tr className='table-warning' key={team.teamId}>
                                                    <td><img id='team-img' src={team.teamUrl} alt='team' /></td>
                                                    <td><b>{team.teamName}</b></td>
                                                    <td style={{ textTransform: 'uppercase' }}><b>{team.ownerName}</b></td>
                                                    <td><b>{team.city}</b></td>
                                                    <td><b>{team.state}</b></td>
                                                    <td><Link type="button" state={{ viewTeam: team }} className="btn btn-outline-dark mr-5" to={`/owner/other-team-players/${team.teamId}`}
                                                    >Team Players</Link></td>
                                                </tr>
                                            )
                                        }
                                    })
                                }
                            </tbody>
                        </table>
                    </div>
                    <div className='col-md-1'></div>
                </div>
            </div>

        </div>


    );
}

export default Owner;