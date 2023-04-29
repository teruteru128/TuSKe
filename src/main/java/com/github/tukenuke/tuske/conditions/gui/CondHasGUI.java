package com.github.tukenuke.tuske.conditions.gui;

import com.github.tukenuke.tuske.TuSKe;
import com.github.tukenuke.tuske.util.Registry;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;


import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class CondHasGUI extends Condition{
	static {
		Registry.newCondition(CondHasGUI.class, "%player% has [a] gui", "slot %number% of %player% is a gui","%player% does(n't| not) have [a] gui", "slot %number% of %player% is(n't| not) [a] gui");
	}

	private Expression<Player> p;
	private Expression<Number> obj = null;
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg, int arg1, Kleenean arg2, ParseResult arg3) {
		if (arg.length > 1){
			obj = (Expression<Number>) arg[0];
			p = (Expression<Player>) arg[1];
			
		} else {
			p = (Expression<Player>) arg[0];
		}
		setNegated(arg1 > 1);
		return true;
	}

	@Override
	public String toString( Event arg0, boolean arg1) {
		return this.p + "' inventory has a gui";
	}

	@Override
	public boolean check(Event e) {
		boolean r = false;
		if (p.getSingle(e) == null)
			return false;
		if (obj != null){
			if (obj.getSingle(e) == null) 
				return false;
			r = TuSKe.getGUIManager().isGUI(p.getSingle(e).getOpenInventory().getTopInventory(), ((Number)obj.getSingle(e)).intValue());
		} else
			r = TuSKe.getGUIManager().hasGUI(p.getSingle(e).getOpenInventory().getTopInventory());
		if (isNegated())
			r = !r;
		return r;
	}

}
