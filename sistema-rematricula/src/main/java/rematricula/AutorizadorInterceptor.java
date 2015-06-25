//package rematricula;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//public class AutorizadorInterceptor extends HandlerInterceptorAdapter {
//	@Override
//	public boolean preHandle(HttpServletRequest request,
//			HttpServletResponse response, Object controller) throws Exception {
//
//		String uri = request.getRequestURI();
//		if (uri.endsWith("/")||
//			uri.endsWith("cadastrarCurso")||
//			uri.endsWith("validaLogin")||
//			uri.endsWith("script")||
//			uri.contains("resources")) {
//			return true;
//		}
//
//		if (request.getSession().getAttribute("usuarioLogado") != null) {
//			return true;
//		}
//		
//		response.sendRedirect("/");
//		return false;
//	}
//}
