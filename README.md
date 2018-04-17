# Poker Fanatic
A Program to engage in poker! This app allows a user to learn how to play poker aginst a Computer with different levels of difficulties.

*Created for CS 4800 - Capstone, [Final Propsal] (https://asulearn.appstate.edu/mod/assign/view.php?id=2244792)*

**Authors:** Maximilian Finnican

**Sources:** Actual Card Art: http://byronknoll.blogspot.com/2011/03/vector-playing-cards.html <br> &emsp; &emsp; Button Css Styling: http://fxexperience.com/2011/12/styling-fx-buttons-with-css/ <br> &emsp; &emsp; Poker Title, Game Help Image, Poker Hand Ranking Image: Maximilian Finnican. (paint) <br> &emsp; &emsp; Button / Crowd Cheering and Booing Sounds: http://soundbible.com/tags-button.html 

---

## Running the Final Project (Luxury Car)
To run the final version of Poker Fanatic! Please download all the required files: All .png .mp3 .css files, in addition to Card.java Deck.java Player.java Opponent.java and PokerTwoGui.java Once Downloaded place within your favorite IDE software or a folder that can combine all the files. Once you have done so the Main Method to call is found within PokerTwoGui.java. So to run the whole project just run PokerTwoGui.java.

---

## Testing the Final Project (Luxury Car)
No Classes have been made to test the final product because it mostly came down to making sure you hear the sounds and see the images. as for the more intelegent AI, I tested that with repitions of games to see the amount of points generated on average and saw good sized jumps from lower to higher levels of intelegence.

---

### Running the Project (Bicycle)
To run the current version of Poker Fanatic! Please download and compile >> javac PokerTwoGui.java then run that particular file to run the most current version of the GUI. You will also need the helper files to be included with that file, (Card.java, Player.java, Opponent.java, Deck.java, Poker.css, Cheetah-card.gif.

---

### Testing the Project (Bicycle)
Most of the testing done has been covered within the scooter, although i did build a sandbox program called testClickCard.java in order to make a demo version of buttons to have different functionalitys for the newest addition of the project. this helped me tremendously.

---

### Running the Project (Scooter)
To run the current version of Poker Fanatic! Please download and compile >> javac PokerGui.java Then run the PokerGui file in your favorite IDE. This version is operated with the mouse and the CTRL button.
Begin the game by clicking "Player Poker!" After that the board should display with your hand delt to you. Next if you would like to give back any cards simply click the cards you would like to give back, if you wish to give back multiple cards hold the CTRL button down and select each card with your mouse once you have the cards selected click "Give Back Cards". Your new cards will be dealt, then once you are ready to see your opponents hand press "flip Cards" a score will be shown on each players side, but to really see who won press the "Show me who Won!" button to see the final results. If you didn't like that game feel free to press "Player Poker!" again and test your luck against my think method!

---

### Running the Project (Skateboard)
To run the poker game download Card.java, Deck.java Player.java Poker.java, Opponent.java. All of these files will be required to run the game in "Console" When the files have been downloaded and placed within your own IDE run the "Poker.java" file to begin the poker game. When playing the game you will be prompted to enter in the amount of cards wanted to give back from your dealt hand, enter in the numbers 0-3, or 4 if you have an Ace in your hand, then you type the rank and suit then hit enter after each card you would like to give back. Once you give back all the cards you do not want the game will give you more cards from the deck then will calculate the winner.

---

### Testing the Project (Skateboard)
Three test classes are provided to test the pokers games base functionality. (testCard.java, testDeck.java, testPlayer.java)
All of these test classes provde that the functions do what they are intended to do, if furthur bug inspection is wanted to be done each test case is built to be customized toward each particular function inside the classes, hopefully making it easier to find tricky bugs within the code. To run all these files download them form the repository place within personal IDE then run each file.(The corresponding class will be needed for testing, I.E. (Card.java, Deck.java, Player.java). as For Opponent.java Testing can be done within the Poker.java file by playing the game and analyizing the computers behavior, futhur test classes will be made if deemed neccisary for these classes.

---

## Project Proposal and Product Backlog

## Metadata section 
Title: Poker Fanatic
Developers: Maximilian Finnican

## Project Overview
Poker Fanatic will satisify all card player's wants by allowing game after game without the hassle of shuffling a real deck of cards. Become a better Poker player by challenging yourself aginst Poker Fanatic's intelegent opponent!
The Features of my product will be implements based on fundamentals of functionality vs attractiveness. Meaning the project will aim for encapsulating all that it takes to have a legitament game of poker take place, then futher improvments will revovle around appearance, and or additional features to the game play.

**Firstly,** the main objective is to have a program that can generate a card, formulate a deck of cards that are all labeled correctly as a normal deck of cards (minus the jokers), then to deal out these cards to the player and the computer. next the game must let the player give back cards he/she do not want then return the same amount of cards back. A player must only be able to give back 3 cards unless he/she has an Ace then that player can give back 4 cards not including the Ace they presented. Finally the program must establish who won the game based off the cards in their respective hands.

**Secondly,** This portion of the project will focus on developing an "Attractive GUI", something that can simulate cards the deck a player and display information about which stage in the game the player is in aswell as show the player who one the game. Also develop a key that shows the player which hands are the best to keep to help them make better decisions when giving back cards.

**Lastly,** Once both of the first programming checkpoints have been met i can then focus on other features such as customizing the AI's intelegence making it either less intelegent or more. Then i would like to add customizations to the player so that he/she could either have their own background color or other customiziable options. lastly i would like to implments a highscore list that shows the players progress.

I have chosen this topic because it hits close to home for me, When i was little my Pop-Pop taught me how to play Poker and it always was a family tradition. So ultimately im very familiar with it's rules. This proved to me a good project idea because i understand how it should be working and just need to implement with code. programming the game seems fairly simple and i plan to have most of my learning curves making the game interactive with a good UI also with the creation of an opponent to play against. I see opportunity in learning how to make UI's and researching algorithms that will make a tough opponent to play against. I also sylfishly see this project making me amazingly good at poker so i will be much better at family events.

This program is a niche program in my opinion it strickly targets people who are interested in card games. If i were to aim and make this more into a commericial project i would look into live Poker playing and impplment a betting system to tap into the Gambling market. I feel the range of potential investors would be people who are looking for a stable app on the market or educational organizations that want to teach people how to player poker better.

## Similiar Exisiting Work
There are many versions of Poker apps along the web, the game itself is very old and people have had alot of time to develop it. Other versions of the game include multiplayer capibilities and online currency trading. My version is realisticly a simpler version of these games but satisfies my need to learn how to construct such a thing from scratch. Online versions give a very cool UI that lets you connect through facebook, or google. So i will try and make the Ui's customizable which would be different from the online's set versions.

## Previous Experience
Software Engineering will allow me to program efficiently and be able to develop stratigies to have sucsessful code. Profecciency in Java will help me with developing the code in general reguardless of the language. The AI part seems pretty similiar to the robot game in 2440 although its a different game. Then creating the GUI reminds me of the calculator we created in 2440 aswell. Data Strucutures will aid my big O calcualtions and in writing efficient algorithms.

## Technology
Java is what i am most comfortable with, so i would use this at first in order to get the program working. To Test my project i can run a Java file to simulate all types of poker hands from a player. Also do basic functions with the Deck. I'll make sure all my libraries are included and that everything is linked correctly by building continously and making simple steps compared to long steps. While coding im sure there will become alot of code, i constantly will be making notes if i can make loops more efficient or encapsulate anything that varies using software engineering principals.

## Risk Areas
Some major Risks im predicting are the construction of a UI, I know i will have to learn alot more about java's Swing library. this should prove to be alot of work and fun once i get the hang out things. I want to also have an inteligent opponent, this will be complicated because i will need to make the computer realize the cards it has and to make the correct decision inorder to win the game. This may make me reseach reinforcment learning patterns which would include writing python for there extensive libraries with machine learning.


