package com.company.board;

import com.company.entities.Player;

public abstract class Move {
    protected Player player;

    public Move(Player p){
        this.player = p;
    }

    public Player getPlayer() {
        return player;
    }
}
