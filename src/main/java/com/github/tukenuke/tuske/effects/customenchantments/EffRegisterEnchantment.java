package com.github.tukenuke.tuske.effects.customenchantments;

import com.github.tukenuke.tuske.manager.customenchantment.AcceptedItems;
import com.github.tukenuke.tuske.manager.customenchantment.CustomEnchantment;
import com.github.tukenuke.tuske.manager.customenchantment.EnchantManager;
import com.github.tukenuke.tuske.util.Registry;
import org.bukkit.event.Event;

import java.util.ArrayList;
import java.util.List;



import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import com.github.tukenuke.tuske.manager.customenchantment.EnchantConfig;

public class EffRegisterEnchantment extends Effect{
	static {
		Registry.newEffect(EffRegisterEnchantment.class, "(register|create) [a] [new] [custom] enchantment with id [name] %string%");
	}

	private Expression<String> id;
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg, int arg1, Kleenean arg2, ParseResult arg3) {
		id = (Expression<String>) arg[0];
		return true;
	}

	@Override
	public String toString( Event arg0, boolean arg1) {
		return "register new custom enchantment with id " + this.id;
	}

	@Override
	protected void execute(Event e) {
		String id = this.id.getSingle(e);
		if (id != null && !EnchantManager.isCustomByID(id) && !EnchantManager.isCustomByName(id)){
			List<AcceptedItems> ac = new ArrayList<AcceptedItems>();
			ac.add(EnchantConfig.ACCEPTED_ITEMS);
			CustomEnchantment.registerNewEnchantment(
				id,
				id,
				EnchantConfig.DEFAULT_NUMBER,
				EnchantConfig.DEFAULT_NUMBER,
				ac,
				EnchantConfig.ENABLED, 
				EnchantConfig.ENABLED_ON_ANVIL, 
				new String[]{""});
			EnchantConfig.y.set("Enchantments." + id + ".Name", id);
			EnchantConfig.save(); 
			
				
		}
		
	}

}
