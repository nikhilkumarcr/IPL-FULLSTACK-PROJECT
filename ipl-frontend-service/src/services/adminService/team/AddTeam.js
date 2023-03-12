import React, { useRef, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import './formStyle.css';
import { useDispatch } from 'react-redux';
import { addTeam } from './teamSlice';


export default function AddTeam() {

   const form = useRef();
   const checkBtn = useRef();

   const required = (value) => {
      if (!value) {
        return (
          <div className="alert alert-danger" role="alert">
             This field cannot be Empty!!!
          </div>
        );
      }
    };

   const navigate = useNavigate()

   const dispatch = useDispatch();

   const [team, setTeam] = useState()

   const onInputChange = (e) => {

      setTeam({ ...team, [e.target.name]: e.target.value });

   }

   const handleAdd = async (e) => {
      e.preventDefault()

      form.current.validateAll();

      dispatch(addTeam(team))

      navigate('/admin/team')
   }


   return (
      <div>
         <br />
         <div className='row'>
            <div className='col-md-2'></div>
            <div className='col-md-8'>
               <div className='container w-50 shadow' id="team-card">
                  <h3><b>Add Team Form : </b></h3>


                  <Form onSubmit={(e) => handleAdd(e)} ref={form}>

                     <div className='card-body'>

                        <div className="form-group">
                           <label><b>Team Name : </b></label>
                           <Input type="text"
                              className="form-control"
                              name='teamName'
                              onChange={(e) => onInputChange(e)}
                              validations={[required]} />
                        </div>

                        <div className="form-group">
                           <label><b>Owner Name : </b></label>
                           <Input type="text"
                              className="form-control"
                              name='ownerName'
                              onChange={(e) => onInputChange(e)}
                              validations={[required]} />
                        </div>

                        <div className="form-group">
                           <label><b>Email Id : </b></label>
                           <Input type="text"
                              className="form-control"
                              name='emailId'
                              onChange={(e) => onInputChange(e)}
                              validations={[required]} />
                        </div>

                        <div className="form-group">
                           <label><b>City : </b></label>
                           <Input type="text"
                              className="form-control"
                              name='city'
                              onChange={(e) => onInputChange(e)}
                              validations={[required]} />
                        </div>
                        
                        <div className="form-group">
                           <label><b>State : </b></label>
                           <Input type="text"
                              className="form-control"
                              name='state'
                              onChange={(e) => onInputChange(e)}
                              validations={[required]} />
                        </div>

                        <div className="form-group">
                           <label><b>Team Image : </b></label>
                           <Input type="text"
                              className="form-control"
                              name='teamUrl'
                              onChange={(e) => onInputChange(e)}
                              validations={[required]} />
                        </div>

                        <br />
                        <div className='d-flex justify-content-around'>
                           <button type="button" className="btn btn-outline-success"><b>Add-Team</b></button>
                           <Link type='button' className='btn btn-outline-danger' to={"/admin/team"}><b>Cancel</b></Link>
                        </div>
                     </div>
                     <CheckButton style={{ display: "none" }} ref={checkBtn} />
                  </Form>
               </div>
            </div>
            <div className='col-md-2'></div>

         </div>
      </div>
   )
}
