import { request } from './request.js'


// 统计功能接口
export class Statistics {
	
	// 获取教学楼停车情况
	static getStopStatus() {
		return request({
			url: '/stop_status',
			method: 'get'
		})
	}
	static getAllDevice() {
		return request({
			url: '/device/get_all_device',
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
	
	static feedback(content,picture) {
		return request({
			url: '/user/complain',
			method: 'post',
			data: {
				content,
				picture
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
				orderBy: 0,
				top: true,
			}
		})
	}
}

