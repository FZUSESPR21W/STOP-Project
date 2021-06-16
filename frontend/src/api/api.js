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

    /**
     * 上传图片
     * @param formData
     * @returns {*}
     */
    static upLoadImg(formData) {
        return axios({
            method: 'post',
            url: 'https://api.shawnxixi.icu/api/upload/pic',
            headers: {
                'Content-Type': 'multipart/form-data'
            },
            data: formData
        })
    }

    /**
     * 删除公告
     * @param id
     * @returns {*}
     */
    static deleteNotice(id) {
        return axios.get(`/notice/delete?id=${id}`)
    }
}

/**
 * 统计api
 */
export class Statistics {
    /**
     * 获取昨日停车数据峰值
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
        return axios.get('/api/get_login_list')
    }

    /**
     * 获取两小时内停车数据
     * @returns {*}
     */
    static getStopStatusHourly() {
        return axios.get('/api/stop_status_hourly')
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

    /**
     * 获取反馈列表
     * @param id
     * @param status
     * @returns {*}
     */
    static update(id, status) {
        return axios.get(`/feedback/update?id=${id}&status=${status}`)
    }

    /**
     * 获取反馈列表
     * @param id
     * @returns {*}
     */
    static getDetail(id) {
        return axios.get(`/feedback/detail?id=${id}`)
    }
}

/**
 * 摄像头设备管理api
 */
export class Device {

    /**
     * 获取所有设备信息
     * @return {*}
     */
    static getAllDevice() {
        return axios.get('/device/get_all_device')
    }

    /**
     * 更新设备信息
     * @return {*}
     */
    static updateDeviceById(params) {
        return axios.post('device/update_device_by_id', params)
    }
}

/**
 * 地图管理用api
 */
export class Map {

    /**
     * 获取地图点数据
     * @return {*}
     */
    static getAllDevice() {
        return axios.get('/device/get_all_device')
    }


    /**
     * 获取区域点集合
     * @return {*}
     */
    static getPoints() {
        return axios.get('api/get_points')
    }
}



