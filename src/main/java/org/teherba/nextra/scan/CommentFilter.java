/*  Remove comments from a source file
    @(#) $Id: CommentFilter.java $
    2024-11-25, Georg Fischer: copied from LineReader
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
import  org.teherba.nextra.scan.LineReader;

/** Read lines from a source file.
 *  @author Dr. Georg Fischer
 */
public class CommentFilter {
    public final static String CVSID = "@(#) $Id: CommentFilter.java  $";

    /** left  bracket */
    private String left  = "/*";
    /** right bracket */
    private String right = "*/";
    /** comment symbols, for example "//", separated by whitespace */
    private String[] list;
    /** reader for liens */
    private LineReader reader;

    /** Constructor - Initialize the CommentFilter.
     *  @param fileName name of the file to be read, or "-", empty = stdin
     *  @param brackets a String of the form "left,right" that specifies the brackets enclosing a comment.
     */
    public CommentFilter(String brackets, String fileName) {
        reader = new LineReader(FileName);
        if (brackets.length() > 0) {
            String[] list = split("\\s+", brackets);
        }
    } // Constructor(String, String)

    /** Read and return the next source line, and
     *  increment the {@link #lineNumber}.
     *  @return source line as String
     */
    public String nextLine() {
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

    //--------------------------------------------------------------
    /**
     *  Test frame: read lines and print the content to stdin and the comments to stderr.
     *  @param args command line arguments: brackets (or empty)
     */
    public static void main (String args[]) {
        CommentFilter filter = new CommentFilter(args[0]);
        String line;
        boolean busy = true;
        while (busy) {
            line = filter.nextLine();
            if (line != null) {
                System.out.println(testReader.getLineNumber() + "\t" + line);
            } else {
                busy = false;
            }
        } // while
    } // main
} // CommentFilter
