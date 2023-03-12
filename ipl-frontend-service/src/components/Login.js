
import { ErrorMessage, Field, Form, Formik } from 'formik';
import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import {  useNavigate } from 'react-router-dom';
import * as Yup from "yup";
import { login } from '../services/authService/authSlice';
import { clearMessage } from '../services/messageService/message';
import './Login.css';


function Login(props) {

    let navigate = useNavigate();

    const [loading, setLoading] = useState(false);

    const  {message}  = useSelector((state) => state.message);

    const dispatch = useDispatch();

    useEffect(() => {
        dispatch(clearMessage());
      }, [dispatch]);


    const handleLogin = (formValue) => {

        const { username, password } = formValue;
        setLoading(true);

        dispatch(login({ username, password }))
            .unwrap()
            .then(() => {

                const user = JSON.parse(localStorage.getItem("user"));

                if (user.roles.includes("ADMIN")) {
                    navigate("/admin/team");
                    window.location.reload();
                } else if (user.roles.includes("OWNER")) {
                    navigate("/owner/owner-page");
                    window.location.reload();
                }
                else {
                    navigate("/home")
                    window.location.reload();

                }
            })
            .catch(() => {
                setLoading(false);
            });
    };

    const validationSchema = Yup.object().shape({
        username: Yup.string().required("This field is required!"),
        password: Yup.string().required("This field is required!"),
      });


    const initialValues = {
        username: "",
        password: "",
    };

    return (

        <div>
          <div></div>
            <div className="card card-container" id="card">
            <h3 className="text-center"><b>Login Form</b></h3>
        <Formik
          initialValues={initialValues}
          validationSchema={validationSchema}
          onSubmit={handleLogin}
        >
          <Form>
            <div className="form-group">
              <label htmlFor="username"><b>Username : </b></label>
              <Field name="username" type="text" className="form-control" />
              <ErrorMessage
                name="username"
                component="div"
                className="alert alert-danger"
              />
            </div>

            <div className="form-group">
              <label htmlFor="password"><b>Password : </b></label>
              <Field name="password" type="password" className="form-control" />
              <ErrorMessage
                name="password"
                component="div"
                className="alert alert-danger"
              />
            </div>
            <br />

            <div className="form-group">
              <button type="submit" className="btn btn-primary btn-block" disabled={loading}>
                {loading && (
                  <span className="spinner-border spinner-border-sm"></span>
                )}
                <span>Login</span>
              </button>
            </div>
          </Form>
        </Formik>
      </div>

      {message && (
        <div className="form-group">
          <div className="alert alert-danger" role="alert">
            {message}
          </div>
        </div>
      )}
        </div>
    );
}

export default Login;