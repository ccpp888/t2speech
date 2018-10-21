# Text to Speech

Google Text to Speech API client to perform a range of functions supporting the copany Academy and (http://spelzee.com)

## Pre requisites

Java install (min Java8)

Maven install

## Build

run: mvn clean package

Supports VSCode build task (tasks.json).

## Authentication

From the Cloud Console, create a service account, download its json credentials file, then set the appropriate environment variable:  

export GOOGLE_APPLICATION_CREDENTIALS=/path/to/your-project-credentials.json

## Run

Within VSCode, run App.java (main)

Or, from command line using 

Supply args:
1. Input containing text to convert to speech
2. Output folder location

E.g. 



## Further help

https://cloud.google.com/text-to-speech

