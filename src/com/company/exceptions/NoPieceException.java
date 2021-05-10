package com.company.exceptions;

import com.company.moves.Move;

import javax.swing.*;

public class NoPieceException extends Exception {
    Move move;

    public NoPieceException(Move move){
        this.move = move;
    }

    public void printMessage(){
        JOptionPane.showMessageDialog(null, String.format("The move %s is not valid since it doesn't move any piece!", move.toString()), "Chess", 0);
    }
}
