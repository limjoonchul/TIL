package com.company.Tictacto;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Player playerOne = new HumanPlayer();
        Player playerTwo = new HumanPlayer();

        playerOne.setName("Jack");
        playerTwo.setName("Jill");

        TicTacToe ticTacToe = new TicTacToe(playerOne,playerTwo);

        Scanner scanner = new Scanner(System.in);

        System.out.printf("How many wins to finish the game? :");
        int N = scanner.nextInt();

        while(playerOne.getTotalWin() < N && playerTwo.getTotalWin() < N){
            Player player = Math.random() > 0.5 ? playerOne : playerTwo;
            ticTacToe.initialize();

            System.out.printf("-- Current Score --");
            System.out.printf(" %s %d : %d %s\n",playerOne.getName(),playerOne.getTotalWin(),
                    playerTwo.getTotalWin(),playerTwo.getName());
            System.out.printf("-------------------");


            while(ticTacToe.observe().getWinnerStatus() == Board.WinnerStatus.NOT_FINISHED){
                System.out.println();
                System.out.println("-- Current Board Stus --");
                ticTacToe.observe().showBoard();
                System.out.printf("--------------------------");

                player.play();
                player = player == playerOne ? playerTwo : playerOne;
            }

            System.out.println();
            System.out.println("-- Final Board Status --");
            ticTacToe.observe().showBoard();
            System.out.printf("-------------------------");

            switch (ticTacToe.observe().getWinnerStatus()){
                case PLAYER_ONE:
                    playerOne.win();
                    System.out.printf("%s has won!\n", playerOne.name);
                    break;

                case PLAYER_TWO:
                    playerTwo.win();
                    System.out.printf("%s has won!\n",playerTwo.name);
                    break;

                default:
                    System.out.println("The game ended tie.");
                    break;
            }
            System.out.println();
        }

        System.out.println("-- Final Score --");
        System.out.printf(" %s %d : %d %s\n", playerOne.getName(), playerOne.getTotalWin(),
                playerTwo.getTotalWin(),playerTwo.getName());
        System.out.println("-----------------");
        System.out.println();
    }
}
