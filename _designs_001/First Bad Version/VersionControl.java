public class VersionControl {
    private int n, firstBadVersion;

    public VersionControl(int n, int firstBadVersion) {
        this.n = n;
        this.firstBadVersion = firstBadVersion;
    }

    public boolean isBadVersion(int version) {
        return version >= firstBadVersion;
    }
}
