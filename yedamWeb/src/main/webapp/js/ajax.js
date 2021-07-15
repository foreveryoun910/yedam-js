/*
 * ajax.js
 */
function ajaxFuc() {

	let xhtp = new XMLHttpRequest();
	xhtp.open("get", "EmpListServlet");
	xhtp.send();
	xhtp.onreadystatechange = function() {
		if(xhtp.readyState == 4 && xhtp.status == 200) {
			let data = JSON.parse(xhtp.responseText);
			console.log(data);
		}
		//console.log(xhtp.readyState, xhtp.status);
	}
}
ajaxFuc();