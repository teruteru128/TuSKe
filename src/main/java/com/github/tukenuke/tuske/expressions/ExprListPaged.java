package com.github.tukenuke.tuske.expressions;

import com.github.tukenuke.tuske.util.Registry;
import org.bukkit.event.Event;

import java.util.ArrayList;



import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprListPaged extends SimpleExpression<Object>{
	static {
		Registry.newSimple(ExprListPaged.class, "page %number% of %objects% with %number% lines");
	}
	
	private Expression<Number> p; //Page
	private Expression<Object> o; //Objects
	private Expression<Number> l; //Amount of items per page
	@Override
	public Class<? extends Object> getReturnType() {
		return Object.class;
	}

	@Override
	public boolean isSingle() {
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg, int arg1, Kleenean arg2, ParseResult arg3) {
		this.p = (Expression<Number>) arg[0]; 
		this.o = (Expression<Object>) arg[1]; 
		this.l = (Expression<Number>) arg[2]; 
		
		return true;
	}

	@Override
	public String toString( Event e, boolean arg1) {
		return "page " + this.p + " of " + this.o + " with " + this.l + " lines";
	}

	@Override
	
	protected Object[] get(Event e) {
		if (this.l.getSingle(e) != null && this.p.getSingle(e) != null && this.o.getAll(e) != null){
			int l = this.l.getSingle(e).intValue();
			int p = this.p.getSingle(e).intValue();
			Object[] ao = (Object[])this.o.getAll(e).clone();
			ArrayList<Object> ob = new ArrayList<Object>();
			if (l < 1 || p < 1)
				return null;
			if (p > 1)
				p = p*l-l;
			else
				p = 0;
			int max =  p+l;
			if (max > ao.length)
				max = ao.length;
			for (int x = p; x < max; x++){
				if (ao[x] != null)
					ob.add(ao[x]);
				
			}
			return ob.toArray(new Object[ob.size()]);
		}
		return null;
	}

}
