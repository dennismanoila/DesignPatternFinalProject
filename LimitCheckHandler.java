package ro.uvt.fi.dp;

public class LimitCheckHandler extends Handler {

    @Override
    public void handle(Account from, double amount) {

        if (amount > 1000) {
            throw new RuntimeException("Transfer limit exceeded");
        }

        if (next != null) {
            next.handle(from, amount);
        }
    }
}