package com.company.pieces;

import com.company.board.Board;
import com.company.board.StandardMove;
import com.company.enums.Color;

public class Knight extends Piece {
    public Knight(Color c){
        super(c);
        if(color == Color.WHITE)
            this.value = "♘";
        else
            this.value = "♞";
    }

    @Override
    public boolean canMove(StandardMove move, Board board) {
        int startRow = move.getStart().getRow();
        int startCol = move.getStart().getCol();
        int endRow = move.getLand().getRow();
        int endCol = move.getLand().getCol();

        int horDistance = Math.abs(startCol - endCol);
        int verDistance = Math.abs(startRow - endRow);

        return horDistance * verDistance == 2;
    }
}
