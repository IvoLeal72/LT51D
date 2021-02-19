package trab3;

import trab2.Contact;
import trab2.NoteBook;
import trab2.Utils;

import java.io.*;
import java.util.*;


public class CallReg {
    private final String number;
    private Map<String, AnsweredCall> answeredCallMap=new HashMap<>();
    private Map<String, RejectedCall> rejectedCallMap=new HashMap<>();
    private Map<String, SentCall> sentCallMap=new HashMap<>();
    private NoteBook noteBook=new NoteBook();

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public CallReg(String number){
        this.number=number;
        File dir=new File("dataFiles");
        if(!dir.exists()) dir.mkdirs();
        try{
            load(new File("dataFiles\\"+number+".data"));
        } catch (IOException | ClassNotFoundException ignored) {
        }
    }

    public NoteBook getNoteBook() {
        return noteBook;
    }

    public String getNumber() {
        return number;
    }

    public String getNameFromNum(String number){
        Iterable<Contact> iterable= noteBook.getContactsOf(number);
        if(iterable==null) return null;
        Iterator<Contact> list=iterable.iterator();
        Contact c=list.next();
        if(c==null || list.hasNext()) return null;
        return c.getName();
    }


    public void addAnsweredCall(Time t, String number){
        AnsweredCall toAdd=new AnsweredCall(t, number);
        Utils.actualize(answeredCallMap, ()->number, ()->toAdd, answeredCall -> answeredCall.merge(toAdd));
        try {
            autoSave();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addRejectedCall(Time t, String number){
        RejectedCall toAdd=new RejectedCall(t, number);
        Utils.actualize(rejectedCallMap, ()->number, ()->toAdd, rejectedCall -> rejectedCall.merge(toAdd));
        try {
            autoSave();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addReceivedCall(Time t, String number, boolean answered){
        if(answered) addAnsweredCall(t, number);
        else addRejectedCall(t, number);
    }

    public void addSentCall(Time t, String number, Duration d){
        SentCall toAdd=new SentCall(t, number, d);
        Utils.actualize(sentCallMap, ()->number, ()->toAdd, sentCall -> sentCall.merge(toAdd));
        try {
            autoSave();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clear(){
        answeredCallMap.clear();
        rejectedCallMap.clear();
        sentCallMap.clear();
        noteBook.clear();
        try {
            autoSave();
        } catch (IOException ignored) {
        }
    }

    private final static Comparator<Call> callCmpDescendingTime= (o1, o2) -> o2.getTime().compareTo(o1.getTime());

    public Iterable<AnsweredCall> getAnsweredCalls(){
        Iterable<AnsweredCall> iterable= answeredCallMap.values();
        LinkedList<AnsweredCall> list=new LinkedList<>();
        for(AnsweredCall call:iterable)
            list.add(call);
        list.sort(callCmpDescendingTime);
        return list;
    }

    public Iterable<RejectedCall> getRejectedCalls(){
        Iterable<RejectedCall> iterable= rejectedCallMap.values();
        LinkedList<RejectedCall> list=new LinkedList<>();
        for(RejectedCall call:iterable)
            list.add(call);
        list.sort(callCmpDescendingTime);
        return list;
    }

    public Iterable<SentCall> getSentCalls(){
        Iterable<SentCall> iterable= sentCallMap.values();
        LinkedList<SentCall> list=new LinkedList<>();
        for(SentCall call:iterable)
            list.add(call);
        list.sort(callCmpDescendingTime);
        return list;
    }

    public Iterable<Call> getAllCalls(){
        Iterable<AnsweredCall> iterable1=answeredCallMap.values();
        Iterable<RejectedCall> iterable2=rejectedCallMap.values();
        Iterable<SentCall> iterable3=sentCallMap.values();
        SortedSet<Call> list=new TreeSet<>(callCmpDescendingTime);
        for(Call call:iterable1)
            list.add(call);
        for(Call call:iterable2)
            list.add(call);
        for(Call call:iterable3)
            list.add(call);
        return list;
    }

    public void save(File file) throws IOException {
        try(ObjectOutputStream objOut=new ObjectOutputStream(new FileOutputStream(file))) {
            objOut.writeObject(answeredCallMap);
            objOut.writeObject(rejectedCallMap);
            objOut.writeObject(sentCallMap);
            objOut.writeObject(noteBook);
        }
    }


    @SuppressWarnings("unchecked")
    public void load(File file) throws IOException, ClassNotFoundException {
        try(ObjectInputStream objIn=new ObjectInputStream(new FileInputStream(file))){
            answeredCallMap= (Map<String, AnsweredCall>) objIn.readObject();
            rejectedCallMap= (Map<String, RejectedCall>) objIn.readObject();
            sentCallMap= (Map<String, SentCall>) objIn.readObject();
            noteBook=(NoteBook) objIn.readObject();
        }
    }

    private String toStringReceivedCallWithName(Call call){
        String name=getNameFromNum(call.getNumber());
        return (name!=null?name:call.getNumber())+" "+call.getTime().toString();
    }

    private String toStringSentCallWithName(SentCall sentCall){
        return toStringReceivedCallWithName(sentCall)+" duration:"+sentCall.getDuration().toString();
    }

    public void autoSave() throws IOException {
        save(new File("dataFiles\\"+number+".data"));
    }

    public String toStringCallWithName(Call call){
        if(call instanceof SentCall)
            return toStringSentCallWithName((SentCall) call);
        return toStringReceivedCallWithName(call);
    }

    public String toStringCallWithNameAndType(Call call){
        String str="";
        if(call instanceof AnsweredCall) str="Answered: ";
        else if(call instanceof RejectedCall) str="Rejected: ";
        else if(call instanceof SentCall) str="Sent: ";
        return str+toStringCallWithName(call);
    }
}
