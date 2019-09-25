new Vue({
    el:'#vue-app',
    data: {
        name: 'Shaun',
        job: 'Job',
        age:20,
        website: 'http://www.chosun.com',
        websitetag: '<a href="http://www.chosun.com">The Ninja</a>',
        x:0,
        y:0
    },
    methods: {
        greet: function(time) {
            
            return 'Good ' + time + ' ' + this.name
        },
        add: function(inc) {
            this.age+=inc
        },
        substract: function(dec) {
            this.age-=dec
        },
        updateXY: function(event) {
            this.x = event.offsetX
            this.y = event.offsetY
        }
    }
});