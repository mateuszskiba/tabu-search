package pl.poznan.put.structures;

import java.util.ArrayList;
import java.util.List;

public class Instance {
    private int id;
    private List<Job> jobs;
    private double h;

    public Instance(int id, List<Job> jobs, double h) {
        this.id = id;
        this.jobs = jobs;
        this.h = h;
    }

    public int getId() {
        return id;
    }

    public List<Job> cloneJobs() {
        final List<Job> result = new ArrayList<>();
        jobs.forEach(job -> result.add(job.clone()));
        return result;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public double getH() {
        return h;
    }
}
