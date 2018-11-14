package Controller;

import Board.GameBoard;
import Player.Player;
import Piece.*;

public class Controller {
    private static Player player1;
    private static Player player2;
    private static String command;
    private static GameBoard gameBoard;
    private static Round round;
    private static void ini(){
        String name1 = "Player1";// If the users don't input their names these are their names
        String name2 = "Player2";// are shown as Player1 & Player2
        // The user input their names,and we use setName() to set their names
        player1 = new Player(name1, 0);
        player2 = new Player(name2, 1);
        gameBoard = new GameBoard();
        round = new Round();
    }

    public static void main(String[] args){
        ini();
        while(true){
            inputFromUser();
            if (isValid()){
                action();// move, save, load
                if (hasWon(player1)){
                    PrintWin(player1);
                    // And do nothing until the user does something
                }
                else if (hasWon(player2)){
                    PrintWin(player2);
                    //And do nothing until the user does something
                }
                round.nextRound();
            }
            else continue;
        }
    }

    private static void inputFromUser(){
        // To assign the value (from user input) to command and use isValid();
    }

    private static boolean hasWon(Player player){
        Piece piece = gameBoard.getPiece(3,8 * (1 - player.getSide()));
        if (piece != null){
            if (piece.getSide() == player.getSide()){
                return true;
            }
            else return false;
        }
        else return false;
    }

    private static void PrintWin(Player player){// To be changed when view is added
        /*System.out.println(player.getName() + " has won!");*/
        // To print on the screen that the player has won.
    }

    private static void action(/*Data to be input.*/){
        // use functions(move, save, load)
    }

    private static boolean isValid(){
        return false;// If there is a response from the move function in Piece
        // If the piece the player controls is on the same side as the player(check the round)
    }
}
