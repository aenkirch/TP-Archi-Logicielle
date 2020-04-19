package server.springboot.models;

import com.esiea.tp4A.domain.MarsRover;
import com.esiea.tp4A.domain.Position;

import java.util.Set;

public class Players {

    private final long id;
    private final String name;
    private final MarsRover rover;
    private final Boolean status;
    private final int laserRange;

    public Players(long id, String name, MarsRover rover, int range, Boolean status, Set<Position> positions, int laserRange) {
        this.id = id;
        this.name = name;
        this.rover=rover;
        this.rover.configureLaserRange(range);
        this.status = status;
        this.laserRange = laserRange;
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

    public Boolean getStatus() { return status; }


    public int getLaserRange() { return  laserRange; }




}
