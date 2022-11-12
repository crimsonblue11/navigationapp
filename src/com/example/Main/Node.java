package com.example.Main;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class Node {
    private final double m_XPos;
    private final double m_YPos;
    private final int m_ID;
    private static int count = 0;
    private final ArrayList<Node> links = new ArrayList<>();

    public void AddLink(Node n) {
        links.add(n);
    }

    public ArrayList<Node> GetLinks() {
        return links;
    }

    public int GetID() {
        return m_ID;
    }

    public double GetX() {
        return m_XPos;
    }

    public double GetY() {
        return m_YPos;
    }

    public static void ResetCount() {
        count = 0;
    }

    public Node(GraphicsContext g, double x, double y) {
        m_ID = count;
        count++;
        m_XPos = x;
        m_YPos = y;

        DrawSelf(g);
    }

    private void DrawSelf(GraphicsContext g) {
        double WIDTH = 25;
        double HEIGHT = 25;

        g.strokeOval(m_XPos, m_YPos, WIDTH, HEIGHT);
        g.fillText("" + m_ID, m_XPos, m_YPos);
    }
}
