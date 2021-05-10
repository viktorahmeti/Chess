package com.company.pieces;

import com.company.board.Board;
import com.company.moves.StandardMove;
import com.company.enums.Color;

public class Pawn extends Piece {
    public Pawn(Color c){
        super(c);
        if(color == Color.WHITE)
            this.value = "♙";
        else
            this.value = "♟";
    }

    //@TODO refactor this better
    //@TODO en-passant
    @Override
    public boolean canMove(StandardMove move, Board board) {
        int startRow = move.getStart().getRow();
        int startCol = move.getStart().getCol();
        int endRow = move.getLand().getRow();
        int endCol = move.getLand().getCol();

        int horDistance = startCol - endCol;
        int verDistance = startRow - endRow;

        //it all depends on the color
        if(color == Color.WHITE){
            //if it moved straight
            if(horDistance == 0){
                if(!hasMoved && verDistance == 2){
                    if(board.getSquares()[endRow][endCol].getPiece() != null || board.getSquares()[endRow + 1][endCol].getPiece() != null)
                        return false;
                    return true;
                }
                else{
                    if(verDistance == 1){
                       //check there is no piece at that place
                       if(board.getSquares()[endRow][endCol].getPiece() != null)
                           return false;
                       return true;
                    }
                    return false;
                }
            }
            //if it moved diagonally
            else{
                if(verDistance == 1){
                    if(horDistance == 1 || horDistance == -1){
                        if(board.getSquares()[endRow][endCol].getPiece() != null && board.getSquares()[endRow][endCol].getPiece().getColor() == Color.BLACK)
                            return true;
                        return false;
                    }
                    return false;
                }
                return false;
            }
        }
        else{
            //if it moved straight
            if(horDistance == 0){
                if(!hasMoved && verDistance == -2){
                    if(board.getSquares()[endRow][endCol].getPiece() != null || board.getSquares()[endRow - 1][endCol].getPiece() != null)
                        return false;
                    return true;
                }
                else{
                    if(verDistance == -1){
                        //check there is no piece at that place
                        if(board.getSquares()[endRow][endCol].getPiece() != null)
                            return false;
                        return true;
                    }
                    return false;
                }
            }
            //if it moved diagonally
            else{
                if(verDistance == -1){
                    if(horDistance == 1 || horDistance == -1){
                        if(board.getSquares()[endRow][endCol].getPiece() != null && board.getSquares()[endRow][endCol].getPiece().getColor() == Color.WHITE)
                            return true;
                        return false;
                    }
                    return false;
                }
                return false;
            }
        }
    }
}
