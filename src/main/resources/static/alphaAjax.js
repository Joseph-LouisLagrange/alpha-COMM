function AjaxAlpha(config) {
    const ip = config.host || location.hostname;
    const port = config.port || this.window.location.port;
    const protocol = config.protocol || location.protocol;
    const path = config.path || location.pathname;
    const get = "GET";
    const post = "POST";
    const url = protocol + "//" + ip + ":" + port + path;
    this.xmlhttp;
    if (window.XMLHttpRequest) {
        //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
        this.xmlhttp = new XMLHttpRequest();
    } else {
        // IE6, IE5 浏览器执行代码
        this.xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    this.xmlhttp.withCredentials = true;
    this.send = function (alpha) {
        this.xmlhttp.open(post, url, false);
        this.xmlhttp.send(alpha);
    }
}
