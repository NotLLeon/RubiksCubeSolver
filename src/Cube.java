
public class Cube {
	private int [][][] faces;
	public Cube(int [][][] f){
		faces = f;
	}
	private int[][][] clone(int[][][] s){
		int [][][] n = new int [6][3][3];
		for(int i = 0; i < 6 ; i++)
			for(int j = 0; j < 3; j++)
				for(int k = 0; k < 3; k++)
					n[i][j][k] = s[i][j][k];
		return n;
	}
	public Cube rotate(int nt, int f){
		int [][][] nw = clone(faces);
		if(nt == 1){
			for(int i = 0; i < 3; i++)
				for(int j = 0; j < 3; j++)
					nw[f][i][j] = faces[f][2-j][i];
		}else if (nt == 2){
			for(int i = 0; i < 3; i++)
				for(int j = 0; j < 3; j++)
					nw[f][i][j] = faces[f][2-i][2-j];
		}else{
			for(int i = 0; i < 3; i++)
				for(int j = 0; j < 3; j++)
					nw[f][i][j] = faces[f][j][2-i];
		}
		int [][] rv = sr[f];
		int sk;
		for(int i = 0; i < 12; i++){
			sk=(i+(4-nt)*3)%12;
			nw[rv[i][0]][rv[i][1]][rv[i][2]] =
					faces[rv[sk][0]][rv[sk][1]][rv[sk][2]];
		}
		return new Cube(nw);
	}
	public int [][][] getFaces(){
		return faces;
	}
	public long getHash(){
		long h = 0, ch = 0, xc = 1;
		for(int i = 0; i < 6; i++){
			if(i%2 == 0){
				h ^= ch;
				ch = 0;
				xc = 1;
			}
			for(int j = 0; j < 3; j++){
				for(int k = 0; k < 3; k++){
					ch += faces[i][j][k]*xc;
					xc *= 10;
				}
			}
		}
		return h^ch;
	}
	private static int[][][]sr=
			{{{1,0,0},{1,1,0},{1,2,0},{3,0,0},{3,1,0},{3,2,0},{4,0,0},{4,1,0},{4,2,0},{5,0,0},{5,1,0},{5,2,0}},
			{{5,2,0},{5,2,1},{5,2,2},{2,0,0},{2,1,0},{2,2,0},{3,0,2},{3,0,1},{3,0,0},{0,2,2},{0,1,2},{0,0,2}},
			{{1,2,2},{1,1,2},{1,0,2},{5,2,2},{5,1,2},{5,0,2},{4,2,2},{4,1,2},{4,0,2},{3,2,2},{3,1,2},{3,0,2}},
			{{1,2,0},{1,2,1},{1,2,2},{2,2,0},{2,2,1},{2,2,2},{4,0,2},{4,0,1},{4,0,0},{0,2,0},{0,2,1},{0,2,2}},
			{{3,2,0},{3,2,1},{3,2,2},{2,2,2},{2,1,2},{2,0,2},{5,0,2},{5,0,1},{5,0,0},{0,0,0},{0,1,0},{0,2,0}},
			{{4,2,0},{4,2,1},{4,2,2},{2,0,2},{2,0,1},{2,0,0},{1,0,2},{1,0,1},{1,0,0},{0,0,0},{0,0,1},{0,0,2}}};
	// DE-BUGGING USE ONLY
	public String toString(){
		String st="";
		for(int i=0;i<6;i++){
			for(int j=0;j<3;j++){
				for(int k=0;k<3;k++){
					st+=faces[i][j][k]+" ";
				}
				st+="\n";
			}
			st+="\n";
		}
		return st;
	}
}
