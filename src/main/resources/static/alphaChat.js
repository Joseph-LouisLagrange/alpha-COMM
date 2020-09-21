var wbt = new WBT({port: 8081, path: "/alpha"});
var ajaxAlpha = new AjaxAlpha({port: 8081, path: "/alpha"});

function Alpha(id, from, to, dataType, action, date, body) {
    this.id = id;
    this.from = from;
    this.to = to;
    this.dataType = dataType;
    this.action = action;
    this.date = date;
    this.body = body;
}

function Body() {
    this.dataUnitList = new Array(1);
    this.addDataUnit = function (dataUnit) {
        this.dataUnitList.push(dataUnit);
    }
}

function Endpoint() {

}

function PersonEndPoint(userName, password) {
    this.prototype = Endpoint;
    this.username;
    this.password = password;
}

function ServerEndPoint() {
    this.prototype = Endpoint;
}

function GroupEndPoint() {
    this.prototype = Endpoint;
}

function toAlpha(messageObject) {

}

function send() {
    ajaxAlpha.send("ABC");
}

function getAlpha() {
    var alphaDouble = {};
    let content = document.getElementById("textArea").value;
    return alphaDouble;
}


//如果在输入中包括文件或者图片，整个信息块采用二进制包发送


