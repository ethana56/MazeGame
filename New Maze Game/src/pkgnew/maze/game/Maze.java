
package pkgnew.maze.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Maze {
    private int width;
    private int height;
    private List<Space> spaces;
    int counter;
    public Maze(int width, int height) {
        this.spaces = new ArrayList<>();
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
            }
        }
        this.spaces.get(0).removeWall(Direction.LEFT);
        this.spaces.get(this.spaces.size() - 1).removeWall(Direction.DOWN);
    }
    
    private void generateMaze(Point initialPoint) {
       List<Point> backTrack = new ArrayList<>();
       backTrack.add(initialPoint);
        
       while (!backTrack.isEmpty()) {
            Point currentPoint = backTrack.get(backTrack.size() - 1);
            Point fromPoint = new Point(currentPoint.getX(), currentPoint.getY());
            while (true) {
                Direction directionToGo = Direction.getRandom();
                if (noPossibleMoves(fromPoint)) {
                    getSpace(fromPoint).setHasBeenVisited(true);
                    backTrack.remove(fromPoint);
                    break;
                }
                if (possibleMove(fromPoint, directionToGo)) {
                    Point toPoint = new Point(fromPoint.getX(), fromPoint.getY());
                    toPoint.move(directionToGo);
                    getSpace(fromPoint).removeWall(directionToGo);
                    getSpace(toPoint).removeWall(Direction.getOpposite(directionToGo));
                    getSpace(fromPoint).setHasBeenVisited(true);
                    backTrack.add(toPoint);
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
        if (getSpace(pointMove).hasBeenVisited()) {
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
    
    
}
