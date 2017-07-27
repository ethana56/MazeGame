
package ethan.solver.domain;

import java.util.HashMap;
import java.util.Map;
import pkgnew.maze.game.Maze;
import pkgnew.maze.game.Point;

public class Solver {
    Maze maze;
    Map<Point, Boolean> visitedPoints;
    public Solver(Maze maze) {
        this.maze = maze;
        this.visitedPoints = new HashMap<>();
    }
    
    public void solve() {
        for (int y = 0; y < this.maze.getHeight(); y++) {
            for (int x = 0; x < this.maze.getWidth(); x++) {
                
            }
        }
    }
}
