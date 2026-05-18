package ro.uvt.fi.dp;

public class EurAccount extends Account {

    public EurAccount(String accountCode, double amount) {
        super(accountCode, amount);
    }

    @Override
    public double getInterest() {
        return 0.01;
    }

    @Override
    public String toString() {
        return "Account EUR: code=" + getAccountCode() + ", amount=" + getAmount();
    }

}
