// variable.js
let age = 10;
console.log(age);

age = 20;
console.log(age);
// 변수를 선언할 때: let (예전 버전: var)

const PI = 3.1415;
// PI = 3.2;
// 상수를 선언할 때: const, 재할당이 불가능

{
    let age = 30;
    console.log(age);
    // 블록을 새로 만들어서 선언해주면 재할당 가능
    // let은 블록 단위로 선언하는 변수?
}
console.log(age);

let v1; // undefined
v1 = 'Hello'; // string
v1 = 100.45; // number
v1 = true; // boolean
v1 = null; // object
v1 = [1, 2, 3]; // 배열: object
v1 = {
    name: 'Hong',
    age: 20,
    score: 80
} // object

v1 = function(a, b) {
    return a + b;
} // function
console.log(typeof v1);
// typeof: 변수의 타입을 출력하고 싶을 때

console.log(v1(10, 20));

v1 = 10 > 20;
v1 = 40; // 값이 있으면 참
v1 = 0; // 0, null, false => 거짓

if('Hello') {
    console.log('참입니다.');
} else {
    console.log('거짓입니다.');
}