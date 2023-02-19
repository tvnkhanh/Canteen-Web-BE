package ptit.cnpm.becanteenweb.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String email) {
        super("Could not found the account with email " + email);
    }
}
