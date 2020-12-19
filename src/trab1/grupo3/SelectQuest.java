package trab1.grupo3;

public class SelectQuest extends Quest{
    private final Quest[] choices;

    public SelectQuest(Answer correct, Quest ... choices){
        super(selectQuestString(choices), correct);
        this.choices=choices;
    }

    public int getPoints(){
        int med=0;
        for (Quest choice : choices) {
            med += choice.getPoints();
        }
        med/=choices.length;
        return med;
    }

    private static String selectQuestString(Quest ... choices){
        StringBuilder str=new StringBuilder("Qual das questões é verdadeira:\n");
        for(int i=0; i< choices.length; i++){
            str.append(String.format(" %d - %s\n", i+1, choices[i].toString()));
        }
        return str.toString();
    }
}
