
package pkgnew.maze.game;

public class Point {
    private int x;
    private int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Point(Point point) {
        this.x = point.getX();
        this.y = point.getY();
    }
    
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void setX(int dx) {
        this.x = dx;
    }
    
    public void setY(int dy) {
        this.y = dy;
    }
    
    public void move(Direction direction) {
        if (direction == Direction.LEFT) {
            this.x--;
        } else if (direction == Direction.RIGHT) {
            this.x++;
        } else if (direction == Direction.UP) {
            this.y--;
        } else if (direction == Direction.DOWN) {
            this.y++;
        }
    }
    
    @Override
    public int hashCode() {
        return this.x * 7 + this.y * 23;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Point pointCast = (Point) obj;
        return pointCast.getX() == this.x && pointCast.getY() == this.y;
    }
    
    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
