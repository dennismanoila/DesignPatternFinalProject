package ro.uvt.fi.dp;

public class Test {

    public static void main(String[] args) {

        // During semester patterns
        header("1. SINGLETON");
        showSingleton();

        header("2. FACTORY");
        showFactory();

        header("3. BUILDER");
        showBuilder();

        header("4. TEMPLATE METHOD");
        showTemplateMethod();

        header("5. DECORATOR");
        showDecorator();

        header("6. COMMAND + UNDO");
        showCommand();

        header("7. CHAIN OF RESPONSIBILITY");
        showChainOfResponsibility();

        // Final project patterns
        header("8. OBSERVER");
        showObserver();

        header("9. STRATEGY");
        showStrategy();

        header("10. STATE");
        showState();

        header("11. MEMENTO");
        showMemento();
    }

    static void header(String title) {
        System.out.println("\n══════════════════════════════════════");
        System.out.println("  " + title);
        System.out.println("══════════════════════════════════════");
    }

    // 1. SINGLETON

    static void showSingleton() {
        AppLogger logger1 = AppLogger.getInstance();
        AppLogger logger2 = AppLogger.getInstance();

        System.out.println("Same instance? " + (logger1 == logger2));
        logger1.log("Singleton logger is working");
    }

    // 2. FACTORY

    static void showFactory() {
        Account ron = AccountFactory.createAccount("RON", "RON01", 500);
        Account eur = AccountFactory.createAccount("EUR", "EUR01", 200);

        System.out.println("Created: " + ron);
        System.out.println("Created: " + eur);

        try {
            AccountFactory.createAccount("GBP", "GBP01", 100);
        } catch (IllegalArgumentException e) {
            System.out.println("Unknown type caught: " + e.getMessage());
        }
    }

    // 3. BUILDER

    static void showBuilder() {
        Client clientWithAddress = new ClientBuilder("C01", "Alice")
                .setAddress("Str. Libertatii 12, Timisoara")
                .build();

        Client clientWithoutAddress = new ClientBuilder("C02", "Bob")
                .build();

        System.out.println("Built client 1: " + clientWithAddress);
        System.out.println("Built client 2 (no address): " + clientWithoutAddress);
    }

    // 4. TEMPLATE METHOD

    static void showTemplateMethod() {
        Account ron = new RonAccount("RON01", 400);
        Account eur = new EurAccount("EUR01", 400);

        System.out.println("RON interest rate (balance < 500): " + ron.getInterest() * 100 + "%");
        System.out.println("RON total with interest: " + ron.getTotalAmount());

        System.out.println("EUR interest rate (fixed): " + eur.getInterest() * 100 + "%");
        System.out.println("EUR total with interest: " + eur.getTotalAmount());
    }

    // 5. DECORATOR

    static void showDecorator() {
        Account plain = new RonAccount("RON01", 300);
        Account withFee = new FeeAccountDecorator(plain);

        System.out.println("Depositing 100 through FeeDecorator (fee = 2.0)...");
        withFee.deposit(100);
        System.out.println("Balance after deposit: " + withFee.getAmount());

        System.out.println("Withdrawing 50 through FeeDecorator (fee = 2.0)...");
        withFee.retrieve(50);
        System.out.println("Balance after withdraw: " + withFee.getAmount());
    }

    // 6. COMMAND + UNDO

    static void showCommand() {
        Account account = new RonAccount("RON01", 500);
        CommandManager manager = new CommandManager();

        System.out.println("Balance: " + account.getAmount());

        manager.executeCommand(new DepositCommand(account, 200));
        System.out.println("After deposit 200: " + account.getAmount());

        manager.executeCommand(new WithdrawCommand(account, 100));
        System.out.println("After withdraw 100: " + account.getAmount());

        System.out.println("Undoing last command (withdraw)...");
        manager.undo();
        System.out.println("After undo: " + account.getAmount());

        Account other = new RonAccount("RON02", 100);
        manager.executeCommand(new TransferCommand(account, other, 150));
        System.out.println("After transfer 150 to other account:");
        System.out.println("  Source: " + account.getAmount() + " | Target: " + other.getAmount());

        System.out.println("Undoing transfer...");
        manager.undo();
        System.out.println("  Source: " + account.getAmount() + " | Target: " + other.getAmount());
    }

    // 7. CHAIN OF RESPONSIBILITY

    static void showChainOfResponsibility() {
        BalanceCheckHandler balance = new BalanceCheckHandler();
        LimitCheckHandler limit = new LimitCheckHandler();
        ApprovalHandler approval = new ApprovalHandler();

        balance.setNext(limit);
        limit.setNext(approval);

        Account account = new RonAccount("RON01", 800);

        System.out.println("Trying valid transfer of 500...");
        balance.handle(account, 500);

        System.out.println("Trying transfer of 1500 (exceeds limit)...");
        try {
            balance.handle(account, 1500);
        } catch (RuntimeException e) {
            System.out.println("Blocked: " + e.getMessage());
        }

        System.out.println("Trying transfer of 900 (exceeds balance)...");
        try {
            balance.handle(account, 900);
        } catch (RuntimeException e) {
            System.out.println("Blocked: " + e.getMessage());
        }
    }

    // 8. OBSERVER

    static void showObserver() {
        Account account = new RonAccount("RON01", 300);

        account.addObserver(new SmsNotificationObserver("+40712345678"));
        account.addObserver(new EmailNotificationObserver("client@bank.com"));
        account.addObserver(new FraudDetectionObserver());

        System.out.println("Depositing 100...");
        account.deposit(100);

        System.out.println("Withdrawing 50...");
        account.retrieve(50);

        System.out.println("Depositing 600 (suspicious amount)...");
        account.deposit(600);
    }

    // 9. STRATEGY

    static void showStrategy() {
        Account account = new RonAccount("RON02", 1000);

        System.out.println("Balance: " + account.getAmount());
        System.out.println("Default (RonAccount, rate=8%): total = "
                + account.getTotalAmount());

        account.setInterestStrategy(new SimpleInterestStrategy(0.05));
        System.out.println("Simple interest strategy (5%): total = "
                + account.getTotalAmount());

        account.setInterestStrategy(new CompoundInterestStrategy(0.05, 3));
        System.out.println("Compound interest strategy (5% x 3 periods): total = "
                + account.getTotalAmount());
    }

    // 10. STATE

    static void showState() {
        Account account = new RonAccount("RON03", 500);

        System.out.println("State: " + account.getState().getStateName());
        account.deposit(100);
        account.retrieve(50);
        System.out.println("Balance after deposit+withdraw: " + account.getAmount());

        System.out.println("\nFreezing the account...");
        account.setState(new FrozenState());
        System.out.println("State: " + account.getState().getStateName());

        account.deposit(100);
        System.out.println("Deposit OK while frozen. Balance: " + account.getAmount());

        try {
            account.retrieve(50);
        } catch (IllegalStateException e) {
            System.out.println("Withdraw blocked: " + e.getMessage());
        }

        System.out.println("\nClosing the account...");
        account.setState(new ClosedState());
        System.out.println("State: " + account.getState().getStateName());

        try {
            account.deposit(100);
        } catch (IllegalStateException e) {
            System.out.println("Deposit blocked: " + e.getMessage());
        }

        try {
            account.retrieve(50);
        } catch (IllegalStateException e) {
            System.out.println("Withdraw blocked: " + e.getMessage());
        }
    }

    // 11. MEMENTO

    static void showMemento() {
        Account account = new RonAccount("RON04", 200);
        AccountHistory history = new AccountHistory();

        System.out.println("Initial balance: " + account.getAmount());
        history.save(account);

        account.deposit(300);
        System.out.println("After depositing 300: " + account.getAmount());
        history.save(account);

        account.deposit(150);
        System.out.println("After depositing 150 more: " + account.getAmount());

        System.out.println("\nRestoring to last saved snapshot...");
        account.restore(history.getLatest());
        System.out.println("Restored balance: " + account.getAmount());
        System.out.println("Restored state: " + account.getState().getStateName());
    }
}
