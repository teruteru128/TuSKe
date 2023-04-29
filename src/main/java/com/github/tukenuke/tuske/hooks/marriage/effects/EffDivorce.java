package com.github.tukenuke.tuske.hooks.marriage.effects;

import com.github.tukenuke.tuske.util.Registry;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.lenis0012.bukkit.marriage2.Marriage;
import com.lenis0012.bukkit.marriage2.MarriageAPI;



import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffDivorce extends Effect{
	static {
		Registry.newEffect(EffDivorce.class, "divorce %player%", "make %player% divorce");
	}

	private Expression<Player> p;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg, int arg1, Kleenean arg2, ParseResult arg3) {
		this.p = (Expression<Player>) arg[0];
		return true;
	}

	@Override
	public String toString( Event e, boolean arg1) {
		return "divorce " + this.p;
	}

	@Override
	protected void execute(Event e) {
		Player p = this.p.getSingle(e);
		Marriage marry = (Marriage) MarriageAPI.getInstance();
		if (p != null && marry.getMPlayer(p.getUniqueId()).isMarried())
			marry.getMPlayer(p.getUniqueId()).divorce();
		
		
	}

}
