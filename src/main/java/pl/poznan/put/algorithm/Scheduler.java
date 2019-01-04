package pl.poznan.put.algorithm;

import pl.poznan.put.structures.Instance;
import pl.poznan.put.structures.Job;

import java.util.List;

public interface Scheduler {
    List<Job> schedule(Instance instance);
}
