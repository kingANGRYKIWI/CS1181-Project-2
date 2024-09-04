import java.util.Random;

public class Computer {//Logan Current, CS-1181L-07, 3/13/2022

    //user won't know that the CPU changes its own percentages based on what the player chose
    private final Random random = new Random();

    private int N_paper = 33;
    private int N_rock = 66;
    private int N_scissors = 99;
    
    //normal generator for the CPU
    public String goNormal(){
        int N_total = N_scissors;
        int rNumber = random.nextInt(N_total);
        String choice = "";
        if(rNumber < N_paper) {//chance number will be below 33
            choice = "paper";
        } else if(rNumber < N_rock) {//above 33, below 66
            choice = "rock";
        } else if(rNumber < N_scissors) {//above 66, below 99
            choice ="scissors";
        }
        return choice;
    }

    private int S_paper = 20;
    private int S_rock = 40;
    private int S_scissors = 60;
    private int S_lizard = 80;
    private int S_spock = 100;

    //generator for the other gamemode
    public String goSheldon(){
        int S_total = S_spock;
        int rNumber = random.nextInt(S_total);
        String choice = "";

        if(rNumber < S_paper){//if the number is below 20
            choice = "paper";
        } else if (rNumber < S_rock){//if the number is above 20, below 40
            choice = "rock";
        } else if(rNumber < S_scissors){//above 40, below 60
            choice = "scissors";
        } else if(rNumber < S_lizard){//above 60,below 80
            choice = "lizard";
        } else if(rNumber < S_spock){//above 80, below 100
            choice = "spock";
        }
        return choice;
    }

    public void addToPaper(String gameModeString){//adds chance to chose paper
        if(gameModeString.equals("normal")){
            N_paper += 9;
            N_rock += 6;
            //caps paper at 42
            if(N_paper > 42){
                N_paper = 42;
            }
            //caps rock at 75
            if(N_rock > 75){
                N_rock =75;
            }
        } else if(gameModeString.equals("sheldon")){
                S_paper += 8;
                S_rock += 4;
                S_scissors +=4;
                S_lizard +=4;
            //caps paper
            if(S_paper > 30){
                S_paper = 30;
            }
            //caps rock
            if (S_rock > 50) {
                S_rock = 50;
            }
            //caps scissors
            if (S_scissors > 70) {
                S_scissors = 70;
            }
            //caps lizard
            if (S_lizard > 90) {
                S_lizard = 90;
            }
        }
    }
    
    public void addToRock(String gameModeString){//adds chance to chose rock 
        if(gameModeString.equals("normal")){
            N_paper -= 6;
            N_rock += 9;
            //floors paper at 24
            if(N_paper < 24){
                N_paper = 24;
            }
            //caps rock at 75
            if(N_rock > 75){
                N_rock =75;
            }
        } else if(gameModeString.equals("sheldon")){
            S_paper -= 4;
            S_rock += 8; 
            S_scissors += 4;
            S_lizard += 4;
            //floors paper
            if(S_paper < 10){
                S_paper = 10;
            }
            //caps rock
            if (S_rock > 50) {
                S_rock = 50;
            }
            //caps scissors
            if (S_scissors > 70) {
                S_scissors = 70;
            }
            //caps lizard
            if (S_lizard > 90) {
                S_lizard = 90;
            }
        }
    }
    
    public void addToScissors(String gameModeString){//adds chance to chose scissors
        if(gameModeString.equals("normal")){
            N_paper -= 6;
            N_rock -= 6;
            //floors paper at 24
            if (N_paper < 24){
                N_paper = 24;
            }
            //floors rock at 57
            if (N_rock < 57){
                N_rock = 57;
            }
        } else if(gameModeString.equals("sheldon")){
            S_paper -= 4;
            S_rock -= 4;
            S_scissors += 8;
            S_lizard += 4;
            //floors paper
            if(S_paper < 10){
                S_paper = 10;
            }
            //floors rock
            if (S_rock < 30) {
                S_rock = 30;
            }
            //caps scissors
            if (S_scissors > 70) {
                S_scissors = 70;
            }
            //caps lizard
            if (S_lizard > 90) {
                S_lizard = 90;
            }
        }
    }
    
    public void addToLizard(String gameModeString){//adds chance to chose lizard
        if(gameModeString.equals("sheldon")){
            S_paper -= 4;
            S_rock -= 4;
            S_scissors -=4;
            S_lizard +=8;
            //floors paper
            if(S_paper < 10){
                S_paper = 10;
            }
            //floors rock
            if (S_rock < 30) {
                S_rock = 30;
            }
            //floors scissors
            if (S_scissors < 50) {
                S_scissors = 50;
            }
            //caps lizard
            if (S_lizard > 90) {
                S_lizard = 90;
            }
        }
    }
    
    public void addToSpock(String gameModeString){//adds chance to chose spock
        if(gameModeString.equals("sheldon")){
            S_paper -= 4;
            S_rock -= 4;
            S_scissors -= 4;
            S_lizard -=4;
            //floors paper
            if(S_paper < 10){
                S_paper = 10;
            }
            //floors rock
            if (S_rock < 30) {
                S_rock = 30;
            }
            //floors scissors
            if (S_scissors < 50) {
                S_scissors = 50;
            }
            //floors lizard
            if (S_lizard < 70) {
                S_lizard = 70;
            }
        }
    }
    
    public void cleanWipe(){ //resets the changed percentages throughout the game
        N_paper = 33;
        N_rock = 66;
        N_scissors = 99;

        S_paper = 20;
        S_rock = 40;
        S_scissors = 60;
        S_lizard = 80;
        S_spock = 100;
    }
}