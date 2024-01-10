package ru.cosmosway.web03;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.io.Serializable;

@Entity
public class Req implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double x;
    private double y;
    private double r;
    private String execTime = "";
    private String time = "";
    private boolean isIn = true;

    // Конструкторы
    public Req() {}

    public Req(double x, double y, double r, String execTime, String time, boolean isIn) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.execTime = execTime;
        this.time = time;
        this.isIn = checkout();
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x; this.isIn = checkout();
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;this.isIn = checkout();
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;this.isIn = checkout();
    }

    public String getExecTime() {
        return execTime;
    }

    public void setExecTime(String execTime) {
        this.execTime = execTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isIsIn() {
        return isIn;
    }

    public void setIsIn(boolean isIn) {
        this.isIn = checkout();
    }

    public boolean checkout() {
        return checkTriangle() || checkRectangle() || checkCircle();
    }

    private boolean checkCircle() {
        return x >= 0 && y <= 0 && x*x + y*y <= (double)r*r;
    }

    private boolean checkTriangle() {
        return x <=0 && y <=0 && ( Math.abs(y + x) <=(double)r/2);
    }

    private boolean checkRectangle() {
        return x <= 0 && y >= 0 && Math.abs(x) <= (double)r && Math.abs(y) <= (double)r/2;
    }
}

