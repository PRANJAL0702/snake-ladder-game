package org.game.snakeandladder.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Board {
    int start;
    int end;
    int size;

    public Board(int size) {
        this.size = size;
        start = 1;
        end = start + size -1;
    }

    public Board(int start, int end, int size) {
        this.start = start;
        this.end = end;
        this.size = size;
    }
}
