package za.co.staffschedule.exception;

import za.co.staffschedule.service.ServiceException;

public class UserException extends ServiceException {
    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Exception exception) {
        super(message, exception);
    }
}
