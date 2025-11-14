package com.AttackablePlayerIndicator;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class AttackablePlayerTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(AttackablePlayer.class);
		RuneLite.main(args);
	}
}