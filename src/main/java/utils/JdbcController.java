package utils;

import exceptions.NotSelectQueryException;

import java.sql.*;

public class JdbcController {
    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;
    public static final String DB_URL = "jdbc:h2:./test";

    public static Connection connectToDB() {
        try {
            connection = DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void executeSQL(String query) {
        try {
            statement = connectToDB().createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet selectExecute(String query) throws NotSelectQueryException {
        if (!query.startsWith("SELECT")) {
            throw new NotSelectQueryException("Not SELECT query. Query should starts with SELECT.");
        } else {
            try {
                statement = connectToDB().createStatement();
                resultSet = statement.executeQuery(query);
                resultSet.next();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultSet;
    }

    public static void dropDefaultTableAfterTest() {
        try {
            statement = connectToDB().createStatement();
            statement.execute("DROP TABLE test");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createDefaultTable() {
        String query = "CREATE TABLE test ("
                + "ID INTEGER NOT NULL,"
                + "FIRST_NAME VARCHAR(45) NOT NULL,"
                + "LAST_NAME VARCHAR(45) NOT NULL,"
                + "TOWN VARCHAR(45));";
        executeSQL(query);
    }

    public String getNameById(int id) {
        //method for mock
        String name = "";
        try {
            PreparedStatement preparedStatement = connectToDB().prepareStatement("SELECT FIRST_NAME FROM test WHERE ID=?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            if (resultSet.getRow() > 0) {
                name = resultSet.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }
}
