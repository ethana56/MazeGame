
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
    Map<Point, Boolean> visitedPoints;
    List<Point> solutionPoints;
    public Solver(Maze maze) {
        this.maze = maze;
        this.visitedPoints = new HashMap<>();
        this.solutionPoints = new ArrayList<>();
        solve();
    }
    
    private void solve() {
        //System.out.println("Hi");
        List<Point> backTrack = new ArrayList<>();
        backTrack.add(new Point(0, 0));
        List<Space> spaces = this.maze.getSpaces();
        Direction[] directions  = {Direction.DOWN, Direction.LEFT, Direction.RIGHT, Direction.UP};
        initializePoints(spaces);
        while(!atLastPoint(backTrack)) {
            Point currentPoint = backTrack.get(backTrack.size() - 1);
            //System.out.println(backTrack.size());
            int counter = 0;
            //System.out.println("Hi");
            while(true) {
                System.out.println(currentPoint);
                if (noPossibleMoves(currentPoint)) {
                    setVisited(currentPoint);
                    backTrack.remove(currentPoint);
                    //System.out.println("Hi");
                    break;
                }
                if (possibleMove(currentPoint, directions[counter])) {
                    
                    Point toPoint = new Point(currentPoint.getX(), currentPoint.getY());
                    toPoint.move(directions[counter]);
                    setVisited(currentPoint);
                    backTrack.add(toPoint);
                    //System.out.println("Hi");
                    break;
                }
                counter++;
            }
        }
        setVisited(backTrack.get(backTrack.size() - 1));
        this.solutionPoints.addAll(backTrack);
    }
    
    private boolean noPossibleMoves(Point point) {
        int counter = 0;
        Direction[] directions = {Direction.DOWN, Direction.RIGHT, Direction.LEFT, Direction.UP};
        for (Direction direction : directions) {
            if (!possibleMove(point, direction)) {
                counter++;
            }
        }
        System.out.println(counter == 4);
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
    
    private void initializePoints(List<Space> spaces) {
        for (Space spaceTemp : spaces) {
            this.visitedPoints.put(new Point(spaceTemp.getPoint()), false);
        }
    }
    
    private boolean atLastPoint(List<Point> points) {
        return points.contains(new Point(this.maze.getWidth(), this.maze.getHeight()));
    }
    
    private void setVisited(Point point) {
        this.visitedPoints.put(point, true);
    }
    
    private boolean hasBeenVisited(Point point) {
        return this.visitedPoints.get(point);
    }
    
    public List<Point> getSolutionPoints() {
        return this.solutionPoints;
    }
    
    private boolean isOutOfBounds(Point point) {
        return point.getX() < 0 || point.getY() < 0 || point.getX() > this.maze.getWidth() - 1 || 
                point.getY() > this.maze.getHeight() - 1;
    }
    
    
}
