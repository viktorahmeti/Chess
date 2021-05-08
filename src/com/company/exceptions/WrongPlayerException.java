package com.company.exceptions;

import com.company.enums.Color;

import javax.swing.*;

public class WrongPlayerException extends Exception {
    Color color;

    public WrongPlayerException(Color color){
        this.color = color;
    }

    public void printMessage(){
        JOptionPane.showMessageDialog(null, String.format("It is %s's turn to play!", color.getValue().toLowerCase()), "Chess", 0);
    }
}
