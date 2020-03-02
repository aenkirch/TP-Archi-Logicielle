package com.esiea.tp4A.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class MarsRoverTest {



	
	final MarsRover marsrover = new MarsRoverImpl().initialize(Position.of(0, 0, Direction.NORTH));
	

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
	
	/*@Test
	void rover_dont_move_when_bad_command() {
		Assertions.assertThat(marsrover.move("w"))
		.isEqualTo(Position.of(0, 0, Direction.NORTH));
	}*/
	
	@Test
	void multiple_moves() {
        Assertions.assertThat(marsrover.move("ff"))
            .as("Mars Rover moving Forward from origin 2 times")
            .extracting(Position::getX, Position::getY, Position::getDirection)
            .containsExactly(0,2,Direction.NORTH);
	}
}