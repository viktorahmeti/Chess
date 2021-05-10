package com.company.pieces;

import com.company.board.Board;
import com.company.moves.StandardMove;
import com.company.enums.Color;

public class King extends Piece {
    private boolean inCheck = false;

    public King(Color c){
        super(c);
        if(color == Color.WHITE)
            this.value = "♔";
        else
            this.value = "♚";
    }

    public boolean inCheck(){
        return inCheck;
    }

    public boolean isInCheck(){
        return inCheck;
    }

    public void setCheck(boolean p){
        inCheck = p;
    }

    @Override
    public boolean canMove(StandardMove move, Board board){
        int startRow = move.getStart().getRow();
        int startCol = move.getStart().getCol();
        int endRow = move.getLand().getRow();
        int endCol = move.getLand().getCol();

        int horDistance = Math.abs(startCol - endCol);
        int verDistance = Math.abs(startRow - endRow);

        //ensure movement
        if(horDistance == 0 && verDistance == 0)
            return false;
        if(horDistance > 1 || verDistance > 1)
            return false;
        return true;
    }
}
