package filters;



import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@WebFilter(filterName = "footer1", urlPatterns = { "*.servlet"  })
public class FooterFilter implements Filter {

	private static final String FOOTER_CONTENT = //
			"date: <div id=\"date\"></div>" //
					+ "</body></html>";
	public void init(FilterConfig fConfig) throws ServletException {
	}
	
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse originalResponse, FilterChain chain)
			throws IOException, ServletException {
		StringResponseWrapper wrapperResponse = new StringResponseWrapper(originalResponse);
		chain.doFilter(request, wrapperResponse);
		appendServletGeneratedResponse(originalResponse, wrapperResponse);
		appendFooter(request, originalResponse);
	}

	private void appendServletGeneratedResponse(ServletResponse originalResponse, StringResponseWrapper wrapperResponse)
			throws IOException {
		String wrapperContent = wrapperResponse.content();
		PrintWriter originalResponseWriter = originalResponse.getWriter();
		originalResponseWriter.print(wrapperContent);
	}

	private void appendFooter(ServletRequest request, ServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		writer.println(FOOTER_CONTENT);
		writer.close();
	}
}