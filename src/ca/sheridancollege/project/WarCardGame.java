/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;



/**
 * The class that models your game. You should create a more specific child of this class and instantiate the methods
 * given.
 *
 * @author Mridul
 * @author Paul Bonenfant Jan 2020
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WarCardGame {
    public static void main(String[] args) {
        // Player registration
        try (Scanner scanner = new Scanner(System.in)) {
            // Player registration
            System.out.println("Player 1, enter your name:");
            Player player1 = new Player(scanner.nextLine());
            System.out.println("Player 2, enter your name:");
            Player player2 = new Player(scanner.nextLine());
            
            // Game initialization
            Deck deck = new Deck();
            deck.shuffle();
            List<Card> deck1 = deck.deal(26);
            List<Card> deck2 = deck.deal(26);
            
            int roundCount = 0;
            
            // Game loop
            while (!deck1.isEmpty() && !deck2.isEmpty() && roundCount < 15) {
                roundCount++;
                System.out.println("\nRound " + roundCount + ":");
                
                Card p1Card = deck1.remove(0);
                Card p2Card = deck2.remove(0);
                
                System.out.println(player1.getName() + " plays card: " + p1Card);
                System.out.println(player2.getName() + " plays card: " + p2Card);
                
                if (p1Card.getRank() > p2Card.getRank()) {
                    player1.updateScore(1);
                    System.out.println(player1.getName() + " wins the round.");
                } else if (p1Card.getRank() < p2Card.getRank()) {
                    player2.updateScore(1);
                    System.out.println(player2.getName() + " wins the round.");
                } else {
                    System.out.println("War!");
                    
                    if (deck1.size() < 3 || deck2.size() < 3) {
                        System.out.println("Not enough cards for war. Game Over!");
                        break;
                    }
                    
                    // Place three additional cards face down
                    List<Card> warCards1 = new ArrayList<>(deck1.subList(0, 3));
                    List<Card> warCards2 = new ArrayList<>(deck2.subList(0, 3));
                    deck1.removeAll(warCards1);
                    deck2.removeAll(warCards2);
                    
                    // Reveal the next card if available
                    if (!deck1.isEmpty() && !deck2.isEmpty()) {
                        Card warCard1 = deck1.remove(0);
                        Card warCard2 = deck2.remove(0);
                        
                        System.out.println(player1.getName() + " plays war card: " + warCard1);
                        System.out.println(player2.getName() + " plays war card: " + warCard2);
                        
                        if (warCard1.getRank() > warCard2.getRank()) {
                            player1.updateScore(4);
                            System.out.println(player1.getName() + " wins the war round.");
                        } else {
                            player2.updateScore(4);
                            System.out.println(player2.getName() + " wins the war round.");
                        }
                    } else {
                        System.out.println("Not enough cards for war. Game Over!");
                        break;
                    }
                }
                
                System.out.println(player1.getName() + "'s score: " + player1.getScore());
                System.out.println(player2.getName() + "'s score: " + player2.getScore());
            }
            
            // Determine the winner
            if (player1.getScore() > player2.getScore()) {
                System.out.println(player1.getName() + " wins the game!");
            } else if (player1.getScore() < player2.getScore()) {
                System.out.println(player2.getName() + " wins the game!");
            } else {
                System.out.println("It's a tie!");
            }
        }
    }
}
