import React, { useEffect, useState } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom'
import './formStyle.css';
import { useDispatch } from 'react-redux';
import { getTeamByTeamId, updateTeam } from './teamSlice';


export default function EditTeam() {

    let navigate = useNavigate();

    const dispatch = useDispatch();

    const {teamId}=useParams()

    const[team,setTeam]=useState({});

    useEffect(()=>{

      dispatch(getTeamByTeamId(teamId))
      .then((response)=>{
         setTeam(response.payload);
      })

    },[])

    const onInputChange=(e)=>{
      
        setTeam({...team,[e.target.name] :e.target.value})
    }

    const onSubmit= async (e)=>{

        e.preventDefault()

        dispatch(updateTeam(team))
        
        navigate('/admin/team')
    } 

  return (
   <div>
      <div className='row'>
      <div className='col-md-2'></div>
         <div className='col-md-8'>

        <br />
        <br />
     <div className='container w-50 shadow' id="team-card">
     <br />
        <div className='display-6'><b>Edit Team Form : </b></div>
        <form  onSubmit={(e) => onSubmit(e)}>

        <div className='card-body'>
           <div className="form-group">
              <label><b>Team Name : </b></label>
              <input type="text" 
              className="form-control" 
              value={team.teamName} 
              name='teamName' 
              onChange={(e) => onInputChange(e)} />
           </div>
           <div className="form-group">
              <label><b>City : </b></label>
              <input type="text" 
              className="form-control" value={team.city} name='city' onChange={(e) => onInputChange(e)} />
           </div>
           <div className="form-group">
              <label><b>State : </b></label>
              <input type="text" className="form-control" value={team.state} name='state' onChange={(e) => onInputChange(e)} />
           </div>
           
           <br />
           <div className='d-flex justify-content-around'>

           <button type="submit" className="btn btn-dark"><b>Update-Team</b></button>
           
           <Link type='button' className='btn btn-danger' to={"/admin/team"}><b>Cancel</b></Link>
           </div>
           <br />
           </div>
        </form>
     </div>
     </div>
     <div className='col-md-2'></div>
      </div>
     </div>
  )
}
