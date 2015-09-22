public class BadVersion {
    private VersionControl versionControl;

    BadVersion(VersionControl versionControl) {
        this.versionControl = versionControl;
    }

    public int firstBadVersion(int n) {
        int l = 1, r = n;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (versionControl.isBadVersion(m)) r = m;
            else l = m + 1;
        }
        return l;
    }
}
