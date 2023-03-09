import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Link} from 'react-router-dom';
import Url from '../../components/ApiUrl';

import './Owner.css';

function Owner() {

    // const { teamId } = useParams()
    const user = JSON.parse(localStorage.getItem("user"));
    const [id, setId] = useState([]);

    const [teams, setTeams] = useState([])

    useEffect(() => {
        loadUser()
    }, [])

    useEffect(() => {
        getid()
    }, [])


    const loadUser = async () => {
        let result = await axios.get(Url.adminUrl + "view-teams");
        console.log(result.data)
        setTeams(result.data)
    }

    const getid = () => {
        axios.get(Url.adminUrl + `get-id/${user.username}`).then((response) => {
            console.log(response)
            setId(response.data)

        })
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
                                    if (team.teamId === id.teamId) {
                                        return (
                                            <div className='display-4'><b>{team.teamName}</b></div>
                                        )
                                    }
                                })}
                            </div>
                        </div>
                        <div className='col-md-4'>
                            <div className='text-right'>
                                <Link type="button" to={`/owner/owner-team/${id.teamId}`} className="btn btn-outline-primary m-2">My Team</Link>
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
                                        if (team.ownerId !== id.teamId) {
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