package ro.uvt.fi.dp;

public abstract class Handler {

    protected Handler next;

    public void setNext(Handler next) {
        this.next = next;
    }

    public abstract void handle(Account from, double amount);
}