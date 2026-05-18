package ro.uvt.fi.dp;

public class SimpleInterestStrategy implements InterestStrategy {

    private double rate;

    public SimpleInterestStrategy(double rate) {
        this.rate = rate;
    }

    @Override
    public double calculateInterest(double balance) {
        return balance * rate;
    }
}
