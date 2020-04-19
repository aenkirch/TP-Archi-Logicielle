package com.esiea.tp4A.domain;

public class Players {
    private final long id;
    private final String name;
    private final MarsRover rover;

    public Players(long id, String name,MarsRover rover,int range) {
        this.id = id;
        this.name = name;
        this.rover=rover;
        this.rover.configureLaserRange(range);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public MarsRover getRover(){
        return rover;
    }




}
