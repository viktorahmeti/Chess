package com.company.game;

import com.company.board.Board;
import com.company.moves.Resignation;
import com.company.enums.Color;
import com.company.views.BoardPainter;
import com.company.moves.Move;
import com.company.entities.Player;
import com.company.rules.Referee;
import com.company.views.IconProvider;

import javax.swing.*;
import java.util.ArrayList;

public class Game {
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Board board;
    private BoardPainter painter;
    private boolean draw = false;
    private Referee ref;
    private ArrayList<Move> moves;

    public Game(){
        greet();
        Player white = new Player(1800, Color.WHITE, IconProvider.WHITE_ICON);
        Player black = new Player(2000, Color.BLACK, IconProvider.BLACK_ICON);
        this.player1 = white;
        this.player2 = black;

        this.board = new Board();
        this.painter = new BoardPainter(board);
        this.ref = new Referee(board);
        this.moves = new ArrayList<>();
    }

    private void greet(){
        //Insert starting greet with rules and tips
        String standardMoveRule = "If you want to move a piece, specify the starting and landing positions as e.g D2-D4";
        String castleRule = "If you want to castle, specify whether you want to castle king-side O-O or queen-side O-O-O";
        String resignRule = "If you're sure you can't win, resign by writing R";

        String greet = String.format("Welcome to the Chess Game. Here are some tips to get you started:\n1: %s\n2: %s\n3: %s",
                standardMoveRule, castleRule, resignRule);

        JOptionPane.showMessageDialog(null, greet, "Chess", -1, IconProvider.CHESS_LOGO_ICON);
    }

    public void start(){
        //starts the game and the loop
        currentPlayer = player1;
        Move temp;
        while(!(player1.hasWon() || player2.hasWon() || endedInDraw())){
            temp = currentPlayer.move();
            if(temp instanceof Resignation)
                getOtherPlayer().makeWinner();
            else {
                if (ref.validateMove(temp)){
                    painter.repaint();
                    ref.check4check(this.getOtherPlayer());
                    moves.add(temp);
                } else
                    continue;
                switchPlayer();
            }
        }
        endGame();
    }

    private void endGame(){
        String message;
        if(player1.hasWon())
            message = player1.getName() + " is the winner!";
        else
            message = player2.getName() + " is the winner!";

        JOptionPane.showMessageDialog(null, String.format("GAME OVER\n%s", message), "Chess", -1, IconProvider.CHESS_LOGO_ICON);
        painter.quit();
        System.out.println(moves);
    }

    private void switchPlayer(){
       if(currentPlayer == player1)
           currentPlayer = player2;
       else
           currentPlayer = player1;
    }

    private Player getOtherPlayer(){
        if(currentPlayer == player1)
            return player2;
        else
            return player1;
    }

    public boolean endedInDraw(){
        return draw;
    }

}
