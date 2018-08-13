import java.math.BigDecimal;

/**
 * Created by luohao on 2016/11/25.
 */
public class Test {
    public static void main(String[] args) {
   /*     int num = 500;
        do {
            num = num + 2;
            System.out.println(num);
        } while (num < 550);*/

     /*   double pow = Math.pow(2, 42);
        System.out.println(pow);
        BigDecimal decimal = new BigDecimal(pow);
        System.out.println(decimal.toPlainString());*/

        System.out.println(f(100));
    }


    public static long f(long x) {
        if (x == 0) {
            return 0;
        }

        return 2 * f(x - 1) + x * x;
    }
}
