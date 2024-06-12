package org.game.snakeandladder.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {
    private String name;
    private int position;
    private boolean won;

    public Player(String name) {
        this.name = name;
        position = 0;
        won = false;
    }
}
