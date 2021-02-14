package algs;

public class NontrivialDivisors {
    private static final long START = 106732567L;
    private static final long END = 152673836L;

    public static void main(String[] args) {
        for (long i = START; i < END; i++) {
            double pow = Math.pow(i, 0.25);
            if (pow % 1 == 0 && isPrime(pow)) {
                System.out.println(i + " " + Math.pow(pow, 3));
            }
        }
    }

    private static boolean isPrime(double num) {
        for (int i = 2; i < Math.floor(Math.sqrt(num)); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
