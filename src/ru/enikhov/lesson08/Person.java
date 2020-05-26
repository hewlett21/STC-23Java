package ru.enikhov.lesson08;

import java.util.List;

public class Person {
    private static final long serialVersionUID = -1073369096518010570L;

    private String name;
    private int age;
    private double height;
    private boolean married;
    private List<String> list;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Person(String name, int age, double height, boolean married, List<String> list) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.married = married;
        this.list = list;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public boolean getMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", married=" + married +
                ", list=" + list +
                '}';
    }
}
