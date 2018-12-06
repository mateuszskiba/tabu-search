package pl.poznan.put.structures;

public class Instance {
    private int id;
    private Problem problem;
    private double h;

    public Instance(int id, Problem problem, double h) {
        this.id = id;
        this.problem = problem;
        this.h = h;
    }

    public int getId() {
        return id;
    }

    public Problem getProblem() {
        return problem;
    }

    public double getH() {
        return h;
    }
}
