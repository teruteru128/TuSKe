package com.github.tukenuke.tuske.effects;

import com.github.tukenuke.tuske.util.Registry;
import org.bukkit.event.Event;
import org.bukkit.permissions.Permission;

import java.util.HashMap;



import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffRegisterPermission extends Effect{
	static {
		Registry.newEffect(EffRegisterPermission.class, "(register|create) master permission %string%");
	}

	private Expression<String> perm;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg, int arg1, Kleenean arg2, ParseResult arg3) {
		perm = (Expression<String>) arg[0];
		
		return true;
	}

	@Override
	public String toString( Event arg0, boolean arg1) {
		return "register master permission";
	}

	@Override
	protected void execute(Event e) {
		if (perm.getSingle(e) != null)
			Permission.loadPermission(perm.getSingle(e), new HashMap<String,Object>());		
	}

}
