import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import AuthService from '../authService/AuthService';

function OwnerId() {

    const user = AuthService.getCurrentUser();
    const [id, setId] = useState([]);

    useEffect(() => {
        getid()
    }, [])

    const getid = () => {
        axios.get(`http://localhost:8082/api/get-id/${user.username}`).then((response) => {
            console.log(response)
            setId(response.data)

        })
    }

    return (
        <div className='container-fluid'>
            <hr />
            <div className='row'>
                <div className='col-md-9'>
                    <div className='display-2'>Owner View Page</div>
                </div>
                <div className='col-md-3'>
                    <div className='text-right'>
                        <Link type="button" className="btn btn-outline-primary" to={`/owner-page/${id.teamId}`} >Owner</Link>
                    </div>

                </div>
            </div>
            <hr />
        </div>
    );
}

export default OwnerId;