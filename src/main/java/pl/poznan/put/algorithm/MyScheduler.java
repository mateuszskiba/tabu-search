package pl.poznan.put.algorithm;

import pl.poznan.put.structures.Instance;
import pl.poznan.put.structures.Job;
import pl.poznan.put.structures.Problem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyScheduler implements Scheduler {
    private static final double BALANCE_RATIO = 2.0;

    @Override
    public Problem schedule(Instance instance) {
        final int dueDate = Calc.countDueDate(instance);

        final Problem problem = instance.getProblem();
        List<Job> jobs = problem.cloneJobs();
        final List<Job> solved = new ArrayList<>();

        final List<Job> group1 = new ArrayList<>();
        final List<Job> group2 = new ArrayList<>();
        while (!jobs.isEmpty()) {
            final Job job = jobs.get(0);
            if (job.getTardinessToEarlinessRatio() > BALANCE_RATIO) {
                group1.add(job);
            } else {
                group2.add(job);
            }
            jobs.remove(0);
        }

        group1.sort((j1, j2) -> {
            if (j1.getEarlinessPenalty() > j2.getEarlinessPenalty()) {
                return 1;
            } else if (j1.getEarlinessPenalty() < j2.getEarlinessPenalty()) {
                return -1;
            } else {
                return Integer.compare(j1.getTardinessPenalty(), j2.getTardinessPenalty());
            }
        });

        group2.sort((j1, j2) -> {
            if (j1.getTardinessToEarlinessRatio() < j2.getTardinessToEarlinessRatio()) {
                return 1;
            } else if (j1.getTardinessToEarlinessRatio() > j2.getTardinessToEarlinessRatio()) {
                return -1;
            } else {
                return Integer.compare(j1.getTardinessPenalty(), j2.getTardinessPenalty());
            }
        });

        jobs = Stream.concat(group1.stream(), group2.stream())
                .collect(Collectors.toList());

        int currentTime = 0;
        while (currentTime < dueDate) {
            final Job job = jobs.get(0);
            if (currentTime + job.getProcessingTime() <= dueDate) {
                currentTime += job.getProcessingTime();
                solved.add(job);
                jobs.remove(0);
            } else {
                break;
            }
        }

        jobs.sort((j1, j2) -> {
            if (j1.getTardinessPenalty() == j2.getTardinessPenalty()) {
                return Integer.compare(j1.getProcessingTime(), j2.getProcessingTime());
            }
            return Integer.compare(j2.getTardinessPenalty(), j1.getTardinessPenalty());
        });

        while (!jobs.isEmpty()) {
            solved.add(jobs.get(0));
            jobs.remove(0);
        }

        return new Problem(solved);
    }

    private void printProblem(Problem problem, int dueDate) {
        System.out.println("Solved " + dueDate);
        System.out.println("id\t\tp(i)\ta(i)\tb(i)\ttard/earl");
        for (Job job : problem.getJobs()) {
            System.out.println(String.format("%d\t\t%d\t\t%d\t\t%d\t\t%.2f",
                    job.getId(),
                    job.getProcessingTime(),
                    job.getEarlinessPenalty(),
                    job.getTardinessPenalty(),
                    job.getTardinessToEarlinessRatio()
            ));
        }
    }
}
