package com.github.tukenuke.tuske.conditions;

import com.github.tukenuke.tuske.listeners.PlayerMovesCheck;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;


import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import com.github.tukenuke.tuske.util.PlayerMoves;

public class CondIsMoving extends Condition{

	private Expression<Player> p;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg, int arg1, Kleenean arg2, ParseResult arg3) {
		this.p = (Expression<Player>) arg[0];
		PlayerMovesCheck.setLoaded(true);
		if (arg1 > 0)
			setNegated(true);
		return true;
	}

	@Override
	public String toString( Event e, boolean arg1) {
		return this.p + " is moving";
	}

	@Override
	public boolean check(Event e) {
		if (this.p.getSingle(e) != null){
			PlayerMoves pm = PlayerMoves.getPlayerM(this.p.getSingle(e));
			if (isNegated())
				return pm.isStopped();
			return !pm.isStopped();
			
		}
		return false;
	}

}
