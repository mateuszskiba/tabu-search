package pl.poznan.put.algorithm.tabuSearch;

import java.util.Objects;

public class Candidate {
    private int startIndex;
    private int destinationIndex;
    private int costProfit;
    private Move moveType;

    public Candidate(Move moveType, int startIndex, int destinationIndex, int costProfit) {
        this.startIndex = startIndex;
        this.destinationIndex = destinationIndex;
        this.costProfit = costProfit;
        this.moveType = moveType;
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

    public Move getMoveType() { return moveType; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return startIndex == candidate.startIndex &&
                destinationIndex == candidate.destinationIndex &&
                moveType == candidate.moveType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startIndex, destinationIndex, moveType);
    }
}
