# react-native-geckoview [![npm](https://badge.fury.io/js/react-native-geckoview.svg)](https://www.npmjs.com/package/react-native-geckoview)

Based on [GeckoView](https://github.com/mozilla/geckoview). Just a proof of concept.

## Getting started

1. `$ yarn add react-native-geckoview`  

2. Add the following to your `build.gradle`'s repositories section. (android/build.gradle)

```gradle
allprojects {
    repositories {
      // ...
      // ...

      // ADD THIS
      maven {
          url "https://maven.mozilla.org/maven2/"
      }
    }
}
```

3. `$ yarn android`


## Usage
```javascript
import GeckoView from 'react-native-geckoview';

<GeckoView source={{ uri: 'https://www.google.com' }} />;
```
