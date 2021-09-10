$(function() {
    $("button").click(function () {
        let userChoice = $(this).attr('id');
        play(userChoice);
    });
})

function play(userChoice) {
    let oppChoice = Math.random();
    let opponentChoice='';
    if (oppChoice<0.34)
        opponentChoice='rock';
    else if (oppChoice<0.67)
        opponentChoice='paper';
    else
        opponentChoice='scissors';

    if (userChoice === opponentChoice)
        alert( "Its a tie! Try again");
    else if (userChoice == "rock")
        if (opponentChoice == "scissors")
            alert( "Congrats You Won !!! Your opponent had scissors");
        else
            alert("Sorry You Lost !!! Your opponent had Papers");
    else if (userChoice == "paper")
        if (opponentChoice == "rock")
            alert( "Congrats You Won !!! Your opponent had rock");
        else
            alert("Sorry You Lost !!! Your opponent had scissors") ;
    else if (userChoice == "scissors")
        if (opponentChoice == "paper")
            alert("Congrats You Won !!! Your opponent had paper");
        else
            alert("Sorry You Lost !!! Your opponent had rock");
}