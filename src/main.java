import java.util.*;
public class main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Cube cb = new Cube(new int [][][]
				{{{5,5,5},{5,5,5},{5,5,5}},{{1,1,1},{1,1,1},{1,1,1}},
			{{6,6,6},{6,6,6},{6,6,6}},{{2,2,2},{2,2,2},{2,2,2}},
			{{3,3,3},{3,3,3},{3,3,3}},{{4,4,4},{4,4,4},{4,4,4}}});
		cb = cb.rotate(0, 0).rotate(0, 1);
		System.out.println(cb);
		System.out.println(Solver.solve3(cb));

	}

}
