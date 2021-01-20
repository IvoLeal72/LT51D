package trab3;

import trab2.Date;
import trab2.Utils;

import java.util.HashMap;
import java.util.Map;


public class CallReg {
    Map<String, Call> calls=new HashMap<>();

    public void add(Call call){
        Utils.actualize(calls, call::getReg, ()->call, (inMap)->inMap.update(call));
    }


    public static void main(String[] args) {
        OutgoingCall test=new OutgoingCall("Ivo", new Date(7,2,2000), new Time(18,9,27), new Time(65));
        System.out.println(test);
        IncomingCall test2= test.toIncoming();
        System.out.println(test2);
    }

}
