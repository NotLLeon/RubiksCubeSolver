import java.io.*;
import java.util.*;

public class Generate {
	
	private static HashMap <Long, Integer> lkp;
	private static SmallCube sbase, sCur, sNex;
	private static long sbHsh, sCurH, sNexH;
	private static Queue <SmallCube> nxtSc;
	private static Queue <Integer> snumMv;
	private static int sCurM, sNexM;
	
	public static void gen() throws IOException{
		FileWriter wr = new FileWriter("numMoves.txt");
		
		int ls = 1, ns = 1, np = 10000;
		init();
		wr.append(sbHsh+" "+0+"\n");
		
		while(!nxtSc.isEmpty()) {
			
			sCur=nxtSc.poll();
			sCurM=snumMv.poll();
			ns--;
			sCurH=sCur.getHash();
			if(ls>=np) {
				System.out.println("Computed "+ls+" positions, "+ns+" positions currently in the stack.");
				np += 10000;
			}
			for(int i = 1; i <= 3; i++) {
				
				for(int j = 0; j < 6; j++) {

					sNex = sCur.rotate(i,j);
					sNexH = sNex.getHash();
					sNexM = sCurM+1;

					if(!lkp.containsKey(sNexH)) {

						ls++;
						ns++;
						lkp.put(sNexH, sNexM);
						nxtSc.add(sNex);
						snumMv.add(sNexM);
						wr.append(sNexH+" "+sNexM+"\n");
						
					}
				}
			}
		}
		
		wr.flush();
		wr.close();
	}
	private static void init(){
		
		sbase = new SmallCube(new int [][][]
				{{{5,5},{5,5}},{{1,1},{1,1}},{{6,6},{6,6}},
			{{2,2},{2,2}},{{3,3},{3,3}},{{4,4},{4,4}}});

		sbHsh = sbase.getHash();
		lkp = new HashMap <Long, Integer>();
		lkp.put(sbHsh, 0);
		nxtSc = new LinkedList <SmallCube> ();
		snumMv = new LinkedList <Integer> ();
		nxtSc.add(sbase);
		snumMv.add(0);
	}
}
