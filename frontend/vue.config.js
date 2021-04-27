var path = require("path");

const resolve = url => {
    return path.join(__dirname, url);
};

module.exports = {
    runtimeCompiler: true,

    configureWebpack: {
        resolve: {
            alias: {
                "@": resolve("./src")
            },
            extensions: ["*", ".js", ".vue", ".json"]
        }
    },

    devServer: {
        proxy: {
            '/api': {
                target: "http://192.168.43.15:8090",
                changeOrigin: true,
                pathRewrite: {
                    '^/api': ''
                }
            }
        }
    },

    lintOnSave: false
};