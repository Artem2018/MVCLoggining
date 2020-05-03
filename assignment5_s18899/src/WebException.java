import java.sql.SQLException;

public class WebException extends RuntimeException {
    private static final long serialVersionUID = 3524387624158855027L;

    public WebException(String message, Throwable cause) {
        super(message, cause);
    }
}
