package trab3;

public class SentCall extends Call{
    private final Duration d;

    public SentCall(Time t, Duration d, String number, String name) {
        super(t, number, name);
        this.d=d;
    }

    public SentCall(Time t, Duration d, String number) {
        super(t, number);
        this.d=d;
    }

    public Duration getDuration() {
        return d;
    }

    public SentCall merge(SentCall other) {
        return new SentCall(other.getTime(), d.add(other.getDuration()), other.getNumber(), other.getName());
    }
}
