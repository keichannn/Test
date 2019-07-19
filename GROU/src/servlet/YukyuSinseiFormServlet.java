package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.YukyuSinseiDao;
import entity.JoinUserApp;
import entity.PaidApplication;
import entity.User_info;
import service.YukyuSinseiFormService;
import util.DbUtil;

/**
 * Servlet implementation class YukyuSinseiForm
 */
@WebServlet("/YukyuSinseiForm")
public class YukyuSinseiFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public YukyuSinseiFormServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();


		//備考欄の文字列取得
		String bikou = request.getParameter("bikou");

		if (bikou.isEmpty() || bikou == null) {
			request.setAttribute("errMsg", "備考を入力してください。");
			String date = (String) session.getAttribute("paidDay");
			YukyuSinseiDao yukyuSinseiDao = new YukyuSinseiDao(DbUtil.getConnection());
			//選択した日に有給申請されている人、許可されている人を表示
			List<JoinUserApp> listPaidUser = yukyuSinseiDao.findPaidUser(date);
			List<PaidApplication> listPaidApp = yukyuSinseiDao.findAllPaid();

			request.setAttribute("PaidApp", listPaidApp);
			request.setAttribute("paidUser", listPaidUser);
			System.out.println(listPaidApp);
			System.out.println(listPaidUser);
			request.getRequestDispatcher("sinseikahi.jsp").forward(request, response);

		} else {

			//user_id取得
			User_info ui = (User_info) session.getAttribute("userinfo");
			Integer userId = ui.getUser_id();

			//選択した日付を取得
			String selectDate = (String) session.getAttribute("paidDay");

			//現在の日付を取得
			java.util.Date d1 = new java.util.Date();

			// 表示形式を指定
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
			String nowDate = sdf2.format(d1);

			YukyuSinseiFormService ysfs = new YukyuSinseiFormService();
			ysfs.sinsei(nowDate, userId, selectDate, bikou);

			request.getRequestDispatcher("infomationChange.jsp").forward(request, response);
		}
	}

	public java.sql.Date convertToSqlDate(java.util.Date utilDate) {
		return new java.sql.Date(utilDate.getTime());
	}

}
