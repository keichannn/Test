package servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.User_info;
import service.SyukkinService;
import util.ParamUtil;

/**
 * Servlet implementation class SyukkinServlet
 */
@WebServlet("/SyukkinServlet")
public class SyukkinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SyukkinServlet() {
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

		User_info ui = (User_info) session.getAttribute("userinfo");
		int userId = ui.getUser_id();

		String str = request.getParameter("syukkin");
		//現在の時刻取得
		Calendar cTime = Calendar.getInstance();
		//時間の取得
		int hour = cTime.get(Calendar.HOUR_OF_DAY);
		//分の取得
		int min = cTime.get(Calendar.MINUTE);
		//年の取得
		int year = cTime.get(Calendar.YEAR);
		//月の取得
		int month = cTime.get(Calendar.MONTH) + 1;
		//日の取得
		int day = cTime.get(Calendar.DATE);

		//曜日の取得
		int week = cTime.get(Calendar.DAY_OF_WEEK) - 1;
		String[] week_name = { "日", "月", "火", "水", "木", "金", "土" };

		String message = null;
		String workTime = null;
		//		SyukkinDao sd = new SyukkinDao(DbUtil.getConnection());
		SyukkinService ss = new SyukkinService();

		//出勤か、退勤が押されたらこれ
		//休憩だとelseの処理
		if (str.equals("出勤") || str.equals("退勤")) {
			workTime = ParamUtil.getStringDate(hour, min);

			//出勤なら出勤時間をDBに登録して"出勤しました"メッセージをmessageへ
			//退勤なら退勤時間をDBに登録して"退勤しました"メッセージをmessageへ
			if (str.equals("出勤")) {
				message = ss.syukkin(userId, year, month, day, week_name[week], workTime);
			} else {
				message = ss.taikin(userId, year, month, day, workTime);
			}
			//messageをリクエストスコープへ
			request.setAttribute("message", message);
		} else {
			String breakTime = request.getParameter("kyuukei");
			//DBに休憩時間登録
			String kyukei = ss.kyuukei(userId, year, month, day, breakTime);
			if (ParamUtil.isNullOrEmpty(kyukei)) {
				message = "出勤していません。";
			} else {
				message = breakTime + "分" + kyukei;
			}
			//messageをリクエストスコープへ
			request.setAttribute("message", message);
			System.out.println(request.getParameter("kyuukei"));
		}
		System.out.print(week_name[week] + " " + hour + " : ");
		System.out.println(message);
		request.getRequestDispatcher("/syukkin.jsp").forward(request, response);
	}

}
