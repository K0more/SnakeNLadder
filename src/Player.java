class Player {
    int playerNumber;
    int currentPosition;
    int diceValue;

    Player(int playerNumber) {
        this.playerNumber = playerNumber;
        this.currentPosition = 1; // Start position changed to 1
        this.diceValue = 0;
    }
}