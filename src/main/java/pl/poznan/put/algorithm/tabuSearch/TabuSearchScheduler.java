package pl.poznan.put.algorithm.tabuSearch;

import pl.poznan.put.algorithm.Calc;
import pl.poznan.put.algorithm.Scheduler;
import pl.poznan.put.structures.Instance;
import pl.poznan.put.structures.Job;

import java.util.*;

public class TabuSearchScheduler implements Scheduler {
    private final int NUMBER_OF_CANDIDATES = 100;
    private final Random random = new Random();

    @Override
    public List<Job> schedule(Instance instance) {
        List<Job> jobs = instance.cloneJobs();
        final int size = jobs.size();

        List<Job> bestSchedule = sortByRatio(jobs);
        int currentCost = Calc.countCostFunctionValue(instance, bestSchedule);
        List<Job> lastSchedule = bestSchedule;
        int bestCost = currentCost;

        List<Candidate> candidates = new ArrayList<>();
        for (int i=0; i<1000; i++) {
            candidates.clear();
            while (candidates.size() < NUMBER_OF_CANDIDATES) {
                int startIndex = getRandInt(size);
                int destinationIndex;
                do {
                    destinationIndex = getRandInt(size);
                } while (startIndex == destinationIndex);
                List<Job> swappedJobs = swapJobs(jobs, startIndex, destinationIndex);
                final int newCost = Calc.countCostFunctionValue(instance, swappedJobs);

                candidates.add(
                        new Candidate(
                                startIndex,
                                destinationIndex,
                                currentCost - newCost)
                );
            }

            candidates = sortByCost(candidates);
            final Candidate bestCandidate = candidates.get(0);
            lastSchedule = swapJobs(lastSchedule, bestCandidate.getStartIndex(), bestCandidate.getDestinationIndex());
            currentCost = Calc.countCostFunctionValue(instance, lastSchedule);
            if (currentCost < bestCost) {
                bestSchedule = lastSchedule;
                bestCost = currentCost;
            }
        }

        return bestSchedule;
    }

    private int getRandInt(int range) {
        return random.nextInt(range);
    }

    private List<Job> swapJobs(List<Job> jobs, int startIndex, int destinationIndex) {
        final List<Job> clonedJobs = new ArrayList<>();
        jobs.forEach(job -> clonedJobs.add(job.clone()));
        Collections.swap(clonedJobs, startIndex, destinationIndex);
        return clonedJobs;
    }

    private List<Candidate> sortByCost(List<Candidate> candidates) {
        candidates.sort(Comparator.comparingInt(Candidate::getCostProfit).reversed());
        return candidates;
    }

    private List<Job> sortByRatio(List<Job> jobs) {
        jobs.sort(Comparator.comparingDouble(Job::getTardinessToEarlinessRatio).reversed());
        return jobs;
    }
}
