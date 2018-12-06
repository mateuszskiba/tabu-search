package pl.poznan.put.structures;

import java.util.ArrayList;
import java.util.List;

public class Problem {
    private List<Job> jobs = new ArrayList<>();

    public Problem() {}

    public Problem(List<Job> jobs) {
        this.jobs = jobs;
    }

    public List<Job> cloneJobs() {
        final List<Job> result = new ArrayList<>();
        jobs.forEach(job -> result.add(job.clone()));
        return result;
    }

    public void addJob(Job job) {
        jobs.add(job);
    }

    public List<Job> getJobs() {
        return jobs;
    }
}
