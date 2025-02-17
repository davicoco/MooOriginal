import java.sql.*;
import java.util.ArrayList;

public class GameRepo {
    private Connection connection;
    private PreparedStatement stmt;
    private PreparedStatement stmt2;

    public GameRepo(Connection connection) {
        try {
            this.connection = connection;
            this.stmt = (PreparedStatement) connection.createStatement();
            this.stmt2 = (PreparedStatement) connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getPlayerId(String playerName) {
        try {
            ResultSet rs = stmt.executeQuery("select id,name from players WHERE name = '" + playerName + "'");
            int id;
            if (rs.next()) {
                id = rs.getInt("id");
            } else {
                return -1;
            }
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveResult(int numberOfGuesses, int playerId) {
        try {
            stmt.executeUpdate("INSERT INTO results (result, playerid) VALUES (" + numberOfGuesses + ", " + playerId + ")");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getAllPlayers() {
        try {
            return stmt.executeQuery("select * from players");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getPlayerResults(int playerId) {
        try {
            return stmt2.executeQuery("select * from results where playerid = " + playerId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<GameController.PlayerAverage> getTopTen() {
        try {
            ArrayList<GameController.PlayerAverage> topList = new ArrayList<>();
            ResultSet rs = getAllPlayers();
            ResultSet rs2;
            while (rs.next()) {
                int playerId = rs.getInt("id");
                String playerName = rs.getString("name");
                rs2 = getPlayerResults(playerId);
                int numberOfGames = 0;
                int totalGuesses = 0;

                while (rs2.next()) {
                    numberOfGames++;
                    totalGuesses += rs2.getInt("result");
                }
                if (numberOfGames > 0) {
                    topList.add(new GameController.PlayerAverage(playerName, (double) totalGuesses / numberOfGames));
                }
            }
            return topList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
