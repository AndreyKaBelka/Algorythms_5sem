import algs.MD5;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(JUnit4.class)
public class MD5Test {
    @Test
    public void getHashTest() {
        assertEquals("1BC29B36F623BA82AAF6724FD3B16718",MD5.getHash("md5"));
        assertEquals("C93D3BF7A7C4AFE94B64E30C2CE39F4F",MD5.getHash("md4"));
        assertEquals("D41D8CD98F00B204E9800998ECF8427E",MD5.getHash(""));
    }
}
