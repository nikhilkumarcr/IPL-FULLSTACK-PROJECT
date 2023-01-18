import React from 'react';
import { Link } from 'react-router-dom';


function Admin(props) {

    return (
        <div className='container'>
            <hr />
            <div className='row'>
                <div className='col-md-9'>
                    <div className='display-4'>
                       <b> Welcome To Admin Page</b>
                    </div>
                </div>
                <div className='col-md-3'>
                    <div className='text-right'>
                        <Link className="btn btn-outline-success m-2" to='/admin/team'><b>Team</b></Link>
                        <Link className="btn btn-outline-dark m-2" to='/admin/player'><b>Player</b></Link>
                    </div>
                </div>



            </div>
            <hr />
        </div>
    );
}

export default Admin;