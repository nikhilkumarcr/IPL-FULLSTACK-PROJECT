  
import React from 'react';
import { Outlet} from 'react-router-dom';
   
   export default function OwnerRoutes() {


    if(JSON.parse(localStorage.getItem('user')).roles[0]==='OWNER'){
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
   
  