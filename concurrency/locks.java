package concurrency;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class locks {
    public static void main(String[] args) {
        int[] state = {1,2,3,4,5,6};
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReadState read1 = new ReadState(state, lock, 1);
        ReadState read2 = new ReadState(state, lock, 2);
        ReadState read3 = new ReadState(state, lock, 3);
        ReadState read4 = new ReadState(state, lock, 4);

        WriteState write1 = new WriteState(state, lock,1);
        WriteState write2 = new WriteState(state, lock, 2);

        // read1.start();
        // write2.start();
        // read2.start();
        // read3.start();
        // write1.start();
        // read4.start();
        String[][] timeZones= {{"Asia","Hong_Kong"},{"America","Mexico_City"}};
        String URL = "https://timeapi.io/api/time/current/zone?timeZone=%s%%2F%s";
        for (String[] timeZone: timeZones ) {
            new Thread(new GetTime(timeZone[0],timeZone[1], URL)).start();
        }
    }
}

class GetTime implements Runnable {
    String con = "";
    String city = "";
    String URL = "";
    public GetTime(String con,String city, String URL) {
        this.con = con;
        this.city = city;
        this.URL = URL;
    }

    @Override
    public void run() {
        try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest rq = HttpRequest.newBuilder().uri(URI.create(String.format(URL, con, city))).GET().build();

                HttpResponse<String> res = client.send(rq, HttpResponse.BodyHandlers.ofString());
                System.out.println(res.body());
            } catch(Exception e) {
                System.err.println(e);
            }
    }
}

class ReadState extends Thread{
    int[] state;
    int id;
    private final ReentrantReadWriteLock lock;
    public ReadState(int[] state, ReentrantReadWriteLock lock, int id) {
        this.state = state;
        this.lock = lock;
        this.id = id;
    }

    @Override
    public void run() {
        for (int i = 0; i<state.length; i++) {
            try {
                Thread.sleep(1000);
                System.out.println(String.format("Reading State from %d: index %d->%d",id,i,state[i]));
            } catch (Exception e) {
                System.err.println(e);
            } finally {
            }
        }
    }
}

class WriteState extends Thread{
    int[] state;
    int id;
    private final ReentrantReadWriteLock lock;
    public WriteState(int[] state, ReentrantReadWriteLock lock, int id) {
        this.state = state;
        this.lock = lock;
        this.id = id;
    }

    @Override
    public void run() {
        for (int i = 0; i<state.length; i++) {
            try {
                Thread.sleep(1000);
                lock.writeLock().lock();
                int temp = state[i];
                state[i] = new Random().nextInt(100);
                System.out.println(String.format("Writing State from %d at index %d Original: %d, New: %d",id, i, temp,state[i]));
            } catch (Exception e) {
                System.err.println(e);
            }
            finally {
                lock.writeLock().unlock();
            }
        }
    }
}


