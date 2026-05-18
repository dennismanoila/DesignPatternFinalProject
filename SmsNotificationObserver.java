package ro.uvt.fi.dp;

public class SmsNotificationObserver implements AccountObserver {

    private String phoneNumber;

    public SmsNotificationObserver(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void update(String accountCode, double newBalance, String operation, double amount) {
        System.out.println("[SMS -> " + phoneNumber + "] Account " + accountCode
                + " | " + operation + " of " + amount + " | New balance: " + newBalance);
    }
}
