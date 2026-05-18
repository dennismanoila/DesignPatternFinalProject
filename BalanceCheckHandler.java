package ro.uvt.fi.dp;

public class BalanceCheckHandler extends Handler {

    @Override
    public void handle(Account from, double amount) {

        if (from.getAmount() < amount) {
            throw new RuntimeException("Not enough balance");
        }

        if (next != null) {
            next.handle(from, amount);
        }
    }
}