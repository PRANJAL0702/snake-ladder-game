package org.game.snakeandladder.models;

import org.apache.commons.lang3.RandomUtils;

import java.util.*;

public class Game {

    private int numberOfSnakes;
    private int numberOfLadders;

    private Queue<Player> players;
    private List<Snake> snakes;
    private List<Ladder> ladders;

    private Board board;
    private Dice dice;

    public Game(int numberOfSnakes, int numberOfLadders, int boardSize) {
        this.numberOfSnakes = numberOfSnakes;
        this.numberOfLadders = numberOfLadders;
        this.players = new ArrayDeque<>();
        board = new Board(boardSize);
        snakes = new ArrayList<>(numberOfSnakes);
        ladders = new ArrayList<>(numberOfLadders);
        dice = new Dice(1,6,2);
        initBoard();
    }

    private void initBoard() {

        for(int i=0; i<numberOfSnakes; i++) {
            Set<String> slSet = new HashSet<>();
            while(true) {
                int snakeStart = RandomUtils.nextInt(board.getStart(), board.getSize());
                int snakeEnd = RandomUtils.nextInt(board.getStart(), board.getSize());
                if(snakeStart >= snakeEnd) {
                    continue;
                }
                String slPair = snakeStart + " "+ snakeEnd;
                if(slSet.contains(slPair)) {
                    continue;
                }
                slSet.add(slPair);
                break;
            }
        }

        for(int i=0; i<numberOfLadders; i++) {
            Set<String> ladderSet = new HashSet<>();
            while(true) {
                int ladderStart = RandomUtils.nextInt(board.getStart(), board.getSize());
                int ladderEnd = RandomUtils.nextInt(board.getStart(), board.getSize());
                if(ladderStart >= ladderEnd) {
                    continue;
                }
                String slPair = ladderStart + " "+ ladderEnd;
                if(ladderSet.contains(slPair)) {
                    continue;
                }
                ladderSet.add(slPair);
                break;
            }
        }
    }

    public void addPlayer(String name) {
        players.add(new Player(name));
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void playGame() {
        while(true) {
            Player player = players.poll();
            assert player != null;
            int currentPosition = player.getPosition();
            int diceRolled = dice.roll();
            int newPosition = currentPosition + diceRolled;

            if(newPosition > board.getEnd()) {
                player.setPosition(player.getPosition());
                players.offer(player);
            }

            else {
                player.setPosition(getNewPosition(newPosition));
                if (player.getPosition() == board.getEnd()) {
                    player.setWon(true);
                    System.out.println("Player " + player.getName() + " Won.");
                } else {
                    System.out.println("Setting " + player.getName() + "'s new position to " + player.getPosition());
                    players.offer(player);
                }
            }

            if(players.size() < 2) break;
        }
    }

    private int getNewPosition(int position) {
        for(Snake snake: snakes) {
            if(snake.getEnd() == position) {
                System.out.println("Snake Bit");
                return snake.getStart();
            }
        }

        for(Ladder ladder: ladders) {
            if(ladder.getStart() == position) {
                System.out.println("Ladder Climbed");
                return ladder.getEnd();
            }
        }
        return position;
    }


}
