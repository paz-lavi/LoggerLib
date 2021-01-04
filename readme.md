
  
  

# Welcome To EZ Log!

[![](https://jitpack.io/v/paz-lavi/Prefy.svg)](https://jitpack.io/#paz-lavi/LoggerLib) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/paz-lavi/LoggerLib/blob/master/LICENSE) [![GitHub forks](https://img.shields.io/github/forks/paz-lavi/LoggerLib)](https://github.com/paz-lavi/LoggerLib/network) [![GitHub stars](https://img.shields.io/github/stars/paz-lavi/LoggerLib)](https://github.com/paz-lavi/LoggerLib/stargazers) [![GitHub issues](https://img.shields.io/github/issues/paz-lavi/LoggerLib)](https://github.com/paz-lavi/LoggerLib/issues)

## Table of Contents

*  [Usage](https://github.com/paz-lavi/LoggerLib/blob/master/readme.md#usage)
 *  [What's New](https://github.com/paz-lavi/LoggerLib/blob/master/readme.md#whats-new)
    *  [V1.0.0:](https://github.com/paz-lavi/LoggerLib/blob/master/README.md#v100)
 *  [Integration](https://github.com/paz-lavi/LoggerLib/blob/master/readme.md#integration)

*  [Init Guide](https://github.com/paz-lavi/LoggerLib/blob/master/readme.md#init-guide)

*  [API](https://github.com/paz-lavi/LoggerLib/blob/master/readme.md#api)
	*  [init](https://github.com/paz-lavi/LoggerLib/tree/master#init)

	*  [getInstance](https://github.com/paz-lavi/LoggerLib/blob/master/readme.md#getinstance)

	*  [start](https://github.com/paz-lavi/LoggerLib/blob/master/readme.md#start)

	*  [setDebug](https://github.com/paz-lavi/LoggerLib/blob/master/readme.md#setdebug)

	*  [setPrintToLogcat](https://github.com/paz-lavi/LoggerLib/blob/master/readme.md#setprinttologcat)

	*  [setCostumerId](https://github.com/paz-lavi/LoggerLib/blob/master/readme.md#setcostumerid)

	*  [debug](https://github.com/paz-lavi/LoggerLib/blob/master/readme.md#debug)

	*  [info](https://github.com/paz-lavi/LoggerLib/blob/master/readme.md#info)

	*  [warn](https://github.com/paz-lavi/LoggerLib/blob/master/readme.md#warn)

	*  [verbose](https://github.com/paz-lavi/LoggerLib/blob/master/readme.md#verbose)

	*  [error](https://github.com/paz-lavi/LoggerLib/blob/master/readme.md#error)

	*  [logException](https://github.com/paz-lavi/LoggerLib/blob/master/readme.md#logexception)

	
*  [License](https://github.com/paz-lavi/LoggerLib/blob/master/readme.md#license)

## Usage

**EZ Log** is an Android SDK that will allow you to save logs from any device that run your app and watch them from a dedicated app. Every session log will saved under one document in the cloud.
* Session - background foreground transition
  
## What's New
### V1.0.0: 
* Library released.

## Integration

Add it in your root build.gradle at the end of repositories:

```css

allprojects {

repositories {

...

maven { url 'https://jitpack.io' }

}

}

```

Add the dependency

```css

dependencies {

implementation 'com.github.paz-lavi:LoggerLib:0.0.01'

}

```

## Init Guide

#### 1. Download the viewer app and singin/ login
#### 2. Retrieve your dev key
####  3. Initialize the SDK
We recommend initializing the SDK inside the app's global application class. This allows the SDK to initialize in all scenarios.

```Java  
import android.app.Application;  
import com.paz.logger.EZLog;  
  
  
public class EZLoggerApp extends Application {  
    private final String devKey = "FTGDmOi0PnUFuspIRu6vKdZQu812";  
  
  @Override  
  public void onCreate() {  
      super.onCreate();  
	  EZLog.init(devKey, this);  
	  EZLog.getInstance()  
                .setDebug(true)  
                .setPrintToLogcat(true)  
                .setPrintToLogcat(true)  
                .setCostumerId("test")  
                .start();  
  }  
}
```
#### 4. Start logging
```Java
import com.paz.logger.EZLog;
public class MainActivity extends AppCompatActivity {
    EZLog ezLog = EZLog.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
        Exception exception = new Exception();
        ezLog.debug("this is debug log");
        ezLog.info("this is info log");
        ezLog.warn("this is warn log");
        ezLog.error("this is error log");
        ezLog.verbose("this is verbose log");
        ezLog.logException("this is exception log", exception);
    }
}
```
**Important: it is crucial to use the correct dev key when initializing the SDK. Using the wrong dev key or an incorrect dev key impacts all traffic sent from the SDK and will cause losing your logs.**



## API

  

### init
initialize the sdk 
```Java

void init(@NonNull String key, @NonNull final Context context);

```
### getInstance
get EZ Log instance. Don't forget to initialize the SDK first
```Java

EZLog getInstance();

```
### start
Start the SDK on app launch.
```Java

void start();

```
### setDebug
Include debug logs in the document. Set to true by default.
```Java

EZLog setDebug(boolean debug);

```
### setPrintToLogcat
Enable the sdk to print the logs to the logcat. Set to true by default.
```Java

EZLog setPrintToLogcat(boolean printToLogcat);

```
### setCostumerId
Set current user Id.
```Java

EZLog setCostumerId(String costumerId);

```
### debug
Log debug message.
```Java

void debug(String msg);

```
### info
Log info message.
```Java

void info(String msg);

```
### warn
Log warn message.
```Java

void warn(String msg);

```
### verbose
Log verbose message.
```Java

void verbose(String msg);

```
### error
Log error message.
```Java

void error(String msg);

```
### logException
Log exception stacktrace and message.
```Java

void logException(String msg, @NonNull Throwable ex);

```


## License

```

Copyright 2021 Paz Lavi

Licensed under the Apache License, Version 2.0 (the "License");

you may not use this file except in compliance with the License.

You may obtain a copy of the License at

https://github.com/paz-lavi/LoggerLib/blob/master/LICENSE

Unless required by applicable law or agreed to in writing, software

distributed under the License is distributed on an "AS IS" BASIS,

WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.

See the License for the specific language governing permissions and

limitations under the License.

```