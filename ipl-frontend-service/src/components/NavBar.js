import { useDispatch, useSelector } from "react-redux";
import { Link } from "react-router-dom";
import EventBus from "./EventBus";

import { logout } from "../services/authService/authSlice";
import { useCallback, useEffect } from "react";

import './NavBar.css'



export const NavBar = () => {

    const { user: currentUser } = useSelector((state) => state.auth);
    const dispatch = useDispatch();

    const logOut = useCallback(() => {
        dispatch(logout());
    }, [dispatch]);

    useEffect(() => {

        EventBus.on("logout", () => {
            logOut();
        });

        return (() => {
            EventBus.remove("logout");
        });
    }, [currentUser, logOut]);

    return (

        <div className="container-fluid">
            <nav className="navbar navbar-expand navbar-dark bg-dark">

                <Link to={"/home"} className="navbar-brand">
                    <img src='https://www.jagranimages.com/images/newimg/21082020/21_08_2020-ipl_logo_20650553.jpg' alt='ipl-logo' id='img' />
                </Link>

                <div className="navbar-nav mr-auto">
                    <li className="nav-item">
                        <Link to={"/home"} className="nav-link">
                            <b>Indian Premier League</b>
                        </Link>
                    </li>

                </div>

                {currentUser ? (
                    <div className="navbar-nav ml-auto">
                        <li className="nav-item">
                            <Link to={"/profile"} className="nav-link">
                                <div style={{ textTransform: 'uppercase' }}>
                                    {currentUser.username}
                                </div>
                            </Link>
                        </li>
                        <li className="nav-item">
                            <a href="/login" className="nav-link" onClick={logOut}>
                                <b>Log-Out</b>
                            </a>
                        </li>
                    </div>
                ) : (
                    <div className="navbar-nav ml-auto">
                        <li className="nav-item">
                            <Link to={"/login"} className="nav-link">
                                <b>Log-in</b>
                            </Link>
                        </li>
                    </div>
                )}
            </nav>
        </div>
    );

}