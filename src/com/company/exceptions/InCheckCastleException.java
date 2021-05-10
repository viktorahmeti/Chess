package com.company.exceptions;

import javax.swing.*;

public class InCheckCastleException extends Exception{
    public void printMessage(){
        JOptionPane.showMessageDialog(null, "You cannot castle when in check!", "Chess", 0);
    }
}
