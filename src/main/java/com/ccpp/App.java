package com.ccpp;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.stream.Stream;
import java.util.function.Consumer;

import org.pmw.tinylog.Logger;

import com.google.cloud.texttospeech.v1beta1.AudioConfig;
import com.google.cloud.texttospeech.v1beta1.AudioEncoding;
import com.google.cloud.texttospeech.v1beta1.SsmlVoiceGender;
import com.google.cloud.texttospeech.v1beta1.SynthesisInput;
import com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechResponse;
import com.google.cloud.texttospeech.v1beta1.TextToSpeechClient;
import com.google.cloud.texttospeech.v1beta1.VoiceSelectionParams;
import com.google.protobuf.ByteString;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Use Google Text-To-Speech API to convert a text file to speech. 
 * 
 * Auth: From the Cloud Console, create a service account, download its json
 * credentials file, then set the appropriate environment variable:  
 *
 * export GOOGLE_APPLICATION_CREDENTIALS=/path/to/your-project-credentials.json 
 * 
 * Default behaviour is to convert a comma delimited list of wordfs in a file to 
 * individual audio files. This is for use by http://spelzee.com.
 * 
 * @author cpurvis 2018
 */
public class App {
     
    private static final String SUFFIX = ".mp3";
    private static final String SPLIT_ON = ",";

    private static String inputFile;
    private static String outputFolder;

    /**
     * Main method
     * 
     * @param args (1) input file, (2) output folder
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        Logger.info("Running Text to Speech");
        parseArgs(args);
        synthesizeWordsFromFile(inputFile);
        Logger.info("Completed");
    }

    private static void parseArgs(String[] userArgs) {
        
        if (userArgs==null || userArgs.length != 2) {            
            throw new IllegalArgumentException("Please supply two args: (1) input file, (2) output folder");
        }       

        inputFile = userArgs[0];
        outputFolder = userArgs[1];        
        Logger.info(System.out.printf("Using supplied args 1.inputFile=%s, 2.outputFolder=%s",inputFile,outputFolder));
    }

    private static void synthesizeWordsFromFile(String fileName) throws Exception {

        Consumer<String> c = (x) -> {            
            for (String s : x.split(SPLIT_ON)) {
                synthesizeText(s);
            }
        };

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(c);
        }
    }

    private static void synthesizeText(String text) {

        Logger.info("Synthesising text: " + text);
        text = text.trim();

        try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create()) {

            SynthesisInput input = SynthesisInput.newBuilder().setText(text).build();
            VoiceSelectionParams voice = VoiceSelectionParams.newBuilder().setLanguageCode("en-GB")
                    .setSsmlGender(SsmlVoiceGender.FEMALE).build();
            AudioConfig audioConfig = AudioConfig.newBuilder().setAudioEncoding(AudioEncoding.MP3).build();
            SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);
            ByteString audioContents = response.getAudioContent();

            writeToFile(audioContents, text);
        } catch (Exception e) {
            Logger.error(e);
        }
    }

    private static void writeToFile(ByteString audioContents, String filename) throws Exception {


        String mp3file = outputFolder + File.separator + filename + SUFFIX;
        try (OutputStream out = new FileOutputStream(mp3file)) {
            out.write(audioContents.toByteArray());
            Logger.info("Audio content written to file: " + mp3file);
        }
    }

}