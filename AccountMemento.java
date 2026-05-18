package ro.uvt.fi.dp;

import java.time.LocalDateTime;

public class AccountMemento {

    private final String accountCode;
    private final double amount;
    private final String stateName;
    private final LocalDateTime savedAt;

    public AccountMemento(String accountCode, double amount, String stateName) {
        this.accountCode = accountCode;
        this.amount = amount;
        this.stateName = stateName;
        this.savedAt = LocalDateTime.now();
    }

    public String getAccountCode() {
        return accountCode;
    }

    public double getAmount() {
        return amount;
    }

    public String getStateName() {
        return stateName;
    }

    public LocalDateTime getSavedAt() {
        return savedAt;
    }

    @Override
    public String toString() {
        return "Snapshot[account=" + accountCode + ", balance=" + amount
                + ", state=" + stateName + ", savedAt=" + savedAt + "]";
    }
}
