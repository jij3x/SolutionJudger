public class Solution {
    public int testBadVersion(int n, int firstBadVersion) {
        BadVersion badVersion = new BadVersion(new VersionControl(n, firstBadVersion));
        return badVersion.firstBadVersion(n);
    }
}