package main;

public class map {
	static int xmax=19;
	static int ymax=29;
	public static String[][] maze = new String[xmax][ymax];
	
	
	
	public map() {
		this.createMaze();

	}
	
	
	private void createMaze(){
	      fillArray();
	      putWall(0,24,0,28);
	      putWall(0,24,5,24);
	      putWall(6,19,6,23);
	      
	      putWall(8,19,8,25);
	      putWall(2,26,7,26);
	      putWall(2,26,2,28);
	      
	      putWall(11,19,11,25);
	      putWall(11,26,16,26);
	      putWall(17,26,17,28);
	      
	      putWall(13,19,13,23);
	      putWall(13,24,18,24);
	      
	      placePuck(3,27);
	      placePuck(14,27);
	      placePuck(3,22);
	      placePuck(14,22);
	      
	     	      
	      for(int i=5;i<14;i++){
	    	  if(i%2==1){
	    	  placePuck(i,10);
	    	  }
	      }
	      for(int i=5;i<14;i++){
	    	  if(i%2==1){
	    	  placePuck(i,7);
	    	  }
	      }
	}
	
	
	
	/*
	 * fills the outside of the array with walls
	 */		
	private void fillArray(){
		for(int y=0;y<ymax;y++){
			for(int x=0;x<xmax;x++){
				if(x==0){
					if(y==0){
						maze[x][y]="1001";
					}
					else if(y==ymax-1){
						maze[x][y]="0011";
					}
					else{
						maze[x][y]="0001";
					}
				}
				else if(x==xmax-1){
					if(y==0){
						maze[x][y]="1100";
					}
					else if(y==ymax-1){
						maze[x][y]="0110";
					}
					else{
						maze[x][y]="0100";
					}
				}
				else if(y==0){
					maze[x][y]="1000";
				}
				else if(y==ymax-1){
					maze[x][y]="0010";
				}
				else{
					maze[x][y]="0000";
				}
			}
		}
	}

	/*
	 * puts a wall in the maze
	 * @param x1 the start of the wall X
	 * @param y1 the start of the wall Y
	 * @param x2 the end of the wall X
	 * @param y2 the end of the wall Y
	 */
	private void putWall(int x1,int y1,int x2, int y2){
		char dir;
		if(x1==x2){dir='v';}
		else if(y1==y2){dir='h';}
		else{return;}
		 
		if(dir=='v'){
			for(int c=y1;c<=y2;c++){
				maze[x1][c]=add(maze[x1][c], "0001");
			}
		}
		if(dir=='h'){
			for(int c=x1;c<=x2;c++){
				maze[c][y1]=add(maze[c][y1], "1000");
			}
		}		
	}
	/*
	 * combines two strings so no data is lost
	 * @ param str1 is the first sting to be combined 
	 * @ param srt2 is the second string to be combined 
	 */	
	private String add(String str1, String str2){
		char[] fin={'0','0','0','0'};
		char[] srt1c = str1.toCharArray();
		char[] srt2c = str2.toCharArray();
		
		for(int i=0; i<str1.length();i++){
			if(srt1c[i]=='1' || srt2c[i]=='1'){
				fin[i]='1';
			}		
		}
		String output = new String(fin);
		return output;
	}
	
	/*
	 * places puck in the maze array at
	 * @param x the x value of the puck
	 * @param y the y value of the puck
	 */
	public  void placePuck(int x,int y){
		maze[x][y]="p".concat(maze[x][y]);
	}
	
	//TODO possibly update this so that it searches for pucks based on there value
	/*
	 * Returns the closest puck to the the arguments  
	 * will return answer in for of x,y in an array
	 * @param where to start the search X
	 * @param where to start the search Y
	 */
	public static int[] cloesetPuck(int x,int y){
		int[] cloest= {0,0};
		int minDist=200;
		for(int i=0; i<ymax;i++){
			for(int j=0; j<xmax;j++){
				if(maze[j][i].contains("p")){
					int deltaax = Math.abs(j-x);
					int deltaay = Math.abs(i-y);
					int dist =(int) Math.sqrt((deltaay^2+deltaax^2));
					if(dist<minDist){
						minDist=dist;
						cloest[0]=j;
						cloest[1]=i;
					}
					}
				}
			}
		return cloest;
	}
	
	
}
