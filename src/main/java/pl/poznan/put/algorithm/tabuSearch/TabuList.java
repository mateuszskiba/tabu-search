package pl.poznan.put.algorithm.tabuSearch;

import java.util.*;

public class TabuList {
    private Set<TabuItem> tabu = new LinkedHashSet<>();
    private int maxSize;

    public TabuList(int maxSize) {
        this.maxSize = maxSize;
    }
    

    public void addMove(Integer jobId) {
        if (tabu.size() >= maxSize) {
            removeOldestMove();
        }
        tabu.add(new TabuItem(jobId));
    }

    public void removeMove(TabuItem item) {
        tabu.remove(item);
    }

    public void removeOldestMove() {
        TabuItem toRemove = null;
        Iterator<TabuItem> iter = tabu.iterator();
        while (iter.hasNext()){
            TabuItem next = iter.next();
            if (toRemove == null || next.getLifetime() < toRemove.getLifetime()) {
                toRemove = next;
            }
        }
        removeMove(toRemove);
    }


    public boolean contains(Integer jobId) {
        return tabu.contains(new TabuItem(jobId));
    }

    public void decrementValuesAndEraseIfNecessary() {
        if (!tabu.isEmpty()) {
            Deque<TabuItem> stack = new ArrayDeque<>();
            tabu.forEach(tabuItem -> {
                tabuItem.decrementLifetime();
                if (tabuItem.isInvalid()) {
                    stack.push(tabuItem);
                }
            });
            while (!stack.isEmpty()) {
                TabuItem item = stack.pop();
                removeMove(item);
            }
        }
    }
}
