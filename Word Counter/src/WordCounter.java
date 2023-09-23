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
 * Project allowing client to enter a txt file and recieve a separate html file
 * counting all the appearances of each word.
 *
 * @author K. Fosu
 */
public final class WordCounter {

    /**
     *
     * implements comparator (ignoring capitalization)
     *
     */

    private static class Sort implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.toLowerCase().compareTo(o2.toLowerCase());
        }
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
     *            the map being used for the method
     * @param userOutFile
     *            the name of the file entered by client
     * @param tQueue
     *            the queue being used for the method
     *
     * @param fileOut
     *            the file being written to
     */
    private static void outputPage(Map<String, Integer> m, String userOutFile,
            Queue<String> tQueue, SimpleWriter fileOut) {
        fileOut.println("<html>");
        fileOut.println("<head>");
        fileOut.println(
                "<title>" + "Words counted in " + userOutFile + "</title>");
        fileOut.println("</head>");
        fileOut.println("<body>");
        fileOut.println("<h2>" + "Words counted in " + userOutFile + "</h2>");
        fileOut.println("<hr />");
        fileOut.println("<table border=\"1\">");
        fileOut.println("<tr>");
        fileOut.println("<th>Words</th>");
        fileOut.println("<th>Counts</th>");
        fileOut.println("</tr>");

        while (tQueue.length() != 0) {
            String current = tQueue.dequeue();
            fileOut.println("<tr>");
            fileOut.println("<td>" + current + "</td>");
            fileOut.println("<td>" + m.value(current) + "</td>");
            fileOut.println("</tr>");
        }

        fileOut.println("</table>");
        fileOut.println("</body>");
        fileOut.println("</html>");
        fileOut.close();

    }

    /**
     *
     * @param fileIn
     *            the SimpleReader file
     * @param m
     *            the empty map
     * @return the queue in alphabetical order
     */
    private static Queue<String> ABCQueue(SimpleReader fileIn,
            Map<String, Integer> m) {

        Queue<String> tQueue = new Queue1L<>();
        Comparator<String> x = new Sort();
        Set1L<Character> separators = new Set1L<>();
        separators.add(' ');
        separators.add(':');
        separators.add(';');
        separators.add(',');
        separators.add('.');
        separators.add('?');
        separators.add('-');

        while (!fileIn.atEOS()) {
            int count = 1;
            int index = 0;
            String line = fileIn.nextLine();
            while (index < line.length()) {
                String word = nextWordOrSeparator(line, index, separators);
                index = index + word.length();
                if (m.hasKey(word) && !separators.contains(word.charAt(0))) {
                    Map.Pair<String, Integer> pair = m.remove(word);
                    count = pair.value();
                    count++;
                    m.add(word, count);

                } else if (!m.hasKey(word)
                        && !separators.contains(word.charAt(0))) {
                    tQueue.enqueue(word);
                    m.add(word, count);
                }

            }

        }

        tQueue.sort(x);
        return tQueue;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();
        out.print("Enter your file with your terms: ");
        String userFile = in.nextLine();
        out.print("Enter a name for the output file: ");
        String userOutFile = in.nextLine();
        SimpleReader fileIn = new SimpleReader1L(userFile);
        SimpleWriter fileOut = new SimpleWriter1L(userOutFile);
        Map<String, Integer> m = new Map1L<>();
        Queue<String> tQueue = ABCQueue(fileIn, m);

        outputPage(m, userFile, tQueue, fileOut);

        in.close();
        out.close();
    }

}
