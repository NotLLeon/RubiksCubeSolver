import java.util.*;
public class Solver {
	private static PriorityQueue<Node>[]pqs;
	private class Node implements Comparable<Node>{
		long hash;
		int hr;
		Node(long a, int b){
			hash=a;
			hr=b;
		}
		public int compareTo(Node e){
			return this.hr-e.hr;
		}
	}
	public static String solve(Cube c){
		pqs=new PriorityQueue[2];
		pqs[0]=new PriorityQueue<Node>();
		pqs[1]=new PriorityQueue<Node>();
		
	}
}
