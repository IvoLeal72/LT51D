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
    private NoteBook noteBook=new NoteBook();

    public CallReg(String number) {
        this.number=number;
    }

    public NoteBook getNoteBook() {
        return noteBook;
    }

    public String getNumber() {
        return number;
    }

    public String getNameFromNum(String number){
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

    public void save(File file) throws IOException {
        try(ObjectOutputStream objOut=new ObjectOutputStream(new FileOutputStream(file))) {
            objOut.writeObject(number);
            objOut.writeObject(answeredCallMap);
            objOut.writeObject(rejectedCallMap);
            objOut.writeObject(sentCallMap);
            objOut.writeObject(noteBook);
        }
    }

    public void load(File file) throws IOException, ClassNotFoundException {
        try(ObjectInputStream objIn=new ObjectInputStream(new FileInputStream(file))){
            number=(String) objIn.readObject();
            answeredCallMap= (Map<String, AnsweredCall>) objIn.readObject();
            rejectedCallMap= (Map<String, RejectedCall>) objIn.readObject();
            sentCallMap= (Map<String, SentCall>) objIn.readObject();
            noteBook=(NoteBook) objIn.readObject();
        }
    }

    public String toStringReceivedCallWithName(Call call){
        String name=getNameFromNum(call.getNumber());
        return name!=null?name:call.getNumber()+" "+call.getTime().toString();
    }

    public String toStringSentCallWithName(SentCall sentCall){
        return toStringReceivedCallWithName(sentCall)+" duration:"+sentCall.getDuration().toString();
    }
}
