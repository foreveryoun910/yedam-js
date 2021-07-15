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


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public RegisterServlet() {
		super();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String eid = request.getParameter("eid");
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
		String email = request.getParameter("email");
		String hire_date = request.getParameter("hire_date");

		EmpDAO dao = new EmpDAO();
		Employee emp = new Employee();
		emp.setEmployeeId(Integer.parseInt(eid));
		emp.setFirstName(first_name);
		emp.setLastName(last_name);
		emp.setEmail(email);
		emp.setHireDate(hire_date);

		dao.insertEmp(emp);

		PrintWriter out = response.getWriter();
		Gson gson = new GsonBuilder().create();
		out.println(gson.toJson(emp));
//		out.println("{\"id\":" + emp.getEmployeeId()
//					+ ", \"first_name\":\"" + emp.getFirstName()
//					+ "\", \"last_name\":\"" + emp.getLastName()
//					+ "\", \"email\":\"" + emp.getEmail()
//					+ "\", \"hire_date\":\"" + emp.getHireDate() + "\"}");

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
