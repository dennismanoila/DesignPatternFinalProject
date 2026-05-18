package ro.uvt.fi.dp;

public class TransferCommand implements Command {

    private Account from;
    private Account to;
    private double amount;

    public TransferCommand(Account from, Account to, double amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    @Override
    public void execute() {
        TransferService.transfer(from, to, amount);
    }

    @Override
    public void undo() {
        TransferService.transfer(to, from, amount);
    }
}