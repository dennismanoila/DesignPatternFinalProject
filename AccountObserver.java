package ro.uvt.fi.dp;

public interface AccountObserver {
    void update(String accountCode, double newBalance, String operation, double amount);
}
