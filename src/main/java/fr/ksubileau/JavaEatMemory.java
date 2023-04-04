
package fr.ksubileau;

import java.util.ArrayList;
import java.util.List;

public class JavaEatMemory {

  public static void main(String[] args) {

    List<byte[]> list = new ArrayList<>();
    int index = 1;

    while (true) {
      // 1MB each loop, 1 x 1024 x 1024 = 1048576
      byte[] b = new byte[1048576];
      list.add(b);
      Runtime rt = Runtime.getRuntime();
      System.out.printf("[%d] free memory: %s%n", index++, rt.freeMemory());
    }

  }
}