package com.company.exceptions;

import javax.swing.*;

public class AlreadyCastledException extends Exception {
    public void printMessage(){
        JOptionPane.showMessageDialog(null, "You have already castled!");
    }
}
