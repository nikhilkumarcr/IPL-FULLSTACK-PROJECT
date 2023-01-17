import React, { useRef, useState } from 'react'
import axios from 'axios'
import { Link, useNavigate } from 'react-router-dom'
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import Select from "react-validation/build/select";
import CheckButton from "react-validation/build/button";
import './Form.css';

export default function AddPlayer() {


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
   let navigate = useNavigate()
   const [player, setPlayer] = useState()

   const onInputChange = (e) => {
      setPlayer({ ...player, [e.target.name]: e.target.value })
   }
   const onSubmit = async (e) => {
      e.preventDefault()
      form.current.validateAll();
      await axios.post(`http://localhost:8082/api/add-player`, player)
      navigate('/player')

   }
   return (
      <div>
         <div className='row'>
            <div className='col-md-2'></div>
            <div className='col-md-8'>
               <br />
               <br />
               <div className='container w-50 shadow' id="card-style">
                  <br />
                  <h3><b>New Player Add Form : </b></h3>

                  <Form onSubmit={(e) => onSubmit(e)} ref={form} >
                     <div className='card-body'>
                        <div className="form-group">
                           <label><b>Player Name : </b></label>
                           <Input type="text"
                              className="form-control"
                              name='playerName'
                              onChange={(e) => onInputChange(e)}
                              validations={[required]} />
                        </div>
                        <div className="form-group">
                           <label ><b>Age : </b></label>
                           <Input type="text"
                              className="form-control"
                              name='age'
                              onChange={(e) => onInputChange(e)}
                              validations={[required]} />
                        </div>
                        <div className="form-group">
                           <label ><b>Speciality : </b></label>
                           {/* <input type="text"
                              className="form-control"
                              name='specialty'
                              onChange={(e) => onInputChange(e)} /> */}

                           <Select className="form-control"
                              type="text"
                              name="specialty"
                              onChange={(e) => onInputChange(e)}
                              validations={[required]}>
                              <option selected>Select Speciality</option>
                              <option value='Batsman'>Batsman</option>
                              <option value='Bowler'>Bowler</option>
                              <option value='All-Rounder'>All-Rounder</option>
                              <option value='WK-Batsman'>WK-Batsman</option>
                           </Select>
                        </div>
                        <div className="form-group">
                           <label><b>Nationality : </b></label>
                           <Input type="text"
                              className="form-control"
                              name='nationality'
                              onChange={(e) => onInputChange(e)}
                              validations={[required]} />
                        </div>
                        <div className="form-group">
                           <label><b>Player Image : </b></label>
                           <Input type="text"
                              className="form-control"
                              name='imageUrl'
                              onChange={(e) => onInputChange(e)}
                              validations={[required]} />
                        </div>
                        <br />
                        <div className='d-flex justify-content-around'>
                           <button type="submit" className="btn btn-outline-success"><b>Add-Player</b></button>
                           <Link type='button' className='btn btn-outline-danger' to={"/player"}><b>Cancel</b></Link>
                        </div>
                     </div>
                     <CheckButton style={{ display: "none" }} ref={checkBtn} />
                  </Form>
               </div>
               <br />
            </div>
            <div className='col-md-2'></div>
         </div>
      </div>
   )
}
