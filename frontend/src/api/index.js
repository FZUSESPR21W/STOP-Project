import * as api from './api.js'

export default {
    install: Vue =>
    {
        Vue.prototype.$api = api;
    }
}

export { api }