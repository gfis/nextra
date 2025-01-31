/*  Read source file lines from a file
    @(#) $Id: LineReader.java 427 2010-06-01 09:08:17Z gfis $
    2024-11-25, Georg Fischer: copied from jextra
*/
/*
 * Copyright 2024 Dr. Georg Fischer <dr dot georg dot fischer at gmail ...>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.teherba.nextra.scan;
import  java.io.BufferedReader;
import  java.io.FileReader;
import  java.io.InputStreamReader;

/** Read lines from a source file.
 *  @author Dr. Georg Fischer
 */
public class LineReader {
    public final static String CVSID = "@(#) $Id: LineReader.java 427 2010-06-01 09:08:17Z gfis $";

    /** tell whether the End Of File was detected */
    boolean atEof = false;

    /** current column in <em>line</em>, 0, 1 ... */
    private int column = 1; // behind 'line'

    /** current line from source file, or null at EOF */
    private String line = "";

    /** sequential number of the line: 1, 2 ... */
    public int lineNumber = 0;

    /** Reader for the source file */
    BufferedReader reader;

    /** Constructor - Initialize the LineReader, open a source file
     *  @param fileName path/name of the source file, "" = STDIN
     */
    public LineReader(String fileName) {
        line = "";
        lineNumber = 0;
        column = 1; // behind 'line'
        atEof = false;
        try {
            reader = new BufferedReader(
                    (fileName == null || fileName.length() <= 0 || fileName.equals("-"))
                    ? new InputStreamReader(System.in)
                    : new FileReader (fileName)
                    );
        } catch (Exception exc) {
            System.err.println(exc.getMessage());
            exc.printStackTrace();;
        } // catch
    } // Constructor(fileName)

    /** Read and return the next source line, and
     *  increment the {@link #lineNumber}.
     *  @return source line as String
     */
    public String next() {
        try {
            line = reader.readLine();
        } catch (Exception exc) {
            System.err.println(exc.getMessage());
            exc.printStackTrace();
        } // try
        column = 0;
        lineNumber ++;
        return line;
    } // nextLine

    /** Gets the current line number: 1, 2 ...
     *  @return line number 1, 2 ...
     */
    public int getLineNumber() {
        return lineNumber;
    } // getLineNumber

    //--------------------------------------------------------------
    /**
     *  Test frame: read lines and print them.
     *  @param args command line arguments: filename or "-" 
     */
    public static void main (String args[]) {
        LineReader testReader = new LineReader(args.length == 0 ? "-" : args[0]);
        String line;
        boolean busy = true;
        while (busy) {
            line = testReader.next();
            if (line != null) {
                System.out.println(testReader.getLineNumber() + "\t" + line);
            } else {
                busy = false;
            }
        } // while
    } // main
} // LineReader
