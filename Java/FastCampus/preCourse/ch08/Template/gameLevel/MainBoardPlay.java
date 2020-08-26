package com.company.ch08.Template.gameLevel;

public class MainBoardPlay {
    public static void main(String[] args) {
        Player player = new Player();
        player.play(1);

        PlayerLevel alevel = new AdvancedLevel();
        player.upgradeLevel(alevel);
        player.play(2);

        PlayerLevel slevel = new SuperLevel();
        player.upgradeLevel(slevel);
        player.play(3);

    }
}
