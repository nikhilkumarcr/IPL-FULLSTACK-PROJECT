import React, { useEffect} from 'react';
import { Link, useParams } from 'react-router-dom';
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
                    <div className='text-center'>
                        <Link type="button" state={{ data: finder() }} className="btn btn-dark m-5" to={`/owner/view-new-player/${teamId}`}>Add-Player</Link>
                        <Link type="button" className="btn  btn-danger m-5" to={`/owner/owner-page`}>Back-To-Owner</Link>

                    </div>
                </div>

            </div>
            <hr />

            <div>
                <div className='row'>
                <div className='col-md-1'></div>
                    <div className='col-md-10'>
                        <div className='d-flex flex-wrap'>
                        <table className="table table-bordered table-hover text-center">
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
                                            <td><button type="button" onClick={(e) => onDelete(player.playerId)} class="btn btn-sm btn-danger">Remove</button></td>
                                        </tr>
                                    )
                                })
                            }
                        </tbody>
                    </table>

                        </div>
                    </div>
                    <div className='col-md-1'></div>
                </div>
            </div>
        </div>
    )
}
