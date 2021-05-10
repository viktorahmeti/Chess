package com.company.exceptions;

import com.company.moves.Move;

import javax.swing.*;

public class ExposedCheckException extends Exception{
    Move move;
    public ExposedCheckException(Move move){
        this.move = move;
    }

    public void printMessage(){
        JOptionPane.showMessageDialog(null, String.format("The move %s cannot be made since it exposes the king!", move.toString()), "Chess", 0);
    }
}
