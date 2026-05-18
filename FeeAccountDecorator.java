package ro.uvt.fi.dp;

public class FeeAccountDecorator extends AccountDecorator {

    private static final double TRANSACTION_FEE = 2.0;

    public FeeAccountDecorator(Account decoratedAccount) {
        super(decoratedAccount);
    }

    @Override
    public void deposit(double amount) {

        if (decoratedAccount == null) {
            return;
        }

        double fee = TRANSACTION_FEE;

        if (amount <= fee) {
            decoratedAccount.deposit(amount);
            return;
        }

        decoratedAccount.deposit(amount - fee);

        AppLogger.getInstance().log(
                "Transaction fee of " + fee + " applied on deposit for account " +
                        decoratedAccount.getAccountCode()
        );
    }

    @Override
    public void retrieve(double amount) {
        decoratedAccount.retrieve(amount + TRANSACTION_FEE);
        AppLogger.getInstance().log(
                "Transaction fee of " + TRANSACTION_FEE + " applied on withdrawal for account " +
                        decoratedAccount.getAccountCode()
        );
    }

    @Override
    public String toString() {
        return decoratedAccount.toString() + " [Fee Decorator applied]";
    }
}