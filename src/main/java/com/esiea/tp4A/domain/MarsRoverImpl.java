package com.esiea.tp4A.domain;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Iterator;

public class MarsRoverImpl implements MarsRover {

    private Position position;
    private PlanetMapImpl rovermap = null;

    public MarsRover initialize(Position position) {
        setPosition(position);
        return this;
    }

    public MarsRover updateMap(PlanetMapImpl map) {
        this.rovermap = map;
        return this;
    }

    public MarsRover configureLaserRange(int range) {
        return this;
    }

    public Position move(String command) {
        CharacterIterator ci = new StringCharacterIterator(command);
        if (rovermap == null) {
            while (ci.current() != CharacterIterator.DONE) {
                movement(ci.current());
                ci.next();
            }
        } else {
            while (ci.current() != CharacterIterator.DONE) {
                if (ci.current() == 'f') {
                    obstacle_test(ci.current());
                } else if (ci.current() == 'b') {
                    obstacle_test(ci.current());
                } else if (ci.current() == 'l' || ci.current() == 'r') {
                    movement(ci.current());
                }
                ci.next();
            }  // rovermap=null;
        }
        return Position.of(position.getX(), position.getY(), position.getDirection());
    }

    private void movement(char command) {
        if (command == 'f') {
            moveForward();
        } else if (command == 'b') {
            moveBackward();
        } else if (command == 'r') {
            moveRightSide();
        } else if (command == 'l') {
            moveLeftSide();
        }
    }

    private void moveForward() {
        if (position.getDirection() == Direction.NORTH) {
            setPosition(Position.of(position.getX(), spherique(position.getY() + 1), position.getDirection()));
        } else if (position.getDirection() == Direction.EAST) {
            setPosition(Position.of(spherique(position.getX() + 1), position.getY(), position.getDirection()));
        } else if (position.getDirection() == Direction.SOUTH) {
            setPosition(Position.of(position.getX(), spherique(position.getY() - 1), position.getDirection()));
        } else if (position.getDirection() == Direction.WEST) {
            setPosition(Position.of(spherique(position.getX() - 1), position.getY(), position.getDirection()));
        }
    }

    private void moveBackward() {
        if (position.getDirection() == Direction.NORTH) {
            setPosition(Position.of(position.getX(), spherique(position.getY() - 1), position.getDirection()));
        } else if (position.getDirection() == Direction.EAST) {
            setPosition(Position.of(spherique(position.getX() - 1), position.getY(), position.getDirection()));
        } else if (position.getDirection() == Direction.SOUTH) {
            setPosition(Position.of(position.getX(), spherique(position.getY() + 1), position.getDirection()));
        } else if (position.getDirection() == Direction.WEST) {
            setPosition(Position.of(spherique(position.getX() + 1), position.getY(), position.getDirection()));
        }
    }

    private void moveRightSide() {
        if (position.getDirection() == Direction.NORTH) {
            setPosition(Position.of(position.getX(), position.getY(), Direction.EAST));
        } else if (position.getDirection() == Direction.EAST) {
            setPosition(Position.of(position.getX(), position.getY(), Direction.SOUTH));
        } else if (position.getDirection() == Direction.SOUTH) {
            setPosition(Position.of(position.getX(), position.getY(), Direction.WEST));
        } else if (position.getDirection() == Direction.WEST) {
            setPosition(Position.of(position.getX(), position.getY(), Direction.NORTH));
        }
    }

    private void moveLeftSide() {
        if (position.getDirection() == Direction.NORTH) {
            setPosition(Position.of(position.getX(), position.getY(), Direction.WEST));
        } else if (position.getDirection() == Direction.EAST) {
            setPosition(Position.of(position.getX(), position.getY(), Direction.NORTH));
        } else if (position.getDirection() == Direction.SOUTH) {
            setPosition(Position.of(position.getX(), position.getY(), Direction.EAST));
        } else if (position.getDirection() == Direction.WEST) {
            setPosition(Position.of(position.getX(), position.getY(), Direction.SOUTH));
        }
    }

    public int spherique(int x) {
        if (x > 50) {
            x = -50;
        } else if (x < -50) {
            x = 50;
        }
        return x;
    }

    public void obstacle_test(char c) {

        int currentX = getPosition().getX();
        int futurY = getPosition().getY();
        int obstacleX;
        int obstacleY;
        Boolean access = null;
        Iterator<Position> obstacle = rovermap.obstaclePositions().iterator();
        
       
       if(c=='f'){
            if (getDirection().toString() == "NORTH") {
                futurY = futurY + 1;
            } else if (getDirection().toString() == "EAST") {
                currentX = currentX + 1;
            } else if (getDirection().toString() == "SOUTH") {
                futurY = futurY - 1;
            } else if (getDirection().toString() == "WEST") {
                currentX = currentX - 1;
            }
        }
            else if(c=='b'){
                if (getDirection().toString() == "NORTH") {
                    futurY = futurY - 1;
                } else if (getDirection().toString() == "EAST") {
                    currentX = currentX - 1;
                } else if (getDirection().toString() == "SOUTH") {
                    futurY = futurY + 1;
                } else if (getDirection().toString() == "WEST") {
                    currentX = currentX + 1;
                }
        }
        while (obstacle.hasNext()) {
            Position lp = obstacle.next();
            obstacleX = lp.getX();
            obstacleY = lp.getY();
            System.out.println(obstacleX);
            System.out.println(obstacleY);
            if (currentX == obstacleX && futurY == obstacleY) {
                access = false;
                break;
            } else {
                access = true;
            }
        }

        if (access == true) {
            movement(c);
        }
    }

  

    public void setPosition(Position posi) {
        this.position = posi;
    }

    private Position getPosition() {
        return this.position;
    }

    private Direction getDirection() {
        return this.position.getDirection();
    }
}
