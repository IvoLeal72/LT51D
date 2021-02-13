package trab3;

import trab2.NoteBook;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class CallReg {
    private final SortedSet<AnsweredCall> answeredCalls=new TreeSet<>(Comparator.reverseOrder());
    private final SortedSet<RejectedCall> rejectedCalls=new TreeSet<>(Comparator.reverseOrder());
    private final SortedSet<SentCall> sentCalls=new TreeSet<>(Comparator.reverseOrder());
    private final NoteBook noteBook=new NoteBook();
}
