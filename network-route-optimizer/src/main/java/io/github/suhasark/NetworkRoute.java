package io.github.suhasark;

public class NetworkRoute {
    public final int cost;
    public final int time;
    public final int hops;
    public final int failureRate;

    public NetworkRoute(int cost, int time, int hops, int failureRate) {
        this.cost = cost;
        this.time = time;
        this.hops = hops;
        this.failureRate = failureRate;
    }
}
