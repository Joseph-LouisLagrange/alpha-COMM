var wbt = new WBT({port: 8081, path: "/alpha"});
var ajaxAlpha = new AjaxAlpha({port: 8081, path: "/alpha"});

const REQUEST_TYPE = "REQUEST";
const RESPONSE_TYPE = "RESPONSE";
const SEND_MESSAGE = "SEND_MESSAGE";
const ADD_FRIEND = "ADD_FRIEND";
const REMOVE_FRIEND = "REMOVE_FRIEND";
const JOIN_GROUP = "JOIN_GROUP";
const WEBSOCKET_PROTOCOL = "WEBSOCKET";
const HTTP_PROTOCOl = "HTTP";

function Alpha(id, from, to, dataType, action, baseProtocol, body) {
    this.id = id;
    this.from = from;
    this.to = to;
    this.dataType = dataType;
    this.action = action;
    this.date = null;
    this.baseProtocol = baseProtocol;
    this.body = body;
}

function Body() {
    this.dataUnitList = new Array(0);
    this.addDataUnit = function (dataUnit) {
        this.dataUnitList.push(dataUnit);
    }
}

function DataUnit(contentType, content) {
    this.contentType = contentType;
    this.content = content;
}

function SimpleTextDataUnit(content) {
    this.type = "SimpleTextDataUnit";
    this.content = content;
}


function FileDataUnit(fileAttributes) {
    this.type = "FileDataUnit";
}


function Endpoint() {

}

function SimpleUserEndpoint(userName) {
    this.type = "SimpleUserEndpoint";
    this.userName = userName;
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
    let body = new Body();
    body.addDataUnit(new SimpleTextDataUnit("123456789"));
    body.addDataUnit(new FileDataUnit(null));
    let alpha = new Alpha(1, new SimpleUserEndpoint("用户名"), new SimpleUserEndpoint("目标用户", "密码"),
        REQUEST_TYPE, SEND_MESSAGE, HTTP_PROTOCOl, body);
    //console.info(JSON.stringify(alpha));
    // ajaxAlpha.send(JSON.stringify(alpha));
    //获取文件,虚拟表单技术
    let formEl = document.getElementById("mainForm");
    let formData = new FormData(formEl);
    formData.append("alpha", JSON.stringify(alpha));
    // console.info(formData.get("file"));
    ajaxAlpha.send(formData);
    // alpha.baseProtocol=WEBSOCKET_PROTOCOL;
    // wbt.sendAlpha(JSON.stringify(alpha));
}

function getAlpha() {
    var alphaDouble = {};
    let content = document.getElementById("textArea").value;
    return alphaDouble;
}


//如果在输入中包括文件或者图片，整个信息块采用二进制包发送


