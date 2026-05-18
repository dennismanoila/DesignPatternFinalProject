package ro.uvt.fi.dp;

public class RonAccount extends Account {

    public RonAccount(String accountCode, double amount) {
        super(accountCode, amount);
    }

    @Override
    public double getInterest() {
        if (getAmount() < 500)
            return 0.03;
        else
            return 0.08;
    }

    @Override
    public String toString() {
        return "Account RON: code=" + getAccountCode() + ", amount=" + getAmount();
    }


}
