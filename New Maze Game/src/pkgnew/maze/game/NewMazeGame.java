
package pkgnew.maze.game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import javafx.scene.shape.Line;

import javafx.stage.Stage;

public class NewMazeGame extends Application implements EventHandler<ActionEvent> {
    
    private static Maze maze;
    
    public static void main(String[] args) {
        NewMazeGame.maze = new Maze(10, 10);
        
        launch(args);
    }
    
    private Parent createContent() {
        int lineLength = 20;
        Pane root = new Pane();
        root.setPrefSize(1000, 1000);
        
        for (int y = 0; y < this.maze.getHeight(); y++) {
            for (int x = 0; x < this.maze.getWidth(); x++) {
                createSpaceSquare(root, this.maze.getSpace(new Point(x, y)), x * lineLength, y * lineLength);
                
            }
        }
        Button button = new Button("Solve");
        root.getChildren().add(button);
        button.setLayoutX(lineLength * this.maze.getWidth());
        button.setOnAction(this);
        return root;
    }
    
    private void createSpaceSquare(Pane pane, Space space, int x, int y) {
        int lineLength = 20;
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

    @Override
    public void handle(ActionEvent event) {
        
    }
    
    
}
    

