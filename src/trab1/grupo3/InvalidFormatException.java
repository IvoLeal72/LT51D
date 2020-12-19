package trab1.grupo3;

public class InvalidFormatException extends Exception{
    public InvalidFormatException(String resp, String msg){
        super(resp+": "+msg);
    }

    public String getResponse(){
        String tmp=getMessage();
        int idx=tmp.indexOf(":");
        return tmp.substring(0, idx);
    }

}
