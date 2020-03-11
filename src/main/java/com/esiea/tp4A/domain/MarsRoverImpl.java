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

    private void movement(char command){
        switch(command){
            case 'f':
                moveForward();
                break;
            case 'b':
                moveBackward();
                break;
            case 'r':
                moveRightSide();
                break;
            case 'l':
                moveLeftSide();
                break;
            default:
                break;
        }
    }

    private void moveForward(){
        setPosition(Position.of(position.getX(), position.getY() + 1, Direction.NORTH));
    }
    private void moveBackward(){
        setPosition(Position.of(position.getX(), position.getY() - 1, Direction.SOUTH));
    }
    private void moveRightSide(){
        setPosition(Position.of(position.getX()+1, position.getY(), Direction.EAST));
    }
    private void moveLeftSide(){
        setPosition(Position.of(position.getX()-1, position.getY(), Direction.WEST));
    }

    public void setPosition(Position posi) {
        this.position = posi;
    }


    private Position getPosition() {
        return this.position;
    }
}
