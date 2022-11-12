package com.example.Main;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Main extends Application {
    private final ArrayList<Node> nodes = new ArrayList<>();
    @FXML
    private Label errorText;
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

    private void drawLink(Node n1, Node n2) {
        double startX = n1.GetX() + 12.5;
        double startY = n1.GetY() + 12.5;
        double endX = n2.GetX() + 12.5;
        double endY = n2.GetY() + 12.5;

        canvas.getGraphicsContext2D().strokeLine(startX, startY, endX, endY);
    }

    @FXML
    private void handleSubmit() {
        Node n1, n2;
        errorText.setVisible(false);

        if (node1input.getText().equals("") || node2input.getText().equals("")) {
            setErrorText("ERROR: one or more null inputs");
            return;
        }

            try {
            n1 = nodes.get(Integer.parseInt(node1input.getText()));
            n2 = nodes.get(Integer.parseInt(node2input.getText()));
        } catch (IndexOutOfBoundsException e) {
            setErrorText("ERROR: Node does not exist");
            return;
        }

        if (n1.GetID() == n2.GetID()) {
            setErrorText("ERROR: Node cannot connect to itself");
            return;
        } else if (n1.GetLinks().contains(n2)) {
            setErrorText("ERROR: Nodes already connected");
            return;
        }

        drawLink(n1, n2);
        n1.AddLink(n2);
        n2.AddLink(n1);

        node1input.setText("");
        node2input.setText("");
    }

    @FXML
    private void handleReset() {
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        nodes.clear();
        Node.ResetCount();
    }

    private void setErrorText(String error) {
        errorText.setText(error);
        errorText.setVisible(true);
    }
}
