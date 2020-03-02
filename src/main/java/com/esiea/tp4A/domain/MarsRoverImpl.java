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
    	if(command.isEmpty()) {
    		return Position.of(0, 0, Direction.NORTH);
    	}else {
    		CharacterIterator ci = new StringCharacterIterator(command);
    		
    		while(ci.current() != CharacterIterator.DONE) {
    			switch(ci.current()) {
    				case 'f' :
    					if(position.getDirection() == Direction.NORTH) {
    						setPosition(Position.of(position.getX(), position.getY() + 1, position.getDirection()));
    					}else if(position.getDirection() == Direction.EAST) {
    						setPosition(Position.of(position.getX() + 1, position.getY(), position.getDirection()));
    					}else if(position.getDirection() == Direction.SOUTH) {
    						setPosition(Position.of(position.getX(), position.getY() - 1, position.getDirection()));
    					}else if(position.getDirection() == Direction.WEST) {
    						setPosition(Position.of(position.getX() - 1, position.getY(), position.getDirection()));
    					}
    					break;
    				case 'b' :
    					if(position.getDirection() == Direction.NORTH) {
    						setPosition(Position.of(position.getX(), position.getY() - 1, position.getDirection()));
    					}else if(position.getDirection() == Direction.EAST) {
    						setPosition(Position.of(position.getX() - 1, position.getY(), position.getDirection()));
    					}else if(position.getDirection() == Direction.SOUTH) {
    						setPosition(Position.of(position.getX(), position.getY() + 1, position.getDirection()));
    					}else if(position.getDirection() == Direction.WEST) {
    						setPosition(Position.of(position.getX() + 1, position.getY(), position.getDirection()));
    					}
    					break;
    				case 'r' :
    					if(position.getDirection() == Direction.NORTH) {
    						setPosition(Position.of(position.getX(), position.getY(), Direction.EAST));
    					}else if(position.getDirection() == Direction.EAST) {
    						setPosition(Position.of(position.getX(), position.getY(), Direction.SOUTH));
    					}else if(position.getDirection() == Direction.SOUTH) {
    						setPosition(Position.of(position.getX(), position.getY(), Direction.WEST));
    					}else if(position.getDirection() == Direction.WEST) {
    						setPosition(Position.of(position.getX(), position.getY(), Direction.NORTH));
    					}
    					break;
    				case 'l' :
    					if(position.getDirection() == Direction.NORTH) {
    						setPosition(Position.of(position.getX(), position.getY(), Direction.WEST));
    					}else if(position.getDirection() == Direction.EAST) {
    						setPosition(Position.of(position.getX(), position.getY(), Direction.NORTH));
    					}else if(position.getDirection() == Direction.SOUTH) {
    						setPosition(Position.of(position.getX(), position.getY(), Direction.EAST));
    					}else if(position.getDirection() == Direction.WEST) {
    						setPosition(Position.of(position.getX(), position.getY(), Direction.SOUTH));
    					}
    					break;
    				default :
    					break;
    			}
    			ci.next();
    		}
    	}
        return Position.of(position.getX(), position.getY(), position.getDirection());
    }
    
    public void setPosition(Position posi) {
    	this.position = posi;
    }

}
