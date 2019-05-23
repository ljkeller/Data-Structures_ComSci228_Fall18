package edu.iastate.cs228.proj4;

import java.util.*;
import java.io.*;

/**
 * @author Lucas Keller
 *
 *
 * An application class that uses EntryTree class to process a file of commands (one per line). Each command line
 * consists of the name of a public method of the EntryTree class followed by its arguments in string form if the method
 * has arguments. The name of the file is available to the main method from its String[] parameter at index 0. You can
 * assume that the command file is always in correct format. The main method creates an object of the EntryTree class
 * with K being Character and V being String, reads each line from the command file, decodes the line by splitting into
 * String parts, forms the corresponding arguments, and calls the public method from the EntryTree object with the
 * arguments, and prints out the result on the console. Note that the name of a public method in the EntryTree class on
 * each command line specifies that the public method should be called from the EntryTree object. A sample input file of
 * commands and a sample output file are provided.
 *
 * The sample output file was produced by redirecting the console output to a file, i.e.,
 *
 * java Dictionary infile.txt > outfile.txt
 *
 *
 * NOTE that all methods of EntryTree class can be present as commands except for getAll method inside the input file.
 */
public class Dictionary {
    public static void main(String[] args) {
        if (args == null || args.length < 1) {
            System.out.println("There is no passed in argument");
            return;
        }

        try {
            File inputFile = new File(args[0]);
            EntryTree<Character, String> mainTree = new EntryTree<>();

            Scanner readFile = new Scanner(inputFile);
            Scanner readLine;
            String tempLine;
            String command;
            String arg1;
            String arg2;
            Object[] result;

            while (readFile.hasNextLine()) {
                tempLine = readFile.nextLine();

                readLine = new Scanner(tempLine);

                command = readLine.next();//command is first input
                if (readLine.hasNext())
                    arg1 = readLine.next();
                else
                    arg1 = "";
                if (readLine.hasNext())
                    arg2 = readLine.next();
                else
                    arg2 = "";
                System.out.println("Command: " + command + " " + arg1 + " " + arg2);
                switch (command) {
                    case "search":
                        System.out.println("Result from a search: " + mainTree.search(arg1.chars().mapToObj(c -> (char) c).toArray(Character[]::new)));
                        break;
                    case "prefix":
                        System.out.print("Result from a prefix: ");
                        result = mainTree.prefix(arg1.chars().mapToObj(c -> (char) c).toArray(Character[]::new));
                        for (Object o :
                                result) {
                            System.out.print(o);
                        }
                        break;
                    case "add":
                        System.out.println("Result from an add: " + mainTree.add(arg1.chars().mapToObj(c -> (char) c).toArray(Character[]::new), arg2));
                        break;
                    case "remove":
                        System.out.println("Result from a remove: " + mainTree.remove(arg1.chars().mapToObj(c -> (char) c).toArray(Character[]::new)));
                        break;
                    case "showTree":
                        System.out.println("");
                        System.out.println("Result from a showTree: ");
                        mainTree.showTree();
                        break;
                }//end switch
                System.out.println("");
            }

        } catch (FileNotFoundException ex) {
            System.out.println("A file was not found");
        }
    }
}
