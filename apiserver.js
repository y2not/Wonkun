const http=require('http');
const url=require('url');
const querystring=require('querystring');

const api=require('./api_execute.js');
const test=require('./api_test.js');
const manage=require('./api_manage.js');

var server=http.createServer((req,res)=> {
  var method= req.method;
  var uri=url.parse(req.url, true);
  var pathname=uri.pathname;

  if(method ==="POST"||method==="PUT") {
    var body="";

    req.on('data',function(data){
      body += data;
    });
    req.on('end',function(){
      var params;
      if (req.headers['content-type']=="application/json") {
        params=JSON.parse(body);
      } else {
        params=querysting.parse(body);
      }
      onRequest(res,method,pathname,params);
    });
  }else {
    onRequest(res, method, pathname, url.query);
  }
}).listen(8000);
console.log('Listening on Port 8000');

function onRequest(res,method,pathname,params) {
  switch(pathname) {
    case "/api":
      api.onRequest(res, method, pathname, params,response);
      break;
    case "/test":
      test.onRequest(res,method,pathname,params,response);
      break;
    case "/manage":
      manage.onRequest(res,method,pathname,params,response);
      break;
    default:
      res.writeHead(404);
      return res.end();
  }
}


function response(res, packet){
    res.writeHead(200,{'Content-Type': 'application/json'});
    res.end(JSON.stringify(packet));
}
