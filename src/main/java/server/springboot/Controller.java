package server.springboot;

import com.esiea.tp4A.domain.*;
import com.sun.net.httpserver.HttpServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


@SpringBootApplication
@RestController
public class Controller {


    @RequestMapping("/")
    public @ResponseBody String greeting() {
        return "Hello, World";
    }


    @RequestMapping(value = "/api/player/{pn}", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody String createPlayer(@PathVariable("pn") String name) {
        return "test ==> " + name;
    }



    /*public void main(String[] args) throws IOException {
        SpringApplication.run(Controller.class, args);
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.setExecutor(null); // creates a default executor
        server.start();
        this.CreatePlayer("azerty");
    }

    private final Map<String, MarsRover> playerName = new HashMap<>();

    @PostMapping("/api/test/{test}")
    private String CreatePlayer(@PathVariable("test") String t){
        return t;
    }


    @PostMapping(path = "/api/player/{playerName}", produces = "application/json")
    private ResponseEntity<?>  CreatePlayer(@PathVariable("playerName") String pn){

        if (playerName.containsKey(pn))
            return ResponseEntity.status(409).body("oups, ce nom de joueur est déjà utilisé");

        Direction d;
        Random r = new Random();
        switch(r.nextInt()%3){
            case 0: d = Direction.NORTH;
            case 1: d = Direction.EAST;
            case 2: d = Direction.WEST;
            case 3: d = Direction.SOUTH;
        }
        //MarsRoverImpl mr = (MarsRoverImpl) new MarsRoverImpl().initialize(Position.of(r.nextInt()%600, r.nextInt()%600, Direction.NORTH));
        final MarsRover mr = new MarsRoverImpl().initialize(Position.of(r.nextInt()%600, r.nextInt()%600, Direction.NORTH));
        Set<Position> obs = new HashSet<>();
        PlanetMapImpl pm = new PlanetMapImpl();
        int ms = pm.getHeight()*pm.getWidth();

        for(int i=0; i<0.15*(ms*ms); i++){
            int obsX = r.nextInt()%ms;
            int obsY = r.nextInt()%ms;
            Position p = Position.of(obsX, obsY, Direction.NORTH);
            pm.ajout_obstacle_dynamique(p);
        }

        mr.updateMap(pm);
        Position pos = mr.getPos();
        Map<String, Object> player = new HashMap<>();

        Map<String, Object> posit = new HashMap<>();
        player.put("name", playerName);
        player.put("status", "alive");
        posit.put("x", pos.getX());
        posit.put("y", pos.getY());
        posit.put("direction", pos.getDirection().toString());
        player.put("position", posit);

        Map<String, Object> resp = new HashMap<>();
        resp.put("player", player);

        Map<String, Object> localMap = new HashMap<>();
        List<Map<String,Integer>> listObs = new ArrayList<>();
        for(Position o : ((PlanetMapImpl) pm).obstaclePositions()) {
            Map<String, Integer> obsObj = new HashMap<>();
            obsObj.put("x", o.getX());
            obsObj.put("y", o.getY());
            listObs.add(obsObj);
        }
        localMap.put("obstacles", listObs);

        List<Map<String,Object>> listPlayer = new ArrayList<>();
        localMap.put("players", listPlayer);
        playerName.put(pn, mr);

        return ResponseEntity.status(201).body("Joueur créé");
    }*/

}
