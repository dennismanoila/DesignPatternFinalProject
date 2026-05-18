package ro.uvt.fi.dp;

public class ActiveState implements AccountState {

    @Override
    public void deposit(Account account, double amount) {
        account.applyDeposit(amount);
    }

    @Override
    public void retrieve(Account account, double amount) {
        account.applyRetrieve(amount);
    }

    @Override
    public String getStateName() {
        return "ACTIVE";
    }
}
