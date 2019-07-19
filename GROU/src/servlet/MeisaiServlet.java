package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Salary;
import entity.User_info;
import service.SalaryService;

/**
 * Servlet implementation class MeisaiServlet
 */
@WebServlet("/MeisaiServlet")
public class MeisaiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MeisaiServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String yearChangeUpStr = request.getParameter("yearUp");
		String yearChangeDownStr = request.getParameter("yearDown");
		String PM = request.getParameter("month");
		int pm = 0;
		if (PM != null) {
			pm = Integer.parseInt(PM);
		}
		request.setAttribute("payMonth", pm);

		//カレンダー表示

		Integer yearChange = (Integer) session.getAttribute("yearMeisai");

		if (yearChangeUpStr != null) {
			yearChange++;
			session.setAttribute("yearMeisai", yearChange);
		}

		if (yearChangeDownStr != null) {
			yearChange--;
			session.setAttribute("yearMeisai", yearChange);
		}

		//ユーザー情報
		User_info ui = (User_info) session.getAttribute("userinfo");
		int userId = ui.getUser_id();

		session.setAttribute("user", userId);
		System.out.println(userId);
		System.out.println(yearChange);
		System.out.println(pm);
		//ここまで

		//		給与表示
		SalaryService ss = new SalaryService();

		List<Salary> ls = ss.findYearMonth(yearChange,pm,userId);

		System.out.println(ls);

		if (ls.size() == 0) {
			request.setAttribute("error", "給与明細がありません");
			request.getRequestDispatcher("meisai.jsp").forward(request, response);
		}

		if (PM != null) {
			List<Salary> list = ss.findAllBySalary(userId, pm, yearChange);
			request.setAttribute("salary", list.get(0));
		}

		request.getRequestDispatcher("meisai.jsp").forward(request, response);
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
