// const fs = require('fs');

const express = require('express');

const app = express();

const routes = require('./api/v1/routes/route');

// Public Html 위치 
app.use(express.static('public'));

// App 호출 시 Html을 출력
// app.use(bodyParser.json());

// *****************
// Log 기록 모듈 정의
// morgan 
// 매번 새로운 log 파일이 생성됨 -> 수정 필요
var logger = require('morgan');
var fs = require('fs');

app.use(logger('dev', {stream:
    fs.createWriteStream('./logs/app.log', { flags: 'w' })
}));

// API 호출 보안 관련하여 Access Control 허용 (모든 사이트)
var cors = require('cors');
app.use(cors());

// 위의 방식으로 대체됨
// app.all('/*', function(req, res, next) {
//     res.header("Access-Control-Allow-Origin", "*");
//     res.header("Access-Control-Allow-Headers", "X-Requested-With");
//     next();
// });

// /api/v1 URI 호출 시 routes 처리 -> const routes로 지정

app.use('/api/v1', routes);

// App 호출 시 Error Handling
app.use( (err, req, res, next) => {
    res.status(422).send({error: err.message})
});

app.listen(process.env.port||3000, () => {
    console.log('now listening for request on port 3000');
});