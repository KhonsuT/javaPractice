package dataBase;
import java.sql.Date;


public class Game {
    Long id;
    String name;
    String genre;
    Date releaseDate;
    public Game() {

    }

    public Game(Long id, String name, Date releaseDate, String genre) {
        this.id = id;
        this.genre = genre;
        this.name = name;
        this.releaseDate = releaseDate;
    }

    public String getName() {return name;}
    public Long getId() {return id;}
    public String getGenre() {return genre;}
    public Date getReleaseDate() {return releaseDate;}

    public void setName(String name) {this.name = name;}
    public void setReleaseDate(Date releaseDate) {this.releaseDate = releaseDate;}
    public void setGenre(String genre) {this.genre = genre;}
    public void setId(Long id) {this.id = id;}

    @Override
    public String toString() {
        return "Game{" +
            "id: " + id +
            "name: "+ name +
            "genre: "+ genre +
            "releasedate: " + releaseDate +
            "}";
    }
}