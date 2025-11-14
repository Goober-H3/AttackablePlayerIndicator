package com.AttackablePlayerIndicator;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("attackableplayerindicator")
public interface AttackablePlayerConfig extends Config
{
    enum CombatRange
    {
        FIVE(5, "+/- 5 levels"),
        TEN(10, "+/- 10 levels"),
        FIFTEEN(15, "+/- 15 levels");

        private final int range;
        private final String displayName;

        CombatRange(int range, String displayName)
        {
            this.range = range;
            this.displayName = displayName;
        }

        public int getRange()
        {
            return range;
        }

        @Override
        public String toString()
        {
            return displayName;
        }
    }

    @ConfigItem(
            keyName = "combatRange",
            name = "Combat Level Range",
            description = "The combat level range for attackable players (+/- from your level)"
    )
    default CombatRange combatRange()
    {
        return CombatRange.FIVE;
    }
}
