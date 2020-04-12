package com.esiea.tp4A.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class MarsRoverTest {

	final MarsRover marsrover = new MarsRoverImpl().initialize(Position.of(0, 0, Direction.NORTH));
	final PlanetMapImpl planetmap = new PlanetMapImpl();

	@Test
	void rover_moves_forward_when_receving_f_command() {
		Assertions.assertThat(marsrover.move("f"))
            .as("Mars Rover moving forward from origin")
            .extracting(Position::getX, Position::getY, Position::getDirection)
            .containsExactly(0,1,Direction.NORTH);
    }

	@Test
	void rover_move_backward_when_receving_b_command() {
        Assertions.assertThat(marsrover.move("b"))
            .as("Mars Rover moving backward from origin")
            .extracting(Position::getX, Position::getY, Position::getDirection)
            .containsExactly(0,-1,Direction.NORTH);
	}
	
	@Test
	void rover_turn_left_when_receving_l_command() {
        Assertions.assertThat(marsrover.move("l"))
            .as("Mars Rover moving left from origin")
            .extracting(Position::getX, Position::getY, Position::getDirection)
            .containsExactly(0,0,Direction.WEST);

	}
	
	@Test
	void rover_turn_right_when_reciving_r_command() {
        Assertions.assertThat(marsrover.move("r"))
            .as("Mars Rover moving right from origin")
            .extracting(Position::getX, Position::getY, Position::getDirection)
            .containsExactly(0,0,Direction.EAST);
	}
	
	@Test
	void planette_spherique_plus_50_f_moins_50_North() {
		MarsRover marsSphere = new MarsRoverImpl().initialize(Position.of(0, 50, Direction.NORTH));
		Assertions.assertThat(marsSphere.move("f"))
			.as("Mars Rover reviens en y = -50")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(0, -50, Direction.NORTH);
	}
	
	@Test
	void planette_spherique_moins_50_b_plus_50_North() {
		MarsRover marsSphere = new MarsRoverImpl().initialize(Position.of(0, -50, Direction.NORTH));
		Assertions.assertThat(marsSphere.move("b"))
			.as("Mars Rover reviens en y = 50")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(0, 50, Direction.NORTH);
	}
	
	
	@Test
	void planette_spherique_plus_50_f_50_moins_East() {
		MarsRover marsSphere = new MarsRoverImpl().initialize(Position.of(50, 0, Direction.WEST));
		Assertions.assertThat(marsSphere.move("b"))
			.as("Mars Rover reviens en x = -50")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(-50, 0, Direction.WEST);
	}
	
	@Test
	void planette_spherique_moins_50_b_plus_50_East() {
		MarsRover marsSphere = new MarsRoverImpl().initialize(Position.of(-50, 0, Direction.EAST));
		Assertions.assertThat(marsSphere.move("b"))
			.as("Mars Rover reviens en x = 50")
			.extracting(Position::getX, Position::getY, Position::getDirection)
			.containsExactly(50, 0, Direction.EAST);
	}

    @Test
    void complex_moves_from_center() {
        Assertions.assertThat(marsrover.move("fflb"))
            .as("Mars Rover moving forward from origin")
            .extracting(Position::getX, Position::getY, Position::getDirection)
            .containsExactly(1,2,Direction.WEST);
    }

    @Test
    void complex_moves_from_center_more_commandes() {
        Assertions.assertThat(marsrover.move("lbblffr"))
            .as("Mars Rover moving forward from origin")
            .extracting(Position::getX, Position::getY, Position::getDirection)
            .containsExactly(2,-2,Direction.WEST);
    }

    @Test
    void complex_moves_from_different_origin() {
        Assertions.assertThat(marsrover.move("ff"))
            .as("Mars Rover moving forward from origin")
            .extracting(Position::getX, Position::getY, Position::getDirection)
            .containsExactly(0,2,Direction.NORTH);
    }

    @Test
    void complex_moves_from_different_origin_more_commandes() {
        Assertions.assertThat(marsrover.move("lbblffr"))
            .as("Mars Rover moving forward from origin")
            .extracting(Position::getX, Position::getY, Position::getDirection)
            .containsExactly(2,-2,Direction.WEST);
    }

    @Test
    void unknown_command_should_be_ignored() {
        Assertions.assertThat(marsrover.move("aff"))
            .as("Mars Rover moving forward from origin")
            .extracting(Position::getX, Position::getY, Position::getDirection)
            .containsExactly(0,2,Direction.NORTH);
    }

    @Test
    void unknown_command_should_be_ignored_commande1() {
        Assertions.assertThat(marsrover.move("f f"))
            .as("Mars Rover moving forward from origin")
            .extracting(Position::getX, Position::getY, Position::getDirection)
            .containsExactly(0,2,Direction.NORTH);
    }

    @Test
    void unknown_command_should_be_ignored_commande2() {
        Assertions.assertThat(marsrover.move("f,f"))
            .as("Mars Rover moving forward from origin")
            .extracting(Position::getX, Position::getY, Position::getDirection)
            .containsExactly(0,2,Direction.NORTH);
    }

    @Test
    void unknown_command_should_be_ignored_commande3() {
        Assertions.assertThat(marsrover.move("ff;"))
            .as("Mars Rover moving forward from origin")
            .extracting(Position::getX, Position::getY, Position::getDirection)
            .containsExactly(0,2,Direction.NORTH);
    }
    @Test
    void obstacle_front_detection(){       
        marsrover.initialize(Position.of(0,0, Direction.NORTH));
        marsrover.updateMap(planetmap);
        planetmap.ajout_obstacle();
        Assertions.assertThat(marsrover.move("f"))
        .as("Mars Rover detect an obstacle in front of him and doesn't move")
        .extracting(Position::getX, Position::getY, Position::getDirection)
        .containsExactly(0,0,Direction.NORTH);
    }
    
    @Test
    void obstacle_back_detection(){       
        marsrover.initialize(Position.of(1,2, Direction.EAST));
        marsrover.updateMap(planetmap);
        planetmap.ajout_obstacle();
        Assertions.assertThat(marsrover.move("b"))
        .as("Mars Rover detect an obstacle in back of him and doesn't move")
        .extracting(Position::getX, Position::getY, Position::getDirection)
        .containsExactly(1,2,Direction.EAST);
    }
    @Test
    void rover_receive_list_command_and_detect_obstacle(){       
        marsrover.initialize(Position.of(10,3, Direction.NORTH));
        marsrover.updateMap(planetmap);
        planetmap.ajout_obstacle();
        Assertions.assertThat(marsrover.move("flfrfrflfrfrf"))
        .as("Mars rover cannot access to this position(X=10,Y=4) du to an obstacle")
        .extracting(Position::getX, Position::getY, Position::getDirection)
        .containsExactly(10,5,Direction.SOUTH);
    }
    @Test
    void mars_rover_obstacle_exemple(){       
        marsrover.initialize(Position.of(0,0, Direction.NORTH));
        marsrover.updateMap(planetmap);
        planetmap.ajout_obstacle();
        Assertions.assertThat(marsrover.move("fflb"))
        .as("Exemple de l'énoncé pour la detection d'obstacle")
        .extracting(Position::getX, Position::getY, Position::getDirection)
        .containsExactly(1,0,Direction.WEST);
    }


    @Test
    void mars_rover_laser(){       
        marsrover.initialize(Position.of(0,0, Direction.NORTH));
        marsrover.updateMap(planetmap);
        planetmap.ajout_obstacle();
        marsrover.configureLaserRange(1);

        Assertions.assertThat(marsrover.move("sf"))
        .as("Mars Rover shot an obstacle in front of him and now can go forward")
        .extracting(Position::getX, Position::getY, Position::getDirection)
        .containsExactly(0,1,Direction.NORTH);
    }


    @Test
    void mars_rover_laser_variable_range(){       
        marsrover.initialize(Position.of(0,0, Direction.NORTH));
        marsrover.updateMap(planetmap);
        planetmap.ajout_obstacle();
        marsrover.configureLaserRange(2);

        Assertions.assertThat(marsrover.move("ssff"))
        .as("Mars shot in front of him for a distance of 2, he can go forwad 2 steps")
        .extracting(Position::getX, Position::getY, Position::getDirection)
        .containsExactly(0,2,Direction.NORTH);
    }

    @Test
    void mars_rover_laser_long_shot(){       
        marsrover.initialize(Position.of(10,0, Direction.NORTH));
        marsrover.updateMap(planetmap);
        planetmap.ajout_obstacle();
        marsrover.configureLaserRange(4);

        Assertions.assertThat(marsrover.move("sffff"))
        .as("Mars Rover long shot obstacles and go to his position to test")
        .extracting(Position::getX, Position::getY, Position::getDirection)
        .containsExactly(10,4,Direction.NORTH);
    }

    @Test
    void mars_rover_laser_shot_for_east(){      
        marsrover.initialize(Position.of(29,20, Direction.EAST));
        marsrover.updateMap(planetmap);
        planetmap.ajout_obstacle();
        marsrover.configureLaserRange(4);

        Assertions.assertThat(marsrover.move("sf"))
        .as("Mars Rover shots obstacles when direction = East")
        .extracting(Position::getX, Position::getY, Position::getDirection)
        .containsExactly(30,20,Direction.EAST);
    }
    @Test
    void mars_rover_laser_shot_for_south(){      
        marsrover.initialize(Position.of(0,3, Direction.SOUTH));
        marsrover.updateMap(planetmap);
        planetmap.ajout_obstacle();
        marsrover.configureLaserRange(4);

        Assertions.assertThat(marsrover.move("sf"))
        .as("Mars Rover shots obstacles when direction = East")
        .extracting(Position::getX, Position::getY, Position::getDirection)
        .containsExactly(0,2,Direction.SOUTH);
    }
 

  
  




    
	/*@Test
	void rover_dont_move_when_bad_command() {
		Assertions.assertThat(marsrover.move("w"))
		.isEqualTo(Position.of(0, 0, Direction.NORTH));
	}*/



}
