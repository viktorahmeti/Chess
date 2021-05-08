package com.company.board;

import com.company.entities.Player;

public class Castle extends Move {
    private boolean kingSide;
    public Castle(Player p, boolean kingSide){
        super(p);
        this.kingSide = kingSide;
    }

    public boolean kingSide(){
        return kingSide;
    }

    @Override
    public String toString(){
        if(kingSide)
            return "O-O";
        else
            return "O-O-O";
    }

}
