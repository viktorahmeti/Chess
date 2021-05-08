package com.company.views;

import com.company.board.Board;

import javax.swing.*;
import java.awt.*;

public class BoardPainter extends JPanel {
    //how many by how many, so an int n
    //size of the squares
    int numOfSquares = 8;
    int sizeOfSquares = 60;
    int offset;
    int width;
    int height;
    Board board;
    JFrame korniza;

    public BoardPainter(Board board){
        offset = sizeOfSquares;
        this.width = sizeOfSquares*numOfSquares + 2*offset;
        this.height = sizeOfSquares*numOfSquares + 3*offset;
        this.board = board;

        korniza = new JFrame();
        korniza.setSize(width, height);
        korniza.setResizable(false);
        korniza.setName("A game of chess");
        korniza.setTitle("Chess");
        korniza.getContentPane().add(this);
        korniza.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        korniza.setVisible(true);
    }

    public void quit(){
        korniza.dispose();
    }

    private void paintBoard(Graphics g){
        int leftPos = offset;
        int topPos = offset;

        g.setFont(new Font("Serif", Font.PLAIN, 30));

        Color color1 = new Color(194, 173, 247);
        Color color2 = new Color(21, 102, 39);
        Color primaryColor = color1;
        boolean isBlack = true;

        for(int i = 0; i < numOfSquares; i++){
            for(int j = 0; j < numOfSquares; j++){
                g.setColor(primaryColor);
                g.fillRect(leftPos, topPos, sizeOfSquares, sizeOfSquares);

                //we change leftpos
                leftPos += sizeOfSquares;

                if(primaryColor.equals(color1)){
                    primaryColor = color2;
                }
                else{
                    primaryColor = color1;
                }
            }
            if(primaryColor.equals(color1)){
                primaryColor = color2;
            }
            else{
                primaryColor = color1;
            }
            leftPos = offset;
            topPos += sizeOfSquares;
        }
    }

    private void paintPieces(Graphics g){
        int leftPos = offset;
        int topPos = offset;

        g.setFont(new Font("Serif", Font.PLAIN, sizeOfSquares));
        g.setColor(Color.black);

        Color color1 = new Color(194, 173, 247);
        Color color2 = new Color(21, 102, 39);
        Color primaryColor = color2;
        boolean isBlack = true;

        for(int i = 0; i < numOfSquares; i++){
            for(int j = 0; j < numOfSquares; j++){
                //g.setColor(primaryColor);
                if(board.getSquares()[i][j].getPiece() != null){
                    g.drawString(board.getSquares()[i][j].getPiece().getValue(), leftPos, topPos + sizeOfSquares - (sizeOfSquares/10));
                }

                //we change leftpos
                leftPos += sizeOfSquares;
            }
            leftPos = offset;
            topPos += sizeOfSquares;
        }
    }

    private void paintNumbers(Graphics g){
        int leftPos = offset - sizeOfSquares;
        int topPos = offset;

        g.setFont(new Font("Serif", Font.PLAIN, sizeOfSquares/2));
        g.setColor(Color.black);

        for(int i = 0; i < numOfSquares; i++){
            g.drawString((8 - i) + "", leftPos + sizeOfSquares/3, topPos + sizeOfSquares - (sizeOfSquares/3));
            leftPos = offset - sizeOfSquares;
            topPos += sizeOfSquares;
        }
    }

    private void paintLetters(Graphics g){
        int leftPos = offset;
        int topPos = offset + numOfSquares * sizeOfSquares;

        g.setFont(new Font("Serif", Font.PLAIN, sizeOfSquares/2));
        g.setColor(Color.black);

        for(int i = 0; i < numOfSquares; i++){
            g.drawString(board.getLetter(i), leftPos + sizeOfSquares/4, topPos + sizeOfSquares - (sizeOfSquares/3));
            leftPos += sizeOfSquares;
        }
    }

    private void paintBackground(Graphics g){
        g.setColor(new Color(143, 143, 143));
        g.fillRect(0, 0, width, height);
    }

    public void paintComponent(Graphics g) {
        paintBackground(g);
        paintBoard(g);
        paintPieces(g);
        paintNumbers(g);
        paintLetters(g);
    }
}
