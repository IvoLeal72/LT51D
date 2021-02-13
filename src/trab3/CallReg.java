package trab3;

import trab2.Contact;
import trab2.NoteBook;
import trab2.Utils;

import java.io.*;
import java.util.*;


public class CallReg {
    private String number;
    private Map<String, AnsweredCall> answeredCallMap=new HashMap<>();
    private Map<String, RejectedCall> rejectedCallMap=new HashMap<>();
    private Map<String, SentCall> sentCallMap=new HashMap<>();
    private NoteBook noteBook;

    public CallReg(String number, NoteBook nb){
        this.number=number; noteBook=nb;
    }

    public CallReg(String number){
        this(number, new NoteBook());
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

    public Iterable<AnsweredCall> getAnsweredCalls(){
        return answeredCallMap.values();
    }

    public Iterable<RejectedCall> getRejectedCalls(){
        return rejectedCallMap.values();
    }

    public Iterable<SentCall> getSentCalls(){
        return sentCallMap.values();
    }

    public void save() throws IOException {
        try(ObjectOutputStream objOut=new ObjectOutputStream(new FileOutputStream(number+".data"))) {
            objOut.writeObject(number);
            objOut.writeObject(answeredCallMap);
            objOut.writeObject(rejectedCallMap);
            objOut.writeObject(sentCallMap);
            objOut.writeObject(noteBook);
        }
    }

    public void load() throws IOException, ClassNotFoundException {
        try(ObjectInputStream objIn=new ObjectInputStream(new FileInputStream(number+".data"))){
            number=(String) objIn.readObject();
            answeredCallMap= (Map<String, AnsweredCall>) objIn.readObject();
            rejectedCallMap= (Map<String, RejectedCall>) objIn.readObject();
            sentCallMap= (Map<String, SentCall>) objIn.readObject();
            noteBook=(NoteBook) objIn.readObject();
        }
    }
}
