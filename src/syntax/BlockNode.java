package syntax;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlockNode extends SentenceNode {

    private List<SentenceNode> sentences;

    public BlockNode(List<SentenceNode> sentences) {
        this.sentences = sentences;
    }

    public BlockNode() {
        this.sentences = new ArrayList<>();
    }

    public void add(SentenceNode node) {
        this.sentences.add(node);
    }

    public List<SentenceNode> getSentences(){
        return sentences;
    }

    @Override
    public Iterable<Node> getChildren() {
        return new ArrayList<>(sentences);
    }
}
