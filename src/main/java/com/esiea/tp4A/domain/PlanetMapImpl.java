package com.esiea.tp4A.domain;

import java.util.HashSet;
import java.util.Set;

public class PlanetMapImpl implements PlanetMap {

    final Position obstacle1 = Position.of(0, 1, Direction.NORTH);
    final Position obstacle2 = Position.of(0, 2, Direction.NORTH);
    final Position obstacle3 = Position.of(10, 4, Direction.NORTH);
    final Position obstacle4 = Position.of(30, 20, Direction.NORTH);

    @Override
    public Set<Position> obstaclePositions() {
        // TODO Auto-generated method stub
        Set<Position> obstacleSet = new HashSet<Position>();
        obstacleSet.add(obstacle1);
        obstacleSet.add(obstacle2);
        obstacleSet.add(obstacle3);
        obstacleSet.add(obstacle4);
        return obstacleSet;
    }

}