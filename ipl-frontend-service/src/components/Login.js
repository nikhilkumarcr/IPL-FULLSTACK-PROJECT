
import { useDispatch } from 'react-redux';
import React, { useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import { login } from '../services/authService/authSlice'
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

function Login() {

  let navigate = useNavigate();

  const form = useRef();
  const checkBtn = useRef();

  const dispatch = useDispatch();

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

    setMessage(" ");
    setLoading(true);

    form.current.validateAll();

    if (checkBtn.current.context._errors.length === 0) {

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
        }, (error) => {
          setLoading(false);
          setMessage("Invalid UserName or Password!!!");
        });
    } else {
      setLoading(false);
    }
  };


  return (
    <div class="container">
      <div class="row">
        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
          <div class="card border-0 shadow rounded-3 my-5" id='card'>
            <div class="card-body p-4 p-sm-5">

              <h3 className="text-center"><b>Log-In</b></h3>

              <Form onSubmit={handleLogin} ref={form}>
                <div className="form-group">
                  <label htmlFor="username">Username</label>
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
                  <label htmlFor="password">Password</label>
                  <Input
                    type="password"
                    className="form-control"
                    name="password"
                    value={password}
                    onChange={onChangePassword}
                    validations={[required]}
                  />
                </div>

                <div className="form-group">
                  <button className="btn btn-primary btn-block m-2" disabled={loading}>
                    {loading && (
                      <span className="spinner-border spinner-border-sm"></span>
                    )}
                    <span>Login</span>
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
      </div>
    </div>
  );
};

export default Login;
