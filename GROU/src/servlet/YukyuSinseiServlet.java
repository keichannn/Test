package servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
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
import entity.Paid;
import entity.PaidApplication;
import entity.User_info;
import service.YukyuSinseiService;
import util.DbUtil;

/**
 * Servlet implementation class YukyuSinseiServlet
 */
@WebServlet("/YukyuSinseiServlet")
public class YukyuSinseiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public YukyuSinseiServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		YukyuSinseiService yss = new YukyuSinseiService();


		User_info ui = (User_info) session.getAttribute("userinfo");
		int userId = ui.getUser_id();



		//有給残り日数
//		PaidService ps = new PaidService();
		List<Paid> list = yss.findPaid(userId);
		if(list.size() == 0) {
			request.setAttribute("yukyu", 0);
		}else {
			request.setAttribute("yukyu", list.get(0));
		}

		//

		//選択した日付を表示
		String paidDay = (String) request.getParameter("paidDay");
		session.setAttribute("paidDay", paidDay);
		//

		//選択した日に有給申請されている人、許可されている人を表示
		List<JoinUserApp> listPaidUser = yss.findPaidUser(paidDay);
		List<PaidApplication> listPaidApp = yss.findAllPaid();

		request.setAttribute("PaidApp", listPaidApp);
		System.out.println(listPaidApp.get(0).getAppli_states());
		request.setAttribute("paidUser", listPaidUser);
		request.getRequestDispatcher("sinseikahi.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		YukyuSinseiService yss = new YukyuSinseiService();

		String date = (String) session.getAttribute("paidDay");
		System.out.println(date);

//		YukyuSinseiDao ysd = new YukyuSinseiDao(DbUtil.getConnection());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date formatDate = null;
		try {
			formatDate = convertToSqlDate(sdf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(formatDate);

		String vals[] = request.getParameterValues("idbutton");
		System.out.println(vals[0]);
	    String val = vals[0];
	    System.out.println(val);
	    System.out.println(val.substring(0, 3));
	    System.out.println(val.substring(val.length() - 1));



		int id = Integer.parseInt(val.substring(0, 3));
		int kahi = Integer.parseInt(val.substring(val.length() - 1));

		if (kahi == 2) {
			//認証が押されたらokに承認された人の名前が入っている
			yss.ninsyo(id, formatDate);

		}else{
			//非認証が押されたらngに非承認された人の名前が入っている

			yss.hiNinsyo(id, formatDate);

		}
		YukyuSinseiDao yukyuSinseiDao = new YukyuSinseiDao(DbUtil.getConnection());
		//選択した日に有給申請されている人、許可されている人を表示
		List<JoinUserApp> listPaidUser = yukyuSinseiDao.findPaidUser(date);
		List<PaidApplication> listPaidApp = yukyuSinseiDao.findAllPaid();

		request.setAttribute("PaidApp", listPaidApp);
		request.setAttribute("paidUser", listPaidUser);
		request.getRequestDispatcher("sinseikahi.jsp").forward(request, response);
	}
	public java.sql.Date convertToSqlDate(java.util.Date utilDate){
	    return new java.sql.Date(utilDate.getTime());
	}

}
