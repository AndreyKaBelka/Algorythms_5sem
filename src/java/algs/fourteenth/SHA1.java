package algs.fourteenth;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;

public class SHA1 {
    private static int h0 = 0x67452301;
    private static int h1 = 0xEFCDAB89;
    private static int h2 = 0x98BADCFE;
    private static int h3 = 0x10325476;
    private static int h4 = 0xC3D2E1F0;
    private static final int k1 = 0x5A827999;
    private static final int k2 = 0x6ED9EBA1;
    private static final int k3 = 0x8F1BBCDC;
    private static final int k4 = 0xCA62C1D6;

    private static int K(int b, int c, int d) {
        return (b & c) | (~b & d);
    }

    private static int D(int b, int c, int d) {
        return b ^ c ^ d;
    }

    private static int Z(int b, int c, int d) {
        return (b & c) | (b & d) | (c & d);
    }

    private static int cycleLeftShift(int k, int n) {
        return (k << n) | (k >>> (Integer.SIZE - n));
    }

    public static String getHash(String text) {
        return getHash(convertToInt(text));
    }

    public static int[] convertToInt(String word) {
        byte[] bytes = word.getBytes();
        StringBuilder binary = new StringBuilder();

        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }

        int[] res = new int[16];
        Arrays.fill(res, 0);
        binary.append(1);
        while (binary.length() % 512 != 448) {
            binary.append(0);
        }
        int[] bytes2 = getBytes(binary.toString());
        System.arraycopy(bytes2, 0, res, 0, bytes2.length);
        int[] length = getBinaryLength(word.length());
        System.arraycopy(length, 0, res, res.length - 2, 2);

        return res;
    }

    private static String getHash(int[] bytes) {
        for (int i = 0; ; i++) {
            int[] chunk = new int[80];
            Arrays.fill(chunk, 0);
            try {
                System.arraycopy(bytes, 16 * i, chunk, 0, 16);
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }

            for (int k = 16; k < 80; k++) {
                int temp = chunk[k - 3] ^ chunk[k - 8] ^ chunk[k - 14] ^ chunk[k - 16];
                int val = cycleLeftShift(temp, 1);
                chunk[k] = val;
            }
            int a = h0;
            int b = h1;
            int c = h2;
            int d = h3;
            int e = h4;

            for (int l = 0; l < 80; l++) {
                int f;
                int k;
                if (l <= 19) {
                    f = K(b, c, d);
                    k = k1;
                } else if (l <= 39) {
                    f = D(b, c, d);
                    k = k2;
                } else if (l <= 59) {
                    f = Z(b, c, d);
                    k = k3;
                } else {
                    f = D(b, c, d);
                    k = k4;
                }
                int temp = cycleLeftShift(a, 5) + f + e + k + chunk[l];
                e = d;
                d = c;
                c = cycleLeftShift(b, 30);
                b = a;
                a = temp;
            }
            h0 = h0 + a;
            h1 = h1 + b;
            h2 = h2 + c;
            h3 = h3 + d;
            h4 = h4 + e;
        }

        return Integer.toHexString(h0) + " " +
                Integer.toHexString(h1) + " " +
                Integer.toHexString(h2) + " " +
                Integer.toHexString(h3) + " " +
                Integer.toHexString(h4);
    }

    private static int[] getBinaryLength(int length) {
        return new int[]{0, length * 8};
    }

    private static int[] getBytes(String concat) {
        List<List<Integer>> len = concat
                .chars()
                .boxed()
                .collect(sliding());
        int[] bytes2 = new int[len.size()];
        for (int l = 0; l < len.size(); l++) {
            StringBuilder con = new StringBuilder();
            len.get(l).forEach(val -> con.append(val - 48));
            bytes2[l] = Long.valueOf(con.toString(), 2).intValue();
        }
        return bytes2;
    }

    private static <T> Collector<T, ?, List<List<T>>> sliding() {
        return new SlidingCollector<>(32, 32);
    }

    public static void main(String[] args) {
        System.out.println(getHash("The quick brown fox jumps over the lazy dog"));
    }
}
