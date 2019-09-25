// SQL 데이터베이스 연결을 위한 라이브러리 import
let Connection = require('tedious').Connection;
let Request = require('tedious').Request;
let TYPES = require('tedious').TYPES;

// 비동기 실행을 위한 라이브러리 import
let async = require('async');

// 공통 Function 정의 import 및 기능 정의
const customfunc = require('../common/customFunc');
const trim = customfunc.trim;

// SQL 데이터베이스 연결을 위한 접속 정보 import
// 정보 노출 방지를 위해 별도 분리 관리
const keys = require('../config/keys');

// 데이터 생성을 위한 쿼리
const QueryString = keys.cmpdb.countryQuery;

// 최대 표시 개수 정의
const maxlist = keys.cmpdb.maxResult;

// 데이터 재로딩 시간 정의
const refreshPeriod = keys.cmpdb.refreshSecond;

// 조회 결과가 없는 경우 표시할 항목
const nomoreitem = keys.cmpdb.noMoreItem;

// 초기 TimeStamp 변수 선언
let date = new Date();
let dateToTimestamp = date.getTime();
let timestampToDate = new Date(dateToTimestamp);

// resultCode를 지정할 전역변수 지정
let resultCode = [];

// Database 접속 정보
let config = {
    server: keys.cmpdb.server,
    // If you're on Windows Azure, you will need this:
    options: {encrypt: false},
    authentication: {
      type: "default",
      options: {
        userName: keys.cmpdb.userName,
        password: keys.cmpdb.clientSecret
      }
    }
   ,options: {
        debug: {
        packet: true,
        data: true,
        payload: true,
        token: false,
        log: true
        },
    database: keys.cmpdb.database,
    encrypt: false // for Azure users
    }
};
  
// 데이터베이스 접속 정보 선언
let connection = new Connection(config);

// Export된 SendResult 함수 (route 정보를 받아서 Client에 결과 전달)
exports.sendResult = function ( req, res, next ) {
    
    // Query 실행 후 지정된 시간이 지난 경우 data refresh 실행
    date = new Date();
    if (dateToTimestamp + refreshPeriod < date.getTime()) {

        // Data Refresh 실행 (비동기)
        async.waterfall([
            Read            
        ], Complete);

        // Timestamp 정보 갱신 
        date = new Date();
        dateToTimestamp = date.getTime();
        timestampToDate = new Date(dateToTimestamp);
        myTimer =  setTimeout(() => {
            console.log(timestampToDate+': Data has been refreshed.');
            makeResult( req, res );
        }, keys.cmpdb.lagTerm);
        
    } else {
        makeResult( req, res );
    };
};

// 데이터베이스를 읽어 생성한 결과를 Client의 Phrase 키워드로 필터를 적용하여 결과를 Client로 리턴 (함수로 분리)
function makeResult(req, res) {

    if ( req.query.phrase != undefined) {

        // Query String에 Phrase 항목이 포함되어 있는지 점검 후 실행, 포함이 안된 경우 Client에 오류 리턴
        // Query String에 next 정의가 안된 경우 첫 maxlist개 표시
        if (req.query.next == null || isNaN(req.query.next)) {
            const filtereddata = resultCode.filter(originalCode => originalCode.keyword[0].toUpperCase() === req.query.phrase.toUpperCase()
            || originalCode.keyword[1].toUpperCase().indexOf(req.query.phrase.toUpperCase()) != -1);
            const result = filtereddata.sort((now, next) => {return now.code < next.code ? -1 : now.code > next.code ? 1 : 0;}).slice(0, maxlist );

            // 결과가 배열의 끝이 아니면 next 값을 배열 아래에 push 처리, 끝인 경우는 nomoreitem 메시지 표시
            if ( maxlist > filtereddata.length ) {
                result.push(nomoreitem);
            } else {
                const moreitem = keys.cmpdb.nextItem;
                result.push(moreitem);
            };

            if (req.query.next === 'all') {
                res.set('Content-Type', 'application/json');
                res.status(200).send(filtereddata);
            } else {
                res.set('Content-Type', 'application/json');
                res.status(200).send(result);
            }
        // Query String에 지정된 keyword와 next 값에 의해 해당하는 배열의 maxlist개 표시
        } else {
            const filtereddata = resultCode.filter(originalCode => originalCode.keyword[0].toUpperCase() === req.query.phrase.toUpperCase()
            || originalCode.keyword[1].toUpperCase().indexOf(req.query.phrase.toUpperCase()) != -1);
            const result = filtereddata.sort((now, next) => {return now.code < next.code ? -1 : now.code > next.code ? 1 : 0;}).slice(req.query.next * maxlist - maxlist, req.query.next * maxlist );

            // 결과가 배열의 끝이 아니면 next 값을 배열 아래에 push 처리, 끝인 경우는 nomoreitem 메시지 표시
            if ( req.query.next * maxlist > filtereddata.length ) {
                result.push(nomoreitem);
            } else {
                const moreitem = { status : Number(req.query.next) + 1, message : 'next items'};
                result.push(moreitem);
            };
            // 결과를 Browser로 리턴
            res.set('Content-Type', 'application/json');
            res.status(200).send(result);
        } 
    } else {
        // phrase Param이 지정되지 않은 경우 에러 메시지 return
        res.status(400).send('Bad Request');
    }; 

}

// Database 데이터 쿼리
function Read(callback) {
    const date = new Date();
    const dateToTimestamp = date.getTime();
    const timestampToDate = new Date(dateToTimestamp);

    // Console display for activation of inquiry
    console.log(timestampToDate+': Reading rows from the Table...');

    // Read all rows from table
    request = new Request( QueryString,
    function(err, rowCount, rows) {
    if (err) { 
        console.error(err); 
    } else {
        console.log(timestampToDate+': '+rowCount + ' row(s) returned');
    }
    });

    // resultCode항목의 초기화 
    resultCode.length = 0;

    request.on('row', function(columns) {
        let codeString = '';
        let nameString = '';
        let addInfoString =[];

        //Parshing the column to make Jason Row
        columns.forEach(function(column) {
            if (column.value === null) {
            console.log('NULL');
            } else {
            if (column.metadata.colName === 'code') {
                codeString = trim(column.value);
            } else if (column.metadata.colName === 'name') {
                nameString = trim(column.value);
            } else if (column.metadata.colName === 'addInfo') {
                // addInfoString에 JSON형태의 Query 결과를 추가
                addInfoString = JSON.parse(column.value);
            };     
            }
        });

        resultCode.push({"code":codeString, "name":nameString, "keyword":[codeString, nameString], "additionalInfo":addInfoString});
        
        columnResult = [];
        // console.log('Result:' + resultCode)
    });

    connection.execSql(request);

}

// Database 처리완료
function Complete(err, result) {
    const date = new Date();
    const dateToTimestamp = date.getTime();
    const timestampToDate = new Date(dateToTimestamp);

    connection.on('close', function(err){
        if (err) {
            console.error(err); // 맨 마지막 callback function should be replaced to "console.error(err)";          
        } else {
            console.log(timestampToDate+": Database Inquiry is done!");
        }
    });
    console.log('처리완료 표시');
};

// Attempt to connect and execute queries if connection goes through
connection.on('connect', function(err) {
    if (err) {
        console.log(err);
    } else {
        console.log(timestampToDate+': Database has been connected');
    
        // Execute all functions in the array serially
        async.waterfall([
            Read
        ], Complete)
    
        // 비동기 처리된 resultCode 결과를 확인하기 위해서 5초 후 결과표시
        // setTimeout(() => console.log(resultCode), 5000);    
    }
});

//-------------------------------------------------------------------------------