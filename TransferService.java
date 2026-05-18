package ro.uvt.fi.dp;

public class TransferService {
    public static void transfer(Account from, Account to, double amount) {
        from.retrieve(amount);
        to.deposit(amount);
        AppLogger.getInstance().log(
                "Transfer of " + amount + " from " +
                        from.getAccountCode() + " to " +
                        to.getAccountCode()
        );
    }
}
