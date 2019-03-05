
//clears Starting Bet value to nothing.
//hides the results tables
//hides the Play Again? playButton
//shows the Play Button
//have field ready to be entered again
function resetForm() {
    document.forms["bets"]["startingBet"].value = "";
    document.getElementById("showResults").style.display = "none";
    document.getElementById("resetButton").style.display = "none";
    document.getElementById("playButton").style.display = "block";
    document.forms["bets"]["startingBet"].focus();
}

function validateItems() {
      var startBett = document.forms["bets"]["startingBet"].value;
      var money = document.forms["bets"]["startingBet"].value;
      //if statement to see if the value is valid else continue game
      if (money == "" || isNaN(money) || money<=0) {
          alert("Starting bet has to be greater than $0.00 or a valid input.");
          document.forms["bets"]["startingBet"].parentElement.className = "form-group has-error";
          document.forms["bets"]["startingBet"].focus();
          return false;
      } else {
          //function for rolling the dice
          function rollDice() {
              return Math.floor(Math.random() * 6) + 1;
          }
          //three variables to keep track for the results table
          var rolls = 0;
          var max = money;
          var rollsAtMax = 0
          //while loop for the game
          while (money > 0) {
              //console.log(money); to check/troubleshoot if the loop is working as intended
              var firstDice = rollDice();
              var secondDice = rollDice();
              rolls++;
              //if statement to see if the results of dice are 7 to add $4 else substract $1
              if (firstDice + secondDice == 7) {
                  money = money + 4;
                  //if statement to keep track of the max amt held my player at that time
                  if (money > max){
                      max = money;
                      rollsAtMax = rolls;
                  }
              } else {
                  money = money - 1;
              }

          }

      }
   //once money reaches < $0, results table and "play again?"" button will show up, while the "Play" button is hidden
   //the table is filled with the corresponding variables
   document.getElementById("showResults").style.display = "block";
   document.getElementById("playButton").style.display = "none";
   document.getElementById("resetButton").style.display = "block";
   document.getElementById("startingBett").innerText = "$"+startBett;
   document.getElementById("totalRolls").innerText = rolls;
   document.getElementById("maxMoney").innerText = "$"+max;
   document.getElementById("totalRollsAtMax").innerText = rollsAtMax;
   return false;
}
