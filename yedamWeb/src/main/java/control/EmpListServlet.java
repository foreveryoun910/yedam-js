package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import common.EmpDAO;
import common.Employee;


@WebServlet("/EmpListServlet")
public class EmpListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public EmpListServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		// out.println("{\"id\":1, \"first_name\":\"Hong\",\"last_name\":\"kildong\"}"); 
		// {"id":1, "first_name":"Hong","last_name":"kildong"} <- JSON 타입 데이터
		

		
		EmpDAO dao = new EmpDAO();
		List<Employee> list = dao.getEmpList();
		Gson gson = new GsonBuilder().create();
		out.println(gson.toJson(list));
//		out.println("[");
//		int cnt = 1;
//		for(Employee emp : list) {
//			out.println("{\"id\":" + emp.getEmployeeId()
//					+ ", \"first_name\":\"" + emp.getFirstName()
//					+ "\", \"last_name\":\"" + emp.getLastName()
//					+ "\", \"email\":\"" + emp.getEmail()
//					+ "\", \"hire_date\":\"" + emp.getHireDate() + "\"}");
//	
//			if(cnt++ != list.size()) {
//				out.println(",");				
//			}
//		}
//		out.println("]");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}




