# The Other 90

## Maximize Your Mentality

This game contains of two game modes, each with three difficulties:

**Sum Elimination**

- Easy
    - Gives 3 numbers and the player must eliminate 1 to achieve the sum
- Medium
    - Gives 4 or 5 numbers and the player must eliminate 1 or 2 to achieve the sum
- Hard
    - Gives 4-6 numbers and the player must eliminate 0-4
    - Also includes negatives

**Word Recollection**

- Easy
    - Common words
    - Lots of time
- Medium
    - Less common words
    - Less time
- Hard
    - Even less common words
    - Even less time
      (flexible on how to figure out these difficulties for now)

The player will be tracked by name and their personal high score on each level will be saved.
People who want to improve their quick addition or memory will use this application.
This project was inspired by the game in Big Brain Academy which my best friend and I played together on the Wii when
we were children. This will serve as a way for us to continue playing the game together now that both of our Wii's have
essentially stopped working.

Words for Word Recollection found on https://github.com/first20hours/google-10000-english.

Logos were both generated using https://aitestkitchen.withgoogle.com/tools/image-fx. The given prompt was, after much
deliberation:
Make a logo for a game called "The Other 90" with the slogan "Maximize Your Mentality". Include both the name and the
tagline in the image . Spell all the words correctly and don't omit any.

## User Stories

1. As a user, I want to be able to choose which game mode and difficulty I am going to play.
2. As a user, I want to be able to see a list of my high scores for each game and difficulty.
3. As a user, I want to be able to see a list of all the words in the "Seen the word game" that I saw in the previous
   round.
4. As a user, I want to be able to have my high score separate from other people's by my name.
5. As a user, I want to be able to be able to actually play the games.
6. As a user, I want to be able to see the time I have for each turn.
7. As a user, I want to be able to see a leaderboard of the top scores.
8. As a user, I want to be able to log into my 'account' and continue working on the same high scores.
9. As a user, I want to be able to reload my data when opening the application.
10. As a user, I want to be able to save my high scores.

# Instructions for Grader

NOTE: Load the high scores before saving in order to make the "adding multiple Xs to a Y" more clear. Additionally,
the "Sum Elimination" game is currently not functional.

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by running the
  application multiple times, loading then saving your account
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by navigating
  through the leaderboards
- You can locate my visual component by looking at the loading screen or top left on the main menu
- You can save the state of my application by hitting the save button on the main menu
- You can reload the state of my application by hitting the load button on the main menu

# Phase 4: Task 2

Sun Mar 31 19:56:40 PDT 2024 <br>
Ethan was added to all Leaderboards <br>
Sun Mar 31 19:56:55 PDT 2024 <br>
Leaderboards were loaded in from file <br>
Sun Mar 31 19:56:55 PDT 2024  <br>
alice was added to all Leaderboards  <br>
Sun Mar 31 19:56:55 PDT 2024 <br>
Leo was added to all Leaderboards <br>
Sun Mar 31 19:56:55 PDT 2024 <br>
John was added to all Leaderboards  <br>
Sun Mar 31 19:56:55 PDT 2024 <br>
Ethan was added to all Leaderboards <br>
Sun Mar 31 19:57:04 PDT 2024 <br>
Ethan had their Word Recollection Hard score updated <br>
Sun Mar 31 19:57:08 PDT 2024 <br>
Leaderboards were saved to file <br>
Sun Mar 31 19:57:15 PDT 2024 <br>
Ethan had their Word Recollection Easy score updated <br>
Sun Mar 31 19:57:43 PDT 2024 <br>
Ethan had their Word Recollection Medium score updated <br>
Sun Mar 31 19:58:05 PDT 2024 <br>
Leaderboards were saved to file <br>

# Phase 4: Task 3

If I was to refactor, I would instead of having the panels get the current player and store it as a field, I would
instead make the current player accessible through the GameGUI instead. This would eliminate a few of the arrows on
the UML diagram.