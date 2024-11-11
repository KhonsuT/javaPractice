package concurrency;
import java.util.concurrent.*;
public class callbacks {
    public static void main(String[] args) {
        CompletableFuture<Void> futureResponse = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(5000);
                System.out.println("Response from HTTP request");
            } catch (Exception e) {
            }
        });

        CompletableFuture<Void> computationFuture = CompletableFuture.runAsync(() -> {
            // Some other task (e.g., computation)
            int sum = 0;
            for (int i = 0; i < 1000; i++) {
                sum += i;
            }
            System.out.println("Computation done: " + sum);
        });

        Runnable printS = new Runnable() {
            @Override
            public void run() {
                printStuff();
            }
        };

        CompletableFuture<Void> systemOutFuture = CompletableFuture.runAsync(printS);

        // Wait for both tasks to finish
        CompletableFuture.allOf(futureResponse, computationFuture, systemOutFuture).join();
        
    }
    static void printStuff() {
        for (int i: new int[8]) {
            System.out.println(i);
        }
    }

}
