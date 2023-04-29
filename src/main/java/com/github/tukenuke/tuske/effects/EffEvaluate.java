package com.github.tukenuke.tuske.effects;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.command.Argument;
import ch.njol.skript.command.Commands;
import ch.njol.skript.config.Config;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.*;
import ch.njol.util.Kleenean;
import ch.njol.util.StringUtils;
import com.github.tukenuke.tuske.util.Evaluate;
import com.github.tukenuke.tuske.util.Registry;
import org.bukkit.event.Event;

import java.util.List;

/**
 * @author Tuke_Nuke on 16/04/2017
 */
@Name("Evaluate")
@Description("This effect will run any Skript effect/condition from a given string or piece of code. " +
		"The difference between {{effects|EvaluateInputEffect|SkQuery effect}} and this " +
		"is basically it returns all syntaxes errors instead of send them to the console, " +
		"It can evaluate a long amount of code and see them easily instead of beeing in one line. " +
		"\n" +
		"For example, you can run a effect from a string, from a piece of code (without beeing quoted) or " +
		"the entiry section of code." +
		"\n" +
		"Starting from 1.8, you can disallow some syntaxes when evaluating. Read more about it at config file (`TuSKe/config.yml`).")
@Examples({
		"set {_effect} to \"send\"",
		"evaluate:",
		"\t%{_effect}% \"This message will be sent to a player.\"",
		"#Before parsing the code, it will convert all variables",
		"#To string, basically it will interpret the code above as it",
		"#was a whole string: \"%{_effect}% \"\"This message will be sent to a player.\"\"\"",
		"#where Skript will replace '%{_effect}%' with the variable value and then it will parse",
		"#The code. It is the same concept of Skript Options.",
		" ",
		"evaluate: kill all players #Not really needed for this but just an example",
		"evaluate: \"broadcast \"\"Hi everyone\"\"\"",
		"evaluate:",
		"\tif true is true:",
		"\t\tgive a diamond sword of %{Enchantment}% 1 to all players",
		"evaluate logging in {_errors::*}: this is not a valid effect",
		"if {_errors::*} is set:",
		"\tloop {_errors::*}: #If there is something wrong, it will add the errors here",
		"\t\tsend loop-value",
		"#It will send a string in Skript default error format:",
		"<Error message>: <wrong expression> (TuSKe/evaluate.sk, line <line of code>, '<whole line>')",
		"'TuSKe/evaluate.sk' is a fictitious file, it doesn't exist."
})
@Since("1.7.5, 1.8 (filtering syntaxes)")
public class EffEvaluate extends Effect{
	static {
		Registry.newEffect(EffEvaluate.class, "eval[uate][ logging [[the] error[s]] in %-objects%][ with safety]: (%-strings%|<.+?>)");
	}

	private Config currentScript;
	private List<Argument<?>> args;
	private Variable results;
	private Expression<String> varStr;
	private String str;
	private boolean withSafety = false;
	@Override
	protected void execute(Event event) {
		String code = str != null ? str : StringUtils.join(varStr.getArray(event), "\n");
		Evaluate.getInstance().evaluate(code, event, results, str != null, currentScript, args, this, withSafety);
	}

	@Override
	public String toString(Event event, boolean b) {
		return "evaluate inline";
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		if (expr[0] != null) {
			if (expr[0] instanceof Variable && ((Variable) expr[0]).isList()) {
				results = (Variable<?>) expr[0];
			} else {
				Skript.error("The parameter needs to be a variable list.");
				return false;
			}
		}
		if (expr[1] == null)
			str = parseResult.regexes.get(0).group(0);
		else
			varStr = (Expression<String>) expr[1];
		withSafety = parseResult.expr.contains("with safety:");
		args = Commands.currentArguments;
		currentScript = ScriptLoader.getCurrentScript();
		return true;
	}
}
