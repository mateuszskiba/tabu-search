package pl.poznan.put.algorithm;

import pl.poznan.put.structures.Instance;
import pl.poznan.put.structures.Job;

import java.util.List;

public class BruteForceScheduler implements Scheduler {

    @Override
    public List<Job> schedule(Instance instance) {
        final List<Job> jobs = instance.cloneJobs();
        jobs.sort((j1, j2) -> {
            if (j1.getTardinessToEarlinessRatio() == j2.getTardinessToEarlinessRatio()) {
                return 0;
            }
            else {
                return j1.getTardinessToEarlinessRatio() < j2.getTardinessToEarlinessRatio() ? 1 : -1;
            }
        });
        return jobs;
    }
}
