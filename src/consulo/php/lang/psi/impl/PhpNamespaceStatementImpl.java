package consulo.php.lang.psi.impl;

import consulo.php.lang.psi.PhpClassReference;
import consulo.php.lang.psi.PhpNamespaceStatement;
import consulo.php.lang.psi.PhpPackage;
import consulo.php.lang.psi.visitors.PhpElementVisitor;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;

/**
 * @author VISTALL
 * @since 19.09.13.
 */
public class PhpNamespaceStatementImpl extends PhpElementImpl implements PhpNamespaceStatement
{
	public PhpNamespaceStatementImpl(ASTNode node)
	{
		super(node);
	}

	@Override
	public void accept(@NotNull PhpElementVisitor visitor)
	{
		visitor.visitNamespaceStatement(this);
	}

	@Override
	public String getNamespace()
	{
		PhpClassReference packageReference = getPackageReference();
		if(packageReference == null)
		{
			return null;
		}
		PsiElement resolve = packageReference.resolve();
		if(resolve instanceof PhpPackage)
		{
			return ((PhpPackage) resolve).getNamespaceName();
		}
		return null;
	}

	@Override
	public PhpClassReference getPackageReference()
	{
		return findNotNullChildByClass(PhpClassReference.class);
	}
}
