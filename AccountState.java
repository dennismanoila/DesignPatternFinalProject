package ro.uvt.fi.dp;

public interface AccountState {
    void deposit(Account account, double amount);
    void retrieve(Account account, double amount);
    String getStateName();
}
