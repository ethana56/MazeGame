
package pkgnew.maze.game;

import java.util.ArrayList;
import java.util.List;

import ethan.solver.domain.Solver;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import javafx.stage.Stage;

public class NewMazeGame extends Application implements EventHandler<ActionEvent> {
    
    private Maze maze;
    private int mazeWidth;
    private int mazeHeight;
    private int spaceSize;
    private List<Point> solutionPoints = new ArrayList<>(); 
    private Stage window;
    private boolean buttonHasBeenPressed = false;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.window = primaryStage;
        primaryStage.setTitle("Maze");
        primaryStage.setScene(new Scene(createContentOptionsScreen()));
        





        
        primaryStage.show();
        
    }
    
    private Parent createContentOptionsScreen() {
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(25, 25, 25, 25));
        root.setPrefSize(500, 500);
        TextField mazeWidthInput = new TextField();
        TextField mazeHeightInput = new TextField();
        TextField spaceSizeInput = new TextField();
        Label mazeWidthLabel = new Label("Maze Width");
        Label mazeHeightLabel = new Label("Maze Height");
        Label spaceSizeLabel = new Label("Space Size");
        Button enterInfo = new Button("Enter");
        root.add(mazeWidthInput, 1, 0);
        root.add(mazeHeightInput, 1, 1);
        root.add(spaceSizeInput, 1, 2);
        root.add(mazeWidthLabel, 0, 0);
        root.add(mazeHeightLabel, 0, 1);
        root.add(spaceSizeLabel, 0, 2);
        root.add(enterInfo, 1, 3);
        
        enterInfo.setOnAction(e -> createContentMazeScreen(Integer.parseInt(mazeWidthInput.getText()),
                Integer.parseInt(mazeHeightInput.getText()), Integer.parseInt(spaceSizeInput.getText()))); 
            
        return root;
    }
    
    private void createContentMazeScreen(int mazeWidth, int  mazeHeight, int spaceSize) {
        if (!this.buttonHasBeenPressed) {
            this.maze = new Maze(mazeWidth, mazeHeight);
        }
        this.spaceSize = spaceSize;
        this.mazeWidth = mazeWidth;
        this.mazeHeight = mazeHeight;
        this.spaceSize = spaceSize;
        
        
        Pane root = new Pane();
        root.setPrefSize(1000, 1000);
        
        for (int y = 0; y < this.maze.getHeight(); y++) {
            for (int x = 0; x < this.maze.getWidth(); x++) {
                createSpaceSquare(root, this.maze.getSpace(new Point(x, y)), x * this.spaceSize, y * this.spaceSize);
                
            }
        }
        Button solveButton = new Button("Solve");
        root.getChildren().add(solveButton);
        solveButton.setLayoutX(this.spaceSize * this.maze.getWidth());
        solveButton.setOnAction(this);
        root.setPrefSize((mazeWidth * this.spaceSize) + solveButton.getWidth() * 2, mazeHeight * this.spaceSize);
        //System.out.println(solveButton.getWidth());
        this.window.setScene(new Scene(root));
    }
    
    private void createSpaceSquare(Pane pane, Space space, int x, int y) {
        
        if (space.isWall(Direction.LEFT)) {
            Line lineLeft = new Line(x, y, x, y + this.spaceSize);
            pane.getChildren().add(lineLeft);
        }
        if (space.isWall(Direction.RIGHT)) {
            Line lineRight = new Line(x + this.spaceSize, y, x + this.spaceSize, y + this.spaceSize);
            pane.getChildren().add(lineRight);
        }
        if (space.isWall(Direction.UP)) {
            Line lineUp = new Line(x, y, x + this.spaceSize, y);
            pane.getChildren().add(lineUp);
        }
        if (space.isWall(Direction.DOWN)) {
            Line lineDown = new Line(x, y + this.spaceSize, x + this.spaceSize, y + this.spaceSize);
            pane.getChildren().add(lineDown);
        }
        if (this.solutionPoints.contains(new Point(space.getPoint().getX(), space.getPoint().getY()))) {
        		Circle circle = new Circle(this.spaceSize / 4);
        		circle.setCenterX(x + 10);
        		circle.setCenterY(y + 10);
        		pane.getChildren().add(circle);
                        System.out.println("hi");
        }
        
    }
    
    @Override
    public void handle(ActionEvent event) {
        this.buttonHasBeenPressed = true;
        Solver solver = new Solver(this.maze);
        List<Point> solutionPoints = solver.getSolutionPoints();
        this.solutionPoints = solutionPoints;
        createContentMazeScreen(this.mazeWidth, this.mazeHeight, this.spaceSize);
    }
    
    public static void main(String[] args) {
        
        launch(args);
    }
    
    
}
    

