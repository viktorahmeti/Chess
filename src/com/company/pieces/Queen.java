package com.company.pieces;

import com.company.board.Board;
import com.company.board.StandardMove;
import com.company.enums.Color;

public class Queen extends Piece {

    public Queen(Color c){
        super(c);
        if(color == Color.WHITE)
            this.value = "♕";
        else
            this.value = "♛";
    }

    @Override
    public boolean canMove(StandardMove move, Board board) {
        //the queen can either move like a bishop or like a rook - so we copy-paste everything
        int startRow = move.getStart().getRow();
        int startCol = move.getStart().getCol();
        int endRow = move.getLand().getRow();
        int endCol = move.getLand().getCol();

        int horDistance = startCol - endCol;
        int verDistance = startRow - endRow;

        if(Math.abs(horDistance) == Math.abs(verDistance)){
            //make sure we at least moved
            if(horDistance == 0)
                return false;

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
        else if(startRow == endRow && startCol != endCol){
            //check the boxes in the between
            int i = Math.min(startCol, endCol);
            int j = (i == startCol)? endCol : startCol;

            i++;
            while(i < j){
                if(board.getSquares()[startRow][i].getPiece() != null)
                    return false;
                i++;
            }
            return true;
        }
        else if(startCol == endCol && startRow != endRow){
            //check the boxes in the between
            int i = Math.min(startRow, endRow);
            int j = (i == startRow)? endRow : startRow;

            i++;
            while(i < j){
                if(board.getSquares()[i][startCol].getPiece() != null)
                    return false;
                i++;
            }
            return true;
        }
        return false;
    }
}
