package net.ombremoon.vita.client;

import net.ombremoon.vita.util.DeathTimerUtil;

public class TimerSync {
    private static int playerDeathTime;

    public static void setPlayerDeathTime(int playerDeathTime) {
        TimerSync.playerDeathTime = playerDeathTime;
    }

    public static int getPlayerDeathTime() {
        return playerDeathTime;
    }

    public static int getScaledTimer() {
        int currentTime = playerDeathTime;
        int maxTime = DeathTimerUtil.MAX_TIME;
        int barSize = 145;

        return currentTime != 0 ? currentTime * barSize / maxTime : 0;
    }
}
