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


function createTable() {
    let tagTable = document.createElement('table');
    tagTable.setAttribute('border', '1');
    for (let row of obj) {
        let tagTr = document.createElement('tr');
        for (let field in row) {
            let tagTd = document.createElement('td');
            let text = document.createTextNode(row[field]);
            tagTd.appendChild(text);

            tagTr.appendChild(tagTd);
        }
        tagTable.appendChild(tagTr);
    }
    document.getElementById('show').appendChild(tagTable);
}

createTable();