package com.pizzaforum.filters;

import com.pizzaforum.models.viewModels.RegisteredUserView;
import com.pizzaforum.staticData.Constants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/forum/login")
public class GuestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        RegisteredUserView registeredUserView = (RegisteredUserView) session.getAttribute(Constants.LOGGED_IN_USER_KEY);
        HttpServletResponse response = ((HttpServletResponse) servletResponse);
        if (registeredUserView != null) {
            response.sendRedirect("/home/topics");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
