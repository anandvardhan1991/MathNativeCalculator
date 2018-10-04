package cordova.plugin.mathcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;
import cordova.plugin.mathcalculator.FetchR;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

import com.example.RetunStringFromJava;


public class NewActivity extends Activity {
    private FetchR fetchR;
    EditText text_to_return;
    Button buttonToClose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fetchR = new FetchR(this);
        String package_name = getApplication().getPackageName();
        setContentView(getApplication().getResources().getIdentifier("main_layout", "layout", package_name));
        text_to_return = (EditText)findViewById(fetchR.getId("id", "edit_text"));
        buttonToClose = (Button)findViewById(fetchR.getId("id","button_to_close"));
        text_to_return.setText(new RetunStringFromJava().returnString());
        buttonToClose.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onClickOfButtonToClose(text_to_return.getText().toString());
            }
        });
        // ToastController toastController = new ToastController();
        // toastController.showToastForHelloWorldFromNative();
    }

    void callTheJarAndLetTheJarReturnSomething(){

    }

    void onClickOfButtonToClose(String text_to_return){
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",text_to_return);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
}