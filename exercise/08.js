var compose = require('compose-function')
var latin = compose(map(angry), reverse);

latin(['flog', 'eyes']);