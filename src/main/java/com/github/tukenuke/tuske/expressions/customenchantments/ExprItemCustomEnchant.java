package com.github.tukenuke.tuske.expressions.customenchantments;

import com.github.tukenuke.tuske.util.Registry;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;


import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.github.tukenuke.tuske.manager.customenchantment.CEnchant;
import com.github.tukenuke.tuske.manager.customenchantment.EnchantManager;

public class ExprItemCustomEnchant extends SimpleExpression<ItemStack>{
	static {
		Registry.newSimple(ExprItemCustomEnchant.class, "%itemstack% with custom enchantment[s] %customenchantments%");
	}

	private Expression<CEnchant> ce;
	private Expression<ItemStack> i;
	@Override
	public Class<? extends ItemStack> getReturnType() {
		return ItemStack.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg, int arg1, Kleenean arg2, ParseResult arg3) {
		this.ce = (Expression<CEnchant>) arg[1];
		this.i = (Expression<ItemStack>) arg[0];
		return true;
	}

	@Override
	public String toString( Event arg0, boolean arg1) {
		return this.i + " with custom enchantments " + this.ce;
	}

	@Override
	
	protected ItemStack[] get(Event e) {
		CEnchant[] ce = this.ce.getArray(e);
		ItemStack i = this.i.getSingle(e).clone();
		if (ce != null && i != null){
			for (int x = 0; x < ce.length; x++){
				if (ce[x].getLevel() == 0)
					EnchantManager.addToItem(i, ce[x].getEnchant(), 1, true);
				else
					EnchantManager.addToItem(i, ce[x].getEnchant(), ce[x].getLevel(), true);
			}
		}
		return new ItemStack[]{i};
	}

}
