package com.esiea.tp4A.domain;

import java.util.HashSet;
import java.util.Set;

public class PlanetMapImpl implements PlanetMap {

    final Position obstacle1 = Position.of(0, 1, Direction.NORTH);
    final Position obstacle2 = Position.of(0, 2, Direction.NORTH);
    final Position obstacle3 = Position.of(10, 4, Direction.NORTH);
    final Position obstacle4 = Position.of(30, 20, Direction.NORTH);
    Set<Position> obstacleSet = new HashSet<Position>();

    @Override
    public Set<Position> obstaclePositions() {
        // TODO Auto-generated method stub
        return this.obstacleSet;
    }

    public void ajout_obstacle() {
        this.obstacleSet.add(obstacle1);
        this.obstacleSet.add(obstacle2);
        this.obstacleSet.add(obstacle3);
        this.obstacleSet.add(obstacle4);
    }

}