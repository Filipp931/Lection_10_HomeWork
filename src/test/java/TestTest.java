import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class TestTest {
    @Test
    public void test(){
        App.factorialMultithreading(Paths.get("nums"));
    }
}