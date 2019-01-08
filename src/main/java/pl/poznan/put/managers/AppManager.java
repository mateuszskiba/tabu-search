package pl.poznan.put.managers;

import pl.poznan.put.algorithm.Calc;
import pl.poznan.put.algorithm.Scheduler;
import pl.poznan.put.algorithm.tabuSearch.TabuSearchScheduler;
import pl.poznan.put.structures.Instance;
import pl.poznan.put.structures.Job;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class AppManager {
    private final int n;
    private final int k;
    private final double h;
    private final int t;
    private final String filePath;

    public AppManager(int n, int k, double h, int t) {
        this.n = n;
        this.k = k;
        this.h = h;
        this.t = t;
        this.filePath = String.format("sch%d.txt", n);
    }

    public void run() throws FileNotFoundException {
        List<List<Job>> problems = FileManager.readFile(filePath);

        Scheduler scheduler = new TabuSearchScheduler(n, t*n);
        Instance instance = new Instance(k, problems.get(k), h);
        List<Job> solved = scheduler.schedule(instance);

        String fileName = String.format("%d_%d_%d.txt", n, k, (int) Math.round(h * 10));
        final int costFunctionValue = Calc.countCostFunctionValue(solved);
        printCostFunctionValue(instance, costFunctionValue);
        //printSolvedWithCostFuctionValue(instance, solved, costFunctionValue);

        try {
            FileManager.saveResult(fileName, costFunctionValue, solved);
        } catch (IOException e) {
            System.out.println("Can't write results to file");
            e.printStackTrace();
        }
    }

    private void printSolvedWithCostFuctionValue(Instance instance, List<Job> solved, int costFunctionValue) {
        printCostFunctionValue(instance, costFunctionValue);
        printProblem(solved);
    }

    private void printCostFunctionValue(Instance instance, int costFunctionValue) {
        System.out.println("Instance id: " + instance.getId() +
                ", h: " + instance.getH() + ", cost: " + costFunctionValue);
    }

    private void printProblem(List<Job> jobs) {
        System.out.println("id\t\tp(i)\ta(i)\tb(i)\ttard/earl");
        for (Job job : jobs) {
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
