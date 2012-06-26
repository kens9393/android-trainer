package com.glm.app;

import android.app.Activity;
import android.content.Intent;

public class ActivityHelper {
    static private final String EXTRA_WRAPPED_INTENT = "ActivityHelper_wrappedIntent";
    private static Activity backActivity;
    /**
     * Create a precondition activity intent.
     * @param activity the original activity
     * @param preconditionActivityClazz the precondition activity's class
     * @return an intent which will launch the precondition activity.
     */
    public static Intent createActivityIntent(Activity activity,
            Class preconditionActivityClazz) {
    	backActivity=activity;
        Intent newIntent = new Intent();
        if(activity!=null &&
        		preconditionActivityClazz!=null){
	        newIntent.setClass(activity, preconditionActivityClazz);
	        newIntent.putExtra(EXTRA_WRAPPED_INTENT, activity.getIntent());
        }
        return newIntent;
    }

    /**
     * Start the original activity, and finish the precondition activity.      
     * @param preconditionActivity
     */
    public static void startOriginalActivityAndFinish(
            Activity preconditionActivity) {
        preconditionActivity.startActivity((Intent) preconditionActivity.getIntent().getParcelableExtra(EXTRA_WRAPPED_INTENT));
        preconditionActivity.finish();

    }

    /**
     * Start the precondition activity using a given intent, which should have
     * been created by calling createPreconditionIntent.
     * @param activity
     * @param intent
     */
    public static void startNewActivityAndFinish(Activity activity,
            Intent intent) {
        activity.startActivity(intent);
        activity.finish();        
    }
    /**
     * Ritorna l'activity che ha lanciato l'attuale
     * 
     * */
    public static Activity getBabkActivity(){
    	return backActivity;
    }
}