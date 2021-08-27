package co.yedam.member.servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.yedam.member.model.MemberMapper;
import co.yedam.member.model.MemberServiceImpl;
import co.yedam.member.model.MemberVO;

@WebServlet("/MemberUpdate")
public class MemberUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberUpdate() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		
		MemberMapper dao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		vo.setId(request.getParameter("id"));
		vo.setName(request.getParameter("name"));
		vo.setPhone(request.getParameter("phone"));
		vo.setBirth(Date.valueOf(request.getParameter("birth")));
		vo.setAddress(request.getParameter("address"));
		dao.memberUpdate(vo);
		
		Gson gson = new GsonBuilder().create();
		response.getWriter().print(gson.toJson(vo));	
	}

}
