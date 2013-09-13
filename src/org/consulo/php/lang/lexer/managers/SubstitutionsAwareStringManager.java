package org.consulo.php.lang.lexer.managers;

import net.jay.plugins.php.lang.lexer.PHPFlexLexer;

import org.jetbrains.annotations.NotNull;

/**
 * Created by IntelliJ IDEA.
 * User: jay
 * Date: 23.03.2007
 *
 * @author jay
 */
public abstract class SubstitutionsAwareStringManager extends StringManager
{

	public SubstitutionsAwareStringManager(@NotNull final PHPFlexLexer lexer)
	{
		super(lexer);
	}

	protected boolean checkForVariable(int pos)
	{
		if(safeReadAt(pos - 1) == '\\')
		{
			return false;
		}
		return safeReadAt(pos) == '$';
	}

	protected boolean checkForExprSubtitution(int pos)
	{
		if(safeReadAt(pos - 1) == '\\')
		{
			return false;
		}
		String s = safeReadStringAt(pos, 2);
		return s.equals("{$");
	}
}