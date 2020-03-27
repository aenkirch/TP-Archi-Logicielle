package com.esiea.tp4A.domain;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public class MarsRoverImpl implements MarsRover {
	
	private Position position;


    public MarsRover initialize(Position position) {
        setPosition(position);
		return this;
    }

    public MarsRover updateMap(PlanetMap map) {
        return this;
    }

    public MarsRover configureLaserRange(int range) {
        return this;
    }

    public Position move(String command) {
        CharacterIterator ci = new StringCharacterIterator(command);

        while(ci.current() != CharacterIterator.DONE){
            movement(ci.current());
            ci.next();
        }
        return Position.of(position.getX(), position.getY(), position.getDirection());
    }

    private void movement(char command) {
    	if(command == 'f') {
    		moveForward();
    	}else if(command == 'b') {
    		moveBackward();
    	}else if(command == 'r') {
    		moveRightSide();
    	}else if(command == 'l') {
    		moveLeftSide();
    	}
    }
    
    private void moveForward(){
    	if(position.getDirection() == Direction.NORTH) {
    		setPosition(Position.of(position.getX(), position.getY() + 1, position.getDirection()));
    	}else if(position.getDirection() == Direction.EAST) {
    		setPosition(Position.of(position.getX() + 1, position.getY(), position.getDirection()));
    	}else if(position.getDirection() == Direction.SOUTH) {
    		setPosition(Position.of(position.getX(), position.getY() - 1, position.getDirection()));
    	}else if(position.getDirection() == Direction.WEST) {
    		setPosition(Position.of(position.getX() - 1, position.getY(), position.getDirection()));
    	}
    }
    private void moveBackward(){
        if(position.getDirection() == Direction.NORTH) {
        	setPosition(Position.of(position.getX(), position.getY() - 1, position.getDirection()));
        }else if(position.getDirection() == Direction.EAST) {
        	setPosition(Position.of(position.getX() - 1, position.getY(), position.getDirection()));
        }else if(position.getDirection() == Direction.SOUTH) {
        	setPosition(Position.of(position.getX(), position.getY() + 1, position.getDirection()));
        }else if(position.getDirection() == Direction.WEST) {
        	setPosition(Position.of(position.getX() + 1, position.getY(), position.getDirection()));
        }
    }
    
    private void moveRightSide(){
        if(position.getDirection() == Direction.NORTH) {
        	setPosition(Position.of(position.getX(), position.getY(), Direction.EAST));
        }else if(position.getDirection() == Direction.EAST) {
        	setPosition(Position.of(position.getX(), position.getY(), Direction.SOUTH));
        }else if(position.getDirection() == Direction.SOUTH) {
        	setPosition(Position.of(position.getX(), position.getY(), Direction.WEST));
        }else if(position.getDirection() == Direction.WEST) {
        	setPosition(Position.of(position.getX(), position.getY(), Direction.NORTH));
        }
    }
    
    private void moveLeftSide(){
        if(position.getDirection() == Direction.NORTH) {
        	setPosition(Position.of(position.getX(), position.getY(), Direction.WEST));
        }else if(position.getDirection() == Direction.EAST) {
        	setPosition(Position.of(position.getX(), position.getY(), Direction.NORTH));
        }else if(position.getDirection() == Direction.SOUTH) {
        	setPosition(Position.of(position.getX(), position.getY(), Direction.EAST));
        }else if(position.getDirection() == Direction.WEST) {
        	setPosition(Position.of(position.getX(), position.getY(), Direction.SOUTH));
        }
    }

    public void setPosition(Position posi) {
        this.position = posi;
    }


    private Position getPosition() {
        return this.position;
    }
}
