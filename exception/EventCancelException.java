package exception;

public class EventCancelException extends RuntimeException {
    public EventCancelException(String message) {
        super(message);
    }
}
