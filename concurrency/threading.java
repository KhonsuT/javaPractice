package concurrency;
import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.locks.ReentrantLock;

public class threading {
    private static String URL = "https://timeapi.io/api/time/current/zone?timeZone=Europe%2FAmsterdam";
    private static String CATS_RANDOM = "";
    private static HttpClient client = HttpClient.newHttpClient();
    private static AtomicIntegerArray variable1 = new AtomicIntegerArray(new int[]{1,2,3,4,5,6});
    private static ReentrantLock lock = new ReentrantLock();
         
    public static void main(String[] args) {

        // HttpRequest rq = HttpRequest.newBuilder().uri(URI.create(URL+CATS_RANDOM)).GET().build();

        // CompletableFuture<HttpResponse<String>> futureResponse = client.sendAsync(rq, HttpResponse.BodyHandlers.ofString());

        // // Process the response when available
        // futureResponse.thenAccept(response -> {
        //     System.out.println("Status Code: " + response.statusCode());
        //     System.out.println("Response Body: " + response.body());
        // }).join(); // Block until the response is complete

        // System.out.println("Request sent asynchronously!");
        // GetTimeThread getTime1 = new GetTimeThread();
        // Thread getTime2 = new Thread(new GetTimeInterface());
        // getTime1.start();
        // getTime2.start();

        Thread mod1 = new Thread(new ModifyVariableInterface());
        Thread mod2 = new Thread(new ModifyVariableInterface());

        mod1.start();
        mod2.start();
    }

    //Http Req & Res using Threading
    static class GetTimeThread extends Thread {
        @Override
        public void run(){
            
            HttpRequest rq = HttpRequest.newBuilder().uri(URI.create(URL)).GET().build();
            HttpResponse<String> res = null;
            try {
                System.out.println("Sleeping");
                Thread.sleep(5000);
                res = client.send(rq, HttpResponse.BodyHandlers.ofString());
            }
            catch (IOException |InterruptedException e) {
                System.err.println(e);
            }
            System.out.println(res.statusCode());
            System.out.println(res.body());
        }
    } 
    static class GetTimeInterface implements Runnable {
        @Override
        public void run(){
            for (int i: new int[10]){
                try {
                    System.out.println("I am Running");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.err.println(e);
                }
            }
        }
    }

    //Variable Racing
    static class ModifyVariableThread extends Thread {
        @Override
        public void run() {
            synchronized (variable1) {
                try {
                    for (int i = 0; i<variable1.length(); i++) {
                        variable1.set(i, -1*variable1.get(i));
                        System.out.println(String.format("Thread1 at %d: %d", i, variable1.get(i)));
                    }
                } catch (Exception e) {
                }
            }
            
        }
    }

    static class ModifyVariableInterface implements  Runnable {
        @Override
        public void run() {
            lock.lock();  // Acquiring the lock
            try {
                for (int i = 0; i < variable1.length(); i++) {
                    int currentValue = variable1.get(i);
                    variable1.set(i, -currentValue);
                    System.out.println(String.format("Thread2 at %d: %d", i, variable1.get(i)));
                }
            } finally {
                lock.unlock();  // Releasing the lock
            }
        }
    }


}
