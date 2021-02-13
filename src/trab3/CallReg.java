package trab3;

import trab2.Contact;
import trab2.NoteBook;
import trab2.Utils;

import java.util.*;


public class CallReg {
    private final Map<String, AnsweredCall> answeredCallMap=new HashMap<>();
    private final Map<String, RejectedCall> rejectedCallMap=new HashMap<>();
    private final Map<String, SentCall> sentCallMap=new HashMap<>();
    private final NoteBook noteBook;

    public CallReg(NoteBook nb){
        noteBook=nb;
    }

    public CallReg(){
        this(new NoteBook());
    }

    private String getNameFromNum(String number){
        Iterator<Contact> list=noteBook.getContactsOf(number).iterator();
        Contact c=list.next();
        if(c==null || list.hasNext()) return null;
        return c.getName();
    }

    public void addAnsweredCall(Time t, String number){
        AnsweredCall toAdd=new AnsweredCall(t, number);
        Utils.actualize(answeredCallMap, ()->number, ()->toAdd, answeredCall -> answeredCall.merge(toAdd));
    }

    public void addRejectedCall(Time t, String number){
        RejectedCall toAdd=new RejectedCall(t, number);
        Utils.actualize(rejectedCallMap, ()->number, ()->toAdd, rejectedCall -> rejectedCall.merge(toAdd));
    }

    public void addSentCall(Time t, String number, Duration d){
        SentCall toAdd=new SentCall(t, number, d);
        Utils.actualize(sentCallMap, ()->number, ()->toAdd, sentCall -> sentCall.merge(toAdd));
    }
}
