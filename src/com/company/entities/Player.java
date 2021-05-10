package com.company.entities;

import com.company.moves.Castle;
import com.company.moves.Move;
import com.company.moves.Resignation;
import com.company.moves.StandardMove;
import com.company.enums.Color;

import javax.swing.*;

public class Player {
    private String name;
    private Color color;
    private ImageIcon signatureIcon;
    private boolean hasWon;
    private boolean hasCastled = false;
    private int rating;

    public Player(int rating, Color color, ImageIcon signatureIcon) {
        this.rating = rating;
        this.color = color;
        this.signatureIcon = signatureIcon;
        setNameView();
    }

    private void setNameView(){
        String p = (String)JOptionPane.showInputDialog(null, String.format("Who's playing %s?", color.toString().toLowerCase()), "Chess", -1, signatureIcon, null, "");
        if(p==null)
            setNameView();
        this.name = p;
    }

    public Move move(){
        String p = (String)JOptionPane.showInputDialog(null,
                String.format("%s's turn: What is your move?", name),
                "Chess", -1, signatureIcon,
                null, "");
        if(p == null)
            return move();
        p = p.trim().toUpperCase();
        if(p.matches("^[A-H]{1}[1-8]{1}-[A-H]{1}[1-8]{1}+") ||
                p.matches("^[A-H]{1}[1-8]{1} [A-H]{1}[1-8]{1}+")){
            String start = p.substring(0, 2);
            String land = p.substring(3, 5);
            return new StandardMove(this, start, land);
        }
        else if(p.equals("O-O")){
            return new Castle(this, true);
        }
        else if(p.equals("O-O-O")){
            return new Castle(this, false);
        }
        else if(p.equals("R")){
            return new Resignation(this);
        }
        JOptionPane.showMessageDialog(null, "Please enter a valid move!",
                "Chess", JOptionPane.ERROR_MESSAGE);
        return move();
    }

    public boolean hasWon(){
        return hasWon;
    }

    public void makeWinner(){
        hasWon = true;
    }

    public Color getColor(){
        return color;
    }

    public void setCastled(){
        hasCastled = true;
    }

    public boolean hasCastled(){
        return hasCastled;
    }

    public String getName() {
        return name;
    }
}
