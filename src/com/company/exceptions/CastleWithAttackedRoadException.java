package com.company.exceptions;

import javax.swing.*;

public class CastleWithAttackedRoadException extends Exception {
    public void printMessage(){
        JOptionPane.showMessageDialog(null, "You cannot castle when the squares in between are attacked");
    }
}
