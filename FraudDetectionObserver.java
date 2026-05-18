package ro.uvt.fi.dp;

public class FraudDetectionObserver implements AccountObserver {

    private static final double SUSPICIOUS_THRESHOLD = 500.0;

    @Override
    public void update(String accountCode, double newBalance, String operation, double amount) {
        if (amount >= SUSPICIOUS_THRESHOLD) {
            System.out.println("[FRAUD ALERT] Suspicious " + operation + " of " + amount
                    + " on account " + accountCode + " | Balance now: " + newBalance);
        }
    }
}
