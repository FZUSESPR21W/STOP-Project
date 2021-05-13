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

    /**
     * 登出
     * @returns {*}
     */
    static logout() {
        return axios.post("/admin/logout")
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

    /**
     * 发布公告
     * @param title
     * @param content
     * @param top
     * @param status
     * @returns {*}
     */
    static publishNotice(title, content, top, status) {
        return axios.post('/notice/publish', {
            title,
            content,
            top,
            status
        })
    }

    /**
     * 获取公告细节
     * @param id
     * @returns {*}
     */
    static getNoticeDetail(id) {
        return axios.get(`/notice/detail?id=${id}`)
    }

    /**
     * 更新公告
     * @param id
     * @param title
     * @param content
     * @param top
     * @param status
     * @returns {*}
     */
    static updateNotice(id, title, content, top, status) {
        return axios.post('/notice/update', {
            id,
            title,
            content,
            top,
            status
        })
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

    /**
     * 获取用户登录记录
     * @returns {*}
     */
    static getLoginList() {
        return axios.get('api/get_login_list')
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


