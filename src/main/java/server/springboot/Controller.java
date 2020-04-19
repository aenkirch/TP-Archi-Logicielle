package server.springboot;

import com.esiea.tp4A.domain.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.springboot.models.*;

import java.util.*;


@SpringBootApplication
@RestController
public class Controller {

    final Set<Global> listGlobal = new HashSet<Global>();
    final Set<Players> list = new HashSet<Players>();
    final Set<LocalMap> listLocalMap = new HashSet<>();
    final int laser = 3;


    @PostMapping("/player/{playername}")
    public Object createPlayer(@PathVariable("playername") String name) {
        for (Players p : list) {
            if (p.getName().equals(name))
                return ResponseEntity.status(409).body("Oups ! Le joueur existe déjà...");
        }
        Random random = new Random();
        PlanetMap planetMap = new PlanetMapImpl();
        int sizeMap = planetMap.getHeight() * planetMap.getWidth();
        MarsRover rover1 = new MarsRoverImpl().initialize(Position.of(random.nextInt()%planetMap.getWidth(), random.nextInt()%planetMap.getHeight(), Direction.NORTH));
        Players player1= new Players(0,name,rover1,0,true, setObstacles(sizeMap, random), laser);
        LocalMap localMap1 = new LocalMap(setObstacles(sizeMap, random), list);
        list.add(player1);
        listLocalMap.add(localMap1);
        Global global1 = new Global(player1, localMap1);
        listGlobal.add(global1);
        Global pl2 = listGlobal.iterator().next();
        return ResponseEntity.status(201).body(pl2);
    }

    private Set<Position> setObstacles(int mapSize, Random random){
        Set<Position> obstacles = new HashSet<>();
        for(int i = 0; i < 0.15*(mapSize) ; i++){
            int x = random.nextInt()%mapSize;
            int y = random.nextInt()%mapSize;
            Position pos = Position.of(x, y, Direction.NORTH);
            obstacles.add(pos);
        }
        return obstacles;
    }


    @GetMapping("/player/{playername}")
    public Object getPlayerStatus(@PathVariable("playername") String name) {
        for (Players p : list) {
            if (p.getName().equals(name))
                return ResponseEntity.status(200).body(p);
        }

        return ResponseEntity.status(404).body("Le joueur n'existe pas !");
    }

    @PatchMapping("/player/{playername}/{command}")
    public ResponseEntity<?> driveRover(@PathVariable("playername") String name, @PathVariable("command") String command) {
        for (Players p : list) {
            if (p.getName().equals(name)){
                p.getRover().move(command);
                return ResponseEntity.status(200).body(p);
            }
        }

        return ResponseEntity.status(404).body("Le joueur n'existe pas !");
    }

}
