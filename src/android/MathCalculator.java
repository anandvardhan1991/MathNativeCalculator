package cordova.plugin.mathcalculator;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.PluginResult;
   
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.content.Context;
import android.content.Intent;

/**
 * This class echoes a string called from JavaScript.
 */
public class MathCalculator extends CordovaPlugin {

    private final int REQUEST_CODE_CALLBACK_ACTIVITY = 100;
    private CallbackContext callbackContext;

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }


    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
//        if (action.equals("coolMethod")) {
//            String message = args.getString(0);
//            this.coolMethod(message, callbackContext);
//            return true;
//        }
//        return false;
        Context context = this.cordova.getActivity().getApplicationContext();
        if(action.equals("add")){
            this.add(args, callbackContext);
            return true;
        } else if(action.equals("subtract")){
            this.subtract(args, callbackContext);
            return true;
        } else if(action.equals("openActivity")){
            this.setTheGlobalCallBackContext(callbackContext);
            this.openActivity(context,callbackContext);
            return true;
        }
        return false;
        
    }

    public void setTheGlobalCallBackContext(CallbackContext callbackContext){
        this.callbackContext = callbackContext;
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


    private void openActivity(Context context, CallbackContext callbackContext){
        try{
        Intent intent = new Intent(context, NewActivity.class);
        this.cordova.setActivityResultCallback(this);
        this.cordova.getActivity().startActivityForResult(intent,this.REQUEST_CODE_CALLBACK_ACTIVITY);
        } catch (Exception e) {
            callbackContext.error("Cannot Open Activity" + e);
        }
    }


    private String calc_sub(int param1, int param2){
        if(param1 >= 0 && param2 >= 0 ){
            return (String.valueOf(param1 - param2));
        } else return param1 < 0 ? "1st not great than 0" : "2nd not great than 0";
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == this.REQUEST_CODE_CALLBACK_ACTIVITY){
            PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, data.getStringExtra("result"));
            pluginResult.setKeepCallback(true);
            this.callbackContext.success(pluginResult.getMessage());
        }
    }

    /**
 * Called when the Activity is being destroyed (e.g. if a plugin calls out to an
 * external Activity and the OS kills the CordovaActivity in the background).
 * The plugin should save its state in this method only if it is awaiting the
 * result of an external Activity and needs to preserve some information so as
 * to handle that result; onRestoreStateForActivityResult() will only be called
 * if the plugin is the recipient of an Activity result
 *
 * @return  Bundle containing the state of the plugin or null if state does not
 *          need to be saved
 */
public Bundle onSaveInstanceState() {
    Bundle state = new Bundle();
    return state;
}

/**
 * Called when a plugin is the recipient of an Activity result after the
 * CordovaActivity has been destroyed. The Bundle will be the same as the one
 * the plugin returned in onSaveInstanceState()
 *
 * @param state             Bundle containing the state of the plugin
 * @param callbackContext   Replacement Context to return the plugin result to
 */
public void onRestoreStateForActivityResult(Bundle state, CallbackContext callbackContext) {
    this.callbackContext = callbackContext;
}
}
