
package pkgnew.maze.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Maze {
    private int width;
    private int height;
    private List<Space> spaces;
    private Map<Point, Boolean> pointsVisited;
    int counter;
    public Maze(int width, int height) {
        this.spaces = new ArrayList<>();
        this.pointsVisited = new HashMap<>();
        this.width = width;
        this.height = height;
        generateGrid(width, height);
        generateMaze(new Point(0,0));
        
    }
   
    public int getHeight() {
        return this.height;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    private void generateGrid(int width, int height) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.spaces.add(new Space(x, y));
                this.pointsVisited.put(new Point(x, y), false);
            }
        }
        this.spaces.get(0).removeWall(Direction.LEFT);
        this.spaces.get(this.spaces.size() - 1).removeWall(Direction.DOWN);
    }
    //the starting space is always 0,0 and ending is always width,height
    private void generateMaze(Point initialPoint) {
       List<Point> backTrack = new ArrayList<>();
       backTrack.add(initialPoint);
        
       while (!backTrack.isEmpty()) {
            Point currentPoint = backTrack.get(backTrack.size() - 1);
            while (true) {
                Direction directionToGo = Direction.getRandom();
                if (noPossibleMoves(currentPoint)) {
                    setVisited(currentPoint);
                    backTrack.remove(currentPoint);
                    break;
                }
                if (possibleMove(currentPoint, directionToGo)) {
                    Point toPoint = new Point(currentPoint.getX(), currentPoint.getY());
                    toPoint.move(directionToGo);
                    getSpace(currentPoint).removeWall(directionToGo);
                    getSpace(toPoint).removeWall(Direction.getOpposite(directionToGo));
                    setVisited(currentPoint);
                    backTrack.add(toPoint);
                    break;
                    } 
                }
            } 
       }
    
    private boolean isOutOfBounds(Point point) {
        return point.getX() < 0 || point.getY() < 0 || point.getX() > this.width - 1 || 
                point.getY() > this.height - 1;
    }
    
    private boolean noPossibleMoves(Point point) {
        int counter = 0;
        Direction[] directions = {Direction.DOWN, Direction.RIGHT, Direction.LEFT, Direction.UP};
        ArrayList<Direction> dirList = new ArrayList<>(Arrays.asList(directions));
        for (Direction direction : dirList) {
            if (!possibleMove(point, direction)) {
                counter++;
            }
        }
        return counter == 4;
    }
    
    private boolean possibleMove(Point point, Direction direction) {
        Point pointMove = new Point(point.getX(), point.getY());
        pointMove.move(direction);
        if (isOutOfBounds(pointMove)) {
            return false;
        }
        if (hasBeenVisited(pointMove)) {
            return false;
        }
        
        return true;
    }
    
    public Space getSpace(Point point) {
        for (Space space : this.spaces) {
            if (space.getPoint().equals(point)) {
                return space;
            }
        }
        return new Space(0, 0);
    }
    
    public List<Space> getSpaces() {
        return this.spaces;
    }
    
    public void setVisited(Point point) {
        this.pointsVisited.put(point, true);
    }
    
    public boolean hasBeenVisited(Point point) {
        return this.pointsVisited.get(point);
    }
    
    
}
