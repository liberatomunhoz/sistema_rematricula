package rematricula;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object controller) throws Exception {

		String uri = request.getRequestURI();
		if (uri.endsWith("/")||
			uri.endsWith("loginValida")||
			uri.contains("resources") ||
			uri.endsWith("404")||
			uri.endsWith("500")) {
			return true;
		}

		if (request.getSession().getAttribute("usuario logado") != null) {
			return true;
		}
		
		response.sendRedirect("/");
		return false;
	}
}
