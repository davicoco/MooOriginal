public interface GameLogic {
    String generateGoal();

    String generateFeedback(String correctFourNumbers, String guess);

    String gameWon();
}
