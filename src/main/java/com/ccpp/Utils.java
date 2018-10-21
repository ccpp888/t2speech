package com.ccpp;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.stream.Stream;
import java.util.Arrays;
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
 * 
 * 
 * @author cpurvis
 */
public class Utils {
    
    public static void main(String[] args) throws Exception {

        String in = "water, bear, find, these, live, away, can't, more, began, say, good, again, I'll, boy, soon, want, cat, round, animals, night, over, long, tree, never, narrator, how, things, magic, next, small, did, new, shouted, first, car, man, after, us, work, couldn't, going, wanted, other, lots, three, where, eat, food, need, head, would, everyone, fox, that's, king, or, our, through, baby, town, took, two, way, fish, I've, school, has, been, gave, around, think, yes, stop, mouse, every, home, play, must, something, garden, who, take, red, bed, fast, didn't, thought, door, may, only, ran, dog, right, still, many, know, well, sea, found, laughed, let's, fun, any, better, lived, much, place, under, hot, birds, suddenly, mother, hat, sun, duck, told, sat, snow, across, horse, another, boat, air, gone, rabbit, great, window, tree, hard, white, why, sleep, bad, floppy, coming, cried, feet, tea, really, he's, keep, morning, top, wind, river, room, queen, eyes, wish, liked, last, each, fell, eggs, giant, jumped, book, friends, once, looks, because, its, box, please, use, even, green, dark, thing, along, am, different, grandad, stopped, plants, before, let, there's, ever, dragon, gran, girl, looking, miss, pulled, clothes, which, end, most, we're, tell, inside, than, cold, fly, key, run, best, park, grow";
        System.out.println(addSingleMarks(in));
    }

    private static String addSingleMarks(String input) {
    
        StringBuilder allWords = new StringBuilder();
        String[] words = input.split(",");
        Stream<String> stream1 = Arrays.stream(words);
        stream1.map(String::trim).map(x -> "'"+x+"',").forEach(x -> allWords.append(x));
        return allWords.toString();
    }

    
}
