package server.springboot.models;

import com.esiea.tp4A.domain.Position;

import java.util.Set;

public class LocalMap {
    private final Set<Position> obstacles;
    private final Set<Players> players;

    public LocalMap(Set<Position> obstacles, Set<Players> players) {
        this.obstacles = obstacles;
        this.players = players;
    }

    public Set<Position> getObstacles() {
        return obstacles;
    }

    public Set<Players> getPlayers() {
        return players;
    }
}
