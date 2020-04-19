package com.esiea.tp4A.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MarsRoverTest {

    final MarsRover marsrover = new MarsRoverImpl().initialize(Position.of(0, 0, Direction.NORTH));
    final PlanetMapImpl planetmap = new PlanetMapImpl();

    @Test
    void rover_moves_forward_when_receving_f_command() {
        Assertions.assertThat(marsrover.move("f")).as("Mars Rover moving forward from origin")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .containsExactly(0, 1, Direction.NORTH);
    }

    @Test
    void rover_move_backward_when_receving_b_command() {
        Assertions.assertThat(marsrover.move("b")).as("Mars Rover moving backward from origin")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .containsExactly(0, -1, Direction.NORTH);
    }

    @Test
    void rover_turn_left_when_receving_l_command() {
        Assertions.assertThat(marsrover.move("l")).as("Mars Rover moving left from origin")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .containsExactly(0, 0, Direction.WEST);

    }

    @Test
    void rover_turn_right_when_reciving_r_command() {
        Assertions.assertThat(marsrover.move("r")).as("Mars Rover moving right from origin")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .containsExactly(0, 0, Direction.EAST);
    }

    @Test
    void planette_spherique_plus_50_f_moins_50_North() {
        MarsRover marsSphere = new MarsRoverImpl().initialize(Position.of(0, 50, Direction.NORTH));
        Assertions.assertThat(marsSphere.move("f")).as("Mars Rover reviens en y = -50")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .containsExactly(0, -50, Direction.NORTH);
    }

    @Test
    void planette_spherique_moins_50_b_plus_50_North() {
        MarsRover marsSphere = new MarsRoverImpl().initialize(Position.of(0, -50, Direction.NORTH));
        Assertions.assertThat(marsSphere.move("b")).as("Mars Rover reviens en y = 50")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .containsExactly(0, 50, Direction.NORTH);
    }



    @Test
    void planette_spherique_plus_50_f_50_moins_East() {
        MarsRover marsSphere = new MarsRoverImpl().initialize(Position.of(50, 0, Direction.WEST));
        Assertions.assertThat(marsSphere.move("b")).as("Mars Rover reviens en x = -50")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .containsExactly(-50, 0, Direction.WEST);
    }

    @Test
    void planette_spherique_moins_50_b_plus_50_East() {
        MarsRover marsSphere = new MarsRoverImpl().initialize(Position.of(-50, 0, Direction.EAST));
        Assertions.assertThat(marsSphere.move("b")).as("Mars Rover reviens en x = 50")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .containsExactly(50, 0, Direction.EAST);
    }


    @Test
    void move_spherical_map_command_b() {
        MarsRover marsSphere = new MarsRoverImpl().initialize(Position.of(-50, -49, Direction.EAST));
        Assertions.assertThat(marsSphere.move("b")).as("Mars Rover reviens en x = +50 direct EAST")
            .extracting(Position::getX, Position::getY, Position::getDirection)
            .containsExactly(50, -49, Direction.EAST);
    }

    @Test
    void move_spherical_map_command_bb() {
        MarsRover marsSphere = new MarsRoverImpl().initialize(Position.of(-50, 34, Direction.WEST));
        Assertions.assertThat(marsSphere.move("bb")).as("Mars Rover reviens en x = -48 direct WEST")
            .extracting(Position::getX, Position::getY, Position::getDirection)
            .containsExactly(-48, 34, Direction.WEST);
    }

    @Test
    void move_spherical_map_command_fff() {
        MarsRover marsSphere = new MarsRoverImpl().initialize(Position.of(32, 49, Direction.SOUTH));
        Assertions.assertThat(marsSphere.move("fff")).as("Mars Rover reviens en y = +46 direct SOUTH")
            .extracting(Position::getX, Position::getY, Position::getDirection)
            .containsExactly(32, 46, Direction.SOUTH);
    }

    @Test
    void move_spherical_map_command_rff() {
        MarsRover marsSphere = new MarsRoverImpl().initialize(Position.of(-27, -50, Direction.NORTH));
        Assertions.assertThat(marsSphere.move("rff")).as("Mars Rover reviens en x = -25 direct NORTH")
            .extracting(Position::getX, Position::getY, Position::getDirection)
            .containsExactly(-25, -50, Direction.EAST);
    }


    @Test
    void complex_moves_from_center() {
        Assertions.assertThat(marsrover.move("fflb")).as("Mars Rover moving forward from origin")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .containsExactly(1, 2, Direction.WEST);
    }

    @Test
    void complex_moves_from_center_more_commandes() {
        Assertions.assertThat(marsrover.move("lbblffr")).as("Mars Rover moving forward from origin")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .containsExactly(2, -2, Direction.WEST);
    }

    @Test
    void complex_moves_from_different_origin() {
        Assertions.assertThat(marsrover.move("ff")).as("Mars Rover moving forward from origin")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .containsExactly(0, 2, Direction.NORTH);
    }

    @Test
    void complex_moves_from_different_origin_more_commandes() {
        Assertions.assertThat(marsrover.move("lbblffr")).as("Mars Rover moving forward from origin")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .containsExactly(2, -2, Direction.WEST);
    }

    @Test
    void unknown_command_should_be_ignored() {
        Assertions.assertThat(marsrover.move("aff")).as("Mars Rover moving forward from origin")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .containsExactly(0, 2, Direction.NORTH);
    }

    @Test
    void unknown_command_should_be_ignored_commande1() {
        Assertions.assertThat(marsrover.move("f f")).as("Mars Rover moving forward from origin")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .containsExactly(0, 2, Direction.NORTH);
    }

    @Test
    void unknown_command_should_be_ignored_commande2() {
        Assertions.assertThat(marsrover.move("f,f")).as("Mars Rover moving forward from origin")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .containsExactly(0, 2, Direction.NORTH);
    }

    @Test
    void unknown_command_should_be_ignored_commande3() {
        Assertions.assertThat(marsrover.move("ff;")).as("Mars Rover moving forward from origin")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .containsExactly(0, 2, Direction.NORTH);
    }

    @Test
    void obstacle_front_detection() {
        marsrover.initialize(Position.of(0, 0, Direction.NORTH));
        marsrover.updateMap(planetmap);
        planetmap.ajout_obstacle();
        Assertions.assertThat(marsrover.move("f"))
                .as("Mars Rover staying at origin because he detects an obstacle in front of him")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .containsExactly(0, 0, Direction.NORTH);
    }

    @Test
    void obstacle_back_detection() {
        marsrover.initialize(Position.of(1, 2, Direction.EAST));
        marsrover.updateMap(planetmap);
        planetmap.ajout_obstacle();
        Assertions.assertThat(marsrover.move("b"))
                .as("Mars Rover staying at origin because he detects an obstacle in back of him")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .containsExactly(1, 2, Direction.EAST);
    }

    @Test
    void complex_moves_with_obstacles_detection() {
        marsrover.initialize(Position.of(0, 0, Direction.NORTH));
        marsrover.updateMap(planetmap);
        planetmap.ajout_obstacle();
        Assertions.assertThat(marsrover.move("fflb")).as("Example of the subject for obstacle detection")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .containsExactly(1, 0, Direction.WEST);
    }

    @Test
    void rover_laser_simple_shot() {
        marsrover.initialize(Position.of(0, 0, Direction.NORTH));
        marsrover.updateMap(planetmap);
        planetmap.ajout_obstacle();
        marsrover.configureLaserRange(1);
        Assertions.assertThat(marsrover.move("sf"))
                .as("Mars Rover shot an obstacle in front of him and moving forward from origin")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .containsExactly(0, 1, Direction.NORTH);
    }

    @Test
    void rover_laser_long_shot() {
        marsrover.initialize(Position.of(10, 0, Direction.NORTH));
        marsrover.updateMap(planetmap);
        planetmap.ajout_obstacle();
        marsrover.configureLaserRange(4);

        Assertions.assertThat(marsrover.move("sffff"))
                .as("Laser range configuration to make a longer shot, Mars rover access to the new available position")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .containsExactly(10, 4, Direction.NORTH);
    }

    @Test
    void rover_laser_multiple_shots() {
        marsrover.initialize(Position.of(0, 0, Direction.NORTH));
        marsrover.updateMap(planetmap);
        planetmap.ajout_obstacle();
        marsrover.configureLaserRange(2);
        Assertions.assertThat(marsrover.move("ssff"))
                .as("Mars Rover shots two times to destroy two obstacles in front of him")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .containsExactly(0, 2, Direction.NORTH);
    }

    @Test
    void mars_rover_laser_shots_for_east_direction() {
        marsrover.initialize(Position.of(29, 20, Direction.EAST));
        marsrover.updateMap(planetmap);
        planetmap.ajout_obstacle();
        marsrover.configureLaserRange(4);
        Assertions.assertThat(marsrover.move("sf")).as("Mars Rover shots obstacles when direction = East")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .containsExactly(30, 20, Direction.EAST);
    }

    @Test
    void mars_rover_laser_shots_for_south_direction() {
        marsrover.initialize(Position.of(30, 21, Direction.SOUTH));
        marsrover.updateMap(planetmap);
        planetmap.ajout_obstacle();
        marsrover.configureLaserRange(1);
        Assertions.assertThat(marsrover.move("sf")).as("Mars Rover shots obstacles when direction = East")
                .extracting(Position::getX, Position::getY, Position::getDirection)
                .containsExactly(30, 20, Direction.SOUTH);
    }

    /*
     * @Test void rover_dont_move_when_bad_command() {
     * Assertions.assertThat(marsrover.move("w")) .isEqualTo(Position.of(0, 0,
     * Direction.NORTH)); }
     */

}
