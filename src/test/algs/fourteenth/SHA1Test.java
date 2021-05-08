package algs.fourteenth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnit4.class)
public class SHA1Test {

    @Test
    public void getHashTest() {
        assertEquals("da39a3ee 5e6b4b0d 3255bfef 95601890 afd80709",SHA1.getHash(""));
        assertEquals("ba79baeb 9f10896a 46ae7471 5271b7f5 86e74640",SHA1.getHash("Sha"));
        assertEquals("d8f45903 20e1343a 915b6394 170650a8 f35d6926",SHA1.getHash("sha"));
        assertEquals("2fd4e1c6 7a2d28fc ed849ee1 bb76e739 1b93eb12",SHA1.getHash("The quick brown fox jumps over the lazy dog"));
    }
}