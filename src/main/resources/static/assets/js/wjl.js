$(function () {
    NProgress.configure({ easing: 'ease', speed: 500, showSpinner: false })
    NProgress.start();
})
$(window).load(function(){
    NProgress.done();
})
$(window).ajaxStart(function () {
    NProgress.start();
});

$(window).ajaxStop(function () {
    NProgress.done();
});
axios.interceptors.request.use(function(config){
    NProgress.start();
    return config;
},function(err){
    NProgress.done();
    return Promise.reject(error);
});
//添加一个响应拦截器
axios.interceptors.response.use(function(res){
    NProgress.done();
    return res;
},function(err){
    NProgress.done();
    return Promise.reject(error);
})