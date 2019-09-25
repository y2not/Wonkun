// 목적: Master code에 대한 auto complete 기능 구현
// api main file for auto complete

// http서버 실행을 위한 express 모듈 (외부) import - "npm install express --save 필요"
const express = require ('express');

// express의 router기능 상수 선언 - 이후 router를 통해 get, post등의 호출 항목에 따른 실행 기능을 routing함
const router = express.Router();

// Country Code 호출에 대한 Router
const countryInquiry = require('./countryInq');
router.get('/country', (req, res, next) => { countryInquiry.sendResult(req, res, next); }) ;

// Subdivision(State, Province) Code 호출에 대한 Router
const subdivInquiry = require('./subdivInq');
router.get('/subdiv', (req, res, next) => { subdivInquiry.sendResult(req, res, next); });

// Location Code 호출에 대한 Router
const locationInquiry = require('./locationInq');
router.get('/location', (req, res, next) => { locationInquiry.sendResult(req, res, next); });

// Organization 호출에 대한 Router
const organizationInquiry = require('./organizationInq');
router.get('/organization', (req, res, next) => { organizationInquiry.sendResult(req, res, next); });

// User 호출에 대한 Router
const userInquiry = require('./userInq');
router.get('/user', (req, res, next) => { userInquiry.sendResult(req, res, next); });

// Service 호출에 대한 Router
const serviceInquiry = require('./serviceInq');
router.get('/service', (req, res, next) => { serviceInquiry.sendResult(req, res, next); });

// Subscription 호출에 대한 Router
const subscriptionInquiry = require('./subscriptionInq');
router.get('/subscription', (req, res, next) => { subscriptionInquiry.sendResult(req, res, next); });

// Billing Policy 호출에 대한 Router
const billPolicyInquiry = require('./billPolicyInq');
router.get('/billPolicy', (req, res, next) => { billPolicyInquiry.sendResult(req, res, next); });

module.exports = router;