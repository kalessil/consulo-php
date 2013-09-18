package org.consulo.php.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.util.IncorrectOperationException;
import org.consulo.php.PhpIcons;
import org.consulo.php.lang.lexer.PhpTokenTypes;
import org.consulo.php.lang.psi.PhpPsiElementFactory;
import org.consulo.php.lang.psi.PhpParameter;
import org.consulo.php.lang.psi.PhpVariableReference;
import org.consulo.php.lang.psi.resolve.types.PhpType;
import org.consulo.php.lang.psi.resolve.types.PhpTypeAnnotatorVisitor;
import org.consulo.php.lang.psi.visitors.PhpElementVisitor;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author jay
 * @date Apr 3, 2008 10:32:38 PM
 */
public class PhpParameterImpl extends PhpNamedElementImpl implements PhpParameter
{
	public PhpParameterImpl(ASTNode node)
	{
		super(node);
	}

	@Override
	public PsiElement getNameIdentifier()
	{
		return findChildByType(PhpTokenTypes.VARIABLE);
	}

	@Override
	public String getName()
	{
		PsiElement nameNode = getNameIdentifier();
		if(nameNode != null)
			return nameNode.getText().substring(1);
		return null;
	}

	@Override
	public PsiElement setName(@NonNls @NotNull String name) throws IncorrectOperationException
	{
		PsiElement nameIdentifier = getNameIdentifier();
		//noinspection ConstantConditions
		if(nameIdentifier != null && !getName().equals(name))
		{
			final PhpVariableReference variable = PhpPsiElementFactory.createVariable(getProject(), name);
			nameIdentifier.replace(variable.getNameIdentifier());
		}
		return this;
	}

	@Override
	public void accept(@NotNull PsiElementVisitor visitor)
	{
		if(visitor instanceof PhpElementVisitor)
		{
			((PhpElementVisitor) visitor).visitPhpParameter(this);
		}
		else
		{
			visitor.visitElement(this);
		}
	}

	@Override
	@Nullable
	public Icon getIcon()
	{
		return PhpIcons.PARAMETER;
	}

	@Override
	@NotNull
	public PhpType getType()
	{
		PhpType type = getUserData(PhpTypeAnnotatorVisitor.TYPE_KEY);
		if(type == null)
		{
			PhpTypeAnnotatorVisitor.process(this);
		}
		type = getUserData(PhpTypeAnnotatorVisitor.TYPE_KEY);
		assert type != null;
		return type;
	}
}