// tableList.js

let data = [{
        name: "이름",
        eng: "영어",
        math: "수학"
    }, {
        name: "홍길동",
        eng: "100",
        math: "90"
    }, {
        name: "김민수",
        eng: "90",
        math: "95"
    }
];



{/* <table border="1">
<tr>
    <td>이름</td>
    <td>영어</td>
    <td>수학</td>
</tr>
<tr>
    <td>홍길동</td>
    <td>100</td>
    <td>90</td>
</tr>
</table> */}




function createTable2() {
    let all = '<table border="1">';
    for(let row of data) {
        all += "<tr>";
        for(let field in row) {
            all += "<td>" + row[field] + "</td>";
        }
    }
}
all += '</table>'
document.write(all);


let all = "<h1>Hello</h1>"
document.write(all);


let tb = "<table border='1'>";
tb += '<tr><td>이름</td><td>영어</td><td>수학</td></tr>';
tb += '<tr><td>홍길동</td><td>100</td><td>90</td></tr>';
tb += '<tr><td>김개똥</td><td>100</td><td>90</td></tr>';
tb += '</table>';
document.write(tb);



function createTable() {
    let tableTag = document.createElement('table');
    tableTag.setAttribute('border', '1');
    for(let row of data) {
        let trTag = document.createElement('tr');
        for(let field in row) {
            let tdTag = document.createElement('td');
            if(field == name) {
                tdTag.style.backgroundColor = "red";
            }
            let text = document.createTextNode(row[field]);
            tdTag.appendChild(text); // <td/>
    
            trTag.appendChild(tdTag);
        }
        tableTag.appendChild(trTag);
    }
    document.getElementById('show').appendChild(tableTag);
}