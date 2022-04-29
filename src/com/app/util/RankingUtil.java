package com.app.util;

import com.app.model.Card;
import com.app.model.Player;
import com.app.model.Ranking;
import com.app.model.Result;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RankingUtil {


    public static void setResult(List<Player> players) {
        for (Player player : players) {
            Result result = isRoyalFlush(player.getCards());
            if (result == null) {
                result = isStraightFlush(player.getCards());
            } if (result == null) {
                result = isFourOfAKind(player.getCards());
            } if (result == null) {
                result = isFullHouse(player.getCards());
            } if (result == null) {
                result = isFlush(player.getCards());
            } if (result == null) {
                result = isStraight(player.getCards());
            } if (result == null) {
                result = isThreeOfAKind(player.getCards());
            } if (result == null) {
                result = isTwoPair(player.getCards());
            } if (result == null) {
                result = isPair(player.getCards());
            }
            player.setResult(result);
        }
    }

    public static Result isRoyalFlush(List<Card> cards) {
        boolean aceExists = false, kingExists = false, queenExists = false, jackExists = false, tenExists = false;
        Map<String, List<Card>> cardsBySuit = cards.stream()
                .collect(Collectors.groupingBy(Card::getSuit));
        List<Card> sameSuitCards = cardsBySuit.values().stream()
                .filter(list -> list.size() == 5)
                .flatMap(list -> list.stream())
                .collect(Collectors.toList());
        if (sameSuitCards.isEmpty())
            return null;
        for (Card c : sameSuitCards) {
            if (c.getFace() == "A") {
                aceExists = true;
            } else if (c.getFace() == "K") {
                kingExists = true;
            } else if (c.getFace() == "Q") {
                queenExists = true;
            } else if (c.getFace() == "J") {
                jackExists = true;
            } else if (c.getFace() == "T") {
                tenExists = true;
            }
        }
        if (aceExists && kingExists && queenExists && jackExists && tenExists) {
            return new Result(Ranking.ROYAL_FLUSH);
        }
        return null;
    }

    public static Result isStraightFlush(List<Card> cards) {
        Map<String, List<Card>> cardsBySuit = cards.stream()
                .collect(Collectors.groupingBy(Card::getSuit));
        List<Card> sameSuitCards = cardsBySuit.values().stream()
                .filter(list -> list.size() == 5)
                .flatMap(list -> list.stream())
                .collect(Collectors.toList());
        if (sameSuitCards.isEmpty()) {
            return null;
        }
        List<Card> sortedCards = sameSuitCards.stream()
                .sorted(Comparator.comparing(Card::getOrder))
                .collect(Collectors.toList());
        if (sortedCards.get(0).getOrder() + 4 == sortedCards.get(4).getOrder()) {
            return new Result(Ranking.STRAIGHT_FLUSH);
        }
        return null;
    }

    public static Result isFourOfAKind(List<Card> cards) {
        Map<String, List<Card>> cardsByFace = cards.stream()
                .collect(Collectors.groupingBy(Card::getFace));
        List<Card> sameFaceCards = cardsByFace.values().stream()
                .filter(list -> list.size() == 4)
                .flatMap(list -> list.stream())
                .collect(Collectors.toList());
        if (!sameFaceCards.isEmpty()) {
            List<Card> remainingCardsOrdered = cards.stream()
                    .sorted(Comparator.comparing(Card::getOrder).reversed())
                    .filter(card -> !sameFaceCards.contains(card))
                    .collect(Collectors.toList());
            return new Result(Ranking.FOUR_OF_A_KIND);
        }
        return null;
    }

    public static Result isFullHouse(List<Card> cards) {
        Map<String, List<Card>> cardsByFace = cards.stream()
                .sorted( (c1, c2) -> ((Integer) c2.getOrder()).compareTo(c1.getOrder()))
                .collect(Collectors.groupingBy(Card::getFace));
        String threesFound = null;
        String twosFound = null;
        for (Map.Entry<String, List<Card>> entrySet : cardsByFace.entrySet()) {
            if ( threesFound == null && entrySet.getValue().size() == 3) {
                threesFound = entrySet.getKey();
            } else if (twosFound == null && entrySet.getValue().size() == 2) {
                twosFound = entrySet.getKey();
            }
            if (threesFound != null && twosFound != null) {
                break;
            }
        }
        return threesFound != null && twosFound != null ? new Result(Ranking.FULL_HOUSE) : null;


    }

    public static Result isFlush(List<Card> cards) {
        Map<String, List<Card>> cardsByFace = cards.stream()
                .collect(Collectors.groupingBy(Card::getSuit));
        List<Card> flushCards = cardsByFace.values().stream()
                .filter(list -> list.size() == 5)
                .findFirst().orElse(null);
        return flushCards != null ? new Result(Ranking.FLUSH) : null;
    }

    public static Result isStraight(List<Card> cards) {
        List<Card> sortedDistinctCards = cards.stream()
                .sorted(Comparator.comparing(Card::getOrder).reversed())
                .distinct()
                .collect(Collectors.toList());

        int j = 1;
        int seq = 1;
        for(int i = 0 ; i < sortedDistinctCards.size() - 1; i++,j++ ) {
            if (sortedDistinctCards.get(i).getOrder()-1 == sortedDistinctCards.get(j).getOrder()) {
                seq++;
            } else {
                seq = 1;
            }
            if (seq == 5) {
                return new Result(Ranking.STRAIGHT);
            }
        }
        return null;
    }

    public static Result isThreeOfAKind(List<Card> cards) {
        Map<String, List<Card>> cardsByFace = cards.stream()
                .collect(Collectors.groupingBy(Card::getFace));

        List<Card> threeOfAKindList = cardsByFace.values().stream()
                .filter(list -> list.size() == 3)
                .findFirst().orElse(null);

        return threeOfAKindList != null ? new Result(Ranking.THREE_OF_A_KIND) : null;
    }

    public static Result isTwoPair(List<Card> cards) {
        Map<String, List<Card>> cardsByFace = cards.stream()
                .collect(Collectors.groupingBy(Card::getFace));
        int twoPairsFound = 0;
        for (Map.Entry<String, List<Card>> entrySet : cardsByFace.entrySet()) {
            if ( entrySet.getValue().size() == 2) {
                twoPairsFound++;
            }
        }
        return twoPairsFound == 2 ? new Result(Ranking.TWO_PAIR) : null;
    }

    public static Result isPair(List<Card> cards) {
        Map<String, List<Card>> cardsByFace = cards.stream()
                .collect(Collectors.groupingBy(Card::getFace));
        boolean isPair =  cardsByFace.values().stream()
                .anyMatch(list -> list.size() == 2);
        return isPair ? new Result(Ranking.PAIR) : null;
    }

}
