import React, { useEffect, useState } from 'react'
import axios from 'axios'
import { Link } from 'react-router-dom';
import { Card } from 'react-bootstrap';
import './Player.css';
import Url from '../../../components/ApiUrl';

export default function Player() {

    const [players, setPlayers] = useState([])

    useEffect(() => {
        loadPlayers()
    }, [])

    const loadPlayers = async () => {
        let result = await axios.get(Url.adminUrl + "view-players")
        setPlayers(result.data)
    }

    const deletePlayer = async (playerId) => {
        await axios.delete( Url.adminUrl + `delete-player/${playerId}`)
        loadPlayers()
    }

    return (
       
        <div className="container">

            <div className='container-fluid'>
            <hr />
            <div className='row'>
                <div className='col-md-9'>
                    <div className='display-4'><b>List of Players</b> </div>
                </div>
                <div className='col-md-3'>
                    <div className='text-right'>
                        <Link className="btn btn-outline-success btn-sm m-2" to='/admin/add-player'>Add-Player</Link>
                        <Link className="btn btn-outline-dark btn-sm m-2" to='/admin/team'>Back-To-Team</Link>

                    </div>

                </div>
                <hr />
            </div>
            </div>


            <div className='row'>
                <div className='col-md-1'></div>
                <div className='col-md-10'>
                    <div className='d-flex flex-wrap'>
                        {
                            players.map((player) => {
                                return (
                                    <div>
                                        <Card id='card-body1'>
                                            <div className='text-center'>
                                                <br />
                                                <Card.Img id='card-img1' variant='top' src={player.imageUrl} />
                                                <Card.Body>
                                                    <hr />
                                                    <Card.Title><b>{player.playerName}</b></Card.Title>
                                                    <hr />
                                                    <div className='d-flex justify-content-around'>
                                                        <Card.Text>Age :{player.age}</Card.Text>
                                                        <Card.Text>Role :{player.specialty}</Card.Text>
                                                    </div>
                                                    <hr />
                                                </Card.Body>
                                                <div className='d-flex justify-content-around'>
                                                    <Link type="button" className="btn btn-outline-dark" to={`/admin/edit-player/${player.playerId}`} >Edit</Link>
                                                    <button type="button" className="btn btn-outline-danger" onClick={() => deletePlayer(player.playerId)}>Delete</button>
                                                </div>
                                                <br />
                                            </div>

                                        </Card>
                                    </div>
                                )
                            })}


                    </div>
                </div>
                <div className='col-md-1'></div>

            </div>


        </div>
    )
}
