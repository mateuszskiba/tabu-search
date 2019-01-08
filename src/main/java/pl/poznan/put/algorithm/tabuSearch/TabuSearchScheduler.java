package pl.poznan.put.algorithm.tabuSearch;

import pl.poznan.put.algorithm.Calc;
import pl.poznan.put.algorithm.Scheduler;
import pl.poznan.put.structures.Instance;
import pl.poznan.put.structures.Job;

import java.util.*;

public class TabuSearchScheduler implements Scheduler {
    private final int NUMBER_OF_CANDIDATES = 100;
    private final int TABU_SIZE = 10;
    private final Random random = new Random();
    private int timeBound;

    public TabuSearchScheduler(int timeBound) {
        this.timeBound = timeBound;
    }

    @Override
    public List<Job> schedule(Instance instance) {
        List<Job> jobs = instance.cloneJobs();
        final int size = jobs.size();

        List<Job> bestSchedule = sortByRatio(jobs);
        Calc.countDueDate(instance);
        int currentCost = Calc.countCostFunctionValue(bestSchedule);
        List<Job> lastSchedule = bestSchedule;
        int bestCost = currentCost;

        List<Candidate> candidates = new ArrayList<>();
        TabuList tabuList = new TabuList(TABU_SIZE);

        long startTime = System.currentTimeMillis();
        long iterationTime = System.currentTimeMillis();
        while((iterationTime - startTime) < timeBound) {
            candidates.clear();
            while (candidates.size() < NUMBER_OF_CANDIDATES) {
                Move randMove = randomMove();
                List<Job> currentSchedule;

                int startIndex;
                Job job;
                do {
                    startIndex = getRandInt(size);
                    job = lastSchedule.get(startIndex);
                } while (tabuList.contains(job.getId()));

                int destinationIndex;
                if (randMove == Move.SWAP) {
                    do {
                        destinationIndex = getRandInt(size);
                        job = lastSchedule.get(destinationIndex);
                    } while (tabuList.contains(job.getId()) || startIndex == destinationIndex);
                    currentSchedule = swapJobs(lastSchedule, startIndex, destinationIndex);
                } else {
                    do {
                        destinationIndex = getRandInt(size);
                    } while (startIndex == destinationIndex);
                    currentSchedule = shiftJobs(lastSchedule, startIndex, destinationIndex);
                }

                final int newCost = Calc.countCostFunctionValue(currentSchedule);

                candidates.add(
                        new Candidate(
                                randMove,
                                startIndex,
                                destinationIndex,
                                currentCost - newCost)
                );
            }

            candidates = sortByCost(candidates);
            Candidate bestCandidate = candidates.get(0);
            lastSchedule = (bestCandidate.getMoveType() == Move.SWAP) ?
                    swapJobs(lastSchedule, bestCandidate.getStartIndex(), bestCandidate.getDestinationIndex()) :
                    shiftJobs(lastSchedule, bestCandidate.getStartIndex(), bestCandidate.getDestinationIndex());
            currentCost = Calc.countCostFunctionValue(lastSchedule);
            if (currentCost < bestCost) {
                bestSchedule = lastSchedule;
                bestCost = currentCost;
            }

            tabuList.addMove(bestCandidate.getStartIndex());
            if (bestCandidate.getMoveType() == Move.SWAP) tabuList.addMove(bestCandidate.getDestinationIndex());

            iterationTime = System.currentTimeMillis();
        }
        System.out.println(iterationTime - startTime);

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

    private List<Job> shiftJobs(List<Job> jobs, int sourceIndex, int destinationIndex) {
        final List<Job> clonedJobs = new ArrayList<>();
        jobs.forEach(job -> clonedJobs.add(job.clone()));
        Job jobToShift = clonedJobs.remove(sourceIndex);
        clonedJobs.add(destinationIndex, jobToShift);
        return clonedJobs;
    }

    private Move randomMove() {
        return Move.values()[random.nextInt(Move.values().length)];
    }

}
