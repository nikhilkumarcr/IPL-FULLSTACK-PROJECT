import React, { useState, useRef } from "react";
import { useNavigate } from 'react-router-dom';
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import AuthService from "../services/authService/AuthService";

import './Login.css';



const required = (value) => {
  if (!value) {
    return (
      <div className="alert alert-danger" role="alert">
        This field is required!
      </div>
    );
  }
};

function Login()  {
  
  let navigate = useNavigate();

  const form = useRef();
  const checkBtn = useRef();

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState("");

  const onChangeUsername = (e) => {
    const username = e.target.value;
    setUsername(username);
  };

  const onChangePassword = (e) => {
    const password = e.target.value;
    setPassword(password);
  };

  const handleLogin = (e) => {
    e.preventDefault();

    setMessage("");
    setLoading(true);

    form.current.validateAll();

    if (checkBtn.current.context._errors.length === 0) {

      AuthService.login(username, password).then(
        () => {
          const user = AuthService.getCurrentUser();
          console.log(user);
          if (user.roles.includes("ADMIN")) {
            navigate("/admin/admin-view");
            window.location.reload();
          } else if (user.roles.includes("OWNER")) {
            navigate("/owner/owner-view");
            window.location.reload();
          }
          else {
            navigate("/home")
            window.location.reload();

          }
        },
        (error) => {
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();

          setLoading(false);
          setMessage("Wrong Credentials!!!");
        }
      );
    } else {
      setLoading(false);
    }
  };

  return (
    <div className="container-fluid" id="login-body">
      <div className="row">

      <div className="col-md-3"></div>

   
    <div className="col-md-6">
      <div>
        <div className="card card-container" id="card">
          <h3 className="text-center"><b>Login Form</b></h3>

          <Form onSubmit={handleLogin} ref={form}>
            <div className="form-group">
              <label  htmlFor="username"><b>Username : </b></label>
              <Input
                type="text"
                className="form-control"
                name="username"
                value={username}
                onChange={onChangeUsername}
                validations={[required]}
              />
            </div>

            <div className="form-group">
              <label htmlFor="password"><b>Password : </b></label>
              <Input
                type="password"
                className="form-control"
                name="password"
                value={password}
                onChange={onChangePassword}
                validations={[required]}
              />
            </div>
            <br />
            <div className="form-group">
              <button className="btn btn-primary btn-block" disabled={loading}>
                {loading && (
                  <span className="spinner-border spinner-border-sm"></span>
                )}
                <span>Log-In</span>
              </button>
            </div>

            {message && (
              <div className="form-group">
                <div className="alert alert-danger" role="alert">
                  {message}
                </div>
              </div>
            )}
            <CheckButton style={{ display: "none" }} ref={checkBtn} />
          </Form>
        </div>
      </div>
    </div>
    <div className="col-md-3"></div>
    </div>
    </div>
  );
};

export default Login;
