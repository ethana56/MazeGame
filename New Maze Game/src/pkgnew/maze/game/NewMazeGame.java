
package pkgnew.maze.game;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import javafx.scene.shape.Line;

import javafx.stage.Stage;

public class NewMazeGame extends Application {
    
    private static Maze maze;
    
    public static void main(String[] args) {
        NewMazeGame.maze = new Maze(50, 50);
        launch(args);
    }
    
    private Parent createContent() {
        int lineLength = 15;
        Pane root = new Pane();
        root.setPrefSize(600, 600);
        
        for (int y = 0; y < this.maze.getHeight(); y++) {
            for (int x = 0; x < this.maze.getWidth(); x++) {
                createSpaceSquare(root, this.maze.getSpace(new Point(x, y)), x * lineLength, y * lineLength);
                
            }
        }
        return root;
    }
    
    private void createSpaceSquare(Pane pane, Space space, int x, int y) {
        int lineLength = 15;
        if (space.isWall(Direction.LEFT)) {
            Line lineLeft = new Line(x, y, x, y + lineLength);
            pane.getChildren().add(lineLeft);
        }
        if (space.isWall(Direction.RIGHT)) {
            Line lineRight = new Line(x + lineLength, y, x + lineLength, y + lineLength);
            pane.getChildren().add(lineRight);
        }
        if (space.isWall(Direction.UP)) {
            Line lineUp = new Line(x, y, x + lineLength, y);
            pane.getChildren().add(lineUp);
        }
        if (space.isWall(Direction.DOWN)) {
            Line lineDown = new Line(x, y + lineLength, x + lineLength, y + lineLength);
            pane.getChildren().add(lineDown);
        }
        
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Maze");
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
        
    }
    
    
}
    

