/*
 * Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course
 * staff.
 */
package P1.poet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import P1.graph.Graph;

/**
 * A graph-based poetry generator.
 * 
 * <p>
 * GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>
 * For example, given this corpus:
 * 
 * <pre>
 *     Hello, HELLO, hello, goodbye!
 * </pre>
 * <p>
 * the graph would contain two edges:
 * <ul>
 * <li>("hello,") -> ("hello,") with weight 2
 * <li>("hello,") -> ("goodbye!") with weight 1
 * </ul>
 * <p>
 * where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>
 * Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 * 
 * <p>
 * For example, given this corpus:
 * 
 * <pre>
 *     This is a test of the Mugar Omni Theater sound system.
 * </pre>
 * <p>
 * on this input:
 * 
 * <pre>
 *     Test the system.
 * </pre>
 * <p>
 * the output poem would be:
 * 
 * <pre>
 *     Test of the system.
 * </pre>
 * 
 * <p>
 * PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */
public class GraphPoet {

    private final Graph<String> graph = Graph.empty();
    private final List<String> corpusWords = new ArrayList<String>();
    private final Map<String, Map<String, Integer>> corpusEdges = new HashMap<String, Map<String, Integer>>();
    // Abstraction function:
    // TODO change corpus to graph
    //      use input to be a poet
    // Representation invariant:
    // TODO graph is made up of corpus
    // Safety from rep exposure:
    // TODO All fields are private

    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(corpus));
        String tempString = null;
        while ((tempString = reader.readLine()) != null) {
            String[] a = tempString.split(" ");
            for (String str : a) {
                if (!str.trim().equals(""))
                    corpusWords.add(str);
            }
        }
        reader.close();
        for (String str : corpusWords) {
            graph.add(str.toLowerCase());
        }

        for (int i = 0; i < corpusWords.size() - 1; i++) {
            if (corpusEdges.containsKey(corpusWords.get(i))
                    && corpusEdges.get(corpusWords.get(i)).containsKey(corpusWords.get(i + 1))) {
                corpusEdges.get(corpusWords.get(i)).put(corpusWords.get(i + 1),
                        (corpusEdges.get(corpusWords.get(i)).get(corpusWords.get(i + 1))).intValue() + 1);
            } else if (!corpusEdges.containsKey(corpusWords.get(i))) {
                Map<String, Integer> iMap = new HashMap<String, Integer>();
                iMap.put(corpusWords.get(i + 1), 1);
                corpusEdges.put(corpusWords.get(i), iMap);
            } else {
                corpusEdges.get(corpusWords.get(i)).put(corpusWords.get(i + 1), 1);
            }
        }
        for (String str1 : corpusEdges.keySet()) {
            for (String str2 : corpusEdges.get(str1).keySet()) {
                graph.set(str1, str2, corpusEdges.get(str1).get(str2));
            }
        }
        // throw new RuntimeException("not implemented");
    }

    // TODO checkRep
    private void checkRep() {
        assert corpusWords.containsAll(corpusEdges.keySet()):"";
        for (String s:corpusEdges.keySet()) {
            assert corpusWords.containsAll(corpusEdges.get(s).keySet());
            assert corpusEdges.get(s)!=null;
        }
    }
    /**
     * Generate a poem.
     * 
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
        String[] inputWords = input.split("\\s");
        StringBuilder poemBuilder = new StringBuilder();

        for (int i = 0; i < inputWords.length; i++) {
            poemBuilder.append(inputWords[i] + " ");
            if (i + 1 >= inputWords.length)
                break;

            Map<String, Integer> word1Targets = graph.targets(inputWords[i].toLowerCase());
            Map<String, Integer> word2Sources = graph.sources(inputWords[i + 1].toLowerCase());
            Map<String, Integer> probableBridges = new HashMap<String, Integer>();

            for (String str : word1Targets.keySet()) {
                if (word2Sources.containsKey(str)) {
                    probableBridges.put(str, word1Targets.get(str) + word2Sources.get(str));
                }
            }
            int j = 1;
            String objString = new String();
            List<String> list = new ArrayList<String>(probableBridges.keySet());
            if (!list.isEmpty())
                objString += list.get(0);
            for (String str : probableBridges.keySet()) {
                if (probableBridges.get(str) > j) {
                    j = probableBridges.get(str);
                    objString = str;
                }
            }
            if (!probableBridges.keySet().isEmpty())
                poemBuilder.append(objString + " ");
        }
        checkRep();
        return poemBuilder.toString().trim();
        // throw new RuntimeException("not implemented");
    }

    // TODO toString()
   @Override
   public String toString() {
        return graph.toString();
    }
}
