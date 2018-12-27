
# react-native-power-saving-mode

## Getting started

`$ npm install react-native-power-saving-mode --save`

### Mostly automatic installation

`$ react-native link react-native-power-saving-mode`

### Manual installation


#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import io.powerSavingMode.RNPowerSavingModePackage;` to the imports at the top of the file
  - Add `new RNPowerSavingModePackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ":react-native-power-saving-mode"
  	project( ":react-native-power-saving-mode" ).projectDir = new File( rootProject.projectDir,	"../node_modules/react-native-power-saving-mode/android" )
  ```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
   ```
      compile project( ":react-native-power-saving-mode" ) 
      // implementation project( ":react-native-power-saving-mode" ) in latest gradle versions
   ```


## Usage
```javascript
import RNPowerSavingMode from "react-native-power-saving-mode";

import { powerSavingOn, powerSavingModeChanged } from "react-native-power-saving-mode"

...

async componentDidMount() {

    /* Register Listener for power saving mode changes. */
    powerSavingModeChanged( this.powerSavingChangeHandler )
    
    /* Access the current value. */
    let isOn = await powerSavingOn();
}

powerSavingChangeHandler( isOn ) {
	/* TODO YOUR CODE HERE */
}
```
