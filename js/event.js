// event.js

console.log(this); // window object가 가진 function 목록
function changeValue() {
    // id="num3" value속성을 읽어와서 그 값의 upperCase 변환
    let elem = document.getElementById('num3').value;
    // console.log(elem);
    // console.log(elem.toUpperCase());
    document.getElementById('num3').value = elem.toUpperCase();
}

let obj = {
    name: "Hong",
    hobby: ["running", "eating", "sleeping"],
    pet: [{
        dog: "뚱순이"
    }, {
        cat: "야옹이"
    }, {
        rabbit: "토순이"
    }]
}

console.log(obj.name);
console.log(obj.hobby[0]);
console.log(obj['hobby'][1]);
console.log(obj.pet[0].dog);
console.log(obj.pet[1]);


// 요소를 생성, element: 태그
let fruits = ['Apple', 'Orange', 'Mango', 'Melon']; // 전역변수

function addFruit() {
    let addVal = document.getElementById('add').value;
    fruits[fruits.length] = addVal;
    document.getElementById('add').value = null;
    removeFromDoc();
    createElements();
}

function createElements() {
    // let ulTag = document.getElementsByTagName('ul');
    let ulTag = document.createElement('ul');
    ulTag.setAttribute('id', 'fruit');
    for (let fruit of fruits) {
        // console.log(fruit);
        let liTag = document.createElement('li');
        let f = document.createTextNode(fruit);
        liTag.appendChild(f);
        // ulTag[0].appendChild(liTag);
        ulTag.appendChild(liTag);
    }
    document.body.appendChild(ulTag);
}

function removeFromDoc() {
    if (document.getElementById('fruit')) { // document.getElementById('fruit') => null
        document.getElementById('fruit').remove(); // <ul id='fruit'></ul>
    }
}


/*
let ulTag = document.getElementsByTagName('ul');

let liTag = document.createElement('li'); // <li>Apple</li>
let apple = document.createTextNode('Apple');
liTag.appendChild(apple);
ulTag[0].appendChild(liTag); // <ul><li>Apple</li></ul>

liTag = document.createElement('li');
let orange = document.createTextNode('Orange');
liTag.appendChild(orange);
ulTag[0].appendChild(liTag); // <ul><li>Apple</li><li>Orange</li></ul>

liTag = document.createElement('li');
let grape = document.createTextNode('Grape');
liTag.appendChild(grape);
ulTag[1].appendChild(liTag);
*/