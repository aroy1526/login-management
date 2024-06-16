import axios from "axios"

let URL ='http://localhost:8080/api/todo'

axios.interceptors.request.use(function (config) {
   config.headers['Authorization']=getToken()
    return config;
  }, function (error) {
    // Do something with request error
    return Promise.reject(error);
  });

export const getAllTodo = ()=>axios.get(URL)
export const getTodoById = (todoId)=>axios.get(URL+'/'+todoId)
export const saveTodo = (todo)=>axios.post(URL,todo)
export const updateTodo= (todoId,todo)=>axios.put(URL+'/'+todoId,todo)
export const deleteTodo = (todoId)=>axios.delete(URL+'/'+todoId)
export const completeTodo = (todoId)=>axios.patch(URL+'/'+todoId)
export const inCompleteTodo = (todoId)=>axios.patch(URL+'?id='+todoId)
export const registerUser =(user)=>axios.post('http://localhost:8080/api/auth/user',user)
export const loginUser = (loginDto)=>axios.post('http://localhost:8080/api/auth/login',loginDto)
export const setToken=(token)=>localStorage.setItem('token',token)
export const getToken = ()=>localStorage.getItem('token')
export const setUser =  (userName)=>sessionStorage.setItem('user',userName)
export const getUser = ()=>sessionStorage.getItem('user')
export const isUserLogin = ()=>{
    const userName = sessionStorage.getItem('user')
    if(userName==null){
        return false;
    }else{
        return true;
    }
}

export const logOut = () =>{
    sessionStorage.clear();
    localStorage.clear();
}