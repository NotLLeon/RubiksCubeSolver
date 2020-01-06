import java.util.*;

public class Solver {
	private static PriorityQueue <Node> [] pqs;
	private static HashMap <Long, Integer> lkp, lkp2;
	private static Cube base;
	private static SmallCube sbase, sCur, sTemp;
	private static long bHsh, sbHsh, sH, sCurH;
	private static Queue <SmallCube> nxtSc;
	private static Queue <Integer> snumMv;
	private static int [] fcs;
	private static int scurM;
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
		
		sH = sc.getHash();
		nxtSc = new LinkedList <SmallCube> ();
		snumMv = new LinkedList <Integer> ();
		nxtSc.add(sc);
		snumMv.add(0);
		int [] fcs = {0,1,3};
		if(!lkp2.containsKey(sH)){
			
			while(true) {
				
				sCur=nxtSc.poll();
				scurM=snumMv.poll();
				if(sCur.getHash() == sbHsh)return scurM;
				
				for(int i = 1; i <= 3; i++) {
					for(int j : fcs) {
						sTemp=sc.rotate(i,j);
						sCurH=sTemp.getHash();
						if(lkp2.containsKey(sCurH))return lkp2.get(sCurH) + scurM;
						else {
							nxtSc.add(sTemp);
							snumMv.add(scurM+1);
						}
					}
				}
				
			}
			
		}
		return lkp2.get(sH);
	}
	private static void init(Cube c){
		
		base = new Cube(new int [][][]
				{{{5,5,5},{5,5,5},{5,5,5}},{{1,1,1},{1,1,1},{1,1,1}},
			{{6,6,6},{6,6,6},{6,6,6}},{{2,2,2},{2,2,2},{2,2,2}},
			{{3,3,3},{3,3,3},{3,3,3}},{{4,4,4},{4,4,4},{4,4,4}}});
		
		sbase = new SmallCube(new int [][][]
				{{{5,5},{5,5}},{{1,1},{1,1}},{{6,6},{6,6}},
			{{2,2},{2,2}},{{3,3},{3,3}},{{4,4},{4,4}}});
		
		bHsh=base.getHash();
		sbHsh=base.getHash();
		fcs = new int [] {0,1,3};
		pqs = new PriorityQueue [2];
		pqs[0] = new PriorityQueue<Node>();
		pqs[1] = new PriorityQueue<Node>();
		pqs[0].add(new Node(c.getHash(), 0, getHeuristic(c)));
		pqs[0].add(new Node(base.getHash(), 0, getHeuristic(base)));
		lkp = new HashMap <Long, Integer>();
		lkp2 = new HashMap <Long, Integer>();
	}
}
