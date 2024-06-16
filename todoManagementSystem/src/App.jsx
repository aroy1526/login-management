import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import ListOfTodo from './component/ListOfTodoComponent'
import { HeaderComponent } from './component/HeaderComponent'
import { BrowserRouter, NavLink, Navigate, Route, Routes } from 'react-router-dom'
import FooterComponent from './component/FooterComponent'
import { AddTodo } from './component/AddTodoComponent'
import { UserRegistrationComponent } from './component/RegisterUser'
import { LoginComponent } from './component/LoginComponent'
import { isUserLogin } from './lmsServvice/LoginManagementService'

function App() {

  function ToAuth({children}){
    const isAuth = isUserLogin();
    if(isAuth){
      return children
    }
    return <Navigate to="/"/>
  }

  return (
    <>
    <BrowserRouter>
    <HeaderComponent/>
    <Routes>
      <Route path='/' element={<LoginComponent/>}></Route>
      <Route path='/todo' element={
        <ToAuth>
        <ListOfTodo/>
        </ToAuth>
      }></Route>
      <Route path='/add-todo' element={
        <ToAuth>
        <AddTodo/>
        </ToAuth>
        }></Route>
      <Route path='/edit-todo/:id' element={
        <ToAuth>
        <AddTodo/>
        </ToAuth>
        }></Route>
      <Route path='/add-user' element={<UserRegistrationComponent/>}></Route>
      <Route path='/login' element={<LoginComponent/>}></Route>
      </Routes>
<FooterComponent/>
  </BrowserRouter>
    </>
  )
}

export default App
