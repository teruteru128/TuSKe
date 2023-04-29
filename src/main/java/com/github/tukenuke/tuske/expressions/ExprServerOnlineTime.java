package com.github.tukenuke.tuske.expressions;

import ch.njol.skript.doc.NoDoc;
import com.github.tukenuke.tuske.util.Registry;
import org.bukkit.event.Event;


import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.util.Timespan;
import ch.njol.util.Kleenean;

@NoDoc
public class ExprServerOnlineTime extends SimpleExpression<Timespan>{
	static {
		Registry.newSimple(ExprServerOnlineTime.class, "[the] online time of server", "server'[s] online time");
	}

	private static final long time = System.currentTimeMillis();

	@Override
	public Class<? extends Timespan> getReturnType() {
		return Timespan.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		return true;
	}

	@Override
	public String toString( Event arg0, boolean arg1) {
		return "the online time of the server";
	}

	@Override

	protected Timespan[] get(Event e) {
		return new Timespan[]{ new Timespan(System.currentTimeMillis() - time)};
	}

}
