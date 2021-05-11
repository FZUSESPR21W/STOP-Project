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
    /**
     * 获取公告列表
     * @param page
     * @param limit
     * @returns {*}
     */
    static getNoticeList(page, limit) {
        return axios.get(`/notice/get_notice_list?page=${page}&limit=${limit}`)
    }
}

/**
 * 统计api
 */
export class Statistics {
    /**
     * 获取每日停车数据
     * @returns {*}
     */
    static getStopStatusDaily() {
        return axios.get('/api/stop_status_daily')
    }
}

/**
 * 反馈api
 */
export class Feedback {
    /**
     * 获取反馈列表
     * @param page
     * @param limit
     * @returns {*}
     */
    static getFeedbackList(page, limit) {
        return axios.get(`/feedback/get_feedback_list?page=${page}&limit=${limit}`)
    }
}


