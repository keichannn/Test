package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.JoinUserApp;
import entity.Paid;
import entity.PaidApplication;
import entity.User_info;
import service.PaidService;

/**
 * Servlet implementation class CalendarServlet
 */
@WebServlet("/CalendarServlet")
public class CalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CalendarServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		User_info ui = (User_info) session.getAttribute("userinfo");
		int userId = ui.getUser_id();

		//有給残り日数
		PaidService ps = new PaidService();
		List<Paid> list = ps.findPaid(userId);

		if (list.size() == 0) {

			session.setAttribute("yukyu", 0);
			System.out.println("aaa");
		} else {
			session.setAttribute("yukyu", list.get(0).getRemaining());
		}

		//

		//選択した日付を表示
		String paidDay = (String) request.getParameter("paidDay");
		request.setAttribute("paidDay", paidDay);
		//

		//選択した日に有給申請されている人、許可されている人を表示
		List<JoinUserApp> listPaidUser = ps.findPaidUser(paidDay);
		List<PaidApplication> listPaidApp = ps.findAllPaid();

		request.setAttribute("PaidApp", listPaidApp);

		request.setAttribute("paidUser", listPaidUser);

		request.getRequestDispatcher("yukyusinsei.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
