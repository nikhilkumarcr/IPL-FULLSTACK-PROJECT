import React, { useEffect } from 'react';
import { Link } from 'react-router-dom';
import { Card } from 'react-bootstrap';
import './team.css';
import { useDispatch, useSelector } from 'react-redux';
import { deleteTeam, viewAllTeams } from './teamSlice';

export default function Team() {


   const dispatch = useDispatch();

   const teams = useSelector((state) => state.team.teamsList);

    useEffect(() => {

      dispatch(viewAllTeams());

  }, [dispatch]);


  const handleDelete = (teamId)=>{

   dispatch(deleteTeam(teamId));
   
   window.location.reload(false);
} 
   return (
      <div className="container">

         <div className='row'>
            <hr />
            <div className='col-md-9'>

               <div className='display-4'><b>List of Teams</b> </div>
            </div>
            <div className='col-md-3'>
               <div className='text-right'>
                  <Link className="btn btn-success m-2" to='/admin/add-team'>New Team</Link>
                  <Link className="btn btn-dark m-2" to='/admin/player'><b>Player</b></Link>
               </div>

            </div>
            <hr />
         </div>

         <br />
         <div className='container'>
            <div className='row'>
               <div className='col-md-12'>
                  <div className='d-flex flex-wrap'>
                     {
                        teams.map((team) => {
                           return (
                              <Card id='card-body' key={team.teamId}>
                                 <br />
                                 <div className='text-center'>
                                    <Card.Img className='img-fluid'
                                       id='card-img'
                                       variant='top'
                                       src={team.teamUrl}
                                    />
                                    <Card.Body>
                                       <hr />
                                       <Card.Title><b>{team.teamName}</b></Card.Title>
                                       <hr />
                                       <div className='d-flex justify-content-around'>
                                          <Card.Text> <b>City: {team.city}</b></Card.Text>
                                          <Card.Text><b>State: {team.state}</b></Card.Text>
                                       </div>
                                       <hr />
                                    </Card.Body>

                                    <div className='d-flex justify-content-around'>
                                       <Link type="button" className="btn btn-outline-dark btn-sm"
                                          to={`/admin/edit-team/${team.teamId}`} ><b>Edit</b></Link> 
                                       <button type="button" className="btn btn-outline-danger btn-sm"
                                          onClick={() => handleDelete(team.teamId)}><b>Delete</b></button>
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

