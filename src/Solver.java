import java.util.*;

public class Solver {
	private static PriorityQueue <Node> [] pqs;
	private static HashMap <Long, Integer> lkp;
	private static HashMap <Long, String> [] vis;
	private static HashSet<Long>sVis;
	private static Cube base, cur, next;
	private static SmallCube sbase, sCur, sNex;
	private static long bHsh, sbHsh, sH, sCurH, sNexH, cHsh, nexH;
	private static Queue <SmallCube> nxtSc;
	private static Queue <Integer> snumMv;
	private static int sCurM, cnt, cDepth;
	private static Node curN;
	private static String [] lbl;
	private static String cMvs, stemp;
	private static class Node implements Comparable<Node>{
		
		Cube cb;
		int hr, depth;
		String mvs;
		
		Node(Cube a, int b, int c, String d){
			
			cb = a;
			depth = b;
			hr = c;
			mvs =d;
		}
		
		public int compareTo(Node e){
			return this.hr - e.hr;
		}
	}
	public static void main (String [] args) {
		System.out.println(format("R2 R2 F3 "));
	}
	public static String solve3(Cube c){
		// TODO
		init(c);
		
		while(true) {
			curN = pqs[cnt%2].poll();
			cur = curN.cb;
			cMvs = curN.mvs;
			cDepth = curN.depth;
			for(int f = 0; f < 6; f++) {
				for(int nt = 1; nt <= 3; nt++) {
					next = cur.rotate(nt, f);
					nexH = next.getHash();
					stemp = cMvs + lbl[f] + nt + " ";
					if(vis[(cnt+1)%2].containsKey(nexH)) {
						
						if(cnt%2 == 0) 
							return stemp + format(vis[(cnt+1)%2].get(nexH));
						else 
							return vis[(cnt+1)%2].get(nexH) + format(stemp);
						
					}else if(!vis[cnt%2].containsKey(nexH) && cDepth < 10) {
						vis[cnt%2].put(nexH, stemp);
						pqs[cnt%2].add(new Node(next, 
												cDepth+1, 
												getHeuristic(next), 
												stemp));
					}
				}
			}
			cnt++;
		}
			
		
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
		if(!lkp.containsKey(sH)){
			
			while(true) {
				sCur=nxtSc.poll();
				sCurM=snumMv.poll();
				sCurH=sCur.getHash();
				
				for(int i = 1; i <= 3; i++) {
					for(int j : fcs) {
						
						sNex=sCur.rotate(i,j);
						sNexH=sNex.getHash();
						
						if(lkp.containsKey(sNexH)) {
							
							lkp.put(sH, lkp.get(sNexH)+sCurM+1);
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
		return lkp.get(sH);
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
		pqs = new PriorityQueue [2];
		pqs[0] = new PriorityQueue<Node>();
		pqs[1] = new PriorityQueue<Node>();
		pqs[0].add(new Node(c, 0, getHeuristic(c), ""));
		pqs[1].add(new Node(base, 0, getHeuristic(base), ""));
		vis = new HashMap [2];
		vis[0] = new HashMap <Long, String>();
		vis[1] = new HashMap <Long, String>();
		vis[1].put(bHsh, "");
		vis[0].put(cHsh,"");
		lkp = new HashMap <Long, Integer>();
		lkp.put(sbHsh, 0);
		cnt = 0;
		lbl = new String[]{"L","U","R","F","D","B"};
	}
	private static String format(String s) {
		//  TEST
		String [] temp = s.split(" ");
		Stack <String> st = new Stack <String>();
		for(int i = temp.length-1; i >= 0; i--) {
			
			if(st.isEmpty()) st.add(temp[i]);
			else if (st.peek().charAt(0)==temp[i].charAt(0)) {
				
				int dt = (st.pop().charAt(1)+temp[i].charAt(1)-96)%4;
				if (dt != 0) st.push(temp[i].charAt(0)+ ""+dt+" ");
				
			}else st.add(temp[i]);
		}
		String ans = "";
		while(!st.isEmpty())ans += st.pop();
		return ans;
		
	}
}
