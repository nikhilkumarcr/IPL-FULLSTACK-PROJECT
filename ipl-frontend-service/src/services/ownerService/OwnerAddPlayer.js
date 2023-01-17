import React, { useEffect, useState } from 'react'
import axios from 'axios'
import { useParams, useLocation, Link } from 'react-router-dom'
import './OwerAddPlayer.css';


export default function OwnerAddPlayer() {


    const [players, setPlayers] = useState([])

    const { teamId } = useParams()

    const location = useLocation()

    const totalPlayers = location.state.data.total

    const foreignPlayer = location.state.data.foreign

    useEffect(() => {
        loadUser()
    }, [])

    const loadUser = async () => {
        let result = await axios.get("http://localhost:8082/api/view-players")
        //console.log(result);
        setPlayers(result.data.filter(ele => ele.available === true))
        //  console.log(result)
    }

    const onAdding = async (e, playerId, nationality) => {

        e.preventDefault()

        if (totalPlayers < 15) {

            if (nationality !== 'India') {

                if (foreignPlayer < 6) {
                    await axios.post(`http://localhost:8082/api/owner/add-player/${teamId}/${playerId}`)
                    window.location.reload(false)
                }
            }
            else {
                await axios.post(`http://localhost:8082/api/owner/add-player/${teamId}/${playerId}`)
                window.location.reload(false)
            }
        }


    }

    return (
        <div className="container">
            <div className='container'>
            <div className='row'>
            <hr />
                <div className='col-md-8'>
                   
                    <br />
                    <h2> <b>Available Player List</b></h2>
                </div>

                <div className='col-md-4'>
                    <div className='text-right'>
                        <Link type="button" className="btn btn-outline-danger m-5" to={`/owner-team/${teamId}`}>Back-To-Owner</Link>
                    </div>
                </div>

            </div>
            </div>
            <br />
            <br />
            <div className='row'>
                <div className='col-md-1'></div>
                <div className='col-md-10'>
                    <table className="table table-bordered table-hover">
                        <thead className="thead-dark">
                            <tr className='table table-dark'>
                                <th>Team Images</th>
                                <th >PlayerName</th>
                                <th >Age</th>
                                <th >Country</th>
                                <th >Speciality</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                players.map((player) => {
                                    return (
                                        <tr className='table-success text-dark'>
                                            <td><img id='player-img' src={player.imageUrl} alt='player' /></td>
                                            <td>{player.playerName}</td>
                                            <td>{player.age}</td>
                                            <td>{player.nationality}</td>
                                            <td>{player.specialty}</td>
                                            <td><button type="button" className="btn btn-danger" onClick={(e) => onAdding(e, player.playerId, player.nationality)}>Add</button></td>
                                        </tr>
                                    )
                                })
                            }
                        </tbody>
                    </table>
                </div>
                <div className='col-md-1'></div>
            </div>

            <div>

            </div>
        </div>
    )
}
