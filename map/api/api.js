import { request } from './request.js'


// 统计功能接口
export class Statistics {
	static getAllDevice() {
		return request({
			url: '/device/get_all_device',
			method: 'get'
		})
	}
	static getPoints() {
		return request({
			url: '/api/get_points',
			method: 'get'
		})
	}
	static getAllParkingValue() {
		return request({
			url: '/api/stop_status',
			method: 'get'
		})
	}
	
	//按两小时获取停车情况
	static getStopStatusHourly() {
		return request({
			url: '/api/stop_status_hourly',
			method: 'get'
		})
	}
}

export class User {
	static login(code) {
		return request({
			url: '/user/login',
			method: 'post',
			data: {
				code
			}
		})
	}
	
	static feedback(username,content) {
		return request({
			url: '/user/complain',
			method: 'post',
			data: {
				username,
				content
			}
		})
	}
}

export class Notice {
	static getNoticeList() {
		return request({
			url: '/notice/get_notice_list',
			method: 'get',
			data: {
				page: 1,
				limit: 3,
			}
		})
	}
}

