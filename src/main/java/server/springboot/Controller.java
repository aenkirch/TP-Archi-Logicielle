package server.springboot;

import com.esiea.tp4A.domain.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@SpringBootApplication
@RestController
public class Controller {

    public void main(String[] args){
        SpringApplication.run(Controller.class, args);
    }

}
