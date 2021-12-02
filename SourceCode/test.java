import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class test {

    @Test
    public void testProductSelection1() {
        COSM myUnit = new COSM();
        String input = Integer.toString(1);
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        int product = myUnit.selectProduct();
        assertEquals(1, product);

    }
    @Test
    public void testProductSelection2() {
        COSM myUnit = new COSM();
        String input = Integer.toString(2);
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        int product = myUnit.selectProduct();
        assertEquals(2, product);

    }
    @Test
    public void testProductSelection3() {
        COSM myUnit = new COSM();
        String input = Integer.toString(3);
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        int product = myUnit.selectProduct();
        assertEquals(3, product);

    }
    
    @Test
    public void testConsecutivePurchases1() {
        COSM myUnit = new COSM();
        myUnit.consecutivePurchases("Coke");
        myUnit.consecutivePurchases("Coke");
        myUnit.consecutivePurchases("Coke");
        
        assertEquals(0, myUnit.getTimes());

    }
    @Test
    public void testConsecutivePurchases2() {
        COSM myUnit = new COSM();
        myUnit.consecutivePurchases("Coke");
        myUnit.consecutivePurchases("Coke");
        
        assertEquals(2, myUnit.getTimes());

    }
    
    @Test
    public void testConsecutivePurchases3() {
        COSM myUnit = new COSM();
        myUnit.consecutivePurchases("Coke");
        myUnit.consecutivePurchases("Soda");
        
        assertEquals(1, myUnit.getTimes());

    }
    
    @Test
    public void testConsecutivePurchases4() {
        COSM myUnit = new COSM();
        myUnit.consecutivePurchases("Soda");
        
        assertEquals("Soda", myUnit.getProduct());

    }
}