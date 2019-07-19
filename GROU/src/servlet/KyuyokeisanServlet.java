package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.SessionInfo;
import entity.User_info;
import service.UserInfoService;
import util.ParamUtil;

/**
 * Servlet implementation class KyuyokeisanServlet
 */
@WebServlet("/KyuyokeisanServlet")
public class KyuyokeisanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public KyuyokeisanServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserInfoService uis = new UserInfoService();

		SessionInfo sessionInfo = ParamUtil.getSessionInfo(request.getSession());

		List<User_info> listUser = uis.findAll();

		sessionInfo.setResisterUserList(listUser);

		request.getSession().setAttribute("sessionInfo", sessionInfo);
		request.getRequestDispatcher("userListByAccounting.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
