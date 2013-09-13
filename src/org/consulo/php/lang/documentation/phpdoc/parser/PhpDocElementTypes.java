package org.consulo.php.lang.documentation.phpdoc.parser;

import com.intellij.lang.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.ILazyParseableElementType;
import com.intellij.util.LanguageVersionUtil;
import net.jay.plugins.php.lang.PHPFileType;
import org.consulo.php.lang.documentation.phpdoc.lexer.PhpDocLexer;
import org.consulo.php.lang.documentation.phpdoc.lexer.PhpDocTokenTypes;
import org.consulo.php.lang.documentation.phpdoc.psi.PhpDocElementType;
import org.jetbrains.annotations.NotNull;

/**
 * @author jay
 * @date Jun 26, 2008 10:12:07 PM
 */
public interface PhpDocElementTypes extends PhpDocTokenTypes
{

	final public ILazyParseableElementType DOC_COMMENT = new ILazyParseableElementType("PhpDocComment")
	{
		@NotNull
		public Language getLanguage()
		{
			return PHPFileType.INSTANCE.getLanguage();
		}

		public ASTNode parseContents(ASTNode chameleon)
		{
			PsiBuilderFactory factory = PsiBuilderFactory.getInstance();
			final PsiElement parentElement = chameleon.getTreeParent().getPsi();

			LanguageVersion<Language> defaultVersion = LanguageVersionUtil.findDefaultVersion(getLanguage());
			final PsiBuilder builder = factory.createBuilder(parentElement.getProject(), chameleon, new PhpDocLexer(), getLanguage(), defaultVersion,
					chameleon.getText());
			final PsiParser parser = new PhpDocParser();
			return parser.parse(this, builder, defaultVersion).getFirstChildNode();
		}
	};

	final public PhpDocElementType phpDocText = new PhpDocElementType("PhpDocText");
	final public PhpDocElementType phpDocTag = new PhpDocElementType("PhpDocTag");
	final public PhpDocElementType phpDocInlineTag = new PhpDocElementType("PhpDocInlineTag");
	final public PhpDocElementType phpDocTagValue = new PhpDocElementType("PhpDocTagValue");
	final public PhpDocElementType phpDocType = new PhpDocElementType("PhpDocType");
	final public PhpDocElementType phpDocVariable = new PhpDocElementType("PhpDocVariable");

}