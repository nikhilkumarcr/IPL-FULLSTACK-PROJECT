import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import { Card } from 'react-bootstrap';
import './Team.css'

export default function Team() {

   const [teams, setTeams] = useState([]);

   useEffect(() => {
      viewTeams()
   }, [])

   const viewTeams = async () => {
      let result = await axios.get("http://3.108.219.116:8082/api/view-teams")
      console.log(result)
      setTeams(result.data)
   }

   const deleteTeam = async (teamId) => {
      await axios.delete(`http://3.108.219.116:8082/api/delete-team/${teamId}`)
      viewTeams()
   }

   return (
      <div className="container">

         <div className='row'>
            <hr />
            <div className='col-md-9'>

               <div className='display-4'><b>Team View Page</b> </div>
            </div>
            <div className='col-md-3'>
               <div className='text-right'>
                  <Link className="btn btn-outline-success btn-sm m-2" to='/admin/add-team'>New Team</Link>
                  <Link className="btn btn-outline-dark btn-sm m-2" to='/admin/admin-view'>Back-To-Home</Link>
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
                              <Card id='card-body'>
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
                                          onClick={() => deleteTeam(team.teamId)}><b>Delete</b></button>
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

