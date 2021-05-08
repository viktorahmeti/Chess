package com.company.board;

import com.company.enums.Color;
import com.company.entities.Player;
import com.company.pieces.*;

import java.sql.SQLOutput;

public class Board {
    private Square[][] squares;
    private Square whiteKing;
    private Square blackKing;
    private final String letters = "ABCDEFGH";

    public Board(){
        this.initialize();
    }

    private void initialize(){
        squares = new Square[8][8];
        squares[7][0] = new Square(new Position(7, 0), new Rook(Color.WHITE));
        squares[7][1] = new Square(new Position(7, 1), new Knight(Color.WHITE));
        squares[7][2] = new Square(new Position(7, 2), new Bishop(Color.WHITE));
        squares[7][3] = new Square(new Position(7, 3), new Queen(Color.WHITE));
        squares[7][4] = new Square(new Position(7, 4), new King(Color.WHITE));
        squares[7][5] = new Square(new Position(7, 5), new Bishop(Color.WHITE));
        squares[7][6] = new Square(new Position(7, 6), new Knight(Color.WHITE));
        squares[7][7] = new Square(new Position(7, 7), new Rook(Color.WHITE));

        whiteKing = squares[7][4];

        for(int i = 0; i < 8; i++){
            squares[6][i] = new Square(new Position(6, i), new Pawn(Color.WHITE));
        }

        squares[0][0] = new Square(new Position(0, 0), new Rook(Color.BLACK));
        squares[0][1] = new Square(new Position(0, 1), new Knight(Color.BLACK));
        squares[0][2] = new Square(new Position(0, 2), new Bishop(Color.BLACK));
        squares[0][3] = new Square(new Position(0, 3), new Queen(Color.BLACK));
        squares[0][4] = new Square(new Position(0, 4), new King(Color.BLACK));
        squares[0][5] = new Square(new Position(0, 5), new Bishop(Color.BLACK));
        squares[0][6] = new Square(new Position(0, 6), new Knight(Color.BLACK));
        squares[0][7] = new Square(new Position(0, 7), new Rook(Color.BLACK));

        blackKing = squares[0][4];

        for(int i = 0; i < 8; i++){
            squares[1][i] = new Square(new Position(1, i), new Pawn(Color.BLACK));
        }

        for(int row = 2; row <= 5; row++){
            for(int col = 0; col < 8; col++){
                squares[row][col] = new Square(new Position(row, col), null);
            }
        }
    }

    public String getLetter(int i){
        return String.valueOf(letters.charAt(i));
    }

    //if the game wants to be run on the command-line
    public void display(){
        System.out.println();
        for(int row = 0; row < squares.length; row++){
            System.out.print((8 - row) + " ");
            for(int col = 0; col < squares.length; col++){
                if(squares[row][col].getPiece() == null)
                    System.out.print("  ");
                else
                    System.out.print(squares[row][col].getPiece().getValue() + " ");
            }
            System.out.println();
        }
        System.out.print("  ");
        for(int i = 0; i < 8; i++){
            System.out.print(letters.charAt(i) + "  ");
        }
        System.out.println();
    }

    public Square getKingSquare(Player p){
        //implement this nicely
        if(p.getColor() == Color.WHITE)
            return whiteKing;
        else
            return blackKing;
    }

    public void setKingSquare(Square s, Player p){
        if(p.getColor() == Color.WHITE)
            whiteKing = s;
        else
            blackKing = s;
    }

    public void recordMove(Square startSquare, Square endSquare){
        startSquare.getPiece().hasMoved();
        endSquare.setPiece(startSquare.getPiece());
        startSquare.setPiece(null);

        //if necessary, update the position of the king
        if(endSquare.getPiece() instanceof King){
            if(endSquare.getPiece().getColor() == Color.WHITE)
                whiteKing = endSquare;
            else
                blackKing = endSquare;
        }

        //if necessary, promote the pawn
        if(endSquare.getPiece() instanceof Pawn){
            if(endSquare.getPiece().getColor() == Color.WHITE){
                if(endSquare.getPosition().getRow() == 0)
                    endSquare.setPiece(new Queen(Color.WHITE));
            }
            else{
                if(endSquare.getPosition().getRow() == 7)
                    endSquare.setPiece(new Queen(Color.BLACK));
            }
        }
    }

    public void castle(Castle move){
        Piece temp;
        if(move.kingSide()){
            if(move.getPlayer().getColor() == Color.WHITE){
                squares[7][6].setPiece(squares[7][4].getPiece());
                squares[7][4].setPiece(null);
                whiteKing = squares[7][6];

                squares[7][5].setPiece(squares[7][7].getPiece());
                squares[7][7].setPiece(null);
            }
            else{
                squares[0][6].setPiece(squares[0][4].getPiece());
                squares[0][4].setPiece(null);
                blackKing = squares[0][6];

                squares[0][5].setPiece(squares[0][7].getPiece());
                squares[0][7].setPiece(null);
            }
        }
        else{
            if(move.getPlayer().getColor() == Color.WHITE){
                squares[7][2].setPiece(squares[7][4].getPiece());
                squares[7][4].setPiece(null);
                whiteKing = squares[7][2];

                squares[7][3].setPiece(squares[7][0].getPiece());
                squares[7][0].setPiece(null);
            }
            else{
                squares[0][2].setPiece(squares[0][4].getPiece());
                squares[0][4].setPiece(null);
                blackKing = squares[0][2];

                squares[0][3].setPiece(squares[0][0].getPiece());
                squares[0][0].setPiece(null);
            }
        }
        move.getPlayer().setCastled();
    }

    public boolean contains(int row, int col){
        return (row >= 0 && row < 8) && (col >= 0 && col < 8);
    }

    public Square[][] getSquares(){
        return squares;
    }
}
