import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class StringReassemblyTest {

    @Test
    public void testCombo() {
        String n = "abcde";
        String m = "defg";
        int overlap = 2;
        String n1 = StringReassembly.combination(n, m, overlap);
        String expected = "abcdefg";
        assertEquals(expected, n1);
    }

    @Test
    public void testCombo2() {
        String n = "xyzabc123";
        String m = "zabc123";
        int overlap = 7;
        String n1 = StringReassembly.combination(n, m, overlap);
        String expected = "xyzabc123";
        assertEquals(expected, n1);
    }

    @Test
    public void testLineWithSep() {
        // Create a SimpleWriter for testing
        SimpleWriter out = new SimpleWriter1L("input2.txt");
        SimpleReader in = new SimpleReader1L("input2.txt");
        // Call the method with a string containing '~' characters
        String text = "This~is~a~test";
        StringReassembly.printWithLineSeparators(text, out);

        String line1 = in.nextLine();
        String line2 = in.nextLine();
        String line3 = in.nextLine();
        String line4 = in.nextLine();

        String expected1 = "This";
        String expected2 = "is";
        String expected3 = "a";
        String expected4 = "test";
        // Check that the output string is correct

        assertEquals(expected1, line1);
        assertEquals(expected2, line2);
        assertEquals(expected3, line3);
        assertEquals(expected4, line4);

        in.close();
    }

    @Test
    public void testLinesFromInput() {

        SimpleReader input = new SimpleReader1L("data/input.txt");
        Set<String> expected = new Set1L<>();
        expected.add("apple");
        expected.add("banana");
        expected.add("orange");
        expected.add("kiwi");

        Set<String> actual = StringReassembly.linesFromInput(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testAdd() {

        Set<String> set = new Set1L<String>();
        set.add("hello");
        set.add("world");

        String str = "hi";
        Set<String> expected = new Set1L<String>();
        expected.add("hello");
        expected.add("world");
        expected.add("hi");

        StringReassembly.addToSetAvoidingSubstrings(set, str);

        assertEquals(expected, set);
    }

}
