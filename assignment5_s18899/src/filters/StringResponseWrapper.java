package filters;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class StringResponseWrapper extends HttpServletResponseWrapper {

	private final StringWriter _writer = new StringWriter();

	public StringResponseWrapper(HttpServletResponse response) {
		super(response);
	}
	
	public StringResponseWrapper(ServletResponse response) {
		this((HttpServletResponse)response);
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		return new PrintWriter(_writer);
	}

	public ServletOutputStream getOutputStream() throws IOException {
		throw new IllegalStateException("getOutputStream() not allowed for StringResponseWrapper");
	}
	
	public String content() {
		return _writer.toString();
	}
}