package trab3;

import trab2.Date;

public class IncomingCall extends Call{

    public IncomingCall(String reg, Date data, Time time, Time duration) {
        super(reg, data, time, duration);
    }

    public boolean isIncoming() {
        return true;
    }

}
