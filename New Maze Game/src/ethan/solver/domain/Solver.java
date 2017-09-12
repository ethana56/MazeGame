
package ethan.solver.domain;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pkgnew.maze.game.Direction;
import pkgnew.maze.game.Maze;
import pkgnew.maze.game.Point;
import pkgnew.maze.game.Space;

public class Solver {
    Maze maze;
    List<Point> visitedPoints;
    List<Point> solutionPoints;
    public Solver(Maze maze) {
        this.maze = maze;
        this.visitedPoints = new ArrayList<>();
        this.solutionPoints = new ArrayList<>();
        solve();
    }
    
    private void solve() {
        List<Point> backTrack = new ArrayList<>();
        backTrack.add(new Point(0, 0));
        List<Space> spaces = this.maze.getSpaces();
        Direction[] directions  = {Direction.DOWN, Direction.LEFT, Direction.RIGHT, Direction.UP};
        while(!atLastPoint(backTrack)) {
            Point currentPoint = backTrack.get(backTrack.size() - 1);
            //System.out.println(backTrack.get(backTrack.size() - 1));
            int counter = 0;
            while(true) {
                if (noPossibleMoves(currentPoint)) {
                    setVisited(currentPoint);
                    backTrack.remove(currentPoint);
                    break;
                }
                if (possibleMove(currentPoint, directions[counter])) {
                    
                    Point toPoint = new Point(currentPoint);
                    toPoint.move(directions[counter]);
                    setVisited(currentPoint);
                    backTrack.add(toPoint);
                    break;
                }
                counter++;
            }
        }
        setVisited(backTrack.get(backTrack.size() - 1));
        this.solutionPoints.addAll(backTrack);
        System.out.println(this.solutionPoints);
    }
    
    private boolean noPossibleMoves(Point point) {
        int counter = 0;
        Direction[] directions = {Direction.DOWN, Direction.RIGHT, Direction.LEFT, Direction.UP};
        for (Direction direction : directions) {
            if (!possibleMove(point, direction)) {
                counter++;
            }
        }
        
        return counter == 4;
    }
    
    private boolean possibleMove(Point point, Direction direction) {
        Point pointCopy = new Point(point);
        pointCopy.move(direction);
        if (this.maze.getSpace(point).isWall(direction)) {
            return false;
        }
        if (hasBeenVisited(pointCopy)) {
            return false;
        }
        if (isOutOfBounds(pointCopy)) {
            return false;
        }
        
        
        return true;
    }
    
    private boolean atLastPoint(List<Point> points) {
        return points.contains(new Point(this.maze.getWidth() - 1, this.maze.getHeight() - 1));
    }
    
    private void setVisited(Point point) {
        this.visitedPoints.add(point);
    }
    
    private boolean hasBeenVisited(Point point) {
        return this.visitedPoints.contains(point);
    }
    
    public List<Point> getSolutionPoints() {
        return this.solutionPoints;
    }
    
    private boolean isOutOfBounds(Point point) {
        return point.getX() < 0 || point.getY() < 0 || point.getX() > this.maze.getWidth() - 1 || 
                point.getY() > this.maze.getHeight() - 1;
    }
    
    
}
