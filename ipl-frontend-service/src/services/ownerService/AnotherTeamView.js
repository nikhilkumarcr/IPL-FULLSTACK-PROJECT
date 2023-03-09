import React, { useEffect } from 'react'
import {  useParams } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { viewPlayersByTeamId } from './ownerSlice';


export default function AnotherTeamView() {

    const { teamId } = useParams();

    const dispatch = useDispatch();

    const players = useSelector((state)=> state.owner.ownerTeamList);
    //console.log(players)

    useEffect(() => {
       dispatch(viewPlayersByTeamId(teamId))
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
                                        <tr className='table-secondary text-dark' key={player.playerId}>
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
