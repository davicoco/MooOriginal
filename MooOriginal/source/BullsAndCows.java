public class BullsAndCows implements GameLogic {

    @Override
    public String generateGoal() {
        String fourNumbers = "";
        for (int i = 0; i < 4; i++) {
            int random = (int) (Math.random() * 10);
            String randomDigit = "" + random;
            while (fourNumbers.contains(randomDigit)) {
                random = (int) (Math.random() * 10);
                randomDigit = "" + random;
            }
            fourNumbers = fourNumbers + randomDigit;
        }
        return fourNumbers;
    }

    @Override
    public String generateFeedback(String correctFourNumbers, String guess) {
        guess += "    ";
        int cows = 0, bulls = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (correctFourNumbers.charAt(i) == guess.charAt(j)) {
                    if (i == j) {
                        bulls++;
                    } else {
                        cows++;
                    }
                }
            }
        }
        String result = "";
        for (int i = 0; i < bulls; i++) {
            result = result + "B";
        }
        result = result + ",";
        for (int i = 0; i < cows; i++) {
            result = result + "C";
        }
        return result;
    }

    @Override
    public String gameWon() {
        return "BBBB,";
    }
}
