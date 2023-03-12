import React, { useEffect, useState } from 'react'
import { useParams, useLocation, Link} from 'react-router-dom'
import './owerAddPlayer.css';
import { useDispatch } from 'react-redux';
import { viewAllPlayers } from '../adminService/player/playerSlice';
import { addPlayerToTeam } from './ownerSlice';


export default function OwnerAddPlayer() {


    const [players, setPlayers] = useState([]);

    const { teamId } = useParams();

    const location = useLocation();

    const dispatch = useDispatch();

    const totalPlayers = location.state.data.total;

    const foreignPlayer = location.state.data.foreign;

    useEffect(() => {

        dispatch(viewAllPlayers())
        .then((result)=>{
            console.log(result)
            setPlayers(result.payload.filter(e => e.available === true));
        })
    }, [dispatch])


    const onAdding = async (e, playerId, nationality) => {

        e.preventDefault();
        console.log(playerId);

        if (totalPlayers < 15) {

            if (nationality !== 'India') {

                if (foreignPlayer < 6) {

                    dispatch(addPlayerToTeam({teamId,playerId}));
                    window.location.reload(false);
                    
                }
            }
            else {

                dispatch(addPlayerToTeam({teamId,playerId}));
                window.location.reload(false);
            }
        }


    }

    return (
        <div className="container">
            <div className='container'>
            <hr />
            <div className='row'>
           
                <div className='col-8'>
                    <br />
                    <div className='display-5'><b>Available Player List</b> </div>
                </div>

                <div className='col-4'>
                    <div className='text-center'>
                        <Link type="button" className="btn btn-danger m-5" to={`/owner/owner-team/${teamId}`}>Back-To-Owner</Link>
                    </div>
                </div>

            </div>
            <hr />
            </div>
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
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                players.map((player) => {
                                    return (
                                        <tr className='table-success text-dark' key={player.playerId}>
                                            {/* <td><img id='player-img' src={player.imageUrl} alt='player' /></td> */}
                                            <td>{player.playerName}</td>
                                            <td>{player.age}</td>
                                            <td>{player.nationality}</td>
                                            <td>{player.specialty}</td>
                                            <td><button type="button" className="btn btn-outline-warning" onClick={(e) => onAdding(e, player.playerId, player.nationality)}>Add</button></td>
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
