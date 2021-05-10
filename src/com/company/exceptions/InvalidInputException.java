package com.company.exceptions;

import com.company.moves.Move;

import javax.swing.*;

public class InvalidInputException extends Exception {
    Move move;
    public InvalidInputException(Move move){
        this.move = move;
    }

    public void printMessage(){
        JOptionPane.showMessageDialog(null, String.format("The move %s does not represent a valid move on the board!", move.toString()), "Chess", 0);
    }
}
