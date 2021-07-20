package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import common.EmpDAO;
import common.Employee;


@WebServlet("/EmpJsonServ2")
public class EmpJsonServ2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public EmpJsonServ2() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// { "data":[{ }, { }, { }, { }] } 형태 // 활용데이터: https://datatables.net/examples/ajax/objects.html
		PrintWriter out = response.getWriter();
		Gson gson = new Gson(); // 순서 없이 출력됨
		new GsonBuilder().create(); // 순서 의미를 갖고 출력됨
		
		EmpDAO dao = new EmpDAO();
		List<Employee> list = dao.getEmpList();
		JsonArray oAry = new JsonArray();
		for (Employee emp : list) {
			JsonObject iObj = new JsonObject();
			iObj.addProperty("eid", emp.getEmployeeId());
			iObj.addProperty("firstName", emp.getFirstName());
			iObj.addProperty("lastName", emp.getLastName());
			iObj.addProperty("email", emp.getEmail());
			iObj.addProperty("hireDate", emp.getHireDate());
			iObj.addProperty("salary", emp.getSalary());
			
			oAry.add(iObj);
		}
		JsonObject obj = new JsonObject(); // key:value? 형식으로 만들어주는 기능?
		obj.add("data", oAry); // data라는 항목으로 oAry가 들어간다?
		
		out.println(gson.toJson(obj));
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
