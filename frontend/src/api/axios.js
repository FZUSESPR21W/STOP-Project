import axios from 'axios'
import store from '@/store'
import router from "../router"
import { Message } from 'element-ui'


let ax = axios.create();


//基础url
ax.defaults.baseURL = "/api"

//请求拦截
ax.interceptors.request.use(config =>
{
    let token = localStorage.getItem("token") || ''
    if (token!=="")
    {
        console.log(token)
        config.headers.common['Authorization'] = token;
        config.headers["Authorization"] = token
    }
    return config
})


//响应拦截
ax.interceptors.response.use(response =>
{
    if (response.status == 200) {
        if(response.data.code == 401){
            Message.error('你的登录已过期/尚未登录，请重新登录')
            router.push('/login')
        }
        return Promise.resolve(response);
    }
    return Promise.reject(response);
}, error =>
{
    return Promise.reject(error);
})

export default ax;