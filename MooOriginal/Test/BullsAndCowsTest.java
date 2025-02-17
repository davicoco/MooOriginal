import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BullsAndCowsTest {

    @Test
    void testGenerateFeedback() {
        BullsAndCows bullsAndCows = new BullsAndCows();

        String feedback = bullsAndCows.generateFeedback("9530", "9595");
        assertEquals("BB,CC", feedback);

        feedback = bullsAndCows.generateFeedback("0987", "0987");
        assertEquals("BBBB,", feedback);

        feedback = bullsAndCows.generateFeedback("1234", "0987");
        assertEquals(",",feedback);


    }
}
