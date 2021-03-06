package consulo.php.lang.psi.impl;

import consulo.php.lang.psi.PhpUnaryExpression;
import consulo.php.lang.psi.visitors.PhpElementVisitor;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;

/**
 * @author jay
 * @date Apr 15, 2008 9:10:04 PM
 */
public class PhpUnaryExpressionImpl extends PhpElementImpl implements PhpUnaryExpression
{
	public PhpUnaryExpressionImpl(ASTNode node)
	{
		super(node);
	}

	@Override
	public PsiElement getExpression()
	{
		return getFirstPsiChild();
	}

	@Override
	public void accept(@NotNull PhpElementVisitor visitor)
	{
		visitor.visitUnaryExpression(this);
	}

	@Override
	public boolean processDeclarations(@NotNull PsiScopeProcessor processor, @NotNull ResolveState state, PsiElement lastParent, @NotNull PsiElement source)
	{
		if(getExpression() != lastParent)
		{
			if(!getExpression().processDeclarations(processor, state, null, source))
			{
				return false;
			}
		}
		return super.processDeclarations(processor, state, lastParent, source);
	}

}
