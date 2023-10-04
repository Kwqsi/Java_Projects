import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Project creating a website from user txt input file, which sorts terms and
 * their definitions also putting them into corresponding files.
 *
 * @author Kwasi Fosu
 *
 */
public final class Glossary {

    /**
     *
     * implements comparator.
     *
     */

    private static class Sort implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    /**
     *
     * @param fileIn
     *            the SimpleReader file
     * @param m
     *            the empty map
     * @return the queue in alphabetical order
     */
    private static Queue<String> termsQueue(SimpleReader fileIn,
            Map<String, String> m) {

//creates queue
        Queue<String> tQueue = new Queue1L<>();
        Comparator<String> x = new Sort();
        //while loop to iterate until file ends
        while (!fileIn.atEOS()) {
//create a term and description
            String term = fileIn.nextLine();
            String description = fileIn.nextLine();
            String line = "";
            //if the file isn't at the end update line
            if (!fileIn.atEOS()) {
                line = fileIn.nextLine();
            }
            //while line is not blank add it to description
            while (!line.equals("")) {
                description += line;
                line = fileIn.nextLine();

            }
//add terms and desc to a map
            m.add(term, description);
            //sort the map
            tQueue.enqueue(term);
            tQueue.sort(x);

        }
        return tQueue;

    }

    /**
     * @param text
     *            the text being used for the method
     * @param position
     *            the position of the characters
     * @param separators
     *            the character that should be detected as a separator
     * @return result first word
     */
    private static String nextWordOrSeparator(String text, int position,
            Set1L<Character> separators) {
//create result with stringbuilder
        StringBuilder result = new StringBuilder();
        boolean isSeparator = separators.contains(text.charAt(position));
//shifting through the word
        while (position < text.length() && (isSeparator == separators
                .contains(text.charAt(position)))) {
            result.append(text.charAt(position));
            position++;
        }

        return result.toString();
    }

    /**
     * @param m
     *            the map
     * @param userFolder
     *            folder name from user
     * @param tQueue
     *            queue input
     */
    private static void outputPages(Map<String, String> m, String userFolder,
            Queue<String> tQueue) {
        //creating a set for the separators with a space bar

        for (int i = tQueue.length(); i != 0; i--) {
            Map.Pair<String, String> current = m.remove(tQueue.front());
            tQueue.rotate(-1);

            SimpleWriter fileOut = new SimpleWriter1L(
                    userFolder + "/" + current.key() + ".html");
            String title = current.key();
            String description = current.value();
//html outputs
            fileOut.println("<html>");
            fileOut.println("<head>");
            fileOut.println("<title>" + title + "</title>");
            fileOut.println("</head>");
            fileOut.println("<body>");
            fileOut.print("<h2>");
            fileOut.print("<b>");
            fileOut.print("<i>");
            fileOut.println("<font color=" + "\"red\">" + title
                    + "</font></i></b></h2>");
            fileOut.println("<p>");
//call to print description for when description contains a key term
            printDescription(description, tQueue, fileOut);

            fileOut.println("<hr />");
            fileOut.println("<p>");
            fileOut.println("Return to <a href=\"index.html\">index</a>");
            fileOut.println("</p>");
            fileOut.println("</body>");
            fileOut.println("</html>");

        }

    }

    /**
     * @param fileIn
     *            SimpleReader to read input
     * @param fileOut
     *            SimpleWriter to output to
     * @param m
     *            The map to use
     * @param tQueue
     *            The queue to use
     */
    private static void outputIndex(SimpleReader fileIn, SimpleWriter fileOut,
            Map<String, String> m, Queue<String> tQueue) {
//html outputs
        fileOut.println("<html>");

        fileOut.print("<title>");
        fileOut.print("Glossary");
        fileOut.println("</title>");
        fileOut.println("</head>");

        fileOut.println("<body>");
        fileOut.print("<h2>");
        fileOut.print("Glossary");
        fileOut.println("</h2>");
        fileOut.println("<hr />");
        fileOut.println("<h3>Index</h3>");
        fileOut.println("<ul>");
//looping through queue
        for (String s : tQueue) {
            fileOut.print("<li>");
            fileOut.println("<a href=\"" + s + ".html\">" + s + "</a></li>");
        }
        fileOut.println("</ul>");
        fileOut.println("</body>");
        fileOut.println("</html>");

    }

    /**
     *
     * @param description
     *            the desciption to use
     * @param tQueue
     *            the queue to use
     * @param fileOut
     *            SimpleWriter to output
     */
    private static void printDescription(String description,
            Queue<String> tQueue, SimpleWriter fileOut) {
        //create " " separators for space
        Set1L<Character> separators = new Set1L<>();
        separators.add(' ');

        fileOut.print("<p>");
        int i = 0;
        //loop to print description
        while (i < description.length()) {
            String current = nextWordOrSeparator(description, i, separators);
            boolean contains = false;
            for (String s : tQueue) {
                //if desc contains key term
                if (s.equals(current)) {
                    contains = true;
                    fileOut.print("<a href= " + '"' + current + ".html" + '"'
                            + ">" + current + " " + "</a>");
                }
            }
            //if desc does not contain key term
            if (!contains) {
                fileOut.print(current + " ");

            }
            i = i + current.length() + 1;

        }

        fileOut.print("</p>");

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {

        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        out.print("Enter your txt file with terms: ");
        String userFile = in.nextLine();
        out.print("Enter a name for a folder to store files: ");
        String userFolder = in.nextLine();
        SimpleReader fileIn = new SimpleReader1L(userFile);
        SimpleWriter fileOut = new SimpleWriter1L(userFolder + "/index.html");
        Map<String, String> m = new Map1L<>();
        Queue<String> termsQueue = termsQueue(fileIn, m);

        outputPages(m, userFolder, termsQueue);
        outputIndex(fileIn, fileOut, m, termsQueue);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
        fileOut.close();
        fileIn.close();
    }

}
