
public class SmallCube{ 
	private int[][][]faces;
	public SmallCube(int[][][]f){
		faces=f;
	}
	public SmallCube rotate(int nt, int f){
		//TODO
	}
	public long getHash(){
		long h=0, ch=0, xc=1;
		for(int i=0;i<6;i++){
			if(i%2==0){
				h^=ch;
				ch=0;
				xc=1;
			}
			for(int j=0;j<2;j++){
				for(int k=0;k<2;k++){
					ch+=faces[i][j][k]*xc;
					xc*=10;
				}
			}
		}
		return h^ch;
	}
	
}
