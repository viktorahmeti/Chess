package com.company.board;

import com.company.enums.Color;
import com.company.moves.Position;
import com.company.pieces.*;

public class Square {
    private Position position;
    private Piece piece;

    public Square(Position position, Piece piece) {
        this.position = position;
        this.piece = piece;
    }

    public Position getPosition() {
        return position;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    //call this when there is not neccesarily a piece on the board - color signifies the color of the piece that might be here
    public boolean isUnderAttack(Board board, Color color){
        //check for pawn attacks
        return isUnderDiagonalAttack(board, color) || isUnderRankAttack(board, color) ||
                isUnderKnightAttack(board, color) || isUnderPawnAttack(board, color);
    }

    //call this when there surely is a piece on the square
    public boolean isUnderAttack(Board board){
        return isUnderAttack(board, this.getPiece().getColor());
    }

    private boolean isUnderKnightAttack(Board board, Color color){
        Square temp;
        for(int row = -2; row <= 2; row++){
            for(int col = -2; col <= 2; col++){
                if(Math.abs(col * row) == 2){
                    if(board.contains(position.getRow() + row, position.getCol() + col)){
                        temp = board.getSquares()[position.getRow() + row][position.getCol() + col];
                        if(temp.getPiece() != null && temp.getPiece().getColor() != color && temp.getPiece() instanceof Knight)
                            return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isUnderDiagonalAttack(Board board, Color color){
        //go north east
        Square temp;
        for(int row = position.getRow() - 1, col = position.getCol() + 1; row >= 0 && col < 8; row--, col++){
            temp = board.getSquares()[row][col];
            if(temp.getPiece() != null){
                if(temp.getPiece().getColor() == color)
                    break;
                else{
                    if(temp.getPiece() instanceof Bishop || temp.getPiece() instanceof Queen)
                        return true;
                }
            }
        }

        //go south east
        for(int row = position.getRow() + 1, col = position.getCol() + 1; row < 8 && col < 8; row++, col++){
            temp = board.getSquares()[row][col];
            if(temp.getPiece() != null){
                if(temp.getPiece().getColor() == color)
                    break;
                else{
                    if(temp.getPiece() instanceof Bishop || temp.getPiece() instanceof Queen)
                        return true;
                }
            }
        }

        //go south-west
        for(int row = position.getRow() + 1, col = position.getCol() - 1; row < 8 && col >= 0; row++, col--){
            temp = board.getSquares()[row][col];
            if(temp.getPiece() != null){
                if(temp.getPiece().getColor() == color)
                    break;
                else{
                    if(temp.getPiece() instanceof Bishop || temp.getPiece() instanceof Queen)
                        return true;
                }
            }
        }

        //go north west
        for(int row = position.getRow() - 1, col = position.getCol() - 1; row >= 0 && col >= 0; row--, col--){
            temp = board.getSquares()[row][col];
            if(temp.getPiece() != null){
                if(temp.getPiece().getColor() == color)
                    break;
                else{
                    if(temp.getPiece() instanceof Bishop || temp.getPiece() instanceof Queen)
                        return true;
                }
            }
        }
        return false;
    }

    private boolean isUnderRankAttack(Board board, Color color){
        //row check up
        Square temp;
        if(position.getRow() < 7) {
            for (int i = position.getRow() + 1; i < 8; i++) {
                temp = board.getSquares()[i][position.getCol()];
                if(temp.getPiece() != null){
                    if(temp.getPiece().getColor() == color)
                        break;
                    else{
                        if(temp.getPiece() instanceof Rook || temp.getPiece() instanceof Queen)
                            return true;
                    }
                }
            }
        }

        //check row down
        if(position.getRow() > 0){
            for (int i = position.getRow() - 1; i >= 0; i--) {
                temp = board.getSquares()[i][position.getCol()];
                if(temp.getPiece() != null){
                    if(temp.getPiece().getColor() == color)
                        break;
                    else{
                        if(temp.getPiece() instanceof Rook || temp.getPiece() instanceof Queen)
                            return true;
                    }
                }
            }
        }

        //check col right
        if(position.getCol() < 7){
            for (int i = position.getCol() + 1; i < 8; i++) {
                temp = board.getSquares()[position.getRow()][i];
                if(temp.getPiece() != null){
                    if(temp.getPiece().getColor() == color)
                        break;
                    else{
                        if(temp.getPiece() instanceof Rook || temp.getPiece() instanceof Queen)
                            return true;
                    }
                }
            }
        }

        //check col left
        if(position.getCol() > 0){
            for (int i = position.getCol() - 1; i >= 0; i--) {
                temp = board.getSquares()[position.getRow()][i];
                if(temp.getPiece() != null){
                    if(temp.getPiece().getColor() == color)
                        break;
                    else{
                        if(temp.getPiece() instanceof Rook || temp.getPiece() instanceof Queen)
                            return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isUnderPawnAttack(Board board, Color color){
        if(color == Color.WHITE){
            if(this.position.getRow() == 0)
                return false;
            if(this.position.getCol() == 0){
                Square right = board.getSquares()[this.position.getRow() - 1][this.position.getCol() - 1];

                return right.getPiece() != null && right.getPiece().getColor() != color && right.getPiece() instanceof Pawn;
            }
            else if(this.position.getCol() == 7){
                Square left = board.getSquares()[this.position.getRow() - 1][this.position.getCol() + 1];
                return left.getPiece() != null && left.getPiece().getColor() != color && left.getPiece() instanceof Pawn;
            }
            else {
                Square left = board.getSquares()[this.position.getRow() - 1][this.position.getCol() + 1];
                Square right = board.getSquares()[this.position.getRow() - 1][this.position.getCol() - 1];

                return (left.getPiece() != null && left.getPiece().getColor() != color && left.getPiece() instanceof Pawn) ||
                        (right.getPiece() != null && right.getPiece().getColor() != color && right.getPiece() instanceof Pawn);
            }
        }
        else{
            if(this.position.getRow() == 7)
                return false;
            if(this.position.getCol() == 0){
                Square right = board.getSquares()[this.position.getRow() + 1][this.position.getCol() + 1];

                return right.getPiece() != null && right.getPiece().getColor() != color && right.getPiece() instanceof Pawn;
            }
            else if(this.position.getCol() == 7){
                Square left = board.getSquares()[this.position.getRow() + 1][this.position.getCol() - 1];
                return left.getPiece() != null && left.getPiece().getColor() != color && left.getPiece() instanceof Pawn;
            }
            else {
                Square left = board.getSquares()[this.position.getRow() + 1][this.position.getCol() - 1];
                Square right = board.getSquares()[this.position.getRow() + 1][this.position.getCol() + 1];

                return (left.getPiece() != null && left.getPiece().getColor() != color && left.getPiece() instanceof Pawn) ||
                        (right.getPiece() != null && right.getPiece().getColor() != color && right.getPiece() instanceof Pawn);
            }
        }
    }
}
