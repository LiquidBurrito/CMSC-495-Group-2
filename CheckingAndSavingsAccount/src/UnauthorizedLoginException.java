package CheckingAndSavingsAccount.src;

// Exception to throw message if incorrect login credentials are entered in Login GUI -- Added 26June
// Exception is handled in BankApp.java
public class UnauthorizedLoginException extends Exception {

    public UnauthorizedLoginException(String message) {
        super(message);
    }
}
