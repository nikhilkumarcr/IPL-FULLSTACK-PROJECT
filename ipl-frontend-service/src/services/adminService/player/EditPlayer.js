import React, { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import './form.css';
import { useDispatch } from 'react-redux';
import { getPlayerByPlayerId, updatePlayer } from './playerSlice';


export default function EditPlayer() {

    const navigate = useNavigate();

    const dispatch = useDispatch();

    const { playerId } = useParams();

    const [player, setPlayer] = useState({});

    useEffect(() => {
       dispatch(getPlayerByPlayerId(playerId))
       .then((response)=>{
        setPlayer(response.payload)
       })
    }, [])

    const onInputChange = (e) => {
        setPlayer({ ...player, [e.target.name]: e.target.value });
    }
    const onSubmit = async (e) => {
        e.preventDefault()
        dispatch(updatePlayer(player));
        navigate('/admin/player');
    }

    return (
        <div className='container'>
            <div className='row'>
                <div className='col-md-2'></div>
                <div className='col-md-8'>
                    <br />
                    <div className='container w-50 shadow' id="card-style">
                        <h3> <b>Edit Player Form :</b> </h3>

                        <form onSubmit={(e) => onSubmit(e)}>
                            <div className="form-group">
                                <label><b>Player Name : </b></label>
                                <input type="text"
                                    className="form-control"
                                    value={player.playerName}
                                    name='playerName'
                                    onChange={(e) => onInputChange(e)} />
                            </div>
                            <div className="form-group">
                                <label><b>Age : </b></label>
                                <input type="text"
                                    className="form-control"
                                    value={player.age} name='age'
                                    onChange={(e) => onInputChange(e)} />
                            </div>
                            <div className="form-group">
                                <label><b>Nationality :</b></label>
                                <input type="text"
                                    className="form-control"
                                    value={player.nationality}
                                    name='nationality'
                                    onChange={(e) => onInputChange(e)} />
                            </div>
                            <div className="form-group">
                                <label><b>Speciality : </b></label>
                                <input type="text"
                                    className="form-control"
                                    value={player.specialty}
                                    name='specialty'
                                    onChange={(e) => onInputChange(e)} />
                            </div>
                            <div className="form-group">
                                <label><b>Player Image : </b></label>
                                <input type="text"
                                    className="form-control"
                                    value={player.imageUrl}
                                    name='imageUrl'
                                    onChange={(e) => onInputChange(e)} />
                            </div>
                            <br />
                            <div className='d-flex justify-content-around'>
                                <button type="submit" className="btn btn-outline-success"><b>Update-Player</b></button>
                                <Link type='button' className='btn btn-outline-danger' to={"/admin/player"}><b>Cancel</b></Link>
                            </div>
                        </form>
                    </div>
                </div>
                <div className='col-md-2'></div>
            </div>
        </div>
    )

}
