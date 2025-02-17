import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, InterruptedException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/Moo", "root", "password");

        GameRepo gameRepo = new GameRepo(connection);
        SimpleWindow simpleWindow = new SimpleWindow("Moo");
        GameController gameController = new GameController(simpleWindow, new GuessTheFruit(), gameRepo);
        gameController.run();
    }
}