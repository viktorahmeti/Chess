package com.company.exceptions;

import com.company.board.Move;

import javax.swing.*;

public class InvalidMoveException extends Exception {
    Move move;

    public InvalidMoveException(Move move){
        this.move = move;
    }

    public void printMessage(){
        JOptionPane.showMessageDialog(null, String.format("The move %s is not a valid move", move.toString()), "Chess", 0);
    }
}
