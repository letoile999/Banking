import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    private BankAccount account1;
    private BankAccount account2;

    @BeforeEach
    public void setUp() {
        account1 = new BankAccount("001", 100.0);
        account2 = new BankAccount("002", 200.0);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testDeposit() {
        account1.deposit(50.0);
        assertEquals(150.0, account1.getBalance(), 0.001);
    }

    @Test
    public void testDepositZeroAmount() {
        account1.deposit(0.0);
        assertEquals(100.0, account1.getBalance(), 0.001);
    }

    @Test
    public void testWithdraw() {
        account1.withdraw(30.0);
        assertEquals(70.0, account1.getBalance(), 0.001);
    }

    @Test
    public void testWithdrawNegativeAmount() {
        account1.withdraw(-10.0);
        assertEquals(100.0, account1.getBalance(), 0.001);
    }

    @Test
    public void testWithdrawInsufficientFunds() {
        account1.withdraw(120.0);
        assertEquals(100.0, account1.getBalance(), 0.001);
    }

    @Test
    public void testTransfer() {
        account1.transfer(account2, 50.0);
        assertEquals(50.0, account1.getBalance(), 0.001);
        assertEquals(250.0, account2.getBalance(), 0.001);
    }

    @Test
    public void testTransferEntireBalance() {
        account1.transfer(account2, 100.0);
        assertEquals(0.0, account1.getBalance(), 0.001);
        assertEquals(300.0, account2.getBalance(), 0.001);
    }
}
