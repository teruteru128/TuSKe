package com.github.tukenuke.tuske.hooks.landlord.expressions;

import com.github.tukenuke.tuske.util.Registry;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;



import com.jcdesimp.landlord.persistantData.LowOwnedLand;

import ch.njol.skript.expressions.base.SimplePropertyExpression;

public class ExprLandOwner extends SimplePropertyExpression<LowOwnedLand, OfflinePlayer>{
	static {
		Registry.newProperty(ExprLandOwner.class, "land[lord] owner", "landclaim");
	}

	@Override
	public Class<? extends OfflinePlayer> getReturnType() {
		return OfflinePlayer.class;
	}

	@SuppressWarnings("deprecation")
	@Override
	
	public OfflinePlayer convert(LowOwnedLand ol) {
		return Bukkit.getOfflinePlayer(ol.getOwnerUsername());
	}

	@Override
	protected String getPropertyName() {
		return "land[lord] owner";
	}

}
