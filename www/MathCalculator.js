var exec = require('cordova/exec');

//exports.coolMethod = function (arg0, success, error) {
//    exec(success, error, 'MathCalculator', 'coolMethod', [arg0]);
//};

var add = (arg0, success, error) => {
    exec(success, error, 'MathCalculator', 'add', [arg0]);
};

var subtract = (arg0, success, error) => {
    exec(success, error, 'MathCalculator', 'subtract', [arg0]);
};

module.exports = { add };
