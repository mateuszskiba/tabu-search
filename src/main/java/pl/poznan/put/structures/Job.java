package pl.poznan.put.structures;

public class Job implements Cloneable {
    private final int id;
    private final int processingTime;
    private final int earlinessPenalty;
    private final int tardinessPenalty;
    private final double tardinessToEarlinessRatio;

    public Job(int id, int processingTime, int earlinessPenalty, int tardinessPenalty) {
        this.id = id;
        this.processingTime = processingTime;
        this.earlinessPenalty = earlinessPenalty;
        this.tardinessPenalty = tardinessPenalty;
        this.tardinessToEarlinessRatio = (double) tardinessPenalty / earlinessPenalty;
    }

    public Job clone() {
        return new Job(
                this.id,
                this.processingTime,
                this.earlinessPenalty,
                this.tardinessPenalty
        );
    }

    public int getId() {
        return id;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public int getEarlinessPenalty() {
        return earlinessPenalty;
    }

    public int getTardinessPenalty() {
        return tardinessPenalty;
    }

    public double getTardinessToEarlinessRatio() {
        return tardinessToEarlinessRatio;
    }
}
