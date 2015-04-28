import java.util.ArrayList;

public class UndirectedGraphNode {
    int _seqNo;
    int label;
    ArrayList<UndirectedGraphNode> neighbors;

    UndirectedGraphNode(int label) {
        _seqNo = -1;
        this.label = label;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }
};
