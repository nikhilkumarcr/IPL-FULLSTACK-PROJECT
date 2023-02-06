import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';

import './Owner.css';

function Owner() {

    const { teamId } = useParams()

    const [teams, setTeams] = useState([])

    useEffect(() => {
        loadUser()
    }, [])

    const loadUser = async () => {
        let result = await axios.get("http://3.108.219.116:8082/api/view-teams")
        setTeams(result.data)
    }

    return (

        <div>
            <div className='container'>
                <div>
                    <hr />
                    <div className='row'>
                        <div className='col-md-8'>
                            <div key={teams.teamId}>
                                {teams.map((team) => {
                                    if (team.teamId === parseInt(teamId)) {
                                        return (
                                            <div className='display-4'><b>{team.teamName}</b></div>
                                        )
                                    }
                                })}
                            </div>
                        </div>
                        <div className='col-md-4'>
                            <div className='text-right'>
                                <Link type="button" to={`/owner/owner-team/${teamId}`} className="btn btn-outline-primary m-2">My Team</Link>
                                <Link type="button" to={`/owner/owner-view`} className="btn btn-outline-danger m-2">Back-To-Home</Link>
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
                                    {/* <th>Team Images</th> */}
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
                                        if (team.ownerId !== parseInt(teamId)) {
                                            return (
                                                <tr className='table-warning'>
                                                    {/* <td><img id='team-img' src={team.teamUrl} alt='team' /></td> */}
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