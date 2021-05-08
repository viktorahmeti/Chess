package com.company.exceptions;

import javax.swing.*;

public class CastleWithBlockedRoadException extends Exception {
    public void printMessage(){
        JOptionPane.showMessageDialog(null, "You cannot castle when the squares in between are not free", "Chess", 0);
    }
}
