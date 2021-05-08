package com.company.rules;

import com.company.board.Position;
import com.company.board.StandardMove;
import com.company.exceptions.InvalidInputException;

public class Parser {
    private static String letters = "ABCDEFGH";
    public static void parseMove(StandardMove move) throws InvalidInputException {
        move.setStart(getPosition(move.getStartString()));
        move.setLand(getPosition(move.getLandString()));
    }

    private static Position getPosition(String str){
        int row = 8 - Integer.parseInt(String.valueOf(str.charAt(1)));
        int col = letters.indexOf(str.charAt(0));
        return new Position(row, col);
    }
}
