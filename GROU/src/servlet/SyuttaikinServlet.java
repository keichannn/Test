package servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserInfoDao;
import entity.Attendance;
import entity.User_info;
import service.AttendanceService;
import util.DbUtil;

/**
 * Servlet implementation class SyuttaikinServlet
 */
@WebServlet("/SyuttaikinServlet")
public class SyuttaikinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SyuttaikinServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		//月表示
		String yearStr = request.getParameter("year");
		String monthStr = request.getParameter("month");

		AttendanceService as = new AttendanceService();

		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar);

		int year = calendar.get(Calendar.YEAR);
		if(yearStr != null) {
			year = Integer.parseInt(yearStr);
		}
		request.setAttribute("year", year);

		int month = calendar.get(Calendar.MONTH) + 1;
		if (monthStr != null) {
			month = Integer.parseInt(monthStr);
		}
		request.setAttribute("month", month);


		//経理画面から見る場合の処理

		Integer id = (Integer) request.getAttribute("u_Id");


		if (id != null) {
			System.out.println("halk");
			UserInfoDao userInfoDao = new UserInfoDao(DbUtil.getConnection());
			List<User_info> users = userInfoDao.findId(id);
			request.setAttribute("user", users.get(0).getUser_name());
			List<Attendance> list = as.findAllByMonth(id, month);
			request.setAttribute("attendance", list);

		} else if (id == null) {

			System.out.println("iron");
			UserInfoDao user = new UserInfoDao(DbUtil.getConnection());
			User_info ui = (User_info) session.getAttribute("userinfo");
			int userId = ui.getUser_id();
			List<User_info> listUser = user.findId(userId);
			session.setAttribute("user", listUser.get(0).getUser_name());
			List<Attendance> list = as.findByYearAndMonth2(listUser.get(0).getUser_id(),year, month);
			request.setAttribute("attendance", list);
		}

		request.getRequestDispatcher("syuttaikinjoukyou.jsp").forward(request, response);
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
