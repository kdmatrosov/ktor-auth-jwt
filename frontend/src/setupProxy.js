const proxy = require('http-proxy-middleware');

module.exports = function (app) {
    app.use(proxy('/api', {
        target: 'http://0.0.0.0:8080/', pathRewrite: function (path) {
            return path.replace('/api', '')
        }
    }));
};
