package pl.poznan.put.algorithm.tabuSearch.candidate;

import pl.poznan.put.algorithm.tabuSearch.Move;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SwapCandidate extends Candidate {
    private final Move moveType = Move.SWAP;
    private int jobId1;
    private int jobId2;

    public SwapCandidate(int startIndex, int destinationIndex, int costProfit, int jobId1, int jobId2) {
        super(startIndex, destinationIndex, costProfit);
        this.jobId1 = jobId1;
        this.jobId2 = jobId2;
    }

    @Override
    public List<Integer> getJobIds() {
        return new ArrayList<>(Arrays.asList(jobId1, jobId2));
    }

    @Override
    public Move getMoveType() {
        return moveType;
    }

}
