exports.onRequest=function (res,method,pathname,params,cb) {
    const fs=require('fs');
    switch (method) {
      case "POST":
        const data1=fs.readFileSync('sample.json','utf-8')
        res.writeHead(200,{'Content-Type': 'text/json'})
        res.end(data1);
      /*
        return register(method, pathname, params,(response) => {
          process.nextTick(cb,res,response);
        });
        */
      case "GET":
        const data2=fs.readFileSync('sample2.json','utf-8')
        res.writeHead(200,{'Content-Type': 'text/json'})
        res.end(data2);
  
      case "DELETE":
        const data3=fs.readFileSync('sample3.json','utf-8')
        res.writeHead(200,{'Content-Type': 'text/json'})
        res.end(data3);
      /*
        return unregister(method, pathname,params,(response)=> {
          process.nextTick(cb,res,response);
        });
        */
      default:
        return process.nextTick(cb,res,null)
    }
  
  };  