package com.esiea.tp4A.domain;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Iterator;

public class MarsRoverImpl implements MarsRover {

    private Position position;
    private PlanetMapImpl rovermap = null;
    private int laser_range = 0;

    public MarsRover initialize(Position position) {
        setPosition(position);
        return this;
    }

    public Position getPos() {
        return this.position;
    }

    public MarsRover updateMap(PlanetMapImpl map) {
        this.rovermap = map;
        return this;
    }

    public MarsRover configureLaserRange(int range) {
        this.laser_range = range;
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
                } else if (ci.current() == 's') {
                    laser_shot(laser_range);
                }
                ci.next();
            }
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
            x = -49;
        } else if (x < -49) {
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

        if (c == 'f') {
            if (getDirection().toString() == "NORTH") {
                futurY = futurY + 1;
            } else if (getDirection().toString() == "EAST") {
                currentX = currentX + 1;
            } else if (getDirection().toString() == "SOUTH") {
                futurY = futurY - 1;
            } else if (getDirection().toString() == "WEST") {
                currentX = currentX - 1;
            }
        } else if (c == 'b') {
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

    public void laser_shot(int rang) {
        int currentX = getPosition().getX();
        int futurY = getPosition().getY();
        String direction = getDirection().toString();
        int obstacleX;
        int obstacleY;
        Iterator<Position> obstacle = rovermap.obstaclePositions().iterator();

        if (rang != 0) {
            if (direction == "NORTH") {
                obstacleX = currentX;
                for (int i = 1; i <= rang; i++) {
                    obstacleY = futurY + i;
                    while (obstacle.hasNext()) {
                        Position lp = obstacle.next();
                        if (lp.getX() == obstacleX && lp.getY() == obstacleY) {
                            rovermap.obstaclePositions().remove(lp);
                            obstacle = rovermap.obstaclePositions().iterator();
                            i = rang;
                            break;
                        }
                    }
                    obstacle = rovermap.obstaclePositions().iterator();
                }
            } else if (direction == "SOUTH") {
                obstacleX = currentX;
                for (int i = 1; i <= rang; i++) {
                    obstacleY = futurY - i;
                    while (obstacle.hasNext()) {
                        Position lp = obstacle.next();
                        if (lp.getX() == obstacleX && lp.getY() == obstacleY) {
                            rovermap.obstaclePositions().remove(lp);
                            obstacle = rovermap.obstaclePositions().iterator();
                            i = rang;
                            break;
                        }
                    }
                    obstacle = rovermap.obstaclePositions().iterator();
                }
            } else if (direction == "EAST") {
                obstacleY = futurY;
                for (int i = 1; i <= rang; i++) {
                    obstacleX = currentX + i;
                    while (obstacle.hasNext()) {
                        Position lp = obstacle.next();
                        if (lp.getX() == obstacleX && lp.getY() == obstacleY) {
                            rovermap.obstaclePositions().remove(lp);
                            obstacle = rovermap.obstaclePositions().iterator();
                            i = rang;
                            break;
                        }
                    }
                    obstacle = rovermap.obstaclePositions().iterator();
                }
            } else if (direction == "WEST") {
                obstacleY = futurY;
                for (int i = 1; i <= rang; i++) {
                    obstacleX = currentX - i;
                    while (obstacle.hasNext()) {
                        Position lp = obstacle.next();
                        if (lp.getX() == obstacleX && lp.getY() == obstacleY) {
                            rovermap.obstaclePositions().remove(lp);
                            obstacle = rovermap.obstaclePositions().iterator();
                            i = rang;
                            break;
                        }
                    }
                    obstacle = rovermap.obstaclePositions().iterator();
                }
            }
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
