//minimum square from marker2.ms.wits.ac.za
//Canadian Computing Competition: 2015, Junior #2


import java.util.Scanner;

class Point {
    int x;
		int y;
		
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
}

public class Program {
	
	
	public static int area(Point a, Point b) {
		int x =Math.abs(a.x - b.x);
		int y =Math.abs(a.y - b.y);

		return (int)Math.pow((int)Math.max(x,y), 2);
    
  }
	
	public static int simpleClosestPair(Point [] P) {
		int d=-1, tmp;
		
		for(int i=0; i<P.length-1; i++) {
			for (int j=i+1; j<P.length; j++) {
				 tmp =area(P[i], P[j]);
				
				if (tmp <d || d<0) {
						d = tmp;
					}


			}
		}
		return d;
	}
  
	public static  void main(String []args){
		Scanner in = new Scanner(System.in);
		int n, x, y;
		n =in.nextInt();
		Point [] P = new Point [n];
		
		for(int i=0; i<n ; i++) {
			x =in.nextInt();
		    y =in.nextInt();
			P[i]= new Point(x, y);
		}
		in.close();
		
		int d = simpleClosestPair(P);
		System.out.println(d);

		
	}
}
