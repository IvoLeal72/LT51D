package trab3;

import trab2.Utils;

import java.util.HashMap;
import java.util.Map;


public class CallReg {
    Map<String, Call> calls=new HashMap<>();

    public void add(Call call){
        Utils.actualize(calls, call::getReg, ()->call, (inMap)->inMap.update(call));
    }


}
