
package pkgnew.maze.game;

import java.util.Random;

public enum Direction {
    LEFT, RIGHT, UP, DOWN;
    
    public static Direction getRandom() {
        Random random = new Random();
        int number = random.nextInt(4);
        if (number == 0) {
            return Direction.LEFT;
        }
        if (number == 1) {
            return Direction.RIGHT;
        }
        if (number == 2) {
            return Direction.UP;
        } 
        return Direction.DOWN;
    }
    
    public static Direction getOpposite(Direction direction) {
        if (direction == Direction.DOWN) {
            return Direction.UP;
        }
        if (direction == Direction.UP) {
            return Direction.DOWN;
        }
        if (direction == Direction.RIGHT) {
            return Direction.LEFT;
        }
        return Direction.RIGHT;
    }
}
