var curry = require('lodash/curry')

var match = curry(function(what, str) {
    return str.match(what);
})

var replace = curry(function(what, replacement, str) {
    return str.replace(what, replacement);
})

var filter = curry(function(f, ary) {
    return ary.filter(f);
})

var map = curry(function(f, ary) {
    return ary.map(f);
})


console.log(match(/\s+/g, 'hello world'));

console.log(match(/\s+/g)('hello world'));

var hasSpaces = match(/\s+/g);

console.log(hasSpaces('hello world'));

console.log(hasSpaces('spaceless'));

console.log(filter(hasSpaces, ['tori_spelling', 'tori amos']));

var findSpaces = filter(hasSpaces);

console.log(findSpaces(['tori_spelling', 'tori amos']));

var noVowels = replace(/[aeiouy]/ig);

var censored = noVowels('*');

// 'Ch*c*l*t* R**n'
console.log(censored('Chocolate Rain'));
