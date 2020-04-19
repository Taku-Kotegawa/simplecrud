package training;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class AssertionsDemoTest {

    public AssertionsDemoTest() {
        System.out.println("concstruct");
    }

    @Test
    void standardAssertionsTest() {
        assertEquals(2, 2);
        assertEquals(4, 4, "省略可能なアサーションメッセージは最後のパラメーター");
        assertTrue('a' < 'b', () -> "アサーションメッセージは遅延評価できる -- "
                + "不必要に複雑なメッセージを構築するコストを割けるために");
    }




    @Test
    void groupedAssertions() {

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");

        // アサーションをグループ化すると、すべてのアサーションが一度に実行され、
        // すべての失敗がまとめて報告される。
        assertAll("person",
                () -> assertEquals("John", person.getFirstName()),
                () -> assertEquals("Doe", person.getLastName())
        );
    }

    @Test
    void dependentAssertions() {
        // コードブロック内でアサーションが失敗すると、同じブロック内の後続のコードはスキップされる。

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");

        assertAll("properties",
                () -> {
                    String firstName = person.getFirstName();
                    assertNotNull(firstName);

                    // 上のアサーションが成功した場合のみ実行される。
                    assertAll("first name",
                            () -> assertTrue(firstName.startsWith("J")),
                            () -> assertTrue(firstName.endsWith("n"))
                    );
                },
                () -> {
                    // グループ化されたアサーションは、first name のアサーションとは独立して実行される。
                    String lastName = person.getLastName();
                    assertNotNull(lastName);

                    // 上のアサーションが成功した場合のみ実行される。
                    assertAll("last name",
                            () -> assertTrue(lastName.startsWith("D")),
                            () -> assertTrue(lastName.endsWith("e"))
                    );
                }
        );
    }

    @Test
    void exceptionTesting() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("a message");
        });
        assertEquals("a message", exception.getMessage());
    }

    @Test
    void timeoutNotExceeded() {
        // 次のアサーションは成功する。
        assertTimeout(ofMinutes(2), () -> {
            // 2分未満で終わるタスクを実行する。
        });
    }

    @Test
    void timeoutNotExceededWithResult() {
        // 次のアサーションは成功し、指定されたオブジェクトを返す。
        String actualResult = assertTimeout(ofMinutes(2), () -> {
            return "a result";
        });
        assertEquals("a result", actualResult);
    }

    @Test
    void timeoutNotExceededWithMethod() {
        // 次のアサーションは、メソッド参照を実行してオブジェクトを返す。
        String actualGreeting = assertTimeout(ofMinutes(2), AssertionsDemoTest::greeting);
        assertEquals("Hello, World!", actualGreeting);
    }

    @Disabled
    @Test
    void timeoutExceeded() {
        // 次のアサーションは、以下のようなエラーメッセージを出して失敗する:
        // execution exceeded timeout of 10 ms by 91 ms
        assertTimeout(ofMillis(10), () -> {
            // 10ミリ秒より時間のかかるタスクをシミュレートする。
            Thread.sleep(100);
        });
    }

    @Disabled
    @Test
    void timeoutExceededWithPreemptiveTermination() {
        // 次のアサーションは、以下のようなエラーメッセージを出して失敗する:
        // execution timed out after 10 ms
        assertTimeoutPreemptively(ofMillis(10), () -> {
            // 10ミリ秒より時間のかかるタスクをシミュレートする。
            Thread.sleep(100);
        });
    }

    private static String greeting() {
        return "Hello, World!";
    }

}
