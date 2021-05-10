import axios from './axios'


/**
 * 管理员api
 */
export class Admin {
    /**
     * 登录
     * @param username
     * @param password
     * @returns {*}
     */
    static login(username, password) {
        return axios.post("/admin/login", {
            username,
            password
        })
    }

}

/**
 * 公告api
 */
export class Notice {

}

/**
 * 统计api
 */
export class Statistics {

}

/**
 * 反馈api
 */
export class Feedback {

}


