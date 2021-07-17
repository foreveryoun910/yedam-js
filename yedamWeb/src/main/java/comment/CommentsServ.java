package comment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@WebServlet("/CommentsServ")
public class CommentsServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CommentsServ() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8"); // 페이지에 한글 포함돼있으면 설정해야 함!
		response.setCharacterEncoding("utf-8"); // 요청정보, 응답정보에 한글이 포함돼있을 때 깨지지 않도록 설정!
		
		PrintWriter out = response.getWriter();
		// Servlet 한개 안에서 파라미터를 받아서 이 페이지에서 모든 동작 처리하도록
		String cmd = request.getParameter("cmd"); // cmd = insert&id=? 이런 방식으로 값 줘서 여러가지 기능 수행하도록 할 듯?
		
		if(cmd == null) { // 파라미터 값이 아무것도 넘어오지 않으면
			StringBuffer sb = new StringBuffer();
			sb.append("<result>"); // xml 데이터를 보여줄 것이다
			sb.append("<code>error</code>"); // code 태그 안의 값을 보여줄 것이다?
			sb.append("<data>");
			sb.append("cmd null"); // 명령문이 없습니다
			sb.append("</data>");
			sb.append("</result>");
			out.print(sb.toString());
			// xml을 사용하는 이유: 정보를 String타입으로만 넣으면 구분이 어려우므로 태그로 묶어서 식별하기 쉽도록
			// json타입: {"code":"error", "data":"cmd null"} 도 사용 가능!
			
		} else if(cmd.equals("selectAll")) { // cmd가 selectAll으로 넘어오면 전체조회
			List<HashMap<String, Object>> list = CommentsDAO.getInstance().selectAll();
			out.print(selectAll(list));
		} else if(cmd.equals("insertComment")) {
			Comments comment = new Comments();
			comment.setName(request.getParameter("name"));
			comment.setContent(request.getParameter("content"));
			HashMap<String, Object> map = CommentsDAO.getInstance().insertComment(comment);
			out.println(toXML(map));
		} else if(cmd.equals("updateComment")) {
			Comments comment = new Comments();
			comment.setName(request.getParameter("name"));
			comment.setContent(request.getParameter("content"));
			HashMap<String, Object> map = CommentsDAO.getInstance().updateComment(comment);
			out.println(toXML(map));
		}
	} // end of doGet
	
	
	private String toXML(HashMap<String, Object>map) {
		StringBuffer sb = new StringBuffer();
		sb.append("<result>");
		sb.append("<code>");
		sb.append(map.get("code"));
		sb.append("</code>");
		sb.append("<data>");
		Gson gson = new GsonBuilder().create();
		sb.append(gson.toJson(map));
		sb.append("</data>");
		sb.append("</result>");
		return sb.toString();
	}


	private String selectAll(List<HashMap<String, Object>> list) {
		StringBuffer sb = new StringBuffer();
		sb.append("<result>");
		sb.append("<code>success</code>");
		sb.append("<data>");
		sb.append("[");
		for(int i=0; i<list.size(); i++) {
			HashMap<String, Object> map = list.get(i);
			sb.append("{ ");
			sb.append("id: " + map.get("id"));
			sb.append(", name: '" + map.get("name"));
			sb.append("', content: '" + map.get("content"));
			sb.append("' }");
			if(i != list.size()-1) { // {}, {} <- 이렇게 만들기. 마지막 요소는 제외.
				sb.append(", ");
			}
		}
		sb.append("]");
		sb.append("</data>");
		sb.append("</result>");
		
		return sb.toString(); // sb로 불러온 내용들은 StringBuffer 타입이니까? String으로 형변환?
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
