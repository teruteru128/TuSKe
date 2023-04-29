package com.github.tukenuke.tuske.conditions;

import com.github.tukenuke.tuske.util.Registry;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;



import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import com.github.tukenuke.tuske.manager.customenchantment.CEnchant;
import com.github.tukenuke.tuske.manager.customenchantment.CustomEnchantment;

public class CondHasCustom extends Condition{
	static {
		Registry.newCondition(CondHasCustom.class,
				"%itemstack% has [a] custom enchantment [%-customenchantment%]",
				"%itemstack% does(n't| not) [have] custom enchantment [%-customenchantment%]");
	}

	private Expression<ItemStack> i;
	private Expression<CEnchant> ce = null;
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg, int arg1, Kleenean arg2, ParseResult arg3) {
		if ( arg[1] != null)
			this.ce = (Expression<CEnchant>) arg[1];
		this.i = (Expression<ItemStack>) arg[0];
		setNegated(arg1 == 1);
		return true;
	}

	@Override
	public String toString( Event arg0, boolean arg1) {
		return this.i + ((isNegated())? " doesn't":" has") + " custom enchantment";
	}

	@Override
	public boolean check(Event e) {
		CEnchant ce = null;
		if (this.i.getSingle(e) == null)
			return false;
		if (this.ce != null)
			ce = this.ce.getSingle(e);
		ItemStack i = this.i.getSingle(e);
		boolean r = false;
		if (ce != null && CustomEnchantment.getCustomEnchants(i).size() > 0 && CustomEnchantment.getCustomEnchants(i).containsKey(ce.getEnchant())){
			if (ce.getLevel() != 0)
				r = (ce.getEnchant().getLevel(i) == ce.getLevel()) ? true:false;
			else
				r = true;
		}
		else if (ce == null)
			r = !CustomEnchantment.getCustomEnchants(i).isEmpty();
		if (isNegated())
			return !r;
		return r;
	}

}
