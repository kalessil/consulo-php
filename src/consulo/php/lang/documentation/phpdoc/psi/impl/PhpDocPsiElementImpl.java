package consulo.php.lang.documentation.phpdoc.psi.impl;

import consulo.php.lang.documentation.phpdoc.psi.PhpDocPsiElement;
import consulo.php.lang.psi.impl.PhpElementImpl;
import com.intellij.lang.ASTNode;

/**
 * @author jay
 * @date Jun 29, 2008 2:09:01 AM
 */
abstract public class PhpDocPsiElementImpl extends PhpElementImpl implements PhpDocPsiElement
{

	public PhpDocPsiElementImpl(ASTNode node)
	{
		super(node);
	}

}
