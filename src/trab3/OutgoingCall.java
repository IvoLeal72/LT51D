package trab3;

import trab2.Date;

public class OutgoingCall extends Call{

    public OutgoingCall(String reg, Date data, Time time, Time duration) {
        super(reg, data, time, duration);
    }

    public boolean isIncoming() {
        return false;
    }

}
