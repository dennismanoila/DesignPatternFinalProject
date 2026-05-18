package ro.uvt.fi.dp;

public class ApprovalHandler extends Handler {

    @Override
    public void handle(Account from, double amount) {
        System.out.println("Transaction approved");
    }
}