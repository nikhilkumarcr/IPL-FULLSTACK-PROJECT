import React, { useEffect, useState } from 'react'
import axios from 'axios'
import {  useParams } from 'react-router-dom';


export default function AnotherTeamView() {

    const { teamId } = useParams()
    const [players, setPlayers] = useState([])

    const loadPlayer = async () => {

        const result = await axios.get(`http://3.108.219.116:8082/api/owner/view-players/${teamId}`)
        setPlayers(result.data)
    }

    useEffect(() => {
        loadPlayer();
    }, [])

    return (
        <div className='container'>

            <hr />
            <div className='row'>
                <div className='col-md-9'>
                    <div className='display-4'>
                        Team Compoition
                    </div>
                </div>
                <div className='col-md-3'>
                    <div className='text-right'>
                    </div>
                </div>

            </div>
            <hr />
            <br />
            <div className='row'>
                <div className='col-md-1'></div>
                <div className='col-md-10'>
                <table className="table table-bordered table-hover">
                        <thead className="thead-dark">
                            <tr className='table table-dark'>
                                {/* <th>Team Images</th> */}
                                <th >PlayerName</th>
                                <th >Age</th>
                                <th >Country</th>
                                <th >Speciality</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                players.map((player) => {
                                    return (
                                        <tr className='table-secondary text-dark'>
                                            {/* <td><img id='player-img' src={player.imageUrl} alt='player' /></td> */}
                                            <td>{player.playerName}</td>
                                            <td>{player.age}</td>
                                            <td>{player.nationality}</td>
                                            <td>{player.specialty}</td>
                                        </tr>
                                    )
                                })
                            }
                        </tbody>
                    </table>
                   
                </div>
                <div className='col-md-1'></div>
            </div>

        </div>

    )
}
