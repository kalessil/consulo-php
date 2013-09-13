package org.consulo.php.lang.inspections;

import org.consulo.php.PHPBundle;

import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeHighlighting.HighlightDisplayLevel;
import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElementVisitor;

/**
 * @author jay
 * @date May 9, 2008 2:25:31 PM
 */
abstract public class PhpInspection extends LocalInspectionTool
{
	@Nls
	@NotNull
	public String getGroupDisplayName()
	{
		return PHPBundle.message("php.inspections.group");
	}

	@NonNls
	@NotNull
	public String getShortName()
	{
		return getClass().getSimpleName();
	}

	public boolean isEnabledByDefault()
	{
		return true;
	}

	/**
	 * @return highlighting level for this inspection tool that is used in default settings
	 */
	@NotNull
	public HighlightDisplayLevel getDefaultLevel()
	{
		return HighlightDisplayLevel.WARNING;
	}

	@NotNull
	abstract public PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly);

}