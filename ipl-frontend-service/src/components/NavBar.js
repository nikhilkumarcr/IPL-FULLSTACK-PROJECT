import { useDispatch, useSelector } from "react-redux";
import { Link } from "react-router-dom";
import EventBus from "./EventBus";
import { logout } from "../services/authService/authSlice";
import { useCallback, useEffect } from "react";
import './NavBar.css';



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

        <div>
            <nav className="navbar navbar-expand navbar-dark bg-dark">

                <button
                    className="navbar-toggler"
                    type="button"
                    data-toggle="collapse"
                    data-target="#navbarNav"
                    aria-controls="navbarNav"
                    aria-expanded="false"
                    aria-label="Toggle navigation"
                >
                    <span className="navbar-toggler-icon"></span>
                </button>

                <Link to={"/home"} className="navbar-brand">
                    <img src='https://www.jagranimages.com/images/newimg/21082020/21_08_2020-ipl_logo_20650553.jpg' alt='ipl-logo' id='img' />
                </Link>

                <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav mr-auto">
                        <>
                            <li className="nav-item">
                                <Link to={"/home"} className="nav-link">
                                    <b>Indian Premier League</b>
                                </Link>
                            </li>

                        </>
                    </ul>
                </div>

                {currentUser ? (
                   <ul className="navbar-nav ml-auto">
                        <li className="nav-item">
                            <Link className="nav-link">
                                <div style={{ textTransform: 'uppercase' }} >
                                    <b>{currentUser.username}</b>
                                </div>
                            </Link>
                        </li>
                        <li className="nav-item">
                            <a href="/login" className="nav-link" onClick={logOut} >
                                <b>Log-Out</b>
                            </a>
                        </li>

                        </ul>
                   
                ) : (
                    <ul className="navbar-nav ml-auto">
                        <li className="nav-item">
                            <Link to={"/login"} className="nav-link" >
                                <b>Log-in</b>
                            </Link>
                        </li>
                    </ul>
                )}
            </nav>


        </div>
    );

}