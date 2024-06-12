package org.game.snakeandladder.models;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomUtils;

@Getter
@Setter
public class Dice {
    private int minValue;
    private int maxValue;
    private int currentValue;

    public Dice(int minValue, int maxValue, int currentValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.currentValue = currentValue;
    }

    public int roll() {
        return RandomUtils.nextInt(minValue, maxValue + 1);
    }
}
