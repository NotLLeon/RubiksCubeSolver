import java.util.*;

public class Solver {
	private static PriorityQueue <Node> [] pqs;
	private static HashMap <Long, Integer> lkp;
	private static class Node implements Comparable<Node>{
		long hash;
		int hr, depth;
		Node(long a, int b, int c){
			hash = a;
			depth = b;
			hr = c;
		}
		public int compareTo(Node e){
			return this.hr - e.hr;
		}
	}
	public static String solve3(Cube c){
		init(c);
		//TODO
	}
	private static int getHeuristic(Cube c){
		int [][][] f = c.getFaces();
		int [][][] smlCube = new int [6][2][2];
		for(int i = 0; i < 6; i++){
			smlCube[i][0][0] = f[i][0][0];
			smlCube[i][0][1] = f[i][0][2];
			smlCube[i][1][0] = f[i][2][0];
			smlCube[i][1][1] = f[i][2][2];
		}
		return solve2(new SmallCube(smlCube));
	}
	public static int solve2(SmallCube sc){
		//TODO
		long hsh = sc.getHash();
		if(!lkp.containsKey(hsh)){
			
		}
		return lkp.get(hsh);
	}
	private static void init(Cube c){
		Cube base = new Cube(new int [][][]
			{{{5,5,5},{5,5,5},{5,5,5}},{{1,1,1},{1,1,1},{1,1,1}},
			{{6,6,6},{6,6,6},{6,6,6}},{{2,2,2},{2,2,2},{2,2,2}},
			{{3,3,3},{3,3,3},{3,3,3}},{{4,4,4},{4,4,4},{4,4,4}}});
		pqs = new PriorityQueue [2];
		pqs[0] = new PriorityQueue<Node>();
		pqs[1] = new PriorityQueue<Node>();
		pqs[0].add(new Node(c.getHash(), 0, getHeuristic(c)));
		pqs[0].add(new Node(base.getHash(), 0, getHeuristic(base)));
		lkp = new HashMap <Long, Integer>();
	}
}
