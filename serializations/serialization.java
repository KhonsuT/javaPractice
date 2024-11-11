package serializations;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
class serialization {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        String filename = "/home/derek/projects/javaPractice/serializations/serialized.ser";
        TVshow flash = new TVshow("Flash Season 1", new Date(), 10);
        TVshow arrow = new TVshow("Arrow Season 1", new Date(), 12);
        List<TVshow> tvshows = new ArrayList<>();
        tvshows.add(flash);
        tvshows.add(arrow);
        try (ObjectOutputStream out  = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(flash);
            out.writeObject(arrow);
            out.writeObject(tvshows);
            System.out.println("Serialized");
        }
        catch (IOException e ){
            System.err.println(e);
        }
        
        TVshow deserializedFlash = null;
        TVshow deserializedArrow = null;
        List<TVshow> deserializedTVshow = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream((filename)))) {
            deserializedFlash = (TVshow) in.readObject();
            deserializedArrow = (TVshow) in.readObject();
            deserializedTVshow = (List<TVshow>) in.readObject();
            System.out.println("Deserialized");
        }
        catch (IOException | ClassNotFoundException e) {
            System.err.println(e);
        }

        System.out.println("Original:" + flash.name);
        System.out.println("Deserialized" + deserializedFlash.name);
        System.out.println("Original:" + arrow.name);
        System.out.println("Deserialized" + deserializedArrow.name);
        System.out.println(flash);
        System.out.println(deserializedFlash);
        System.out.println(tvshows);
        System.out.println(deserializedTVshow);
        System.out.println(tvshows.get(0).name);
        System.out.println(deserializedTVshow.get(1).name);
    }
}

class TVshow implements Serializable {
    private static final long serialVersionUID = 1L;
    String name = "";
    Date airDate = new Date();
    int episodes = 0;

    public TVshow (String name, Date airDate, int episodes) {
        this.name = name;
        this.airDate = airDate;
        this.episodes = episodes;
    }
}
