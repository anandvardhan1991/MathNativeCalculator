package cordova.plugin.mathcalculator;

public class AddAssistant {
    private int param1;
    private int param2;

    public AddAssistant(int param1, int param2){
        this.param1 = param1;
        this.param2 = param2;
    }

    public String addAssistantMethod(){
        if(param1 >= 0 && param2>=0){
            return (String.valueOf(param1+param2));
        } else return "Someone is 0";
    }
}