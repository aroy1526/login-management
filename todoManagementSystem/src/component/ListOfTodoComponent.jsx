import { useEffect, useState } from "react"
import { completeTodo, deleteTodo, getAllTodo, inCompleteTodo } from "../lmsServvice/LoginManagementService"
import { useNavigate } from "react-router-dom"

const ListOfTodo = ()=>{

const [todoList,setTodoList]=useState([])

useEffect(()=>{getTotoList()},[])

function getTotoList(){
    getAllTodo().then((Response)=>{
        setTodoList(Response.data)
    }).catch((err)=>{console.error(err);})
}

const navigate =useNavigate()

function addTodo(){
    navigate('/add-todo')
}

function updateTodo(id){
    navigate(`/edit-todo/${id}`)
}

function removeTodo(id){
    deleteTodo(id).then((Response=>{
        getTotoList()
    })).catch((err)=>{console.error(err)})
}

function markCompleteTodo(id){
    completeTodo(id).then((Response)=>{
        getTotoList()
    }).catch((err)=>{console.error(err)})
}

function markInCompleteTodo(id){
    inCompleteTodo(id).then((Response)=>{
        getTotoList()
    }).catch((err)=>{console.error(err)})
}


    return(
       <div>
    <h1 className='text-center'>List Of Todo</h1> 
    {<button className="btn btn-success" onClick={addTodo}>Add Todo</button>}
    <br/><br/>
    <table className="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>Todo Id</th>
                        <th>Todo Name</th>
                        <th>Todo Description</th>
                        <th>Todo Complete</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        todoList.map(todo =>
                           <tr key={todo.id}>
                            <td>{todo.id}</td>
                            <td>{todo.todoTitle}</td>
                            <td>{todo.todoDescription}</td>
                            <td>{todo.complete.toString()}</td>
                            {/* onClick={()=>{updateDepartment(dep.id)}} */}
                            <td><button className="btn btn-info" onClick={()=>{updateTodo(todo.id)}} >Update</button>
                            {/* onClick={()=>{removeDepartment(dep.id)}} */}
                            <button className="btn btn-danger" style={{marginLeft:'12px'}} onClick={()=>{removeTodo(todo.id)}} >Delete</button>
                            <button className="btn btn-success" style={{marginLeft:'12px'}} onClick={()=>{markCompleteTodo(todo.id)}} >Complete</button>
                            <button className="btn btn-success" style={{marginLeft:'12px'}} onClick={()=>{markInCompleteTodo(todo.id)}} >InComplete</button>
                            </td>
                           </tr>
                        
                        )
                    }
                </tbody>
            </table>
    </div>   
    )
}

export default ListOfTodo