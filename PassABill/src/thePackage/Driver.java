package thePackage;

import java.util.Random;
import stddraw.StdDraw;
import java.util.Scanner;

public class Driver {
	//-------------------------------------------Main Program-----------------------------------------------
	public static void main(String[] args) {
		String running = "y";
		Scanner keyboard = new Scanner(System.in);
		String userParty = " ";
		String presidentParty = " ";
		String pass = " ";
		int demPercentage = 0;
		int repPercentage = 0;
		Driver d = new Driver();
		int theCanvasWidth = 600;
        int theCanvasHeight = 600;
        StdDraw.setCanvasSize(theCanvasWidth, theCanvasHeight);
        StdDraw.setXscale(0.0, theCanvasWidth);
        StdDraw.setYscale(0.0, theCanvasHeight);
		
        //Main sequence of the program
		while (running.equalsIgnoreCase("y")){
			//Setup the party of the user using keyboard input
			System.out.println("Choose a party 'D' or 'R'.");
			userParty = keyboard.next();
			if (!userParty.equalsIgnoreCase("D") && !userParty.equalsIgnoreCase("r")){
				System.out.println("ONLY TYPE IN 'D' or 'R'.");
				userParty = keyboard.next();
			}
			
			//Setup the party of the president using keyboard input
			System.out.println("Enter the president's party: 'D' or 'R'.");
			presidentParty = keyboard.next();
			if (!presidentParty.equalsIgnoreCase("D") && !presidentParty.equalsIgnoreCase("r")){
				System.out.println("ONLY TYPE IN 'D' or 'R'.");
				presidentParty = keyboard.next();
			}
			
			//Sets the percentage of congress that is the user's party
			System.out.println("Enter the percent of your party's representation in congress: 0-100");
			if (userParty.equalsIgnoreCase("D")){
				demPercentage = keyboard.nextInt();
				repPercentage = 100 - demPercentage;
			}
			else {
				repPercentage = keyboard.nextInt();
				demPercentage = 100 - repPercentage;
			}

			//Sequence of the bill going from legislation to a vote in congress
			System.out.println("You present a bill and it is sent to a queue.");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("It is now sent to an appropriate committee to be analyzed and changed.");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("It is now debated on the floor congress.");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Now it is sent for a vote.");
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			pass = d.initialVote(userParty, demPercentage, repPercentage);
			
			//If the vote passes a congressional vote, it is sent to the president to decide on 
			if (pass.equalsIgnoreCase("y")){
				System.out.println("Congratulations your bill passed!");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Now a report is made and the bill is sent to the president.");
				try {
					Thread.sleep(2500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				pass = d.presidentVote(userParty, presidentParty);
				
				//If the president signs it into law
				if (pass.equalsIgnoreCase("y")){
					System.out.println("Congratulations the president signed your bill into law!");
					System.out.println("Go again? 'y' / 'n'");
					running = keyboard.next();
					if (!running.equalsIgnoreCase("y") && !running.equalsIgnoreCase("n")){
						System.out.println("ONLY TYPE IN 'Y' or 'N'.");
						running = keyboard.next();
					}
				}
				//If the president vetoes the user has an option to send to a re-vote needs 2/3 of the vote
				else {
					System.out.println("The president vetoed your Bill.");
					System.out.println("Do you want to send it for another vote Y/N?");
					System.out.println("If it receives 2/3 of the vote it will become law.");
					pass = keyboard.next();
					if (pass.equalsIgnoreCase("Y")) {
						System.out.println("The bill is debated on the floor of congress again and will be voted on.");
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						pass = d.specialVote(userParty, demPercentage, repPercentage);
						
						//If the vote gets 2/3 of the vote
						if (pass.equalsIgnoreCase("y")) {
							System.out.println("You're bill got enough votes now it's a law!");
							System.out.println("Go again? 'y' / 'n'");
							running = keyboard.next();
							if (!running.equalsIgnoreCase("y") && !running.equalsIgnoreCase("n")){
								System.out.println("ONLY TYPE IN 'Y' or 'N'.");
								running = keyboard.next();
							}
						} 
						//If it doesn't get 2/3 of the vote
						else {
							System.out.println("It's the end of the road for your bill you didn't get the votes.");
							System.out.println("Go again? 'y' / 'n'");
							running = keyboard.next();
							if (!running.equalsIgnoreCase("y") && !running.equalsIgnoreCase("n")){
								System.out.println("ONLY TYPE IN 'Y' or 'N'.");
								running = keyboard.next();
							}
						}
					}
					else {
						System.out.println("Go again? 'y' / 'n'");
						running = keyboard.next();
						if (!running.equalsIgnoreCase("y") && !running.equalsIgnoreCase("n")){
							System.out.println("ONLY TYPE IN 'Y' or 'N'.");
							running = keyboard.next();
						}
					}
				}
				
				
			}
			
			//The bill doesn't pass the initial vote
			else {
				System.out.println("Sorry your bill got shut down. Start over 'y' / 'n'");
				running = keyboard.next();
				if (!running.equalsIgnoreCase("y") && !running.equalsIgnoreCase("n")){
					System.out.println("ONLY TYPE IN 'Y' or 'N'.");
					running = keyboard.next();
				}
			}
		}

	}
	//-------------------------------------------Main Program-----------------------------------------------
	
	
	//This function runs the initial vote in the Senate
	public String initialVote(String yourParty, int dPercent, int rPercent){
		int rWith = 75;
		int rAgainst = 10;
		int dWith = 89;
		int dAgainst = 10;
		int voteForD = 0;
		int voteForR = 0;
		int voteAgainstD = 0;
		int voteAgainstR = 0;
		int temp;
		Random rand = new Random();
		String yesNo = null;
		StdDraw.clear();
		
		//Draw the D representation on the canvas
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.filledRectangle(200, 300, 100, dPercent * 2);
		
		//Draw the R representation on the canvas
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledRectangle(400, 300, 100, rPercent * 2);
		
		//-----------------------------------Democrat Bill-------------------------------------------
		if (yourParty.equalsIgnoreCase("D")){
			//Determine how many D vote for/ against the bill
			for (int i = 0; i < dPercent; i++){
				temp = rand.nextInt(100);
				if (temp <= dWith){
					voteForD++;
				}
				else {
					voteAgainstD++;
				}
			}
			//Determine how many R vote for/ against the bill
			for (int i = 0; i < rPercent; i++){
				temp = rand.nextInt(100);
				if (temp <= rAgainst){
					voteForR++;
				}
				else {
					voteAgainstR++;
				}
			}
			//Check if it got the necessary amount of votes to pass
			if (voteForD + voteForR >= 51){
				yesNo = "Y";
			}
			else {
				yesNo = "N";
			}
		}
		
		//-----------------------------------Republican Bill-------------------------------------------
		else if (yourParty.equalsIgnoreCase("R")){
			//Determine how many D vote for/ against the bill
			for (int i = 0; i < dPercent; i++){
				temp = rand.nextInt(100);
				if (temp <= dAgainst){
					voteForD++;
				}
				else {
					voteAgainstD++;
				}
			}
			//Determine how many R vote for/ against the bill
			for (int i = 0; i < rPercent; i++){
				temp = rand.nextInt(100);
				if (temp <= rWith){
					voteForR++;
				}
				else {
					voteAgainstR++;
				}
			}
			//Check if it got enough votes to pass 
			if (voteForR + voteForD >= 51){
				yesNo = "Y";
			}
			else {
				yesNo = "N";
			}
		}
		
		//Draw the number of each party that voted for the bill
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.filledRectangle(200, 300, 100, voteForD * 2);
		StdDraw.filledRectangle(400, 300, 100, voteForR * 2);
		
		//Print out the values of the results
		StdDraw.setPenColor(StdDraw.BLACK);
		String toScreen = "For R: " + voteForR + ", For D: " + voteForD;
		StdDraw.text(300, 580, toScreen);
		toScreen = "Against R: " + voteAgainstR + ", Against D: " + voteAgainstD;
		StdDraw.text(300, 560, toScreen);
		toScreen = "Total For: " + (voteForR + voteForD) + " Total Against: " + (voteAgainstR + voteAgainstD);
		StdDraw.text(300, 50, toScreen);
		
		return yesNo;
	}
	
	//This function is ran if the president shoots down a bill and it goes back for a vote
		public String specialVote(String yourParty, int dPercent, int rPercent){
			int rWith = 75;
			int rAgainst = 10;
			int dWith = 89;
			int dAgainst = 10;
			int voteForD = 0;
			int voteForR = 0;
			int voteAgainstD = 0;
			int voteAgainstR = 0;
			int temp;
			Random rand = new Random();
			String yesNo = null;
			
			StdDraw.clear();
			
			//Draw the D representation
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.filledRectangle(200, 300, 100, dPercent * 2);
			
			//Draw the R representation
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.filledRectangle(400, 300, 100, rPercent * 2);
			
			//-----------------------------------Democrat Bill-------------------------------------------
			if (yourParty.equalsIgnoreCase("D")){
				//Determine how many D vote for/ against the bill
				for (int i = 0; i < dPercent; i++){
					temp = rand.nextInt(100);
					if (temp <= dWith){
						voteForD++;
					}
					else {
						voteAgainstD++;
					}
				}
				//Determine how many R vote for/ against the bill
				for (int i = 0; i < rPercent; i++){
					temp = rand.nextInt(100);
					if (temp <= rAgainst){
						voteForR++;
					}
					else {
						voteAgainstR++;
					}
				}
				//Check if it got enough votes to pass
				if (voteForD + voteForR >= 66){
					yesNo = "Y";
				}
				else {
					yesNo = "N";
				}
			}
			
			//-----------------------------------Republican Bill-------------------------------------------
			else if (yourParty.equalsIgnoreCase("R")){
				//Determine how many D vote for/ against the bill
				for (int i = 0; i < dPercent; i++){
					temp = rand.nextInt(100);
					if (temp <= dAgainst){
						voteForD++;
					}
					else {
						voteAgainstD++;
					}
				}
				//Determine how many R vote for/ against the bill
				for (int i = 0; i < rPercent; i++){
					temp = rand.nextInt(100);
					if (temp <= rWith){
						voteForR++;
					}
					else {
						voteAgainstR++;
					}
				}
				//Check if it got enough votes to pass
				if (voteForD + voteForR >= 66){
					yesNo = "Y";
				}
				else {
					yesNo = "N";
				}
			}
			
			//Draw the number of each party that voted for the bill 
			StdDraw.setPenColor(StdDraw.GREEN);
			StdDraw.filledRectangle(200, 300, 100, voteForD * 2);
			StdDraw.filledRectangle(400, 300, 100, voteForR * 2);
			
			//Print out the data results
			StdDraw.setPenColor(StdDraw.BLACK);
			String toScreen = "For R: " + voteForR + ", For D: " + voteForD;
			StdDraw.text(300, 580, toScreen);
			toScreen = "Against R: " + voteAgainstR + ", Against D: " + voteAgainstD;
			StdDraw.text(300, 560, toScreen);
			toScreen = "Total For: " + (voteForR + voteForD) + " Total Against: " + (voteAgainstR + voteAgainstD);
			StdDraw.text(300, 50, toScreen);
			
			return yesNo;
			
		}
	
	//This function tests whether or not the president passes the bill into law
	public String presidentVote(String yourParty, String presidentParty){
		int rWith = 75;
		int rAgainst = 8;
		int dWith = 89;
		int dAgainst = 8;
		String vote = null;
		int temp;
		Random rand = new Random();
		
		//----------------------------------------------If user is a D-----------------------------------
		if (yourParty.equalsIgnoreCase("D")){
			if (presidentParty.equalsIgnoreCase("D")){
				temp = rand.nextInt(100);
				if (temp <= dWith){
					vote = "Y";
				}
				else {
					vote = "N";
				}
			}
			else {
				temp = rand.nextInt(100);
				if (temp <= rAgainst){
					vote = "Y";
				}
				else {
					vote = "N";
				}
			}
		}
		
		//----------------------------------------------If user is an R-----------------------------------
		else if (yourParty.equalsIgnoreCase("R")){
			if (presidentParty.equalsIgnoreCase("R")){
				temp = rand.nextInt(100);
				if (temp <= rWith){
					vote = "Y";
				}
				else {
					vote = "N";
				}
			}
			else {
				temp = rand.nextInt(100);
				if (temp <= dAgainst){
					vote = "Y";
				}
				else {
					vote = "N";
				}
			}
		}
		return vote;
	}
	

}
