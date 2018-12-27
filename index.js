import { NativeModules, DeviceEventEmitter, Platform } from "react-native";

let powerSavingOn = null,
    closeApp = null,
    powerSavingModeChanged = null;

if( Platform.OS == "android" ) {
    let PowerSavingMode = NativeModules.PowerSavingMode;

    closeApp = PowerSavingMode.closeApp;

    powerSavingOn = async () => {
        try {
            return await PowerSavingMode.currentState();
        } catch( error ) {
            console.warn( error )
        }
    }

    powerSavingModeChanged = ( callback ) => {
        DeviceEventEmitter.addListener( "PowerSavingModeChanged", callback )
    }
}

module.exports = {
    powerSavingOn,
    closeApp,
    powerSavingModeChanged
}