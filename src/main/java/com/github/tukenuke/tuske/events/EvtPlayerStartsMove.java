package com.github.tukenuke.tuske.events;

import org.bukkit.event.Event;



import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import com.github.tukenuke.tuske.listeners.PlayerMovesCheck;

public class EvtPlayerStartsMove extends SkriptEvent{

	@Override
	public String toString( Event arg0, boolean arg1) {
		return "player starts moving";
	}

	@Override
	public boolean check(Event ev) {		
		return true;
	}

	@Override
	public boolean init(Literal<?>[] arg0, int arg1, ParseResult arg2) {
		PlayerMovesCheck.setLoaded(true);
		return true;
	}

}
