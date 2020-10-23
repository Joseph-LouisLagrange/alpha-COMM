var wbt = new WBT({port: 8081, path: "/alpha"});
var ajaxAlpha = new AjaxAlpha({port: 8081, path: "/alpha"});


const REQUEST_TYPE = "REQUEST";
const RESPONSE_TYPE = "RESPONSE";
const ADVICE_TYPE= "ADVICE";


const SEND_MESSAGE = "SEND_MESSAGE";
const ADD_FRIEND = "ADD_FRIEND";
const REMOVE_FRIEND = "REMOVE_FRIEND";
const JOIN_GROUP = "JOIN_GROUP";
const LOGIN = "LOGIN";
const REGISTER = "REGISTER";


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

function BasicAuthenticateDataUnit(userName,password){
    this.userName=userName;
    this.password=password;
    this.type="AuthenticateDataUnit";
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

function SimpleServerEndPoint() {
    this.type="SimpleServerEndpoint";
}

function GroupEndPoint() {
    this.prototype = Endpoint;
}

function toAlpha(messageObject) {

}



//以下为触发函数
function basicLogin(userName , password){
    let body=new Body();
    let basicAuthenticateDataUnit=new BasicAuthenticateDataUnit(userName,password);
    body.addDataUnit(basicAuthenticateDataUnit);
    let alpha=new Alpha(1,new SimpleUserEndpoint(userName),new SimpleServerEndPoint()
    ,REQUEST_TYPE,LOGIN,null,body);
    httpSend(alpha);
}

function basicRegister(userName,password){
    let body=new Body();
    let basicAuthenticateDataUnit=new BasicAuthenticateDataUnit(userName,password);
    body.addDataUnit(basicAuthenticateDataUnit);
    let alpha=new Alpha(1,new SimpleUserEndpoint(userName),new SimpleServerEndPoint()
        ,REQUEST_TYPE,REGISTER,null,body);
    httpSend(alpha);
}


function httpSend(alpha){
    alpha.baseProtocol=HTTP_PROTOCOl;
    ajaxAlpha.send(JSON.stringify(alpha));
}

function wsSend(alpha){
    alpha.baseProtocol=WEBSOCKET_PROTOCOL;
    wbt.sendAlpha(JSON.stringify(alpha));
}




