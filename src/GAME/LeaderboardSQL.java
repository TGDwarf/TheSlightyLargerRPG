package GAME;

import java.sql.*;
import java.util.List;

/**
 * Created by dot on 02-03-2016.
 */
public class LeaderboardSQL {
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/tlrpg?autoReconnect=true&useSSL=false";
    private static final String DB_USER = "tlrpgClient";
    private static final String DB_PASSWORD = "tlrpgClientpw";
    private int generatedKey = 1;


    public void insertIntoLeaderBoard(Game game) {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String insertTableSQL = "INSERT INTO userhighscore"
                + "(name, level, experience, turns, creaturesKilled, aliveAtExit) VALUES"
                + "(?,?,?,?,?,?)";

        try {
            dbConnection = getDBConnection();
            //preparedStatement = dbConnection.prepareStatement(insertTableSQL);
            preparedStatement = dbConnection.prepareStatement(insertTableSQL, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, "" + game.getPlayerName());
            preparedStatement.setInt(2, game.player.getLevel());
            preparedStatement.setDouble(3, game.player.getExperience());
            preparedStatement.setInt(4, game.getTurnsInGame());
            preparedStatement.setInt(5, game.getCreaturesDefeated());
            preparedStatement.setBoolean(6, game.player.isAlive());

            // execute insert SQL stetement
            preparedStatement.executeUpdate();

            // get the id of the last insert
            ResultSet rs = preparedStatement.getGeneratedKeys();
            generatedKey = 1;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }

            //DEBUG
            //System.out.println("Inserted record's ID: " + generatedKey);
            //System.out.println("Record is inserted into userhighscore table!");

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    public void updateLeaderBoard(Game game){

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        try {
            dbConnection = getDBConnection();

            String updateTableSQL = "UPDATE userhighscore SET name = ?, level = ?, experience = ?, turns = ?, creaturesKilled = ?, aliveAtExit = ?"
                    + " WHERE id = ?";
            preparedStatement = dbConnection.prepareStatement(updateTableSQL);

            preparedStatement.setString(1, "" + game.getPlayerName());
            preparedStatement.setInt(2, game.player.getLevel());
            preparedStatement.setDouble(3, game.player.getExperience());
            preparedStatement.setInt(4, game.getTurnsInGame());
            preparedStatement.setInt(5, game.getCreaturesDefeated());
            preparedStatement.setBoolean(6, game.player.isAlive());
            preparedStatement.setInt(7, generatedKey);

            preparedStatement.executeUpdate();


        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }



    }

    public void getLeaderboard(){
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        try {
            dbConnection = getDBConnection();

            String selectTableSQL = "SELECT * FROM userhighscore ORDER BY experience DESC LIMIT 5";
            preparedStatement = dbConnection.prepareStatement(selectTableSQL);

            ResultSet rs = preparedStatement.executeQuery();

            System.out.println("The follow is the top 5 on the current leaderboard");
            while (rs.next()) {

                String name = rs.getString("name");
                int level = rs.getInt("level");
                double experience = rs.getDouble("experience");
                int turns = rs.getInt("turns");
                int creaturesKilled = rs.getInt("creaturesKilled");
                boolean aliveAtExit = rs.getBoolean("aliveAtExit");
                Timestamp dateCreated = rs.getTimestamp("dateCreated");

                //I know all too well that this wont look very good with the spaces,
                // if I had more time i would look into using string format or some sort of padding method,
                // but time is short, corners must be cut
                System.out.print("Name : " + name + "    ");
                System.out.print("Level : " + level + "    ");
                System.out.print("Experience : " + experience + "    ");
                System.out.print("Turns : " + turns + "    ");
                System.out.print("Creatures Killed : " + creaturesKilled + "    ");
                System.out.print("Alive At Game Exit : " + aliveAtExit + "    ");
                System.out.print("Date Created : " + dateCreated + "    ");
                System.out.println();
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private static Connection getDBConnection() {

        Connection dbConnection = null;

        try {
            Class.forName(DB_DRIVER);

        } catch (ClassNotFoundException e) {

            System.out.println(e.getMessage());

        }

        try {
            dbConnection = DriverManager.getConnection(
                    DB_CONNECTION, DB_USER,DB_PASSWORD);
            return dbConnection;

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return dbConnection;

    }

}
