package assignment4;
/* CRITTERS Main.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Hassan Chughtai>
 * <hac865>
 * <Student1 5-digit Unique No.>
 * <Ali Ahmed>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2016
 */

import java.util.List;
import java.util.Scanner;
import java.io.*;


/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main {

    static Scanner kb;	// scanner connected to keyboard input, or input file
    private static String inputFile;	// input file, used instead of keyboard input if specified
    static ByteArrayOutputStream testOutputString;	// if test specified, holds all console output
    private static String myPackage;	// package of Critter file.  Critter cannot be in default pkg.
    private static boolean DEBUG = false; // Use it or not, as you wish!
    static PrintStream old = System.out;	// if you want to restore output to console
    private static boolean SIM=true;

    // Gets the package name.  The usage assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    /**
     * Main method.
     * @param args args can be empty.  If not empty, provide two parameters -- the first is a file name, 
     * and the second is test (for test output, where all output to be directed to a String), or nothing.
     */
    public static void main(String[] args) { 
        if (args.length != 0) {
            try {
                inputFile = args[0];
                kb = new Scanner(new File(inputFile));			
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java Main OR java Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java Main OR java Main <input file>  <test output>");
            }
            if (args.length >= 2) {
                if (args[1].equals("test")) { // if the word "test" is the second argument to java
                    // Create a stream to hold the output
                    testOutputString = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(testOutputString);
                    // Save the old System.out.
                    old = System.out;
                    // Tell Java to use the special stream; all console output will be redirected here from now
                    System.setOut(ps);
                }
            }
        } else { // if no arguments to main
            kb = new Scanner(System.in); // use keyboard and console
        }

        /* Do not alter the code above for your submission. */
        /* Write your code below. */
        
        System.out.println("CRITTER WORLD SIMULATOR");
        while(SIM){
            String instruction = kb.nextLine();
            //instruction=instruction.toLowerCase();
            String[] split= instruction.split(" ");

            if(split[0].equals("quit")){
                if(split.length>1) {
                    System.out.println("error processing:" + instruction);
                }
                kb.close();
                return;
            }
            else if (split[0].equals("show")){
                if(split.length>1){
                    System.out.println("error processing:" + instruction);
                }
                else{
                    Critter.displayWorld();
                }
                continue;
            }
            else if(split[0].equals("step")){
                int steps=0;
                if(split.length==1){
                    Critter.worldTimeStep();
                    continue;
                }
                if(split.length>2){
                    System.out.println("error processing:" + instruction);
                    continue;
                }
                try {
                   steps = Integer.parseInt(split[1]);
                   for(int i=0;i<steps;i++){
                       Critter.worldTimeStep();
                   }
                }catch (NumberFormatException e){
                    System.out.println("error processing:" + instruction);

                }

            }
            else if(split[0].equals("seed")){
                Integer seed;
                if(split.length>2){
                    System.out.println("error processing:" + instruction);

                }
                else{
                    try{
                        seed=Integer.parseInt(split[1]);
                        Critter.setSeed(seed);
                    }
                    catch(NumberFormatException e){
                        System.out.println("error processing:" + instruction);

                    }

                }
            }
            else if(split[0].equals("make")){
                Integer count=1;

                if(split.length>3||split.length<2){
                    System.out.println("error processing:" + instruction);
                }
                else if(split.length==2){
                    try{
                        String name= split[1];
                        Critter.makeCritter(name);
                    }catch (InvalidCritterException e){
                        System.out.println("error processing:" + instruction);

                    }
                }
                else {

                    String name = split[1];
                    try {
                        count = Integer.parseInt(split[2]);
                    } catch (NumberFormatException e) {
                        System.out.println("error processing:" + instruction);
                        continue;
                    }
                    try {
                        for (int i = 0; i < count; i++) {

                            Critter.makeCritter(name);
                        }
                    } catch (InvalidCritterException e) {
                        System.out.println("error processing:" + instruction);

                    }

                }
            }
            else if(split[0].equals("stats")){
                if(split.length!=2){
                    System.out.println("error processing:" + instruction);
                    continue;
                }
                String name = split[1];
                try{
                    List<Critter> crits=Critter.getInstances(name);
                    Class<?>critclass = null;
                    critclass=Class.forName(name);
                    critclass.getMethod("runStats",List.class).invoke(critclass,crits);
                }
                catch (InvalidCritterException e){
                    System.out.println("error processing:" + instruction);
                    continue;
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
            else{
                System.out.println("error processing "+instruction);
            }

        }
        
        /* Write your code above */
        System.out.flush();

    }
}
