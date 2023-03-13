import React, { useEffect } from 'react'
import { Link } from 'react-router-dom';
import { Card } from 'react-bootstrap';
import './player.css';
import { useDispatch, useSelector } from 'react-redux';
import { deletePlayer, viewAllPlayers } from './playerSlice';

export default function Player() {

    const dispatch = useDispatch();

    const players = useSelector((state) => state.player.playersList);

    useEffect(() => {

        dispatch(viewAllPlayers());


    }, [dispatch]);

    const handleDelete = (playerId)=>{

        dispatch(deletePlayer(playerId));

        window.location.reload(false);
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
                        <Link className="btn btn-success btn-sm m-2" to='/admin/add-player'>Add-Player</Link>
                        <Link className="btn btn-dark btn-sm m-2" to='/admin/team'>Back-To-Team</Link>

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
                                        <Card id='card-body1' key={player.playerId}>
                                            <div className='text-center'>
                                                <br />
                                                <Card.Img id='card-img1' variant='top' src={player.imageUrl} />
                                                <Card.Body>
                                                    <hr />
                                                    <Card.Title><b>{player.playerName}</b></Card.Title>
                                                    <hr />
                                                    <div className='d-flex justify-content-around'>
                                                        <Card.Text><b>Age :{player.age}</b></Card.Text>
                                                        <Card.Text><b>Role :{player.specialty}</b></Card.Text>
                                                    </div>
                                                    <hr />
                                                </Card.Body>
                                                <div className='d-flex justify-content-around'>
                                                    <Link type="button" className="btn btn-outline-dark" to={`/admin/edit-player/${player.playerId}`} >Edit</Link>
                                                    <button type="button" className="btn btn-outline-danger" onClick={() => handleDelete(player.playerId)}>Delete</button>
                                                </div>
                                                <br />
                                            </div>

                                        </Card>
                                )
                            })}


                    </div>
                </div>
                <div className='col-md-1'></div>

            </div>


        </div>
    )
}
