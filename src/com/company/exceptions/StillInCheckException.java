package com.company.exceptions;

import com.company.moves.Move;

import javax.swing.*;

public class StillInCheckException extends Exception {
    Move move;
    public StillInCheckException(Move move){
        this.move = move;
    }

    public void printMessage(){
        JOptionPane.showMessageDialog(null, String.format("The move %s cannot be made since the king doesn't get out of the check!", move.toString()), "Chess", 0);
    }
}
