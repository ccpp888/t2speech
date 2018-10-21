# Text to Speech

Google Text to Speech API client to perform a range of functions supporting text to speech conversion and [Spelzee](http://spelzee.com).

## Pre requisites

Install Java *(min Java8)*

Install Maven *(optional)*


## Build

Run using Maven
```
mvn clean package
```

Supports VSCode build task (tasks.json).

## Authentication

From the Cloud Console, create a service account, download its json credentials file, then set the appropriate environment variable:  

```
export GOOGLE_APPLICATION_CREDENTIALS=/path/to/your-project-credentials.json
```

## Run

From command line (within /lib or folder containign jar file) using java -jar {jar-with-dependencies}

Supply args:
1. Input containing text to convert to speech
2. Output folder location

E.g. 
```
java -jar t2speech-1.0.jar-with-dependencies.jar /tmp/in/allWords.txt /tmp/out/audio-files
```

Or, within VSCode, run App.java (main)

## Further help

https://cloud.google.com/text-to-speech

