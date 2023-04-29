package com.github.tukenuke.tuske.hooks.marriage.conditions;

import com.github.tukenuke.tuske.util.Registry;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.lenis0012.bukkit.marriage2.Marriage;
import com.lenis0012.bukkit.marriage2.MarriageAPI;



import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class CondMarried extends Condition{
	static {
		Registry.newCondition(CondMarried.class, "%player% is married", "%player% is(n't| not) married");
	}
	private Expression<Player> p;
	private int neg;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg, int neg, Kleenean arg2, ParseResult arg3) {
		this.p = (Expression<Player>) arg[0];
		this.neg = neg;
		return true;
	}

	@Override
	public String toString( Event e, boolean arg1) {
		return "married";
	}

	
	public boolean check(Event e) {
		Player p = this.p.getSingle(e);
		Marriage marry = (Marriage) MarriageAPI.getInstance();
		if (this.neg == 0)
			return (marry.getMPlayer(p.getUniqueId()).isMarried());
		return !(marry.getMPlayer(p.getUniqueId()).isMarried());
	}

}
