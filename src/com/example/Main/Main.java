package com.example.Main;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Main extends Application {
    private final ArrayList<Node> nodes = new ArrayList<>();

    @FXML
    private TextField node1input;
    @FXML
    private TextField node2input;
    @FXML
    private Canvas canvas;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Parent p = null;
        try {
            p = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/main.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(e.hashCode());
        }

        Scene scene = new Scene(p);

        stage.setScene(scene);
        stage.show();
    }

    private void nodeDraw(double x, double y) {
        Node n = new Node(canvas.getGraphicsContext2D(), x, y);
        nodes.add(n);
    }

    @FXML
    private void handleCanvas(MouseEvent m) {
        nodeDraw(m.getSceneX(), m.getSceneY());
    }

    private void addLink(Node n1, Node n2) {
        double startX = n1.GetX() + 12.5;
        double startY = n1.GetY() + 12.5;
        double endX = n2.GetX() + 12.5;
        double endY = n2.GetY() + 12.5;

        canvas.getGraphicsContext2D().strokeLine(startX, startY, endX, endY);
    }

    @FXML
    private void handleSubmit() {
        Node n1 = nodes.get(Integer.parseInt(node1input.getText()));
        Node n2 = nodes.get(Integer.parseInt(node2input.getText()));
        addLink(n1, n2);
    }

    @FXML
    private void handleReset() {
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        nodes.clear();
    }
}
