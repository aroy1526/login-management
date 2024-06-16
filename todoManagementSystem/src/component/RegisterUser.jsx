import { useState } from "react"
import { registerUser } from "../lmsServvice/LoginManagementService"
import { useNavigate } from "react-router-dom"

export const UserRegistrationComponent = ()=>{
    const [userName,setUserName]=useState('')
    const [name,setName]=useState('')
    const [email,setEmail]=useState('')
    const [password,setPassword]=useState('')
    const [roleName, setRoleName] = useState([]);
    const nav = useNavigate()

    function register(e){
        e.preventDefault()
        //const roleName = tmpRoleName.map(item => JSON.stringify(item).replace(/"/g, '"'));
        

       // console.log(roleName)
        const userDetails = {userName,name,email,password,roleName}
        console.log(JSON.stringify(userDetails))
        registerUser(userDetails).then(
            (Response)=>{
                console.log(Response);
                nav('/login')
                window.location.reload(false)
            }
        ).catch((err)=>{console.error(err)})

    }

    const options = [
        "ROLE_ADMIN",
        "ROLE_USER",
        "ROLE_MANAGER"
    ];


    return(
        <div className='container col-md-6 offset-md-3 offset-md-3'>
        <br/><br/>
        <div className='row'>
            <div className='card'>
            <h2 className='text-center'>Register User</h2>
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
                        <label className='form-lebel'>Name:</label>
                        <input type='text'
                        placeholder='Enter Name'
                        name='name'
                        value={name}
                        className="form-control"
                        onChange={(e)=>{setName((e.target.value))}}/>            
                    </div>
                    <div className='form-group mb-2'>
                        <label className='form-lebel'>email:</label>
                        <input type='text'
                        placeholder='Enter Email'
                        name='email'
                        value={email}
                        className="form-control"
                        onChange={(e)=>{setEmail((e.target.value))}}/>            
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
                    <div className='form-group mb-2'>
                    <label className='form-lebel'>Roles:</label>
                    <select className="form-control" onChange={(e)=>{
                        setRoleName([(e.target.value)])
                    }}>
                    <option >Please choose one option</option>
                        {options.map((option, index) => {
                            return (
                             <option key={index}>
                            {option}
                        </option>
                        );
                        })}
                    </select>       
                    </div>
                    <button className="btn-success btn" onClick={register}>Submit</button>
                </form>
            </div>

        </div>
        </div>
    </div>
        
        )
}