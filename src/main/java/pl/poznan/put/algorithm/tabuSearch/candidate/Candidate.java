package pl.poznan.put.algorithm.tabuSearch.candidate;

import pl.poznan.put.algorithm.tabuSearch.Move;

import java.util.List;

public abstract class Candidate {
    private int startIndex;
    private int destinationIndex;
    private int costProfit;

    public Candidate(int startIndex, int destinationIndex, int costProfit) {
        this.startIndex = startIndex;
        this.destinationIndex = destinationIndex;
        this.costProfit = costProfit;
    }

    public int getStartIndex() {
        return startIndex;
    }
    public int getDestinationIndex() {
        return destinationIndex;
    }
    public int getCostProfit() {
        return costProfit;
    }
    public abstract List<Integer> getJobIds();
    public abstract Move getMoveType();
}
