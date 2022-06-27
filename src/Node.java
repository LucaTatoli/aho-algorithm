import java.util.ArrayList;

public class Node {

    private ArrayList<Node> children;
    private Node faiulreLink;
    private char value;
    private int level;
    private String prefix;
    private Node root;
    private boolean wordFlag;

    public Node(String... strings) {
        value = '-';
        prefix = "";
        level = 0;
        children = new ArrayList<>();
        root = this;
        wordFlag = false;
        for(int i = 0; i < strings.length; i++)
            pushString(strings[i]);
    }

    public Node(char c, String prefix, int level, Node root) {
        value = c;
        this.level = level;
        this.prefix = prefix+c;
        this.root = root;
        this.wordFlag = false;
        children = new ArrayList<>();
    }

    public void pushString(String s) {
        if(s.isEmpty()) {
            wordFlag = true;
            return;
        }

        boolean isChild = false;

        for(Node child : children)
            if(child.getValue() == s.charAt(0)) {
                isChild = true;
                child.pushString(s.substring(1));
                break;
            }

        if(!isChild) {
            Node child = new Node(s.charAt(0), prefix, level+1, root);
            child.pushString(s.substring(1));
            children.add(child);
        }

    }

    public char getValue() {
        return value;
    }

    public Node getChild(char value) {
        for(Node child : children)
            if(child.getValue() == value)
                return child;
        return null;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return printAll();
    }

    private String printAll() {
        String spacer = "";
        for(int i = 0; i < 15 - prefix.length(); i++)
            spacer += " ";
        String out = level+": "+prefix+spacer+ "is word " + wordFlag + " \n";
        for(Node child : children)
            out += child.printAll();
        return out;
    }

    public void generateFailureLinks() {
        generateFailureLink();
        for(Node child : children) {
            child.generateFailureLinks();
        }
    }

    private void generateFailureLink() {
        if(prefix.length() <= 1) {
            faiulreLink = root;
            return;
        }

        int prefixIndex = 1;

        Node temp;
        boolean foundPrefix;

        while(prefixIndex < prefix.length()) {

            temp = root;
            foundPrefix = true;

            for(int i = prefixIndex; i < prefix.length(); i++) {
                temp = temp.getChild(prefix.charAt(i));
                if(temp == null) {
                    foundPrefix = false;
                    break;
                }
            }

            if(foundPrefix) {
                faiulreLink = temp;
                return;
            }

            prefixIndex++;

        }

        faiulreLink = root;

    }

    public Node getFaiulreLink() {
        return faiulreLink;
    }

    public String getPrefix() {
        return prefix;
    }

    public boolean isWord() {
        return wordFlag;
    }
}
