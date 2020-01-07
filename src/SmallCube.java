
public class SmallCube{ 
	private int [][][] faces;
	public SmallCube(int[][][] f){
		faces = f;
	}
	public int[][][] getFaces() {
		return faces;
	}
	private int[][][] clone(int[][][] s){

		int [][][] n = new int [6][2][2];

		for(int i = 0; i < 6 ; i++)
			for(int j = 0; j < 2; j++)
				for(int k = 0; k < 2; k++)
					n[i][j][k] = s[i][j][k];

		return n;
	}
	public SmallCube rotate(int nt, int f){

		int [][][] nw = clone(faces);

		if(nt == 1){

			for(int i = 0; i < 2; i++)
				for(int j = 0; j < 2; j++)
					nw[f][i][j] = faces[f][1-j][i];

		}else if (nt == 2){

			for(int i = 0; i < 2; i++)
				for(int j = 0; j < 2; j++)
					nw[f][i][j] = faces[f][1-i][1-j];

		}else{

			for(int i = 0; i < 2; i++)
				for(int j = 0; j < 2; j++)
					nw[f][i][j] = faces[f][j][1-i];

		}
		int [][] rv = sr[f];
		int sk;
		for(int i = 0; i < 8; i++){

			sk=(i+(4-nt)*2)%8;
			nw[rv[i][0]][rv[i][1]][rv[i][2]] =
					faces[rv[sk][0]][rv[sk][1]][rv[sk][2]];
		}

		return new SmallCube(nw);
	}
	public long getHash(){

		long h = 0, ch = 0, xc = 1;
		for(int i = 0; i < 6; i++){

			if(i%2 == 0){
				h ^= ch;
				ch = 0;
				xc = 1;
			}

			for(int j = 0; j < 2; j++){
				for(int k = 0; k < 2; k++){
					ch += faces[i][j][k]*xc;
					xc *= 10;
				}
			}
		}
		return h^ch;
	}
	private int [][][] sr = 
		{{{1,0,0},{1,1,0},{3,0,0},{3,1,0},{4,0,0},{4,1,0},{5,0,0},{5,1,0}},
				{{5,1,0},{5,1,1},{2,0,0},{2,1,0},{3,0,1},{3,0,0},{0,1,1},{0,0,1}},
				{{1,1,1},{1,0,1},{5,1,1},{5,0,1},{4,1,1},{4,0,1},{3,1,1},{3,0,1}},
				{{1,1,0},{1,1,1},{2,1,0},{2,1,1},{4,0,1},{4,0,0},{0,1,0},{0,1,1}},
				{{3,1,0},{3,1,1},{2,1,1},{2,0,1},{5,0,1},{5,0,0},{0,0,0},{0,1,0},},
				{{4,1,0},{4,1,1},{2,0,1},{2,0,0},{1,0,1},{1,0,0},{0,0,0},{0,0,1},}};
}
