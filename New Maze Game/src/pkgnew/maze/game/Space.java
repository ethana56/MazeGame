
package pkgnew.maze.game;

public class Space {
    private Point point;
    private boolean hasBeenVisited;
    private boolean wallRight;
    private boolean wallLeft;
    private boolean wallUp;
    private boolean wallDown;
    private boolean partOfPath;
    public Space(int x, int y) {
        this.point = new Point(x, y);
        this.wallRight = true;
        this.wallLeft = true;
        this.wallUp = true;
        this.wallDown = true;
    }
    
    public void setPartOfPath(boolean bool) {
        this.partOfPath = bool;
    }
    
    public boolean isPartOfPath() {
        return this.partOfPath;
    }
    
    public boolean isWall(Direction direction) {
        if (direction == Direction.RIGHT) {
            return this.wallRight;
        }
        if (direction == Direction.LEFT) {
            return this.wallLeft;
        }
        if (direction == Direction.UP) {
            return this.wallUp;
        }
        return this.wallDown;
    }
    
    public void removeWall(Direction direction) {
        if (direction == Direction.RIGHT) {
            this.wallRight = false;
        } else if (direction == Direction.LEFT) {
            this.wallLeft = false;
        } else if (direction == Direction.UP) {
            this.wallUp = false;
        } else if (direction == Direction.DOWN) {
            this.wallDown = false;
        }
    }
    
    
    public Point getPoint() {
        return this.point;
    }
    
    @Override
    public String toString() {
        return "" + this.point + " Right " + this.wallRight + " Left " + 
                this.wallLeft + " Up " + this.wallUp + " Down " + this.wallDown;
    }
    
    
    
}
