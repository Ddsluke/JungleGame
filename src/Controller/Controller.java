package Controller;

import Board.GameBoard;
import Player.Player;
import Piece.*;

public class Controller {
    private static Player player1;
    private static Player player2;
    private static String command;
    private static GameBoard gameBoard;

    private static void ini(){
        String name1 = "Player1";// If the users don't input their names these are their names
        String name2 = "Player2";// are shown as Player1 & Player2
        player1 = new Player(name1, 0);
        player2 = new Player(name2, 1);
        gameBoard = new GameBoard();
    };

    public static void main(String[] args){
        ini();
        while(true){
            inputFromUser();
            if (isValid()){
                move();
                if (hasWon(player1)){
                    PrintWin(player1);
                    // And do nothing
                }
                else if (hasWon(player2)){
                    PrintWin(player2);
                    //And do nothing
                }
            }
        }
    }

    private static void inputFromUser(){
        // To assign the value (from user input) to command and use isValid();
    };

    private static boolean hasWon(Player player){
        Piece piece = gameBoard.getPiece(3,8 * (1 - player.getSide()));
        if (piece != null){
            if (piece.getSide() == 0){
                return true;
            }
            else return false;
        }
        else return false;
    }

    private static void PrintWin(Player player){// To be changed when view is added
        System.out.println(player.getName() + " has won!");
    }

    private static void move(/*Data to be input.*/){
        // use move function from Piece package
    }

    private static boolean isValid(){
        return false;
    }
}
