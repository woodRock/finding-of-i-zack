package TheFindingOfIZack.FileIO.Util;

import java.util.*;

/**
 * A new instance of HuffmanCoding is created for every run. The constructor is
 * passed the full text to be encoded or decoded, so this is a good place to
 * construct the tree. You should store this tree in a field and then use it in
 * the encode and decode methods.
 */
public class  Huffman {


    private Map<Character, BinaryNode> characterToNode = new HashMap<>();
    private BinaryNode HuffmanTree;
    private Map<Character,Integer> characterTable;
    /**
     * This would be a good place to compute and store the tree.
     */
    public Huffman (String text) {

    }

    /**
     * Take an input string, text, and encode it with the stored tree. Should
     * return the encoded text as a binary string, that is, a string containing
     * only 1 and 0.
     */
    public String encode(String text) {
		/*
		 * Get required information inorder to encode
		 */
        characterTable = frequencyMap(text);
        HuffmanTree = buildTree(characterTable);
        Map<Character,String> characterCodes = generateCodes(characterTable.keySet());

        StringBuilder encodedText = new StringBuilder();

        //Put Character and frequencies at start of  file for proper decode
        //encodedText.append(characterTablesSave(characterTable));

        //Put the generated code for each character rather than the character in the encoded text
        for (int i = 0; i < text.length(); i++){
            char c = text.charAt(i);
            encodedText.append(characterCodes.get(c));
        }
        return encodedText.toString();
    }

    /**
     * Create a tree from the character frequencies for encoding and decoding the text
     * @param map
     * @return
     */
    private BinaryNode buildTree(Map<Character,Integer> map) {
        Queue<BinaryNode> frequencyQueue = characterByFrequency(map);
        while (frequencyQueue.size() > 1){
            BinaryNode left = frequencyQueue.poll();
            BinaryNode right = frequencyQueue.poll();
            BinaryNode node = new BinaryNode(left.frequency + right.frequency, left, right);
            frequencyQueue.offer(node);
        }
        return frequencyQueue.poll();
    }

    /**
     * Generate a string representation of the character table for the file
     *
     * @return formatted characterTable for saving to encoded file
     */
    private String characterTablesSave(Map<Character,Integer> characterTable){
        StringBuilder out = new StringBuilder();
        for (Character c: characterTable.keySet()){
            out.append("[" + c.toString() +"|" + characterTable.get(c) + "|" + "]");
        }
        return out.toString();
    }

    /**
     * Take encoded input as a binary string, decode it using the stored tree,
     * and return the decoded text as a text string.
     */
    public String decode(String encoded) {
        StringBuilder text = new StringBuilder();
		/*
		 * Decode the remaining string which is the main text
		 */
        for (int i = 0; i < encoded.length(); i++){
            BinaryNode node = HuffmanTree;
            char c;
            while (node.right != null && node.left != null ) {
                c = encoded.charAt(i);
                if (c == '0') {
                    node = node.left;
                } else if (c == '1') {
                    node = node.right;
                }
                i++;
            }
            i--;	//Undo the last i++ in the while loop when the character is found
            text.append(node.character);

        }
        return text.toString();
    }

    /**
     * The getInformation method is here for your convenience, you don't need to
     * fill it in if you don't wan to. It is called on every run and its return
     * value is displayed on-screen. You could use this, for example, to print
     * out the encoding tree.
     */
    public String getInformation() {
        StringBuilder s = new StringBuilder();

        HuffmanTree.printTree(s);

        System.out.print(s.toString());
        return "";
    }


    /**
     * Get the frequency at which characters occur
     * @param text
     * @return
     */
    private Map<Character, Integer> frequencyMap(String text){
        HashMap<Character,Integer> frequency = new HashMap<>();

        for (int i = 0; i < text.length(); i++){
            Character c = text.charAt(i);
            frequency.put(c,(frequency.getOrDefault(c,0))+ 1);
        }
        return frequency;
    }

    /**
     * Create a queue of TreeNodes that represent characters and frequencies
     * Sorted from most frequent to least frequent
     * @param frequency
     * @return
     */
    private Queue<BinaryNode> characterByFrequency(Map<Character,Integer> frequency){
        Queue<BinaryNode> sorted = new PriorityQueue<>();
        for (Character c : frequency.keySet()){
            Integer i = frequency.get(c);
            BinaryNode node = new BinaryNode(i,c);
            characterToNode.put(c,node);
            sorted.offer(node);
        }
        return sorted;
    }

    /**
     * Generate Huffman codes for each character in the text,
     * Uses the binary tree of frequencies to generate codes
     *
     * @param chars	all the characters present in the text
     * @return	Map of Character to code stored as a string
     */
    private Map<Character, String> generateCodes(Set<Character> chars){
        HashMap<Character, String> codes = new HashMap<>();
        for (Character c : chars){
            StringBuilder code = new StringBuilder();
            BinaryNode node = characterToNode.get(c);
            while (node != null && node.parent != null){
                if (node.parent.left == node) code.append('0');
                if (node.parent.right == node) code.append('1');
                node = node.parent;
            }

            codes.put(c,code.reverse().toString());

        }
        return codes;
    }

    /**
     *  This class captures the notion of a BinaryNode necessary to create a Huffman
     *  encoding table, and to encode/decode the data
     */
    public class BinaryNode implements  Comparable<BinaryNode>{
        private int frequency;
        private Character character;
        private BinaryNode parent;
        private BinaryNode left;
        private BinaryNode right;

        public BinaryNode(int frequency, Character character){
            this.frequency = frequency;
            this.character = character;
        }
        public BinaryNode(int frequency, BinaryNode left, BinaryNode right){
            this.frequency = frequency;
            this.left = left;
            this.right = right;

            left.parent = this;
            right.parent = this;
        }


        public void printTree(StringBuilder out) {
            if (right != null) {
                right.printTree(out, true, "");
            }
            printNodeValue(out);
            if (left != null) {
                left.printTree(out, false, "");
            }
        }

        /**
         *  This is a helped method for printing the HuffmanTree
         * @param out
         * @return
         */
        private StringBuilder printNodeValue(StringBuilder out) {
            if (frequency == 0) {
                out.append("<null>");
            } else {
                out.append("freq: " + frequency );
                if (character != null){
                    out.append(" char: " + character);
                }
            }
            out.append('\n');
            return out;
        }

        /**
         * Creates a visual display of the encoding tree for debugging purposes
         * @param out the output string to be printed
         * @param isRight if it is on the right
         * @param indent
         */
        private void printTree(StringBuilder out, boolean isRight, String indent) {
            if (right != null) {
                right.printTree(out, true, indent + (isRight ? "        " : " |      "));
            }
            out.append(indent);
            if (isRight) {
                out.append(" /");
            } else {
                out.append(" \\");
            }
            out.append("----- ");
            printNodeValue(out);
            if (left != null) {
                left.printTree(out, false, indent + (isRight ? " |      " : "        "));
            }
        }

        /**
         * Comparator for the layout of the Huffman encoding tree
         * @param o the other node to be compared
         * @return positive if this is bigger, 0 if equal, -1 otherwise
         */
        @Override
        public int compareTo(BinaryNode o){ return this.frequency - o.frequency;}
    }

}
