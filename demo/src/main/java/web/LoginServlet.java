package web;

import com.mysql.cj.util.StringUtils;
import domain.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("登录");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String captcha = req.getParameter("captcha");
        System.out.println(username);
        System.out.println(password);
        System.out.println(captcha);

        if (StringUtils.isNullOrEmpty(captcha)) {
            req.setAttribute("error", "验证码没有输入");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
        UserService userService = new UserService();
        try {
            User user = userService.getUserByUsername(username);
            if (user == null) {
                req.setAttribute("error", "无此用户名称" + username);
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            } else {
                if (password.equals(user.getPassword())) {
                    req.getSession().setAttribute("loginUser", username);
                    resp.sendRedirect(req.getContextPath() + "/index.jsp");
                } else {
                    req.setAttribute("error", "此用户名称" + username + "密码错误，请重新输入");
                    req.getRequestDispatcher("/login.jsp").forward(req, resp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
