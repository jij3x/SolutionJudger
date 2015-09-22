public class Interval {
    int _seqNo;
    int start;
    int end;

    Interval() {
        _seqNo = -1;
        start = 0;
        end = 0;
    }

    Interval(int start, int end) {
        _seqNo = -1;
        this.start = start;
        this.end = end;
    }
}
