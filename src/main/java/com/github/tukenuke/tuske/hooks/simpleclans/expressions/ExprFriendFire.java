package com.github.tukenuke.tuske.hooks.simpleclans.expressions;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;



import ch.njol.skript.classes.Changer;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;

public class ExprFriendFire extends SimplePropertyExpression <Player, Boolean>{

	@Override
	public Class<? extends Boolean> getReturnType() {
		return Boolean.class;
	}

	@Override

	public Boolean convert(Player p) {
		ClanPlayer cp = SimpleClans.getInstance().getClanManager().getClanPlayer(p);
		if (cp != null){
			return cp.isFriendlyFire();
		}
		return false;
	}

	@Override
	protected String getPropertyName() {
		return "friend fire";
	}
	
	public void change(Event e, Object[] delta, Changer.ChangeMode mode){
	    Object[] pl = getExpr().getArray(e);
	    Player p = (Player) pl[0];
		if (mode == ChangeMode.SET){
			ClanPlayer cp = SimpleClans.getInstance().getClanManager().getClanPlayer(p);
			if (cp != null)
				cp.setFriendlyFire((Boolean) delta[0]);
		}
			
	}
	@SuppressWarnings("unchecked")
	public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
		if (mode == ChangeMode.RESET || mode == ChangeMode.DELETE)
			return CollectionUtils.array(Boolean.class);
		return null;
	}


}
