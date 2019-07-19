package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Role;
import entity.SessionInfo;
import service.RoleService;
import service.UserInfoService;
import util.ParamUtil;


@WebServlet("/UserInsertConfirmServlet")
public class UserInsertConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserInsertConfirmServlet() {
        super();
    }

	/**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String rePassword = request.getParameter("rePassword");

		HttpSession session = request.getSession();
        SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

        RoleService rs = new RoleService();
        List<Role> lr = rs.findAll();
        request.setAttribute("role", lr);
        sessionInfo.setRoleList(lr);

        String user_name = request.getParameter("user_name");

        String birthStr = request.getParameter("birthday");

        java.util.Date birth = null;
		try {
			birth = new SimpleDateFormat("yyyy-MM-dd").parse(birthStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

        java.sql.Date birthday = new java.sql.Date(birth.getTime());

        String login_id = request.getParameter("login_id");

        String password = request.getParameter("password");

        String hireStr = request.getParameter("hiredate");
        java.util.Date hire = null;
		try {
			hire = new SimpleDateFormat("yyyy-MM-dd").parse(hireStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        java.sql.Date hiredate = new java.sql.Date(hire.getTime());

        String basicStr = request.getParameter("basic");
        Integer basic = Integer.parseInt(basicStr);


        String role_name = request.getParameter("role_name");



        if (ParamUtil.isNullOrEmpty(rePassword)) {
            request.setAttribute("errMsg", "前画面で入力したパスワードと一致しません");
            request.getRequestDispatcher("userInfoConfirm.jsp").forward(request, response);
            return;
        }


        UserInfoService uis = new UserInfoService();
        uis.insert(user_name, birthday, login_id, password, hiredate, basic, role_name);

        sessionInfo.setResisterUser(null);

		request.getRequestDispatcher("infomationChange.jsp").forward(request, response);
	}

}
