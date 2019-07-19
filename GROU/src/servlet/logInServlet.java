package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserInfoDao;
import entity.User_info;
import util.ParamUtil;

/**
 * Servlet implementation class logInServlet
 */
@WebServlet("/logInServlet")
public class logInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public logInServlet() {
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

		// 入力値取得
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");

		// ID入力値チェック
		if (ParamUtil.isNullOrEmpty(id) || ParamUtil.isNullOrEmpty(pass)) {
			request.setAttribute("errMsg", "IDもしくはPasswordが入力されていません");
			request.getRequestDispatcher("/login.jsp").forward(request, response);

		}

		UserInfoDao uid = new UserInfoDao();
		User_info ui = uid.findByLoginIdAndPassword(id, pass);
		try {

			if (ui.getUser_id() != null) {

				//テスト
				System.out.println("↓ログインした際のRole_id↓");
				System.out.println(ui.getRole_id());

				session.setAttribute("userinfo", ui);
				request.getRequestDispatcher("/myPage.jsp").forward(request, response);
			}

		}catch(RuntimeException e){
			request.setAttribute("errMsg", "IDもしくはPasswordが間違っています");

			request.getRequestDispatcher("/login.jsp").forward(request, response);

		}


	}

}
