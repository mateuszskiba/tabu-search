package pl.poznan.put.algorithm;

import pl.poznan.put.structures.Instance;
import pl.poznan.put.structures.Problem;

public interface Scheduler {
    Problem schedule(Instance instance);
}
