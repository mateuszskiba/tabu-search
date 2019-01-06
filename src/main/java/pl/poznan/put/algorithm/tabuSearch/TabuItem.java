package pl.poznan.put.algorithm.tabuSearch;

import java.util.Objects;

public class TabuItem {
    private int jobId;
    private int lifetime;
    private final static int TABU_ITEM_LIFETIME = 10;

    public TabuItem(int jobId) {
        this.jobId = jobId;
        this.lifetime = TABU_ITEM_LIFETIME;
    }

    public int getJobId() {
        return jobId;
    }

    public int getLifetime() {
        return lifetime;
    }

    public void decrementLifetime(){
        lifetime--;
    }

    public boolean isInvalid(){
        return lifetime == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TabuItem tabuItem = (TabuItem) o;
        return jobId == tabuItem.jobId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(jobId);
    }
}
