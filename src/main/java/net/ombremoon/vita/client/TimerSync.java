package net.ombremoon.vita.client;

import net.ombremoon.vita.util.DeathTimerUtil;

public class TimerSync {
    private static int playerDeathTime;
    private static boolean isCreative;

    public static void setPlayerDeathTime(int playerDeathTime) {
        TimerSync.playerDeathTime = playerDeathTime;
    }

    public static int getPlayerDeathTime() {
        return playerDeathTime;
    }

    public static void setIsCreative(boolean isCreative) {
        TimerSync.isCreative = isCreative;
    }

    public static boolean isCreative() {
        return isCreative;
    }

    public static int getScaledTimer() {
        int currentTime = playerDeathTime;
        int maxTime = DeathTimerUtil.MAX_TIME;
        int barSize = 145;

        return currentTime != 0 ? currentTime * barSize / maxTime : 0;
    }
}
