package exceptions;

import java.sql.SQLException;

public class NotSelectQueryException extends SQLException {
    public NotSelectQueryException(String message) {
        super(message);
    }
}
