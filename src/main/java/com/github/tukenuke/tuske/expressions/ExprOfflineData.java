package com.github.tukenuke.tuske.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import com.github.tukenuke.tuske.TuSKe;
import com.github.tukenuke.tuske.util.Registry;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;



import ch.njol.skript.expressions.base.SimplePropertyExpression;

@Name("Player Data")
@Description("Returns a {{types|OfflinePlayer|offline player}} as it was a player, which means that you can get some values like money, inventory, enderchest,etc. Maybe all values doesn't work. If you want to change these values, you will have to {{effects|SavePlayerData|save player data}}.")
@Examples({
		"command /money <offlineplayer>:",
		"\ttrigger:",
		"\t\tif arg is not online:",
		"send \"%money of player data of arg%\""})
@Since("1.1")
public class ExprOfflineData extends SimplePropertyExpression<OfflinePlayer, Player>{
	static {
		Registry.newProperty(ExprOfflineData.class, "player data", "offlineplayer");
	}

	@Override
	public Class<? extends Player> getReturnType() {
		return Player.class;
	}

	@Override
	
	public Player convert(OfflinePlayer p) {
		if (!p.isOnline()){
			Player player = TuSKe.getNMS().getToPlayer(p);
			if ( player != null){
				return player;
			}
		}
		return null;
	}

	@Override
	protected String getPropertyName() {
		return "player data";
	}

}
