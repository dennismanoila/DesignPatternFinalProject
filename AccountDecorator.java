package ro.uvt.fi.dp;

public abstract class AccountDecorator extends Account {

    protected Account decoratedAccount;

    public AccountDecorator(Account decoratedAccount) {
        super(decoratedAccount.getAccountCode(), 0.01);
        this.decoratedAccount = decoratedAccount;
    }

    @Override
    public double getTotalAmount() {
        return decoratedAccount.getTotalAmount();
    }

    @Override
    public double getInterest() {
        return decoratedAccount.getInterest();
    }

    @Override
    public void deposit(double amount) {
        decoratedAccount.deposit(amount);
    }

    @Override
    public void retrieve(double amount) {
        decoratedAccount.retrieve(amount);
    }

    @Override
    public String getAccountCode() {
        return decoratedAccount.getAccountCode();
    }

    @Override
    public double getAmount() {
        return decoratedAccount.getAmount();
    }

    @Override
    public String toString() {
        return decoratedAccount.toString();
    }
}