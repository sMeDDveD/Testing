package Fourth;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class Malyavko_Soloduha_Test {
    @BeforeAll
    static void setUp() {
        System.out.println("Initialization...");
    }

    @AfterAll
    static void tearDown() {
        System.out.println("Closing...");
    }

    @Test
    public void test1() {
        System.out.println("I am test №1!");
    }

    @Test
    public void test2() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void test3() {
        fail("Fail!");
    }
}
