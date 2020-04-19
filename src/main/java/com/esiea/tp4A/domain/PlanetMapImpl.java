package com.esiea.tp4A.domain;

import java.util.HashSet;
import java.util.Set;

public class PlanetMapImpl implements PlanetMap {

    private final Position obstacle1 = Position.of(0, 1, Direction.NORTH);
    private final Position obstacle2 = Position.of(0, 2, Direction.NORTH);
    private final Position obstacle3 = Position.of(10, 4, Direction.NORTH);
    private final Position obstacle4 = Position.of(30, 20, Direction.NORTH);
    private final Set<Position> obstacleSet = new HashSet<Position>();

    @Override
    public Set<Position> obstaclePositions() {
        return this.obstacleSet;
    }
    public void ajout_obstacle() {
        this.obstacleSet.add(obstacle1);
        this.obstacleSet.add(obstacle2);
        this.obstacleSet.add(obstacle3);
        this.obstacleSet.add(obstacle4);
    }

    public void ajout_obstacle_dynamique(Position o){
        this.obstacleSet.add(o);
    }


}
