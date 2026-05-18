package ro.uvt.fi.dp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    @Test
    void testDeposit() {
        Account acc = new RonAccount("RON1", 100);
        acc.deposit(50);

        assertEquals(150, acc.getAmount());
    }

    @Test
    void testWithdraw() {
        Account acc = new RonAccount("RON1", 200);
        acc.retrieve(50);

        assertEquals(150, acc.getAmount());
    }

    @Test
    void testWithdrawTooMuch() {
        Account acc = new RonAccount("RON1", 100);

        assertThrows(IllegalArgumentException.class, () -> {
            acc.retrieve(200);
        });
    }

    @Test
    void testInterestRON() {
        Account acc = new RonAccount("RON1", 600);

        assertEquals(0.08, acc.getInterest());
    }

    @Test
    void testTransfer() {
        Account a1 = new RonAccount("A1", 200);
        Account a2 = new RonAccount("A2", 100);

        TransferService.transfer(a1, a2, 50);

        assertEquals(150, a1.getAmount());
        assertEquals(150, a2.getAmount());
    }
}