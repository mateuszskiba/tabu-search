package pl.poznan.put.algorithm;

import pl.poznan.put.structures.Instance;
import pl.poznan.put.structures.Job;
import pl.poznan.put.structures.Problem;

import java.util.List;

public class Calc {

    private Calc() {}

    public static int countDueDate(Instance instance) {
        final List<Job> jobs = instance.getProblem().getJobs();
        final int sumP = jobs.stream()
                .mapToInt(Job::getProcessingTime)
                .sum();
        final double h = instance.getH();
        return (int) Math.floor(sumP * h);
    }

    public static int countCostFunctionValue(Instance instance, Problem solved) {
        final int dueDate = Calc.countDueDate(instance);
        int currentTime = 0;
        int costFunctionValue = 0;

        for (Job job : solved.getJobs()) {
            currentTime += job.getProcessingTime();
            if (dueDate > currentTime) {
                costFunctionValue += (dueDate - currentTime) * job.getEarlinessPenalty();
            } else {
                costFunctionValue += (currentTime - dueDate) * job.getTardinessPenalty();
            }
        }
        return costFunctionValue;
    }
}
