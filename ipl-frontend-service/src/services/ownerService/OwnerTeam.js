import React, { useEffect} from 'react';
import { Link, useParams } from 'react-router-dom';
import { Card } from 'react-bootstrap';
import './ownerTeam.css';
import { useDispatch, useSelector } from 'react-redux';
import { deletePlayerFromTeam, viewPlayersByTeamId } from './ownerSlice';


export default function OwnerTeam() {

    const { teamId } = useParams();

    const dispatch = useDispatch();

    useEffect(() => {
        dispatch(viewPlayersByTeamId(teamId))
    }, []);

    const players = useSelector((state) => state.owner.ownerTeamList);

    const finder = () => {
        let total = players.length
        let foreign = players.filter(player => player.nationality !== 'India').length
        let data = {
            total: total,
            foreign: foreign
        }
        return data
    }

    const onDelete = async (playerId) => {
        dispatch(deletePlayerFromTeam(playerId))
        window.location.reload(false)
    }


    return (
        <div className='container' id="body">
            <hr />
            <div className='row'>

                <div className='col-md-7'>
                    <br />
                    <div className='display-5'><b>Team Owner Page View</b> </div>
                </div>
                <div className='col-md-5'>
                    <div className='text-right'>
                        <Link type="button" state={{ data: finder() }} className="btn btn-outline-warning m-5" to={`/owner/view-new-player/${teamId}`}>Add-Player</Link>
                        <Link type="button" className="btn btn-outline-danger m-5" to={`/owner/owner-page`}>Back-To-Owner</Link>

                    </div>
                </div>

            </div>
            <hr />

            <div>
                <div className='row'>
                    <div className='col-md-12'>
                        <div className='d-flex flex-wrap'>
                            {
                                players.map((player) => {
                                    return (

                                        <Card id='card-body2' key={player.playerId}>
                                            <div className='text-center'>
                                                <br />
                                                {/* <Card.Img id='card-img2' variant='top' src={player.imageUrl} /> */}
                                                <Card.Body>
                                                    <hr />
                                                    <Card.Title><b>{player.playerName}</b></Card.Title>
                                                    <hr />
                                                    <div className='d-flex justify-content-around'>
                                                        <Card.Text>Age :{player.age}</Card.Text>
                                                        <Card.Text>{player.specialty}</Card.Text>
                                                    </div>
                                                    <hr />
                                                </Card.Body>
                                                <div className='text-center'>
                                                    <button type="button" onClick={(e) => onDelete(player.playerId)} class="btn btn-outline-danger">Remove</button>
                                                </div>
                                                <br />
                                            </div>

                                        </Card>
                                    )
                                })}


                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}
