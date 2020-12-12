package io.zipcoder;

import sun.misc.Unsafe;

import java.util.Scanner;
import java.util.stream.Stream;

public class MonkeyTypewriter {

    public static void main(String[] args) {

        String introduction = "It was the best of times,\n" +
                "it was the blurst of times,\n" +
                "it was the age of wisdom,\n" +
                "it was the age of foolishness,\n" +
                "it was the epoch of belief,\n" +
                "it was the epoch of incredulity,\n" +
                "it was the season of Light,\n" +
                "it was the season of Darkness,\n" +
                "it was the spring of hope,\n" +
                "it was the winter of despair,\n" +
                "we had everything before us,\n" +
                "we had nothing before us,\n" +
                "we were all going direct to Heaven,\n" +
                "we were all going direct the other way--\n" +
                "in short, the period was so far like the present period, that some of\n" +
                "its noisiest authorities insisted on its being received, for good or for\n" +
                "evil, in the superlative degree of comparison only.";

        // Do all of the Monkey / Thread building here
        // For each Copier(one safe and one unsafe), create and start 5 monkeys copying the introduction to
        // A Tale Of Two Cities.
        UnsafeCopier usc = new UnsafeCopier(introduction);
        Thread[] unsafeThreads = {new Thread(usc)
                ,new Thread(usc)
                ,new Thread(usc)
                ,new Thread(usc)
                ,new Thread(usc)};
        for (Thread t : unsafeThreads) {t.start();}

        SafeCopier sc = new SafeCopier(introduction);
        Thread[] safeThreads = {new Thread(sc)
                ,new Thread(sc)
                ,new Thread(sc)
                ,new Thread(sc)
                ,new Thread(sc)};
        for (Thread t : safeThreads) {t.start();}



        // This wait is here because main is still a thread and we want the main method to print the finished copies
        // after enough time has passed.
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            System.out.println("MAIN INTERRUPTED");
        }
        System.out.println("UNSAFE ///////////\n" + usc.copied);
        System.out.println("SAFE ///////////\n" + sc.copied);


        // Print out the copied versions here.

    }
}