import java.util.*;

public class Solver {
	private static PriorityQueue <Node> [] pqs;
	private static HashMap <Long, Integer> lkp2;
	private static HashMap <Long, String> [] lkp;
	private static HashSet<Long>sVis;
	private static Cube base;
	private static SmallCube sbase, sCur, sNex;
	private static long bHsh, sbHsh, sH, sCurH, sNexH, cHsh;
	private static Queue <SmallCube> nxtSc;
	private static Queue <Integer> snumMv;
	private static int [] fcs;
	private static int sCurM;
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
		sVis = new HashSet <Long>();
		sVis.add(sH);
		nxtSc.add(sc);
		snumMv.add(0);
		int [] fcs = {0,1,3};
		all:
		if(!lkp2.containsKey(sH)){
			
			while(true) {
				sCur=nxtSc.poll();
				sCurM=snumMv.poll();
				sCurH=sCur.getHash();
				
				for(int i = 1; i <= 3; i++) {
					for(int j : fcs) {
						
						sNex=sCur.rotate(i,j);
						sNexH=sNex.getHash();
						
						if(lkp2.containsKey(sNexH)) {
							
							lkp2.put(sH, lkp2.get(sNexH) + sCurM + 1);
							break all;
							
						} else if (!sVis.contains(sNexH)){
							
							sVis.add(sNexH);
							nxtSc.add(sNex);
							snumMv.add(sCurM+1);
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
		
		bHsh = base.getHash();
		sbHsh = sbase.getHash();
		cHsh = c.getHash();
		fcs = new int [] {0,1,3};
		pqs = new PriorityQueue [2];
		pqs[0] = new PriorityQueue<Node>();
		pqs[1] = new PriorityQueue<Node>();
		pqs[0].add(new Node(cHsh, 0, getHeuristic(c)));
		pqs[1].add(new Node(bHsh, 0, getHeuristic(base)));
		lkp = new HashMap [2];
		lkp[0] = new HashMap <Long, String>();
		lkp[1] = new HashMap <Long, String>();
		lkp2 = new HashMap <Long, Integer>();
		lkp[1].put(bHsh, "");
		lkp[0].put(cHsh,"");
		lkp2.put(sbHsh, 0);
	}
}
