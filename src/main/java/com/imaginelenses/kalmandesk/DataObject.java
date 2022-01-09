package com.imaginelenses.kalmandesk;

import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.robot.Robot;

public class DataObject {

    public Float x;
    public Float y;
    public Float z;
    public Float timestamp;


    public DataObject() {
    }

    public DataObject(String x, String y, String z, String timestamp) {
        super();
        this.x = Float.parseFloat(x);
        this.y = Float.parseFloat(y);
        this.z = Float.parseFloat(z);
        this.timestamp = Float.parseFloat(timestamp);
    }

    public void moveCur(Float x, Float y) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Robot robot = new Robot();
                Point2D p = robot.getMousePosition();
                System.out.printf("%s %s%n", p.getX(), p.getY());
                robot.mouseMove(p.getX() + x, p.getY() + y);
            }
        });
    }
}

