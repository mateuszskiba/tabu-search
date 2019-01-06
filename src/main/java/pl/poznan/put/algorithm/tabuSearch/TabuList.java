package pl.poznan.put.algorithm.tabuSearch;

import java.util.Collections;
import java.util.Map;

public class TabuList {
    private Map<Integer, Integer> tabu;
    private int size;
    private final static int ROUNDS_TABU = 10;

    public TabuList(Map<Integer, Integer> tabu, int size) {
        this.tabu = tabu;
        this.size = size;
    }
    

    public boolean addMove(Integer jobId) {
        if (tabu.size() >= size) return false;
//        if (contains(jobId)) return false;
        tabu.put(jobId, ROUNDS_TABU);
        return true;
    }

    public boolean removeMove(Integer jobId) {
//        if (!contains(jobId)) return false;
        tabu.remove(jobId);
        return true;
    }

//    public boolean removeOldestMove() {
//        int oldestJobLifetime = Collections.min(tabu.values());
//
//    }


    public boolean contains(Integer jobId) {
        return tabu.containsKey(jobId);
    }

    public void decrementValuesOrEraseFromTabu() {
        if (!tabu.isEmpty()) {
            tabu.forEach(((jobId, lifetime) -> {
                lifetime--;
                if (lifetime <= 0) removeMove(jobId);
            }));
        }
    }
}
