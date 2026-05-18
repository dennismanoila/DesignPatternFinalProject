package ro.uvt.fi.dp;

public class CompoundInterestStrategy implements InterestStrategy {

    private double rate;
    private int periods;

    public CompoundInterestStrategy(double rate, int periods) {
        this.rate = rate;
        this.periods = periods;
    }

    @Override
    public double calculateInterest(double balance) {
        return balance * Math.pow(1 + rate, periods) - balance;
    }
}
