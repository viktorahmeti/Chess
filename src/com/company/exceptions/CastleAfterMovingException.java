package com.company.exceptions;

import javax.swing.*;

public class CastleAfterMovingException extends Exception {
    public void printMessage(){
        JOptionPane.showMessageDialog(null, "You cannot castle when either the King or Rook have moved!");
    }
}
