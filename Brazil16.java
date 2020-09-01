import java.util.*;

class Teams{
	String name;
	String group;
	int level;
	int num;
	
	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}

	Teams(String name, String group, int level, int num ){
		this.name = name;
		this.group = group;
		this.level = level;
		this.num = num;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(String group) {
		this.group = group;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	
	public boolean canPlay(int round, Teams team) {
		boolean canPlay = false;
		
		//teams in the same level in round 16 can play together
		if(round == 0 && this.level == team.level && team.name !=this.name) {
			canPlay = true;
		}
		//teams in the same group but different levels, in the quarter finals can play together
		else if (round == 1 && this.group == team.group && this.level != team.level) {
			canPlay = true;
			
		}
		//teams in group A can only play teams in group B  vice versa
		//& teams in group C can only play teams in group D vice versa, in the semi-finals
		else if (round == 2) {
			if((this.group =="A" && team.group =="B" )||(this.group =="B" && team.group =="A" )) {
				canPlay = true;
			}
			if((this.group =="D" && team.group =="C" )||(this.group =="C" && team.group =="D" )) {
				canPlay = true;
			}
		}
		//teams in group A or B can only play teams in group C or D  vice versa,
		//in the final
		else if (round == 3) {
			if((this.group =="A" && team.group =="C" )||(this.group =="A" && team.group =="D" )) {
				canPlay = true;
			}
			if((this.group =="B" && team.group =="C" )||(this.group =="B" && team.group =="D" )) {
				canPlay = true;
			}
			if((this.group =="C" && team.group =="A" )||(this.group =="C" && team.group =="B" )) {
				canPlay = true;
			}
			if((this.group =="D" && team.group =="A" )||(this.group =="B" && team.group =="B" )) {
				canPlay = true;
			}
		
			
		}
		
		return canPlay;
		
		
	}
	
}
public class Brazil16 {
    
	
	public static double getP(int round, double [][] pWin, double [][]pRound, Teams team, ArrayList<Teams> teams) {
		if(round==0) {
			pRound[team.num][round]=1;
			return 1;
		}
		else if (pRound[team.num][round] != 0) {
			return pRound[team.num][round];
		}
		
		else {
			double probability = 0;
			for (Teams t: teams) {
				if(t.canPlay(round-1, team) && round >0) {
					probability += getP(round-1, pWin,pRound,team, teams) *(
							getP(round-1, pWin,pRound,t, teams)*pWin[team.num][t.num]);
							
				}
				
			}
			pRound[team.num][round] = probability;
			return probability;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Teams> teams = new ArrayList<Teams>();
		double [][] pWin = new double [16][16];
		double [][] pRound = new double [16][5];
		
		Scanner in = new Scanner(System.in);
		for(int i=0; i<32; i++) {
			//reading in team names
			String name = in.nextLine();
			if(i <16) {
				Teams team;
				if(i <2) {
					team = new Teams (name,"A",1,i);
				}
				else if (i>=2 && i<4) {
					team = new Teams (name,"A",2,i);
				}
				else if (i>=4 && i<6) {
					team = new Teams (name,"B",3,i);
				}
				else if (i>=6 && i<8) {
					team = new Teams (name,"B",4,i);
				}
				else if (i>=8 && i<10) {
					team = new Teams (name,"C",5,i);
				}
				else if (i>=10 && i<12) {
					team = new Teams (name,"C",6,i);
				}
				else if (i>=12 && i<14) {
					team = new Teams (name,"D",7,i);
				}
				else {
					team = new Teams (name,"D",8,i);
				}
				teams.add(team);
			}
			//reading in the given probabilities
			else {
				String [] teamProbs = name.split(" ");
				for(int j=0; j<16; j++) {
					pWin [i-16][j]=(double)Integer.parseInt(teamProbs[j])/100;
				}
			}
		}
		
		in.close();
		for(Teams team : teams) {
			double prob = getP(4, pWin, pRound, team,teams);
		}
		
		for(int i=0; i<16; i++) {
			double p = pRound [i][4]*100;
			System.out.println(teams.get(i).name + "  "+"p="+ String.format("%.2f", p)+"%");
		}
		
		

	}

}
