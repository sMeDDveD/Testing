package Fourth;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@Nested
@DisplayName("Test class")
public class Malyavko_Soloduha_Test {
    @BeforeAll
    static void start() {
        System.out.println("Testing started");
    }

    @AfterAll
    static void end() {
        System.out.println("Testing finished");
    }

    @Nested
    @DisplayName("Test suite")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class GroupOfTest {

        @BeforeEach
        void init(TestInfo testInfo) {
            System.out.println(testInfo.getDisplayName() + " started");
        }

        @AfterEach
        void destruct(TestInfo testInfo) {
            System.out.println(testInfo.getDisplayName() + " ended");
        }

        @Test
        @Order(3)
        @DisplayName("Test №1")
        void test1() {
            System.out.println("Checking test №1");
        }

        @Test
        @Order(2)
        @DisplayName("Test №2")
        void test2() {
            assertTrue(true);
        }

        @Test
        @Order(1)
        @DisplayName("Test №3")
        void test3() {
            fail("Failed test!");
        }
    }

}
