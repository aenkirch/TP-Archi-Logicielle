package server.springboot;

import com.esiea.tp4A.domain.*;
import com.sun.net.httpserver.HttpServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
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

    final Set<Players> list = new HashSet<Players>();

    @PostMapping("/api/player/{playername}")
    public ResponseEntity<?> createPlayer(@PathVariable("playername") String name) {
        for (Players p : list) {
            if (p.getName().equals(name))
                return ResponseEntity.status(409).body("Oups ! Le joueur existe déjà...");
        }

        MarsRover rover1 = new MarsRoverImpl().initialize(Position.of(0, 0, Direction.EAST));
        Players player1 = new Players(list.size(),name,rover1,0);
        list.add(player1);

        return ResponseEntity.status(201).body(player1);
    }

    @GetMapping("/api/player/{playername}")
    public ResponseEntity<?> getPlayerStatus(@PathVariable("playername") String name) {
        for (Players p : list) {
            if (p.getName().equals(name))
                return ResponseEntity.status(200).body(p);
        }

        return ResponseEntity.status(404).body("Le joueur n'existe pas !");
    }

    //TODO: créer la route PATCH

    @GetMapping("/api/player/{playername}/deplc")
    public Players deplacerPlayer() {
        list.iterator().next().getRover().initialize(Position.of(10,10,Direction.NORTH));


        return list.iterator().next();
    }

}
