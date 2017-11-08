package com.pizzaforum.filters;

import com.pizzaforum.enums.Role;
import com.pizzaforum.models.viewModels.RegisteredUserView;
import com.pizzaforum.models.viewModels.TopicDetailsView;
import com.pizzaforum.services.api.TopicService;
import com.pizzaforum.staticData.Constants;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/home/categories/*", "/forum/logout", "/topics/new",
        "/forum/profile/*", "/topics/delete/*", "/topics/edit/*",
        "/topics/details/add-reply/*"})
public class LoggedInUserFilter implements Filter {

    private TopicService topicService;

    @Inject
    public LoggedInUserFilter(TopicService topicService) {
        this.topicService = topicService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = ((HttpServletRequest) servletRequest);
        HttpSession session = request.getSession();
        RegisteredUserView registeredUserView = (RegisteredUserView) session.getAttribute(Constants.LOGGED_IN_USER_KEY);
        HttpServletResponse response = ((HttpServletResponse) servletResponse);
        if (registeredUserView == null) {
            response.sendRedirect("/forum/login");
            return;
        }

        String url = request.getRequestURI();
        if (url.contains("/topics/delete/") || url.contains("/topics/edit/")) {
            String[] urlTokens = url.split("/");
            Integer urlTokensLength = urlTokens.length;
            Long topicId = Long.valueOf(urlTokens[urlTokensLength - 1]);
            TopicDetailsView topicView = this.topicService.findTopicDetailsById(topicId);
            if (topicView == null) {
                response.sendRedirect("/home/topics");
                return;
            }

            String loggedInUserUsername = registeredUserView.getUsername();
            Role loggedInUserRole = registeredUserView.getRole();
            if ((loggedInUserRole != Role.ADMIN) &&
                    (!loggedInUserUsername.equals(topicView.getAuthorUsername()))) {
                response.sendRedirect("/home/topics");
                return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
