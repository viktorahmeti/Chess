package com.company.pieces;

import com.company.board.Board;
import com.company.board.StandardMove;
import com.company.enums.Color;

public class Rook extends Piece {

    public Rook(Color c){
        super(c);
        if(color == Color.WHITE)
            this.value = "♖";
        else
            this.value = "♜";
    }

    @Override
    public boolean canMove(StandardMove move, Board board) {
        int startRow = move.getStart().getRow();
        int startCol = move.getStart().getCol();
        int endRow = move.getLand().getRow();
        int endCol = move.getLand().getCol();

        //check if on the same row
        if(startRow == endRow && startCol != endCol){
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
