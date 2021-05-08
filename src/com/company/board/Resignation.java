package com.company.board;

import com.company.entities.Player;

public class Resignation extends Move {
    public Resignation(Player p){
        super(p);
    }

    @Override
    public String toString() {
        return "RESIGNS";
    }
}
