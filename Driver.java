import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Driver {//Logan Current, CS-1181L-07, 3/13/2022

    private final JPanel mainScreen;
    private final JFrame frame;
    private JPanel mainScreenPanel;
    private JPanel scoreJPanel;
    private JLabel playeArea;
    private JLabel drawArea;
    private JLabel computerArea;
    private JRadioButton normalGameButton;
    private JRadioButton sheldonGameButton;
    private String gameModeString = "";
    private Computer CPU = new Computer();
    private String playerChoice = "";
    private String computerChoice = "";
    private int playerScore = 0;
    private int computerScore = 0;
    private int draws = 0;

    public Driver() {
        frame = new JFrame("Rock, Paper, Scissors"); //whole game frame
        mainScreen = directions(); //main panal
        frame.add(mainScreen); //adds it to the game frame

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(1000,800);
    }

    private JPanel directions() {

        JPanel mainScreenPanel = new JPanel();//made a panel to add everything on in the middle 
        mainScreenPanel.setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel(); //made another one to be able to center everyhting I wanted 
        BoxLayout centerBox = new BoxLayout(centerPanel, BoxLayout.Y_AXIS);
        centerPanel.setLayout(centerBox);
        centerPanel.setMinimumSize(new Dimension(600, 600));
        centerPanel.setMaximumSize(new Dimension(600, 600));

        //first text that tells the player thier goal and how to play
        String M_String1 = "<html>The goal is simple! <br/>Click an option to beat your opponent, good luck!...<html>";
        JLabel directionsMessage1 = new JLabel("<html><div style='text-align: center;'>" + M_String1 + "</div></html>");
        directionsMessage1.setFont(new Font("Serif", Font.PLAIN, 30)); 

        //another text box to tell the player a little bit about the game modes
        String M_String2 = "<html>Your opponent is different depending on the gamemode<br/><html>";
        JLabel directionsMessage2 = new JLabel("<html><div style='text-align: center;'>" + M_String2 + "</div></html>");
        directionsMessage2.setFont(new Font("Serif", Font.ITALIC, 30));

        //warns player about lost progress while switching
        String M_String3 = "<html>Your score will reset if you decide to switch game modes<html>";
        JLabel directionsMessage3 = new JLabel("<html><div style='text-align: center;'>" + M_String3 + "</div></html>");
        directionsMessage3.setFont(new Font("Serif", Font.BOLD, 24));
        directionsMessage3.setBorder(new EmptyBorder(0,40,0,0));

        //add it all onto one panel to make it nice and lined up and the adds that to the centerPanel to put at the top of the screen
        JPanel directionsMessagePanel = new JPanel();
        directionsMessagePanel.setLayout(new BorderLayout());
        directionsMessagePanel.setBorder(new EmptyBorder(0,165,0,0));
        centerPanel.add(directionsMessagePanel, BorderLayout.CENTER);
        centerPanel.add(directionsMessagePanel, BorderLayout.NORTH);

        directionsMessagePanel.add(directionsMessage1, BorderLayout.CENTER);
        directionsMessagePanel.add(directionsMessage1, BorderLayout.NORTH);
        directionsMessagePanel.add(directionsMessage2, BorderLayout.CENTER);
        directionsMessagePanel.add(directionsMessage3, BorderLayout.SOUTH);

        //made radio buttons for the game modes
        JPanel radioButtonPanel = new JPanel();
        radioButtonPanel.setLayout(new GridBagLayout());
        radioButtonPanel.setBorder(new EmptyBorder(0,0,0,0));
        centerPanel.add(radioButtonPanel, BorderLayout.CENTER);

        //group makes it that the user can only select one option
        ButtonGroup group = new ButtonGroup();
        //the radio buttons with the label of which choice is which
        normalGameButton = new JRadioButton("3 Choices: R, P, S           ");
        sheldonGameButton= new JRadioButton("5 choices: R, P, S, L, S");
        group.add(normalGameButton);
        group.add(sheldonGameButton);
        normalGameButton.setFont(new Font("Serif", Font.PLAIN, 25));
        sheldonGameButton.setFont(new Font("Serif", Font.PLAIN, 25));
        normalGameButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        sheldonGameButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //add both buttons to the panel to make it centered
        radioButtonPanel.add(normalGameButton);
        radioButtonPanel.add(sheldonGameButton);
        

        //start button to go to the game
        JButton dButton = new JButton("Start!");
        dButton.addActionListener(e -> {
            //pops up directions in a options pane and make the desired game
            if(normalGameButton.isSelected()){
                JOptionPane.showMessageDialog(frame, "<html> Scissors cuts Paper <br/> Paper covers Rock <br/> and Rock crushes Scissors <html>");
                gameModeString = "normal";
                frame.getContentPane().remove(mainScreenPanel);
                frame.getContentPane().add(gameJPanel());
                frame.revalidate();
                frame.repaint();

            } else if(sheldonGameButton.isSelected()){
                JOptionPane.showMessageDialog(frame, "<html> Scissors cuts Paper <br/> Paper covers Rock <br/> Rock crushes Lizard <br/> Lizard poisons Spock <br/> Spock smashes Scissors <br/> Scissors decapitates Lizard <br/> Lizard eats Paper <br/> Paper disproves Spock <br/> Spock vaporizes Rock <br/> and as it always has been, <br/> Rock crushes Scissors <html>");
                gameModeString = "sheldon";
                frame.getContentPane().remove(mainScreenPanel);
                frame.getContentPane().add(gameJPanel());
                frame.revalidate();
                frame.repaint();

            } 
		});
        dButton.setFont(new Font("Serif", Font.PLAIN, 25));
        dButton.setSize(10, 50);
        dButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        //exits the user out of the program entirly 
        JButton exitButton = new JButton("Exit!");
        exitButton.addActionListener(e -> System.exit(0));
        exitButton.setFont(new Font("Serif", Font.PLAIN, 25));
        exitButton.setSize(10, 50);
        exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        //add both buttons to the panel to make them centered
        JPanel dButtonPanel = new JPanel();
        dButtonPanel.setLayout(new BorderLayout());
        dButtonPanel.setBorder(new EmptyBorder(0, 330, 0, 330));

        centerPanel.add(dButtonPanel);
        dButtonPanel.add(dButton,BorderLayout.CENTER);
        dButtonPanel.add(dButton,BorderLayout.NORTH);
        dButtonPanel.add(exitButton, BorderLayout.SOUTH);
        

        mainScreenPanel.add(centerPanel, BorderLayout.CENTER);
        return mainScreenPanel;
    }

    private JPanel normalGame(){
        //sets up the player if the normal 3 option game of RPS 
        JPanel playerCardPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(1, 3, 50, 0);
        playerCardPanel.setLayout(gridLayout);
        playerCardPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        
        //rock button that adds an image then prompts the game to check who won, pups up results in the end after it's done 
        JButton rockJButton =  newButton("rock_300x330.jpg");
        rockJButton.addActionListener(e->{
            playerChoice = "rock";
            computerChoice = CPU.goNormal();
            results();
        });
        //paper button that adds an image then prompts the game to check who won, pups up results in the end after it's done 
        JButton paperJButton = newButton("paper_300x330.jpg");
        paperJButton.addActionListener(e->{
            playerChoice = "paper";
            computerChoice = CPU.goNormal();
            results();
        });
        //scissors button that adds an image then prompts the game to check who won, pups up results in the end after it's done 
        JButton scissorsJButton = newButton("scissors_300x330.jpg");
        scissorsJButton.addActionListener(e->{
            playerChoice = "scissors";
            computerChoice = CPU.goNormal();
            results();
        });
        

        playerCardPanel.add(rockJButton);
        playerCardPanel.add(paperJButton);
        playerCardPanel.add(scissorsJButton);

        playerCardPanel.setOpaque(false);

        return playerCardPanel;
    }

    private JPanel sheldonGame(){
        //makes the 5 choice game from the show Big Bang Theory season 2 episode 8
        JPanel playerCardPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(1,5, 25, 0);
        playerCardPanel.setLayout(gridLayout);
        playerCardPanel.setBorder(new EmptyBorder(0, 0, 0, 0));

        //rock button that adds an image then prompts the game to check who won, pups up results in the end after it's done 
        JButton rockJButton =  newButton("rock_280x330.jpg");
        rockJButton.addActionListener(e->{
            playerChoice = "rock";
            computerChoice = CPU.goSheldon();
            results();
        });
        //paper button that adds an image then prompts the game to check who won, pups up results in the end after it's done 
        JButton paperJButton = newButton("paper_280x330.jpg");
        paperJButton.addActionListener(e->{
            playerChoice = "paper";
            computerChoice = CPU.goSheldon();
            results();
        });
        //scissors button that adds an image then prompts the game to check who won, pups up results in the end after it's done 
        JButton scissorsJButton = newButton("scissors_280x330.jpg");
        scissorsJButton.addActionListener(e->{
            playerChoice = "scissors";
            computerChoice = CPU.goSheldon();
            results();
        });
        //lizard button that adds an image then prompts the game to check who won, pups up results in the end after it's done 
        JButton lizardJButton =  newButton("lizard_280x330.jpg");
        lizardJButton.addActionListener(e->{
            playerChoice = "lizard";
            computerChoice = CPU.goSheldon();
            results();
        });
        //spock button that adds an image then prompts the game to check who won, pups up results in the end after it's done 
        JButton spockJButton =  newButton("spock_280x330.jpg");
        spockJButton.addActionListener(e->{
            playerChoice = "spock";
            computerChoice = CPU.goSheldon();
            results();
        });
        //resets the choce after it's done
        playerChoice = "";
        computerChoice = "";

        playerCardPanel.add(rockJButton);
        playerCardPanel.add(paperJButton);
        playerCardPanel.add(scissorsJButton);
        playerCardPanel.add(lizardJButton);
        playerCardPanel.add(spockJButton);

        playerCardPanel.setOpaque(false);
        return playerCardPanel;
    }

    private JButton newButton(String imageURL){
        //made this the copy and paste of all the buttons in the actual game 
        JButton button = new JButton(new ImageIcon(imageURL));
        button.setPreferredSize(new Dimension(220,330));
        button.setBackground(Color.BLACK);
        button.setBorder(new LineBorder(Color.black, 5, false));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }
    
    private JPanel scoreJPanel(){
        //panel to keep track of the user's score
        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new GridLayout(1,3,100,0));
        scorePanel.setBorder(new EmptyBorder(0,0,0,0));
        scorePanel.setPreferredSize(new Dimension(90,90));
        
        //make the labels that adds the score
        playeArea = new JLabel("Player: "+Integer.toString(playerScore)+"\t\t");
        drawArea = new JLabel("Draws: "+Integer.toString(draws)+"\t\t");
        computerArea = new JLabel("CPU: "+Integer.toString(computerScore)+"\t");

        playeArea.setFont(new Font("Serif", Font.BOLD, 25));
        drawArea.setFont(new Font("Serif", Font.BOLD, 25));
        computerArea.setFont(new Font("Serif", Font.BOLD, 25));

        playeArea.setForeground(Color.WHITE);
        drawArea.setForeground(Color.WHITE);
        computerArea.setForeground(Color.WHITE);

        playeArea.setOpaque(false);
        drawArea.setOpaque(false);
        computerArea.setOpaque(false);

        scorePanel.add(playeArea);
        scorePanel.add(drawArea);
        scorePanel.add(computerArea);

        return scorePanel;
    }

    private JPanel computerPanel(){
        //the computer face the player sees
        JPanel computerJPanel = new JPanel();
        computerJPanel.setLayout(new BorderLayout());

        //puts the face on the opponent depending on the game mode
        JPanel computerOpponent = new JPanel();
        if(gameModeString.equals("normal")){
            DisplayImagePanel(computerOpponent, "squirrel_160x230.jpg");
        } else if(gameModeString.equals("sheldon")){
            DisplayImagePanel(computerOpponent, "sheldon_160x230.jpg");
            
        }
        computerOpponent.setOpaque(false);
        computerJPanel.add(computerOpponent);
        return computerOpponent;
    }
    
    private JPanel gameJPanel(){
        //sets the back ground to the game depending on the game mode
        //also the main panel that make everything center
        if(gameModeString.equals("normal")){
            mainScreenPanel = new ImagePanel("woods.jpg");
        } else if(gameModeString.equals("sheldon")){
            mainScreenPanel = new ImagePanel("sheldonSet.jpg");
        }
        
        mainScreenPanel.setLayout(new BorderLayout());

        //center panel that adds everything up and down
        JPanel centerPanel = new JPanel();
        BoxLayout centerBox = new BoxLayout(centerPanel, BoxLayout.Y_AXIS);
        centerPanel.setLayout(centerBox);
        centerPanel.setMinimumSize(new Dimension(600, 600));
        centerPanel.setMaximumSize(new Dimension(600, 600));

        //makes the computer panel and the "face" of the opponent
        JPanel computerPanel = computerPanel();
        //adds it
        centerPanel.add(computerPanel);

        //makes the score board
        scoreJPanel = scoreJPanel();
        //adds it to the panel
        centerPanel.add(scoreJPanel);
        
        //makes the player choices depending on the gamemode and adds the amount of buttons they need
        if(gameModeString.equals("normal")){
            JPanel playerJPanel = normalGame();
            centerPanel.add(playerJPanel);
        } else if(gameModeString.equals("sheldon")){
            JPanel playerJPanel = sheldonGame();
            centerPanel.add(playerJPanel);
        }
        
        //back button in case they want to change gamemode or change thier mind about something
        JButton backButton = new JButton("Back!");
        backButton.addActionListener(e -> {
            //clears all data for the new game
                playerScore = 0;
                draws = 0;
                computerScore = 0;
                //clears the history of the computer
                CPU.cleanWipe();
                frame.getContentPane().remove(mainScreenPanel);
                frame.getContentPane().add(directions());
                frame.revalidate();
                frame.repaint();
        });
        backButton.setFont(new Font("Serif", Font.PLAIN, 25));
        backButton.setSize(10, 50);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        //panel for back button and centers it
        JPanel backButtonPanel = new JPanel();
        backButtonPanel.setLayout(new GridBagLayout());
        backButtonPanel.setBorder(new EmptyBorder(0,0,0,0));
        centerPanel.add(backButtonPanel);
        backButtonPanel.add(backButton);
        
        //everything is able to be seen and seen through
        mainScreenPanel.setOpaque(false);
        centerPanel.setOpaque(false);
        computerPanel.setOpaque(false);
        scoreJPanel.setOpaque(false);
        backButtonPanel.setOpaque(false);

        mainScreenPanel.add(centerPanel, BorderLayout.CENTER);
        return mainScreenPanel;
    }

    private void DisplayImagePanel(JPanel jp, String url) {
        //makes it that I can add picture to the panel I want, in this case I used it for the computer
        JLabel jl=new JLabel();
        jl.setIcon(new javax.swing.ImageIcon(getClass().getResource(url)));
        jl.setPreferredSize(new Dimension(160,230));
        jl.setBorder(new LineBorder(Color.white, 3, false));
        jp.add(jl);
    }
    
    private String logic(String playerChoice, String CPUchoice){
        //stores the result and the opponent's name
        String result = "";
        String opponent = "";
        //names of the opponents
        if(gameModeString.equals("normal")){
            opponent = "Angry Fluffy Tail";
        } else if(gameModeString.equals("sheldon")){
            opponent = "Sheldon";
        }
        //all cases for if the player chose rock
        if(playerChoice.equals("rock")){
            if(CPUchoice.equals("rock")){
                result = opponent+" chose "+CPUchoice+". It's a draw!";
                draws++;
                
            } else if(CPUchoice.equals("paper") || CPUchoice.equals("spock")){
                result = opponent+" chose "+CPUchoice+". You lost!";
                computerScore++;
                
            } else if(CPUchoice.equals("scissors") || CPUchoice.equals("lizard")){
                result = opponent+" chose "+CPUchoice+". You won!";
                playerScore++;
                
            } else {
                result = "Something went wrong, try again!";
            }
            //updates the computer so they can try to beat the player better next time
            CPU.addToPaper(gameModeString);
            CPU.addToSpock(gameModeString);
        }

        //all cases if the user chose paper
        if(playerChoice.equals("paper")){
            if(CPUchoice.equals("rock") || CPUchoice.equals("spock")){
                result = opponent+" chose "+CPUchoice+". You won!";
                playerScore++;
                
            } else if(CPUchoice.equals("paper")){
                result = opponent+" chose "+CPUchoice+". It's a draw!";
                draws++;
            } else if(CPUchoice.equals("scissors") || CPUchoice.equals("lizard")){
                result = opponent+" chose "+CPUchoice+". You lost!";
                computerScore++;
            } else {
                result = "Something went wrong, try again!";
            }
            //updates the computer so they can try to beat the player better next time
            CPU.addToScissors(gameModeString);
            CPU.addToLizard(gameModeString);
        }

        //all cases if the user chose scissors
        if(playerChoice.equals("scissors")){
            if(CPUchoice.equals("rock") || CPUchoice.equals("spock")){
                result = opponent+" chose "+CPUchoice+". You lost!";
                computerScore++;
            } else if(CPUchoice.equals("scissors")){
                result = opponent+" chose "+CPUchoice+". It's a draw!";
                draws++;
            } else if(CPUchoice.equals("paper") || CPUchoice.equals("lizard")){
                result = opponent+" chose "+CPUchoice+". You won!";
                playerScore++;
                
            } else {
                result = "Something went wrong, try again!";
            }//updates the computer so they can try to beat the player better next time
            CPU.addToRock(gameModeString);
            CPU.addToSpock(gameModeString);
        }
        //all cases if the user chose lizard
        if(playerChoice.equals("lizard")){
            if(CPUchoice.equals("paper") || CPUchoice.equals("spock")){
                result = opponent+" chose "+CPUchoice+". You won!";
                playerScore++;
                
            } else if(CPUchoice.equals("lizard")){
                result = opponent+" chose "+CPUchoice+". It's a draw!";
                draws++;
            } else if(CPUchoice.equals("rock") || CPUchoice.equals("scissors")){
                result = opponent+" chose "+CPUchoice+". You lost!";
                computerScore++;
            } else {
                result = "Something went wrong, try again!";
            }
            //updates the computer so they can try to beat the player better next time
            CPU.addToRock(gameModeString);
            CPU.addToScissors(gameModeString);
        }
        //all cases if the user chose spock
        if(playerChoice.equals("spock")){
            if(CPUchoice.equals("rock") || CPUchoice.equals("scissors")){
                result = opponent+" chose "+CPUchoice+". You won!";
                playerScore++;
                
            } else if(CPUchoice.equals("spock")){
                result = opponent+" chose "+CPUchoice+". It's a draw!";
                draws++;
            } else if(CPUchoice.equals("paper") || CPUchoice.equals("lizard")){
                result = opponent+" chose "+CPUchoice+". You lost!";
                computerScore++;
            } else {
                result = "Something went wrong, try again!";
            }
            //updates the computer so they can try to beat the player better next time
            CPU.addToPaper(gameModeString);
            CPU.addToLizard(gameModeString);
        }
        //updates the scoreboard for the user to see
        updateScore();
        //returns the string so the option pane cane tell the user the results
        return result;
    }

    private void results(){
        //takes the string results and puts it into a message dialog so the user can see
        String results = logic(playerChoice, computerChoice);
        JOptionPane.showMessageDialog(frame, results);
        playerChoice = "";
        computerChoice = "";
    }

    private void updateScore(){
        //updates the scoreboard with the right score of everything
        playeArea.setText("Player: "+Integer.toString(playerScore)+"\t\t");
        drawArea.setText("Draws: "+Integer.toString(draws)+"\t\t");
        computerArea.setText("CPU: "+Integer.toString(computerScore)+"\t");
    }

    class ImagePanel extends JPanel {
        //class to make a panel for the background of the main game
        private final Image backImage;
        //gets the image from the filename in the string given
        public ImagePanel(String imageFile) {
            backImage = Toolkit.getDefaultToolkit().createImage(imageFile);
        }
        @Override
        protected void paintComponent(Graphics g) {
            //makes the image and paints it to the right size
            super.paintComponent(g);
            repaint();
            validate();
            //depending on the gamemode it changes
            if(gameModeString.equals("normal")){
            g.drawImage(backImage, -175, 0, 2600, 1080, null); 
            } else if(gameModeString.equals("sheldon")){
            g.drawImage(backImage, -175, 0, 4000, 1080, null);
            }
        }
    }
    public static void main(String[] args) {
        //whole code used to drive the game
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Driver();
            }
        });
    }
}