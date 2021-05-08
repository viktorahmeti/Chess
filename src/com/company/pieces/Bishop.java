package com.company.pieces;

import com.company.board.Board;
import com.company.board.StandardMove;
import com.company.enums.Color;

public class Bishop extends Piece {
    public Bishop(Color c){
        super(c);
        if(color == Color.WHITE)
            this.value = "♗";
        else
            this.value = "♝";
    }

    @Override
    public boolean canMove(StandardMove move, Board board) {
        int startRow = move.getStart().getRow();
        int startCol = move.getStart().getCol();
        int endRow = move.getLand().getRow();
        int endCol = move.getLand().getCol();

        int horDistance = startCol - endCol;
        int verDistance = startRow - endRow;

        //make sure we at least moved
        if(horDistance == 0)
            return false;

        //make sure that the move is at least that a bishop makes
        if(Math.abs(horDistance) == Math.abs(verDistance)){
            //we now ensure there is nothing in between
            if(horDistance < 0){
                if(verDistance < 0){//going south-east
                    for(int i = startRow + 1; i < endRow; i++){
                        if(board.getSquares()[i][++startCol].getPiece() != null)
                            return false;
                    }
                }
                else{//going north-east
                    for(int i = startRow - 1; i > endRow; i--){
                        if(board.getSquares()[i][++startCol].getPiece() != null)
                            return false;
                    }
                }
                return true;
            }
            else{
                if(verDistance < 0){//going south-west
                    for(int i = startRow + 1; i < endRow; i++){
                        if(board.getSquares()[i][--startCol].getPiece() != null)
                            return false;
                    }
                }
                else{//going north-west
                    for(int i = startRow - 1; i > endRow; i--){
                        if(board.getSquares()[i][--startCol].getPiece() != null)
                            return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
}
