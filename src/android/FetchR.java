package cordova.plugin.mathcalculator;

import android.app.Activity;
import android.content.Context;

public class FetchR{
    private Context context;
	private String packageName;

	public FetchR(Activity activity) {
		context = activity.getApplicationContext();
		packageName = context.getPackageName();
	}

	public FetchR(Context context) {
		this.context = context;
		packageName = context.getPackageName();
	}

	public int getId(String group, String key) {
		return context.getResources().getIdentifier(key, group, packageName);
	}

	public static int getId(Context context, String group, String key) {
		return context.getResources().getIdentifier(key, group, context.getPackageName());
	}
}