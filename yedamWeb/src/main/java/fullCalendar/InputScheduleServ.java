package fullCalendar;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/InputScheduleServ")
public class InputScheduleServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public InputScheduleServ() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파라미터를 통해서 사용자 입력값 받아오기
		FullCalendar cal = new FullCalendar();
		
		cal.setTitle(request.getParameter("title"));
		cal.setStartDate(request.getParameter("start"));
		cal.setEndDate(request.getParameter("end"));
		
		CalDAO dao = new CalDAO();
		String val = dao.insertSchedule(cal);
		response.getWriter().print(val);
		// 정상처리 -> success
		// 비정상처리 -> fail
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
