package com.github.tukenuke.tuske.hooks.marriage.expressions;

import com.lenis0012.bukkit.marriage2.MPlayer;
import com.github.tukenuke.tuske.util.Registry;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.lenis0012.bukkit.marriage2.MarriageAPI;



import ch.njol.skript.expressions.base.SimplePropertyExpression;

public class ExprPartnerOf extends SimplePropertyExpression <Player, OfflinePlayer>{
	static {
		Registry.newProperty(ExprPartnerOf.class, "partner", "player");
	}

	@Override
	public Class<OfflinePlayer> getReturnType() {
		return OfflinePlayer.class;
	}

	@Override
	
	public OfflinePlayer convert(Player p) {
		MPlayer mp = MarriageAPI.getInstance().getMPlayer(p.getUniqueId());
		if (mp != null && mp.isMarried())
			return Bukkit.getOfflinePlayer(mp.getPartner().getUniqueId());
		return null;
	}

	@Override
	protected String getPropertyName() {
		return "partner";
	}

}
