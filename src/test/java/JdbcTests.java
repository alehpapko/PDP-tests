import utils.JdbcController;
import org.junit.jupiter.api.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcTests {

    @BeforeAll
    public static void setUp() {
        Assertions.assertNotNull(JdbcController.connectToDB());
        JdbcController.createDefaultTable();
    }

    @AfterAll
    public static void tearDown() {
        JdbcController.dropDefaultTableAfterTest();
        JdbcController.closeConnection();
    }

    @Test
    @DisplayName("Insert into table test")
    public void insertIntoTableTest() throws SQLException {
        //insert
        String query = "INSERT INTO test (ID, FIRST_NAME, LAST_NAME, TOWN) VALUES (412890, 'Leonardo', 'Di Caprio', 'Los Angeles')";
        JdbcController.executeSQL(query);
        //select
        query = "SELECT * FROM test";
        ResultSet resultSet = JdbcController.selectExecute(query);
        //assert
        assertAll("Should return inserted data",
                () -> assertEquals("412890", resultSet.getString("ID")),
                () -> assertEquals("Leonardo", resultSet.getString("FIRST_NAME")),
                () -> assertEquals("Di Caprio", resultSet.getString("LAST_NAME")),
                () -> assertEquals("Los Angeles", resultSet.getString("TOWN")));
    }

    @Test
    @DisplayName("Select from table test")
    public void selectFromTableTest() throws SQLException {
        //insert data for assert
        String query = "INSERT INTO test (ID, FIRST_NAME, LAST_NAME, TOWN) VALUES (412890, 'Leonardo', 'Di Caprio', 'Los Angeles')";
        JdbcController.executeSQL(query);
        //select
        query = "SELECT * FROM test WHERE id=412890";
        ResultSet resultSet = JdbcController.selectExecute(query);
        String expectedTown = "Los Angeles";
        String actualTown = resultSet.getString("town");
        assertEquals(expectedTown, actualTown);
    }

    @Test
    @DisplayName("Remove from table test")
    public void dropFromTableTest() throws SQLException {
        //insert record to removing
        String query = "INSERT INTO test (ID, FIRST_NAME, LAST_NAME, TOWN) VALUES (412890, 'Leonardo', 'Di Caprio', 'Los Angeles')";
        JdbcController.executeSQL(query);
        //remove
        query = "DELETE FROM test WHERE ID=412890";
        JdbcController.executeSQL(query);
        //assert
        query = "SELECT * FROM test";
        ResultSet resultSet = JdbcController.selectExecute(query);
        Assertions.assertEquals(0, resultSet.getRow());
    }

    @Test
    @DisplayName("Update records test")
    public void updateTest() throws SQLException {
        //insert record to update
        String query = "INSERT INTO test (ID, FIRST_NAME, LAST_NAME, TOWN) VALUES (412890, 'Leonardo', 'Di Caprio', 'Los Angeles')";
        JdbcController.executeSQL(query);
        //update
        query = "UPDATE test SET TOWN = 'Moscow' WHERE ID=412890";
        JdbcController.executeSQL(query);
        //assert
        query = "SELECT * FROM test WHERE ID=412890";
        ResultSet resultSet = JdbcController.selectExecute(query);
        String expectedTown = "Moscow";
        String actualTown = resultSet.getString("town");
        Assertions.assertEquals(expectedTown, actualTown);
    }
}
