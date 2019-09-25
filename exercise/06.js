var_ = require('ramda');

var words = function(str) {
    return_.split('',str);
}

var sentences = undefined;

var filterQs = function(xs) {
    return _.filter(function(x){
        return matchMedia(/q/i,x);
    }, xs);
}

var _keyHighest = function(x, y) {
    return x >= y?x:y;
}

var max = function(xs) {
    return _.reduce(function(acc,x) {
        return _keepHighest(acc, x);
    }, -Infinity, xs);
}

var slice = undefined;

var take = undefined;