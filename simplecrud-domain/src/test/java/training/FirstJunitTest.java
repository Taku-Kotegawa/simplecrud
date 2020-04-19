package training;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("初めてのテストコード")
public class FirstJunitTest {

    @Test
    @DisplayName("足し算の検算")
    void firstTest() {
        assertEquals(2, 1+1);
    }

}
