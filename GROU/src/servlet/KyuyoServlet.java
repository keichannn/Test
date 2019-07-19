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

import dao.MeisaiDao;
import dao.UserInfoDao;
import entity.Salary;
import entity.User_info;
import util.DbUtil;

/**
 * Servlet implementation class KyuyoServlet
 */
@WebServlet("/KyuyoServlet")
public class KyuyoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public KyuyoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		//カレンダー表示
		Calendar year = Calendar.getInstance();
		session.setAttribute("yearMeisai", year.get(Calendar.YEAR));
		request.setAttribute("payMonth", year.get(Calendar.MONTH) + 1);
		//ここまで

		Integer month = (Integer) request.getAttribute("payMonth");
		Integer years = (Integer) session.getAttribute("yearMeisai");

		//仮ユーザー情報

		User_info ui = (User_info) session.getAttribute("userinfo");
		int userId = ui.getUser_id();

		UserInfoDao user = new UserInfoDao(DbUtil.getConnection());
		List<User_info> listUser = user.findId(userId);
		session.setAttribute("user", listUser.get(0));
		//ここまで

		//		給与表示
		MeisaiDao meisaiDao = new MeisaiDao(DbUtil.getConnection());

		if (month != null) {
			List<Salary> list = meisaiDao.findAllBySalary(userId, month, years);

			System.out.print(list.get(0).getMonth());

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
