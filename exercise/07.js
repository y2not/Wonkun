var_ = require('ramda');
var compose = require('compose-function')

var words = function(str) {
    return_.split('',str);
}

function _each(list, func) {
    for (var i = 0, endi = list.length; i < endi; ++i) {
        func(list[i], i, list);
    }
}

function _rest(list, start) {
    var ret = [];
    start = start || 0
    _each(list, function (item, index, list) {
        if (index >= start) {
            ret.push(item);
        }
    })
    return ret;
}

function _reduce(list, iter, memo) {
    if (arguments.length === 2) {
        list = _rest(list, 1);
        memo = list[0];
    }
    _each(list, function (value) {
        memo = iter(memo, value);
    });
    return memo;
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

var compose = function (f, g) {
    return function(x) {
        return f(g(x));
    }
}

var toUpperCase = function(x) {
    return x.toUpperCase();
}

var exclaim = function(x) {
    return x + '!';
}

var shout = function(x) { return exclaim(toUpperCase(x)) };

console.log(shout("send in the clowns"))

var head = function(x) {
    return x[o];
}

var reverse = _reduce(function(acc, x){
    return [x].concat(acc);
}, [])

var last = compose(head, reverse)

console.log(last(['jumpkick', 'roundhouse', 'uppercut']))