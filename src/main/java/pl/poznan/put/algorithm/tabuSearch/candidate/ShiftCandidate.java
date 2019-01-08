package pl.poznan.put.algorithm.tabuSearch.candidate;

import pl.poznan.put.algorithm.tabuSearch.Move;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShiftCandidate extends Candidate {
    private final Move moveType = Move.SHIFT;
    private int jobId;

    public ShiftCandidate(int startIndex, int destinationIndex, int costProfit, int jobId) {
        super(startIndex, destinationIndex, costProfit);
        this.jobId = jobId;
    }

    @Override
    public List<Integer> getJobIds() {
        return new ArrayList<>(Arrays.asList(jobId));
    }

    @Override
    public Move getMoveType() {
        return moveType;
    }
}
