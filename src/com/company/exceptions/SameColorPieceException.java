package com.company.exceptions;

import com.company.board.Move;

import javax.swing.*;

public class SameColorPieceException extends Exception {

    public SameColorPieceException(){
        super();
    }

    public void printMessage(){
        JOptionPane.showMessageDialog(null, "You cannot take a piece of the same color!", "Chess", 0);
    }
}
