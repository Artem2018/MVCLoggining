package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;

//@WebFilter(filterName = "header1", urlPatterns = { "*.html", "*.servlet" })
//@WebFilter(filterName = "header1", urlPatterns = { "/*" })
@WebFilter(filterName = "header1", urlPatterns = { "*.servlet" })
public class HeaderFilter implements Filter {

	private static final String HEADER = "<html><head>" //
			+ "<script src=\"../../web/scripts/jquery.js\"></script>" //
			+ "<script src=\"../../web/scripts/index.js\"></script>" //
			+ "</head><body>";
	
	private static final String HEADER_CONTENT = "<br>HEADER</br>";
	
	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		PrintWriter writer = response.getWriter();
		writer.write(HEADER);
		writer.write(HEADER_CONTENT);
		chain.doFilter(request, response);
		 writer.write("Header-1\n");
	}
}