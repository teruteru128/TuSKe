package com.github.tukenuke.tuske.expressions.recipe;

import com.github.tukenuke.tuske.TuSKe;
import com.github.tukenuke.tuske.manager.recipe.RecipeManager;
import com.github.tukenuke.tuske.util.Registry;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;



import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.inventory.ShapedRecipe;

public class ExprItemsOfRecipe extends SimpleExpression<ItemStack>{
	static {
		Registry.newProperty(ExprItemsOfRecipe.class, "[all] ingredients", "recipe");
	}
	private Expression<Recipe> recipe;

	@Override
	public Class<? extends ItemStack> getReturnType() {
		return ItemStack.class;
	}

	@Override
	public boolean isSingle() {
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg, int arg1, Kleenean arg2, ParseResult arg3) {
		recipe = (Expression<Recipe>) arg[0];
		return true;
	}

	@Override
	public String toString( Event e, boolean arg1) {
		return "ingredients of " + recipe.toString(e, arg1);
	}

	@Override
	
	protected ItemStack[] get(Event e) {
		Recipe r = recipe.getSingle(e);
		if (r != null){
			RecipeManager rm = TuSKe.getRecipeManager();
			Recipe r2 = rm.getIfContainsCustomRecipe(r.getResult(), rm.getIngredients(r));
			if (r2 != null)
				r = r2;
			return r instanceof ShapedRecipe ? rm.getShapedIngredients((ShapedRecipe) r) : rm.fixIngredients(rm.getIngredients(r));
		}
		return null;
	}

}
