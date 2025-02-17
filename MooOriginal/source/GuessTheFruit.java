import java.util.ArrayList;

public class GuessTheFruit implements GameLogic {
    @Override
    public String generateGoal() {
        int randomIndexFromList;
        String randomFruitFromList = "";
        ArrayList<String> fruits = fruits();
        for (int i = 0; i < fruits.size(); i++){
            randomIndexFromList=(int)(Math.random()* fruits.size());
            randomFruitFromList=fruits.get(randomIndexFromList);
        }
        return randomFruitFromList;
    }

    @Override
    public String generateFeedback(String correctFruit, String guess) {
        if(guess.equalsIgnoreCase(correctFruit)){
            return gameWon();
        }else {
            return "Keep guessing";
        }


    }

    @Override
    public String gameWon() {
        return "You won!";
    }

    public ArrayList<String> fruits() {
        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("banana");
        fruits.add("apple");
        fruits.add("pear");
        fruits.add("blueberry");
        fruits.add("mango");
        fruits.add("strawberry");
        fruits.add("grape");
        fruits.add("kiwi");
        return fruits;
    }
}
