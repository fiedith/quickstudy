import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JUnitTest {

    @DisplayName("1 + 2 = 3임.")
    @Test
    public void junitTest() {
        int a = 1;
        int b = 2;
        int sum = 3;

        assertEquals(sum, a+b);
    }
}
