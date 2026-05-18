package ro.uvt.fi.dp;

public class ClosedState implements AccountState {

    @Override
    public void deposit(Account account, double amount) {
        throw new IllegalStateException("Account is closed. No operations are allowed.");
    }

    @Override
    public void retrieve(Account account, double amount) {
        throw new IllegalStateException("Account is closed. No operations are allowed.");
    }

    @Override
    public String getStateName() {
        return "CLOSED";
    }
}
