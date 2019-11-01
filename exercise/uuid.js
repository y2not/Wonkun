const uuidv1 = require('uuid/v1');
const Keygrip = require('keygrip')();

upash.install('argon2', require('@phc/argon2'))

const uuidstr = uuidv1()
const keys = new Keygrip([uuidstr],'sha256','hex');

console.log('[api id]'+uudistr+'[credential]'+hashstr);