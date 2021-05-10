package com.company.moves;

import com.company.entities.Player;

public class StandardMove extends Move {
    private Position start;
    private Position land;
    private String startString;
    private String landString;

    public StandardMove(Player player, Position start, Position land) {
        super(player);
        this.start = start;
        this.land = land;
    }

    public StandardMove(Player player, String startString, String landString){
        super(player);
        this.startString = startString;
        this.landString = landString;
    }

    public void setStart(Position start) {
        this.start = start;
    }

    public void setLand(Position land) {
        this.land = land;
    }

    public Position getStart() {
        return start;
    }

    public Position getLand() {
        return land;
    }

    public String getStartString() {
        return startString;
    }

    public String getLandString() {
        return landString;
    }

    public String toString(){
        return String.format("%s-%s", getStartString(), getLandString());
    }

}
