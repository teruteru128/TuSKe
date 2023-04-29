package com.github.tukenuke.tuske.effects.gui;

import com.github.tukenuke.tuske.TuSKe;
import com.github.tukenuke.tuske.util.Registry;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;


import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffUnformatGUI extends Effect{
	static {
		Registry.newEffect(EffUnformatGUI.class,
				"(unformat|remove|clear|reset) [the] gui slot %numbers% of %players%",
				"(unformat|remove|clear|reset) [all] [the] gui slots of %players%");
	}

	private Expression<Player> p;
	private Expression<Number> s;
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg, int arg1, Kleenean arg2, ParseResult arg3) {
		if (arg1 == 1)
			p = (Expression<Player>) arg[0];
		else{
			p = (Expression<Player>) arg[1];
			s = (Expression<Number>) arg[0];
		}
		return true;
	}

	@Override
	public String toString( Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event e) {
		if (p.getArray(e) != null)
			for (Player player : p.getArray(e))
					if (player != null)
						if (s != null){
							for (Number slot : s.getArray(e))
								if (TuSKe.getGUIManager().isGUI(player.getOpenInventory().getTopInventory(), slot.intValue()))
									TuSKe.getGUIManager().remove(player.getOpenInventory().getTopInventory(), slot.intValue());
						} else if (TuSKe.getGUIManager().hasGUI(player.getOpenInventory().getTopInventory()))
								TuSKe.getGUIManager().removeAll(player.getOpenInventory().getTopInventory());
						
		
	}

}
