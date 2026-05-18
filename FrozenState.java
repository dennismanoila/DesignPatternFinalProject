package ro.uvt.fi.dp;

public class FrozenState implements AccountState {

    @Override
    public void deposit(Account account, double amount) {
        account.applyDeposit(amount);
    }

    @Override
    public void retrieve(Account account, double amount) {
        throw new IllegalStateException("Account is frozen. Withdrawals are not allowed.");
    }

    @Override
    public String getStateName() {
        return "FROZEN";
    }
}
