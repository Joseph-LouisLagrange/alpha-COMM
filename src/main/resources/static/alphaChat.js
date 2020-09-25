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
    this.dataUnitList = new Array(1);
    this.addDataUnit = function (dataUnit) {
        this.dataUnitList.push(dataUnit);
    }
}

function DataUnit(contentType, content) {
    this.contentType = contentType;
    this.content = content;
}

function StringDataUnit(content) {
    this.contentType = "STRING";
    this.content = content;
}

StringDataUnit.prototype = DataUnit;

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
    let alpha = new Alpha(1, new Endpoint(), new Endpoint(),
        REQUEST_TYPE, SEND_MESSAGE, HTTP_PROTOCOl, new StringDataUnit("123456789"));
    ajaxAlpha.send(JSON.stringify(alpha));
    //获取文件,虚拟表单技术
    let formEl = document.getElementById("mainForm");
    console.info(formEl);
    let formData = new FormData(formEl);
    formData.append("name1", "value1")
    console.info(formData.get("file"));
    //ajaxAlpha.send(formData);
    // alpha.baseProtocol=WEBSOCKET_PROTOCOL;
    // wbt.sendAlpha(JSON.stringify(alpha));
}

function getAlpha() {
    var alphaDouble = {};
    let content = document.getElementById("textArea").value;
    return alphaDouble;
}


//如果在输入中包括文件或者图片，整个信息块采用二进制包发送


