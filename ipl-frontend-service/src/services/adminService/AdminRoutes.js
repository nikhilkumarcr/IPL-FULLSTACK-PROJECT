  
import React from 'react';
import { Outlet} from 'react-router-dom';
   
   export default function AdminRoutes() {


    if(JSON.parse(localStorage.getItem('user')).roles[0]==='ADMIN'){
        return <Outlet/>
    }

    else{
        return(
            <div>
                <br />
                <br />
                <br />
                <div className='display-3'>
                    Un-Authorized to access this page !!!
                </div>
            </div>
        )
    }
   }
   
  