// 基于Promise封装的请求
const BASE_URL = 'https://api.shawnxixi.icu'

export const request = (options) => {
	
	let header = {}
	try{
		let tokenKey = uni.getStorageSync('tokenKey') || null
		let tokenValue = uni.getStorageSync('tokenValue') || null
		header[tokenKey] = tokenValue
	}
	catch(err) {
		console.log(err)
	}
	finally{
		// Promise封装
		return new Promise((resolve, reject) => {
			uni.request({
				// url基地址
				url: BASE_URL + options.url,
				// 请求方法，默认get
				method: options.method || 'GET',
				// 请求体
				data: options.data || {},
				// 请求头
				header: header,
				// 请求成功回调
				success: res => {
					resolve(res)
				},
				// 请求失败回调
				fail: err => {
					reject(err)
				}
			})
		})
	}
	
}
