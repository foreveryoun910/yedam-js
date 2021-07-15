package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import common.EmpDAO;
import common.Employee;


@WebServlet("/ModifyServlet")
public class ModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ModifyServlet() {
        super();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//입력요청된 정보를 읽어옴
		String eid = request.getParameter("eid");
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
		String email = request.getParameter("email");
		String hire_date = request.getParameter("hire_date");
		
		Employee emp = new Employee(); // updateEmp()의 매개값으로 사용
		emp.setEmployeeId(Integer.parseInt(eid));
		emp.setFirstName(first_name);
		emp.setLastName(last_name);
		emp.setEmail(email);
		emp.setHireDate(hire_date);
		
		EmpDAO dao = new EmpDAO();
		dao.updateEmp(emp);
		Gson gson = new GsonBuilder().create();
		
		//PrintWriter out = response.getWriter();
		response.getWriter().println(gson.toJson(emp)); // json 타입으로 결과 반환
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
