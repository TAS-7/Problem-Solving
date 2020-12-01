//building a house solution from marker2.ms.wits.ac.za
//Canadian Computing Competition: 2015, Junior #4
import java.util.*;


public class Building {
	


	public static void main(String [] args){
		Scanner in = new Scanner(System.in);
		int length = Integer.parseInt(in.nextLine()) + 1;
		int numberOfAnimals =  Integer.parseInt(in.nextLine());
		int [] arr = new int[length];

		for(int i=0; i<numberOfAnimals; i++){
			String segment = in.nextLine();
			String [] coordinates = segment.split(" ");
			int init = Integer.parseInt(coordinates[0]);
			int end = Integer.parseInt(coordinates[1]);

			for(int j=init; j<=end; j++){
				arr[j] = -1;
			}

		}

		int max = 0;
		int counter = 0 ;

		for(int i=0; i<length; i++){
			if(arr[i] != -1){
				counter += 1;
				if(counter > max){
					max =counter;
				}
		``}

			else {
				counter = 0;
			}
		}

		System.out.println(max);

	} 
}
