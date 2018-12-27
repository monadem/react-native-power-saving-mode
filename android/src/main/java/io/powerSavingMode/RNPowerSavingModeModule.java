
package io.powerSavingMode;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;

import android.os.PowerManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.BroadcastReceiver;

public class RNPowerSavingModeModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;
    private final PowerSavingBroadcastReceiver powerSavingBroadcastReceiver;

    public RNPowerSavingModeModule( ReactApplicationContext reactContext ) {
        super( reactContext );
        this.reactContext = reactContext;

        /* Define and register receiver */
        this.powerSavingBroadcastReceiver = new PowerSavingBroadcastReceiver();
        registerReceiver();
    }

    @Override
    public String getName() {
        return "PowerSavingMode";
    }

    private void registerReceiver() {
        IntentFilter filter = new IntentFilter( PowerManager.ACTION_POWER_SAVE_MODE_CHANGED );
        getReactApplicationContext().registerReceiver( this.powerSavingBroadcastReceiver, filter );
    }

    @ReactMethod
    public void currentState( Promise mode ) {
        PowerManager powerManager = ( PowerManager ) this.reactContext.getSystemService( Context.POWER_SERVICE );

        mode.resolve( powerManager.isPowerSaveMode() );
    }

    @ReactMethod
    public void closeApp() {
        android.os.Process.killProcess( android.os.Process.myPid() );
    }

    /**
     * Class that receives intent whenever the power saving mode changes.
     */
    private class PowerSavingBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive( Context context, Intent intent ) {
            ReactApplicationContext reactContext = getReactApplicationContext();
            PowerManager powerManager = ( PowerManager ) reactContext.getSystemService( Context.POWER_SERVICE );

            reactContext.getJSModule( RCTDeviceEventEmitter.class )
                .emit( "PowerSavingModeChanged", powerManager.isPowerSaveMode() );
        }
    }

}
