package pl.poznan.put.algorithm.tabuSearch;

import java.util.*;

public class TabuList {
    private List<Integer> tabu = new ArrayList<>();
    private int maxSize;

    public TabuList(int maxSize) {
        this.maxSize = maxSize;
    }
    

    public void addMove(Integer jobId) {
        tabu.add(jobId);
        if (tabu.size() >= maxSize) {
            tabu.remove(0);
        }
    }


    public boolean contains(Integer jobId) {
        return tabu.contains(jobId);
    }

}
