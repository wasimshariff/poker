package com.app;

import com.app.model.Card;
import com.app.model.Player;
import com.app.util.RankingUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MyPokerHand {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 5 Community Cards:");
        String commData = scanner.nextLine();
        String[] communityCardsArray = commData.toUpperCase().split("\\s+");
        if (communityCardsArray.length != 5) {
            throw new Exception("Incorrect Community Cards Passed");
        }
        List<Card> communityCards = new ArrayList<>();
        for (String communityCard : communityCardsArray) {
            validateInput(communityCard);
            Card c = new Card(String.valueOf(communityCard.charAt(0)), String.valueOf(communityCard.charAt(1)));
            communityCards.add(c);
        }
        System.out.println("Enter Number of Players:");
        int playerCount = scanner.nextInt();
        scanner.nextLine();
        List<Player> players = new ArrayList<>();
        int i =0;
        while (i < playerCount) {
            System.out.println("Enter Player Name followed by 2 cards:");
            String playerData = scanner.nextLine();
            String[] playerDataArray = playerData.toUpperCase().split("\\s+");
            if (playerDataArray.length != 3) {
                throw new Exception("Incorrect Player Data Passed");
            }
            Player player = new Player();
            player.setName(playerDataArray[0]);
            validateInput(playerDataArray[1], playerDataArray[2]);
            Card c1 = new Card(String.valueOf(playerDataArray[1].charAt(0)), String.valueOf(playerDataArray[1].charAt(1)));
            Card c2 = new Card(String.valueOf(playerDataArray[2].charAt(0)), String.valueOf(playerDataArray[2].charAt(1)));
            List<Card> playerCards = new ArrayList<>(Arrays.asList(c1, c2));
            playerCards.addAll(communityCards);
            player.setCards(playerCards);
            players.add(player);
            i++;
        }

        System.out.println("::Running Logic::");

        RankingUtil.setResult(players);

        System.out.println(players);
        List<Player> collect = players.stream()
                .sorted(Comparator.comparing(p -> p.getResult().getRank()))
                .collect(Collectors.toList());
        IntStream.range(0, collect.size()).forEach(index -> {
            Player player = collect.get(index);
            int rank = index+1;
            System.out.println(player.getName() + " " + rank + " " + player.getResult().getRank() + " " + (player.getResult().getCardDesc()));
        });
    }

    private static void validateInput(String ... cardInputs) throws Exception {
        for ( String input : cardInputs) {
            if (!input.matches("^([2-9]|[T,J,Q,K,A])[D,S,C,H]")) {
                throw new Exception("Invalid Card Input: " + input);
            }
        }
    }

}
