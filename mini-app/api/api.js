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
}