package pl.poznan.put.algorithm.tabuSearch;

public class Candidate {
    private int startIndex;
    private int destinationIndex;
    private int costProfit;
    private Move moveType;

    public Candidate(int startIndex, int destinationIndex, int costProfit, Move moveType) {
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
}
