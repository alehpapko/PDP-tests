import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import utils.JdbcController;

public class MockTests {
    @Test
    public void testMock() {
        JdbcController jdbcControllerMock = Mockito.mock(JdbcController.class);

        Mockito.when(jdbcControllerMock.getNameById(10)).thenReturn("Bob");

        Assertions.assertEquals("Bob", jdbcControllerMock.getNameById(10));

        System.out.println(jdbcControllerMock.getNameById(10));
    }

    @Test
    public void testSpy() {
        JdbcController jdbcControllerMock = Mockito.spy(JdbcController.class);

        Mockito.when(jdbcControllerMock.getNameById(10)).thenReturn("Bob");

        Assertions.assertEquals("Bob", jdbcControllerMock.getNameById(10));

        System.out.println(jdbcControllerMock.getNameById(10));
    }
}
