package cordova.plugin.mathcalculator;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import cordova.plugin.mathcalculator.AddAssistant;

/**
 * This class echoes a string called from JavaScript.
 */
public class MathCalculator extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
//        if (action.equals("coolMethod")) {
//            String message = args.getString(0);
//            this.coolMethod(message, callbackContext);
//            return true;
//        }
//        return false;
        if(action.equals("add")){
            this.add(args, callbackContext);
            return true;
        } else if(action.equals("subtract")){
            this.subtract(args, callbackContext);
            return true;
        }
        return false;
        
    }

//    private void coolMethod(String message, CallbackContext callbackContext) {
//        if (message != null && message.length() > 0) {
//            callbackContext.success(message);
//        } else {
//            callbackContext.error("Expected one non-empty string argument.");
//        }
//    }
    
    private void add(JSONArray args, CallbackContext callbackContext) {
        if(args != null){
            try{
                int param1 = Integer.parseInt(args.getJSONObject(0).getString("param1"));
                int param2 = Integer.parseInt(args.getJSONObject(0).getString("param2"));
                callbackContext.success("" + new AddAssistant(param1,param2).addAssistantMethod());
            }catch(Exception e){
                callbackContext.error("Something went wrong"+ e);
            }
        } else {
            callbackContext.error("Please do not pass null value");
        }
    }


    private void subtract(JSONArray args, CallbackContext callbackContext) {
        if(args != null){
            try{
                int param1 = Integer.parseInt(args.getJSONObject(0).getString("param1"));
                int param2 = Integer.parseInt(args.getJSONObject(0).getString("param2"));
                callbackContext.success(calc_sub(param1, param2));
            }catch(Exception e){
                callbackContext.error("Something went wrong"+ e);
            }
        } else {
            callbackContext.error("Please do not pass null value");
        }
    }


    private String calc_sub(int param1, int param2){
        if(param1 >= 0 && param2 >= 0 ){
            return (String.valueOf(param1 - param2));
        } else return param1 < 0 ? "1st not great than 0" : "2nd not great than 0";
    }
}
