package ro.uvt.fi.dp;

public class EmailNotificationObserver implements AccountObserver {

    private String email;

    public EmailNotificationObserver(String email) {
        this.email = email;
    }

    @Override
    public void update(String accountCode, double newBalance, String operation, double amount) {
        System.out.println("[Email -> " + email + "] Account " + accountCode
                + " | " + operation + " of " + amount + " | New balance: " + newBalance);
    }
}
