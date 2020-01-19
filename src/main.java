import java.util.*;
public class main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Cube cb = new Cube(new int [][][]
				{{{5,5,5},{5,5,5},{5,5,5}},{{1,1,1},{1,1,1},{1,1,1}},
			{{6,6,6},{6,6,6},{6,6,6}},{{2,2,2},{2,2,2},{2,2,2}},
			{{3,3,3},{3,3,3},{3,3,3}},{{4,4,4},{4,4,4},{4,4,4}}});
		SmallCube sb = new SmallCube(new int [][][]
				{{{5,5},{5,5}},{{1,1},{1,1}},{{6,6},{6,6}},
			{{2,2},{2,2}},{{3,3},{3,3}},{{4,4},{4,4}}});
		Solver.solve3(cb);
		sb=sb.rotate(1, 0);
		System.out.println(Solver.solve2(sb));

	}

}
