package com.company.pieces;

import com.company.board.Board;
import com.company.board.StandardMove;
import com.company.enums.Color;

public abstract class Piece {
    protected Color color;
    protected boolean alive;
    protected boolean hasMoved = false;
    protected String value; //this can later be a picture

    public Piece(Color c){
        this.color = c;
    }

    public abstract boolean canMove(StandardMove move, Board board);

    public void hasMoved(){
        hasMoved = true;
    }

    public boolean getHasMoved(){return hasMoved;}

    public Color getColor(){
        return color;
    }

    public String getValue(){
        return value;
    }
}
