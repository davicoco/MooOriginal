import java.util.ArrayList;

public class GameController {
    private SimpleWindow simpleWindow;
    private GameLogic gameLogic;
    private GameRepo gameRepo;
    private int id;

    public GameController(SimpleWindow simpleWindow, GameLogic gameLogic, GameRepo gameRepo) {
        this.simpleWindow = simpleWindow;
        this.gameLogic = gameLogic;
        this.gameRepo = gameRepo;
    }

    public void run() {

        simpleWindow.addString("Enter your user name:\n");
        String name = simpleWindow.getString();

        id = gameRepo.getPlayerId(name);
        if (id < 0) {
            simpleWindow.addString("User not in database, please register with admin");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            simpleWindow.exit();
        }

        boolean running = true;
        while (running) {
            String correctAnswer = gameLogic.generateGoal();
            simpleWindow.clear();
            simpleWindow.addString("New game:\n");
//            comment out or remove next line to play real games!
            simpleWindow.addString("For practice, correct answer is: " + correctAnswer + "\n");

            String guess = simpleWindow.getString();
            simpleWindow.addString(guess + "\n");
            int numberOfGuesses = 1;
            String feedbackOnGuess = gameLogic.generateFeedback(correctAnswer, guess);
            simpleWindow.addString(feedbackOnGuess + "\n");
            while (!feedbackOnGuess.equals(gameLogic.gameWon())) {
                numberOfGuesses++;
                guess = simpleWindow.getString();
                simpleWindow.addString(guess + ":\n");
                feedbackOnGuess = gameLogic.generateFeedback(correctAnswer, guess);
                simpleWindow.addString(feedbackOnGuess + "\n");
            }

            gameRepo.saveResult(id, numberOfGuesses);
            showTopTen();
            running = simpleWindow.yesNo("Correct, it took " + numberOfGuesses
                    + " guesses\nContinue?");

        }
        simpleWindow.exit();
    }

    public void showTopTen() {
        ArrayList<PlayerAverage> topList = gameRepo.getTopTen();
        simpleWindow.addString("Top Ten List\n    Player     Average\n");
        int pos = 1;
        topList.sort((p2, p1) -> Double.compare(p1.average, p2.average));
        for (PlayerAverage p : topList) {
            simpleWindow.addString(String.format("%3d %-10s%5.2f\n", pos, p.name, p.average));
            if (pos++ == 10) break;
        }
    }

    static class PlayerAverage {
        String name;
        double average;

        public PlayerAverage(String name, double average) {
            this.name = name;
            this.average = average;
        }
    }
}
