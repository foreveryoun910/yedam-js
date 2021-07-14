// employee.html
// json -> obj: JSON.parse()

let json = `[{"id":1,"first_name":"Karalee","last_name":"Jursch","email":"kjursch0@ed.gov","gender":"Male","ip_address":"121.59.9.197"},
{"id":2,"first_name":"Orly","last_name":"Carstairs","email":"ocarstairs1@fotki.com","gender":"Polygender","ip_address":"96.236.148.23"},
{"id":3,"first_name":"Vinnie","last_name":"Ledram","email":"vledram2@whitehouse.gov","gender":"Genderqueer","ip_address":"225.149.8.14"},
{"id":4,"first_name":"Phaedra","last_name":"Durbann","email":"pdurbann3@ed.gov","gender":"Female","ip_address":"26.11.77.96"},
{"id":5,"first_name":"Freida","last_name":"Dingwall","email":"fdingwall4@meetup.com","gender":"Genderfluid","ip_address":"109.127.248.152"},
{"id":6,"first_name":"Berkly","last_name":"Riply","email":"briply5@home.pl","gender":"Bigender","ip_address":"185.253.38.109"},
{"id":7,"first_name":"Kata","last_name":"Heephy","email":"kheephy6@ovh.net","gender":"Genderfluid","ip_address":"220.77.158.99"},
{"id":8,"first_name":"Celie","last_name":"Francesconi","email":"cfrancesconi7@slashdot.org","gender":"Genderqueer","ip_address":"49.253.129.156"},
{"id":9,"first_name":"Skipton","last_name":"Umbers","email":"sumbers8@wix.com","gender":"Polygender","ip_address":"75.153.45.246"},
{"id":10,"first_name":"Lyndsie","last_name":"Jessopp","email":"ljessopp9@1688.com","gender":"Polygender","ip_address":"173.42.85.48"}]`;

// json data가 string타입이라서 parsing을 해줬다.
let obj = JSON.parse(json);
// console.log(obj); // [{}, {}, {} .... {}]

// for (let row of obj) {
//     console.log(row);
// }

let tableTag = document.createElement('table');

function createTable() {
    tableTag.setAttribute('border', '1');

    // ~~~~~~~집에 가서 해보기~~~~~~~
    // let trTag = document.createElement('tr');
    // let tdTag = document.createElement('td').colSpan'5';
    // colSpan 쓰는 법: alert(document.getElementById("td").colSpan); 
    // let text = document.createTextNode('엥');
    // tdTag.appendChild(text);
    // trTag.appendChild(tdTag);

    let caption = document.createElement('caption');
    caption.appendChild(document.createTextNode('회원리스트'));
    tableTag.appendChild(caption);

    createHeader2(); // 타이틀(첫째행) 보여주기
    for (let row of obj) {
        let trTag = document.createElement('tr');
        trTag.onmouseover = changeColor; // click event가 발생할 때 실행할 함수 -> () 안 써줌
        trTag.onmouseout = originColor;
        trTag.onclick = showRow;
        //onclick: click event 발생
        //onmouseover: mouse가 지나가면 event 발생

            for (let field in row) {
                let tdTag = document.createElement('td');
                let text = document.createTextNode(row[field]);
                tdTag.appendChild(text);

                trTag.appendChild(tdTag);
            }
        
           
        // <td><button>삭제</button></td> // 버튼을 추가

        let btn = document.createElement('button');
        btn.onclick = deleteRowId;
        let text = document.createTextNode('삭제');
        btn.appendChild(text);
        let tdTag = document.createElement('td');
        tdTag.appendChild(btn);
        trTag.appendChild(tdTag);

        tableTag.appendChild(trTag);
    }
    document.getElementById('show').appendChild(tableTag);
}



function showRow() {
    let inputs = document.getElementsByTagName('input');
    // document.querySelectorAll();
    console.log(inputs);
    for (let i=0; i<inputs.length; i++) {
        inputs[i].value = this.childNodes[i].childNodes[0].nodeValue;
    }

    // let id = this.childNodes[0].childNodes[0].nodeValue;
    // let first_name = this.childNodes[1].childNodes[0].nodeValue;
    // let last_name = this.childNodes[2].childNodes[0].nodeValue;
    // let email = this.childNodes[3].childNodes[0].nodeValue;
    // let gender = this.childNodes[4].childNodes[0].nodeValue;
    // let ip_address = this.childNodes[5].childNodes[0].nodeValue;

    // document.getElementById('eid').value = id;
    // document.getElementById('first_name').value = first_name;
    // document.getElementById('last_name').value = last_name;
    // document.getElementById('email').value = email;
    // document.getElementById('gender').value = gender;
    // document.getElementById('ip_address').value = ip_address;   
}




function addRow() { // 사용자의 입력값을 받아서 테이블에 행 추가
    let id = document.getElementById('eid').value;
    let first_name = document.getElementById('first_name').value;
    let last_name = document.getElementById('last_name').value;
    let email = document.getElementById('email').value;
    let gender = document.getElementById('gender').value;
    let ip_address = document.getElementById('ip_address').value;
    let ary  = [id, first_name, last_name, email, gender, ip_address];

    let trTag = document.createElement('tr');
    trTag.onmouseover = changeColor; // click event가 발생할 때 실행할 함수 -> () 안 써줌
    trTag.onmouseout = originColor;
    for (let f of ary) {
        let tdTag = document.createElement('td');
        let text = document.createTextNode(f);
        tdTag.appendChild(text);
        trTag.appendChild(tdTag);
    }

    let btn = document.createElement('button');
    btn.onclick = deleteRowId;
    let text = document.createTextNode('삭제');
    btn.appendChild(text);
    let tdTag = document.createElement('td');
    tdTag.appendChild(btn);
    trTag.appendChild(tdTag);

    document.getElementsByTagName('table')[0].appendChild(trTag);
}




function deleteRow() { // 한 라인 삭제
    console.log(this.parentNode.parentNode.remove());
             // this.tr단위.td단위.remove();
}


function deleteRowId() { // id를 기준으로 데이터 삭제
    // 화면에서 삭제
    let id = this.parentNode.parentNode.childNodes[0].childNodes[0].nodeValue; // 해당 node의 값과 데이터의 값을 비교하기 위해 nodeValue로 값 가져오기
    console.log(id);
    this.parentNode.parentNode.remove();

    // 실제 데이터 삭제
    for (let i=0; i<obj.length; i++) {
        if (obj[i].id == parseInt(id)) { // 6 == '6': true
            obj.splice(i, 1); // delete obj[i]; : delete는 해당 데이터만 삭제한다?
            // 배열메소드 삭제 : splice() ?
            // i부터 1개만 지우겠다는 뜻, 2개 지우려면 1 자리에 2 넣으면 됨
            // 삭제하고 배열 가운데 텅 비어있는 게 아니라 배열 자체가 지워지니까 빵꾸 안 생김
            break;
        }
    }
    console.log(obj);
}



function originColor() { // 원래 색으로 변경
    this.style.backgroundColor = '';
}

function changeColor() {
    this.style.backgroundColor = 'yellow'; // this: 클릭이벤트가 발생하는 요소
    //this.childNodes[1].childNodes[0]
}

function clickFunc() {
    this.style.backgroundColor = 'yellow'; // this: 클릭이벤트가 발생하는 요소
    //this.childNodes[1].childNodes[0]
}



function createHeader2() {
    let titles = ['id', 'first_name', 'last_name', 'email', 'gender', 'ip_address', 'del'];
    let tr = document.createElement('tr');
    for (let field of titles) { // title은 배열이라서 of
        let th = document.createElement('th');
        let text = document.createTextNode(field);
        th.appendChild(text);
        tr.appendChild(th);
    }
    tableTag.appendChild(tr);
}

function createHeader() {
    let row = obj[0];
    let tr = document.createElement('tr');
    for (let field in row) { // object에서 field 가져오는 거라서 in
        let th = document.createElement('th');
        let text = document.createTextNode(field);
        th.appendChild(text);
        tr.appendChild(th);
    }
    tableTag.appendChild(tr);
}


createTable();