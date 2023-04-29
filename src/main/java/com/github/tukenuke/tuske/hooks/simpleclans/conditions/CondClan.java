package com.github.tukenuke.tuske.hooks.simpleclans.conditions;

import com.github.tukenuke.tuske.util.Registry;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;


import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;

public class CondClan extends Condition{
	static {
		Registry.newCondition(CondClan.class, "%player% (has|have) [a] clan", "%player% (hasn't|doesn't have) [a] clan");
	}
	private Expression<Player> p;
	private boolean neg = false;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg, int arg1, Kleenean arg2, ParseResult arg3) {
		if (arg1 == 1)
			this.neg = true;
		this.p = (Expression<Player>) arg[0];
		return true;
	}

	@Override
	public String toString( Event e, boolean arg1) {
		return this.p + " has[n't] clan";
	}

	@Override
	public boolean check(Event e) {
		if (this.p.getSingle(e) != null){
			ClanPlayer c = SimpleClans.getInstance().getClanManager().getClanPlayer((Player) this.p.getSingle(e));
			if (this.neg)
				return ( c == null);
			return (c != null);
		}
		return false;
	}

}
