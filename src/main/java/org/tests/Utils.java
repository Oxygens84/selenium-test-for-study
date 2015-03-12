package org.tests;

import org.openqa.selenium.TimeoutException;

public class Utils {

    public static void tryAttempts(int times, Runnable executor) {
        int attempt = 0;
        while (attempt < times) {
            try {
                executor.run();
                return;
            } catch (TimeoutException ex) {
                attempt++;
                System.out.println("   attempt " + attempt + " failed");
            }
        }
        throw new RuntimeException("Unsuccessful operation");
    }

}
