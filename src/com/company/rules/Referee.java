package com.company.rules;

import com.company.board.*;
import com.company.entities.Player;
import com.company.enums.Color;
import com.company.exceptions.*;
import com.company.pieces.King;
import com.company.pieces.Piece;

import javax.swing.*;

public class Referee {
    private Board board;

    public Referee(Board board) {
        this.board = board;
    }

    public boolean validateMove(Move mov){
        if(mov instanceof StandardMove){
            StandardMove move = (StandardMove)mov;
            Player player = move.getPlayer();
            //validate the input
            try{
                checkInput(move);
            }
            catch (InvalidInputException e){
                e.printMessage();
                return false;
            }

            //extract the piece to be moved
            Square start = board.getSquares()[move.getStart().getRow()][move.getStart().getCol()];
            Square land = board.getSquares()[move.getLand().getRow()][move.getLand().getCol()];

            //Check starting for piece and color
            try{
                checkMovingPiece(move, start);
            }
            catch(WrongPlayerException e){
                e.printMessage();
                return false;
            }
            catch(NoPieceException e){
                e.printMessage();
                return false;
            }

            //Check landing for piece and color
            try{
                checkLandingPiece(land, player);
            }
            catch (SameColorPieceException e){
                e.printMessage();
                return false;
            }

            //check for valid hop
            try{
                checkValidHop(start, move);
            }
            catch (InvalidMoveException e){
                e.printMessage();
                return false;
            }

            //check if we are in check
            try{
                wentInCheck(move, start, land);
            }
            catch (ExposedCheckException e){
                e.printMessage();
                return false;
            }
            catch(StillInCheckException e){
                e.printMessage();
                return false;
            }

            //if you used to be in a check, undo it
            undoCheck(player);

            //record the move
            board.recordMove(start, land);

            //notify that all is AOK
            return true;
        }
        else if(mov instanceof Castle){
            //see if the player has already castled
            try{
                seeIfCastled(mov);
            }
            catch (AlreadyCastledException e){
                e.printMessage();
                return false;
            }

            Castle move = (Castle)mov;

            //see if king has moved
            try{
                kingMoved(move);
            }
            catch (CastleAfterMovingException e){
                e.printMessage();
                return false;
            }

            //see if king is in check
            if(((King)board.getKingSquare(move.getPlayer()).getPiece()).inCheck())
                return false;

            //see if king is attacked
            try{
                rookMoved(move);
            }
            catch (CastleAfterMovingException e){
                e.printMessage();
                return false;
            }


            //see if squares in between are attacked
            try{
                inBetweenSquares(move);
            }
            catch (CastleWithBlockedRoadException e){
                e.printMessage();
                return false;
            }
            catch(CastleWithAttackedRoadException e){
                e.printMessage();
                return false;
            }

            //perform the move
            board.castle(move);


            //return true
            return true;
        }
        return false;
    }

    private void seeIfCastled(Move move) throws AlreadyCastledException{
        if(move.getPlayer().hasCastled())
            throw new AlreadyCastledException();
    }

    private void inBetweenSquares(Castle move) throws CastleWithAttackedRoadException, CastleWithBlockedRoadException{
        if(move.getPlayer().getColor() == Color.WHITE){
            if(move.kingSide()){
                //columns 5 and 6
                for(int i = 5; i <= 6; i++){
                    if(board.getSquares()[7][i].getPiece() == null){
                        if(board.getSquares()[7][i].isUnderAttack(board, Color.WHITE))
                            throw new CastleWithAttackedRoadException();
                    }
                    else{
                        throw new CastleWithBlockedRoadException();
                    }
                }
            }
            else{
                //columns 1, 2, and 3
                for(int i = 1; i <= 3; i++){
                    if(board.getSquares()[7][i].getPiece() == null){
                        if(board.getSquares()[7][i].isUnderAttack(board, Color.BLACK))
                            throw new CastleWithAttackedRoadException();
                    }
                    else{
                        throw new CastleWithBlockedRoadException();
                    }
                }
            }
        }
        else{
            if(move.kingSide()){
                //columns 5 and 6
                for(int i = 5; i <= 6; i++){
                    if(board.getSquares()[0][i].getPiece() == null){
                        if(board.getSquares()[0][i].isUnderAttack(board, Color.WHITE))
                            throw new CastleWithAttackedRoadException();
                    }
                    else{
                        throw new CastleWithBlockedRoadException();
                    }
                }
            }
            else{
                //columns 1, 2, and 3
                for(int i = 1; i <= 3; i++){
                    if(board.getSquares()[0][i].getPiece() == null){
                        if(board.getSquares()[0][i].isUnderAttack(board, Color.BLACK))
                            throw new CastleWithAttackedRoadException();
                    }
                    else{
                        throw new CastleWithBlockedRoadException();
                    }
                }
            }
        }
    }

    private void rookMoved(Castle move) throws CastleAfterMovingException{
        if(move.getPlayer().getColor() == Color.WHITE){
            if(board.getSquares()[7][7].getPiece() == null || board.getSquares()[7][7].getPiece().getHasMoved())
                throw new CastleAfterMovingException();
        }
        else{
            if(board.getSquares()[0][7].getPiece() == null || board.getSquares()[0][7].getPiece().getHasMoved())
                throw new CastleAfterMovingException();
        }
    }

    private void kingMoved(Castle move) throws CastleAfterMovingException{
        if(board.getKingSquare(move.getPlayer()).getPiece().getHasMoved())
            throw new CastleAfterMovingException();
    }

    public void check4check(Player player){
        //see if the new player is now checked, and make the appropriate changes
        Square kingSquare = board.getKingSquare(player);
        if(kingSquare.isUnderAttack(board)){
            ((King)board.getKingSquare(player).getPiece()).setCheck(true);
            JOptionPane.showMessageDialog(null,
                    String.format("%s is now in check!", player.getColor().getValue()));
        }
    }

    private void undoCheck(Player player){
        if(((King)board.getKingSquare(player).getPiece()).isInCheck()){
            ((King)board.getKingSquare(player).getPiece()).setCheck(false);
        }
    }

    private void checkInput(StandardMove move) throws InvalidInputException{
        //validate the input and fix the move
        Parser.parseMove(move);
    }

    private void checkMovingPiece(Move move, Square start) throws WrongPlayerException, NoPieceException {
        //Check starting for piece and color
        if(start.getPiece() == null) {
            throw new NoPieceException(move);
        }
        else if(start.getPiece().getColor() != move.getPlayer().getColor()){
            throw new WrongPlayerException(move.getPlayer().getColor());
        }
    }

    private void checkLandingPiece(Square land, Player player) throws SameColorPieceException {
        //Check landing for piece and color
        if(land.getPiece() != null && land.getPiece().getColor() == player.getColor()){
            throw new SameColorPieceException();
        }
    }

    private void checkValidHop(Square start, StandardMove move) throws InvalidMoveException {
        //check if the hop is valid
        if(!start.getPiece().canMove(move, board))
            throw new InvalidMoveException(move);
    }

    private void wentInCheck(Move move, Square start, Square land) throws ExposedCheckException, StillInCheckException{
        boolean temp1 = false;
        Piece temp = land.getPiece();
        land.setPiece(start.getPiece());
        start.setPiece(null);

        if(land.getPiece() instanceof King) {
            board.setKingSquare(land, move.getPlayer());
            temp1 = ((King)land.getPiece()).isInCheck();
        }

        //now the board has been altered, so we can check if the king is actually exposed
        Square kingSquare = board.getKingSquare(move.getPlayer());
        if(kingSquare.isUnderAttack(board)){
            //retrace steps and return false
            start.setPiece(land.getPiece());
            land.setPiece(temp);
            if(start.getPiece() instanceof King)
                board.setKingSquare(start, move.getPlayer());
            if(!temp1)
                throw new ExposedCheckException(move);
            else
                throw new StillInCheckException(move);
        }
        start.setPiece(land.getPiece());
        land.setPiece(temp);
        if(start.getPiece() instanceof King)
            board.setKingSquare(start, move.getPlayer());
    }
}
