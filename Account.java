package ro.uvt.fi.dp;

import java.util.ArrayList;
import java.util.List;

public abstract class Account implements Operations {

    private String accountCode = null;
    private double amount = 0;

    // Observer pattern: list of listeners notified on every balance change
    private List<AccountObserver> observers = new ArrayList<>();

    // State pattern: controls which operations are allowed right now
    private AccountState state = new ActiveState();

    // Strategy pattern: interest calculation algorithm
    private InterestStrategy interestStrategy = null;

    protected Account(String accountCode, double amount) {
        this.accountCode = accountCode;
        applyDeposit(amount);
    }

    //Observer pattern

    public void addObserver(AccountObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(AccountObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(String operation, double amount) {
        for (AccountObserver observer : observers) {
            observer.update(accountCode, this.amount, operation, amount);
        }
    }

    // State pattern

    public void setState(AccountState state) {
        this.state = state;
    }

    public AccountState getState() {
        return state;
    }

    //Strategy pattern

    public void setInterestStrategy(InterestStrategy strategy) {
        this.interestStrategy = strategy;
    }

    //Core operations

    public double getAmount() {
        return amount;
    }

    @Override
    public double getTotalAmount() {
        if (interestStrategy != null) {
            return amount + interestStrategy.calculateInterest(amount);
        }
        return amount + amount * getInterest();
    }

    @Override
    public void deposit(double amount) {
        state.deposit(this, amount);
    }

    @Override
    public void retrieve(double amount) {
        state.retrieve(this, amount);
    }

    void applyDeposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        this.amount += amount;
        AppLogger.getInstance().log("Deposit of " + amount + " in account " + accountCode);
        notifyObservers("DEPOSIT", amount);
    }

    void applyRetrieve(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > this.amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        this.amount -= amount;
        AppLogger.getInstance().log("Withdrawal of " + amount + " from account " + accountCode);
        notifyObservers("WITHDRAWAL", amount);
    }

    // Memento pattern

    public AccountMemento save() {
        return new AccountMemento(accountCode, amount, state.getStateName());
    }

    public void restore(AccountMemento memento) {
        this.amount = memento.getAmount();
        switch (memento.getStateName()) {
            case "ACTIVE": this.state = new ActiveState(); break;
            case "FROZEN": this.state = new FrozenState(); break;
            case "CLOSED": this.state = new ClosedState(); break;
        }
    }



    @Override
    public abstract String toString();

    public String getAccountCode() {
        return accountCode;
    }

    public abstract double getInterest();
}
