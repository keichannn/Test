package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Role;
import entity.SessionInfo;
import entity.User_info;
import service.RoleService;
import service.UserInfoService;
import util.ParamUtil;

@WebServlet("/UserInsertServlet")
public class UserInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public UserInsertServlet() {
        super();
    }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	List<Role> role = RoleService.find();

    	request.setAttribute("role", role);

    	request.getRequestDispatcher("userInsert.jsp").forward(request, response);
	}

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String user_name = request.getParameter("user_name");
		String birthday = request.getParameter("birthday");
		System.out.println(birthday);
		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");
		String hiredate = request.getParameter("hiredate");
		String basic = request.getParameter("basic");
		String role_idStr = request.getParameter("role_id");
		Integer role_id = ParamUtil.checkAndParseInt(role_idStr);

		User_info user_info =new User_info();
		HttpSession session = request.getSession();

		session.setAttribute("name", user_name);
		session.setAttribute("birth", birthday);
		session.setAttribute("loginId", login_id);
		session.setAttribute("pass", password);
		session.setAttribute("hire", hiredate);
		session.setAttribute("basic", basic);

        SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

        RoleService rs = new RoleService();
        List<Role> lr = rs.findAll();
        request.setAttribute("role", lr);
        sessionInfo.setRoleList(lr);

        Role li = rs.findByRoleId(role_id);
        session.setAttribute("roleName", li.getRole_name());

        user_info.setRole_name(ParamUtil.getRoleNameByRoleId(role_id, sessionInfo.getRoleList()));

        sessionInfo.setResisterUser(user_info);

        boolean hasError = false;

        if (ParamUtil.isNullOrEmpty(user_name)) {
            request.setAttribute("nameErrMsg", "名前は必須です");
            hasError = true;
        }

        if (ParamUtil.isNullOrEmpty(birthday)) {
            request.setAttribute("birthErrMsg", "生年月日は必須です");
            hasError = true;
        }

        if (ParamUtil.isNullOrEmpty(login_id)) {
            request.setAttribute("idErrMsg", "IDは必須です");
            hasError = true;
        }

        if (ParamUtil.isNullOrEmpty(password)) {
            request.setAttribute("passErrMsg", "パスワードは必須です");
            hasError = true;
        }

        if (ParamUtil.isNullOrEmpty(hiredate)) {
            request.setAttribute("hireErrMsg", "入社日は必須です");
            hasError = true;
        }

        if (ParamUtil.isNullOrEmpty(basic)) {
            request.setAttribute("basicErrMsg", "基本給は必須です");
            hasError = true;
        }

        if (hasError) {
            request.getRequestDispatcher("/userInsert.jsp").forward(request, response);
            return;
        }

        UserInfoService userInfoService = new UserInfoService();


        if (userInfoService.existsUserByLoginId(login_id)) {
            request.setAttribute("errMsg", "IDが重複しています");
            request.getRequestDispatcher("/userInsert.jsp").forward(request, response);
            return;
        }

			request.getRequestDispatcher("/userInfoConfirm.jsp").forward(request, response);


	}

}
