public class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        long Al = A, Bl = B, Cl = C, Dl = D, El = E, Fl = F, Gl = G, Hl = H;

		// area of rectangle 1
		long total = Math.abs(Al - Cl) * Math.abs(Bl - Dl);

		total -= Math.max(Math.min(Cl, Gl) - Math.max(Al, El), 0)
				* Math.max(Math.min(Dl, Hl) - Math.max(Bl, Fl), 0);

		// add area of rectangle 2
		total += Math.abs(El - Gl) * Math.abs(Fl - Hl);

		return (int) total;
    }
}