package pl.poznan.put.algorithm;

import pl.poznan.put.structures.Instance;
import pl.poznan.put.structures.Job;

import java.util.List;

public class Calc {

    private static Integer dueDate;

    private Calc() {}

    public static void countDueDate(Instance instance) {
        final List<Job> jobs = instance.getJobs();
        final int sumP = jobs.stream()
                .mapToInt(Job::getProcessingTime)
                .sum();
        final double h = instance.getH();
        dueDate = (int) Math.floor(sumP * h);
    }

    public static int countCostFunctionValue(List<Job> solved) {
        int currentTime = 0;
        int costFunctionValue = 0;

        for (Job job : solved) {
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
