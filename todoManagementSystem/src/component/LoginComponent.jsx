import { useState } from "react"
import { loginUser, setToken, setUser } from "../lmsServvice/LoginManagementService"
import { useNavigate } from "react-router-dom"

export const LoginComponent = ()=>{

    const [userName,setUserName]=useState('')
    const [password,setPassword]=useState('')
    const navigate =useNavigate()

    function login(e){
        e.preventDefault()
        const loginDto = {userName,password}
        console.log(loginDto)
        loginUser(loginDto).then(
            (Response)=>{
                console.log(Response.data)
                const token = 'Basic '+ window.btoa(userName+":"+password)
                setToken(token)
                setUser(userName)
                navigate('/todo')
                window.location.reload()
            }
        ).catch((err)=>{console.error(err);})
    }


    return(
        <div className='container col-md-6 offset-md-3 offset-md-3'>
        <br/><br/>
        <div className='row'>
            <div className='card'>
            <h2 className='text-center'>Login</h2>
            <div className='card-body'>
                <form>
                    <div className='form-group mb-2'>
                        <label className='form-lebel'>User Name:</label>
                        <input type='text'
                        placeholder='Enter User Name'
                        name='userName'
                        value={userName}
                        className="form-control"
                        onChange={(e)=>{setUserName((e.target.value))}}/>            
                    </div>
                    <div className='form-group mb-2'>
                        <label className='form-lebel'>Password:</label>
                        <input type='password'
                        placeholder='Enter Password'
                        name='password'
                        value={password}
                        className="form-control"
                        onChange={(e)=>{setPassword((e.target.value))}}/>            
                    </div>
                    <button className="btn-success btn" onClick={login}>Submit</button>
                </form>
            </div>

        </div>
        </div>
    </div>
    )
}