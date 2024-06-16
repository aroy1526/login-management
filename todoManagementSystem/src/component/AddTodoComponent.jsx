import { useEffect, useState } from "react"
import { getTodoById, saveTodo, updateTodo } from "../lmsServvice/LoginManagementService"
import { useNavigate, useParams } from "react-router-dom"

export const AddTodo = () =>{
const[todoTitle,setTodoTitle]=useState('')
const[todoDescription,setTodoDescription] =useState('')
const[complete,setComplete] = useState('')

const nav = useNavigate()

//read the value from url
const {id} = useParams()

//getMethod

useEffect(()=>{
    getTodoById(id).then((Response)=>{
        setTodoTitle(Response.data.todoTitle)
        setTodoDescription(Response.data.todoDescription)
        setComplete(Response.data.complete)
    })},[id])


function saveOrUpdate(e){
    e.preventDefault()
    const todo = {todoTitle,todoDescription,complete}
    if(id){
        updateTodo(id,todo).then((Response)=>{
            nav('/todo')
        }).catch((err)=>{console.error(err)})
    }else{
            saveTodo(todo).then((Response)=>{
                nav('/todo')
            }).catch((err)=>{console.error(err)})
        }
}

function pageTitle(){
    if(id){
return ( <h2 className='text-center'>Update Todo</h2>)
    } else{
       return <h2 className='text-center'>Add Todo</h2>
    }
}

    return (
        <div className='container col-md-6 offset-md-3 offset-md-3'>
        <br/><br/>
        <div className='row'>
            <div className='card'>
            {pageTitle()}
            <div className='card-body'>
                <form>
                    <div className='form-group mb-2'>
                        <label className='form-lebel'>Todo Title:</label>
                        <input type='text'
                        placeholder='Enter Todo Title'
                        name='todoTitle'
                        value={todoTitle}
                        className="form-control"
                        onChange={(e)=>{setTodoTitle((e.target.value))}}/>            
                    </div>
                    <div className='form-group mb-2'>
                        <label className='form-lebel'>Todo Description:</label>
                        <input type='text'
                        placeholder='Enter Todo Description'
                        name='todoDescription'
                        value={todoDescription}
                        className="form-control"
                        onChange={(e)=>{setTodoDescription((e.target.value))}}/>            
                    </div>
                    <div className='form-group mb-2'>
                        <label className='form-lebel'>Complete:</label>
                        <input type='text'
                        placeholder='Enter Todo Complete'
                        name='complete'
                        value={complete}
                        className="form-control"
                        onChange={(e)=>{setComplete((e.target.value))}}/>            
                    </div>
                    <button className="btn-success btn" onClick={saveOrUpdate}>Submit</button>
                </form>
            </div>

        </div>
        </div>
    </div>
    )
}