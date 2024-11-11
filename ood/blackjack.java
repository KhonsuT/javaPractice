package ood;
import java.util.*;
public class blackjack {
    List<Card> Deck;
    int numberOfDeck = 0;
    List<Player> players;
    House house;

    public blackjack(List<Players> players, int numberOfDecks) {
        this.deck = new Deck(numberOfDecks); //Deck class is contructored and return based on numberOfDecks a List<Card>
        this.players = players;
        this.house = new House();
    }

    public start() {
        //start the game with the current players and Deck("remaining of the deck " or a new deck)
    }

    public reset() {
        //reset the deck base on numberOfDeck
    }
    public reset(int numberOfDeck) {
        //reset the deck base on numberOfDeck
        // update the internal variable and reset the deck
        this.numberOfDeck = numberOfDeck;
    }

    public addPlayer(Player player) {
        players.add(player);
    }

    public stop() {
        //calculation of all players winning and out a killscreen with rankings
    }

    public restart() {
        //reset players and new list of players
    }
}

class Card{
    int value;
    String face;

}

class Person {
    String name;
   
    public hit() {
        //ask a new card from the deck
    }

    public stand() {
        // pass give up go next
    }
}

class Player extends Person {
    public buyChips () {

    }
    public cashOut () {

    }

    public double() {
        // one more card only and bet()
    }

    public bet() {
        // amount
    }
}

class House extends Person{
    // have a set of runs that whether or not it wants to call
    // if value of the card is less than 17 it will call
    

}