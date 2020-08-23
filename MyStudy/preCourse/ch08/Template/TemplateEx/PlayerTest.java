package com.company.ch08.Template.TemplateEx;

public class PlayerTest {
    public static void main(String[] args) {
        Player lowPlayer = new LowLevelPlayer();
        Player midPlayer = new MidLevelPlayer();
        Player highPlayer = new HighLevelPlayer();

        lowPlayer.go(1);

        midPlayer.go(2);

        highPlayer.go(3);
    }

}
