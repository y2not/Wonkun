var Immutable = require('immutable');

var punch = function(player, target) {
    return target.set('hp',target.get('hp')-1)
}

var jobe = Immutable.Map({
    name:'Jobe',
    hp:20,
    team:'red'
})

var michael = Immutable.Map({
    name:'Michael',
    hp:20,
    team:'red'
})

console.log(punch(jobe, michael))