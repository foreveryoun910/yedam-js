// function.js

// 자바스크립트에서는 타입이 별로 의미가 x
// 변수 이름만 써주면 됨

function plus() {
    let i1 = document.getElementById('num1'); // input
    let i2 = document.getElementById('num2'); // input
    let result = document.getElementById('result');
    result.value = sum(parseInt(i1.value), parseInt(i2.value));
}


function sum(v1, v2) {
    return v1 + v2;
}

console.log(sum('10', 20)); // string 더하기 연산 => concatenate


// 익명함수에 기능만 넣어주기?
var fnc = function sum(v1, v2) {
    return v1 +  v2;
}

console.log(fnc);
console.log(fnc()); // NaN: Not a Number
console.log(fnc(10, 20));
console.log(fnc(10, 20, 30)); // 변수에 설정한 개수만큼만 연산(앞에서부터인 듯?)