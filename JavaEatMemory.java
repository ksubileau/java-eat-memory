
import java.util.ArrayList;
import java.util.List;
import java.lang.Thread;

public class JavaEatMemory {

    public static void main(String[] args) {

        List<byte[]> list = new ArrayList<>();
        int index = 1;
        int delay = 0;

        try {
            delay = Integer.parseInt(System.getenv().getOrDefault("CRASH_WAIT_SECONDS", "60"));
        } catch (Exception e) {
            System.err.printf("Failed to parse CRASH_WAIT_SECONDS environment value");
            return;
        }

        try {
            Thread.sleep(delay * 1000);
        } catch (Exception e) {
        }

        while (true) {
            // 1MB each loop, 1 x 1024 x 1024 = 1048576
            byte[] b = new byte[1048576];
            list.add(b);
            Runtime rt = Runtime.getRuntime();
            System.out.printf("[%d] free memory: %s%n", index++, rt.freeMemory());
        }

    }
}