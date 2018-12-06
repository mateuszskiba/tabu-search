package pl.poznan.put.algorithm;

import pl.poznan.put.structures.Instance;
import pl.poznan.put.structures.Job;
import pl.poznan.put.structures.Problem;

import java.util.List;

public class BruteForceScheduler implements Scheduler {

    @Override
    public Problem schedule(Instance instance) {
        final Problem problem = instance.getProblem();
        final List<Job> jobs = problem.getJobs();
        jobs.sort((j1, j2) -> {
            if (j1.getTardinessToEarlinessRatio() == j2.getTardinessToEarlinessRatio()) {
                return 0;
            }
            else {
                return j1.getTardinessToEarlinessRatio() < j2.getTardinessToEarlinessRatio() ? 1 : -1;
            }
        });
        return new Problem(jobs);
    }
}
