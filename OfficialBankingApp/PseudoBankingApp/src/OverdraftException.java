import java.io.IOException;

public class OverdraftException extends IOException {
    OverdraftException(String message) {
        super(message);
    }
}
