package pl.poznan.put.algorithm.tabuSearch;

import java.util.Map;

public class TabuList {
    private Map<Candidate, Integer> tabuList;
    private int size;
    private final static int ROUNDS_TABU = 10;

    public TabuList(Map<Candidate, Integer> tabuList, int size) {
        this.tabuList = tabuList;
        this.size = size;
    }

    public boolean addMove(Candidate candidate) {
        if (tabuList.size() >= size) return false;
        if (!contains(candidate)) return false;
        tabuList.put(candidate, ROUNDS_TABU);
        size++;
        return true;
    }

    public boolean removeMove(Candidate candidate) {
//        if (!contains(candidate)) return false;
        tabuList.remove(candidate);
        return true;
    }

    public boolean contains(Candidate candidate) {
        return tabuList.containsKey(candidate);
    }

    public void decrementValuesOrEraseFromTabu() {
        if (!tabuList.isEmpty()) {
            tabuList.forEach(((candidate, integer) -> {
                integer--;
                if (integer <= 0) removeMove(candidate);
            }));
        }
    }
}
