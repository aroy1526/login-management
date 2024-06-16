import {NavLink, useNavigate} from 'react-router-dom'
import 'bootstrap/dist/css/bootstrap.min.css';
import { isUserLogin, logOut } from '../lmsServvice/LoginManagementService';


export const HeaderComponent = () =>{
    const isLogin = isUserLogin();
    const nav = useNavigate()
    function handelLogout(){
        logOut()
        nav("/")
    }
    return(
        <div>
            <header>
                <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
                <div class="container-fluid">
                <div class="collapse navbar-collapse" id="navbarNav">
      <ul className="navbar-nav">
        {
            isLogin && 
            <li className="nav-item">
            <NavLink className="nav-link" to="/todo" >Todo</NavLink>
          </li>
        }
       {
        !isLogin &&
        <li>
        <NavLink className="nav-link" to="/add-user">Register</NavLink>
        </li>
        }
        {
            !isLogin &&
        <li>
        <NavLink className="nav-link" to="/login">Login</NavLink>
        </li>
        }
         {
            isLogin && 
            <li className="nav-item">
            <NavLink className="nav-link"  to="/login" onClick={handelLogout} >Logout</NavLink>
          </li>
        }
        </ul>
        </div>
                    </div>
                </nav>
            </header>
        </div>
    )
}