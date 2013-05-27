package net.jay.plugins.php.lang.psi.elements.impl;

import net.jay.plugins.php.lang.psi.elements.ElseIf;
import net.jay.plugins.php.lang.psi.elements.PHPPsiElement;
import net.jay.plugins.php.lang.psi.visitors.PHPElementVisitor;

import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;

/**
 * @author jay
 * @date Jul 2, 2008 3:06:57 AM
 */
public class ElseIfImpl extends PHPPsiElementImpl implements ElseIf
{

	public ElseIfImpl(ASTNode node)
	{
		super(node);
	}

	public PHPPsiElement getCondition()
	{
		return getFirstPsiChild();
	}

	public PHPPsiElement getStatement()
	{
		if(getCondition() != null)
			return getCondition().getNextPsiSibling();
		return null;
	}

	public void accept(@NotNull PsiElementVisitor visitor)
	{
		if(visitor instanceof PHPElementVisitor)
		{
			((PHPElementVisitor) visitor).visitPhpElseIf(this);
		}
		else
		{
			visitor.visitElement(this);
		}
	}

	@Override
	public boolean processDeclarations(@NotNull PsiScopeProcessor processor, @NotNull ResolveState state, PsiElement lastParent, @NotNull PsiElement source)
	{
		if(getCondition() != lastParent)
		{
			if(!getCondition().processDeclarations(processor, state, null, source))
			{
				return false;
			}
			if(getStatement() != lastParent)
			{
				if(!getStatement().processDeclarations(processor, state, null, source))
				{
					return false;
				}
			}
		}
		return super.processDeclarations(processor, state, lastParent, source);
	}

}
