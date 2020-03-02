package com.esiea.tp4A.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class MarsRoverTest {



	
	final MarsRover marsrover = new MarsRoverImpl().initialize(Position.of(0, 0, Direction.NORTH));
	

	@Test
	void rover_moves_forward_when_receving_f_command() {
		//Position position = new FixedPosition(0, 0, Direction.NORTH);
		Assertions.assertThat(marsrover.move("f"))
			.isEqualTo(Position.of(1, 0, Direction.NORTH));
	}

	@Test
	void rover_move_backward_when_receving_b_command() {
		Assertions.assertThat(marsrover.move("b"))
		.isEqualTo(Position.of(-1, 0, Direction.NORTH));

	}
	
	@Test
	void rover_turn_left_when_receving_l_command() {
		Assertions.assertThat(marsrover.move("l"))
		.isEqualTo(Position.of(0, 0, Direction.WEST));

	}
	
	@Test
	void rover_turn_right_when_reciving_r_command() {
		Assertions.assertThat(marsrover.move("r"))
		.isEqualTo(Position.of(1, 0, Direction.EAST));
	}
	
	@Test
	void rover_dont_move_when_bad_command() {
		Assertions.assertThat(marsrover.move("w"))
		.isEqualTo(Position.of(0, 0, Direction.NORTH));

	}
	
	@Test
	void multiple_moves() {
		
	}
}
