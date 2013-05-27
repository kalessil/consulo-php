package net.jay.plugins.php.lang.parser;

import net.jay.plugins.php.lang.parser.parsing.Program;
import net.jay.plugins.php.lang.parser.util.PHPPsiBuilder;

import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;

/**
 * Created by IntelliJ IDEA.
 * User: jay
 * Date: 26.02.2007
 *
 * @author jay
 */
public class PHPPsiParser implements PsiParser
{

	@NotNull
	public ASTNode parse(IElementType root, PsiBuilder builder)
	{
		//		builder.setDebugMode(true);
		PHPPsiBuilder psiBuilder = new PHPPsiBuilder(builder);

		PsiBuilder.Marker marker = psiBuilder.mark();
		//    long startTime = System.currentTimeMillis();
		Program.parse(psiBuilder);
		//    System.out.println("parsing time: " + (System.currentTimeMillis() - startTime));
		marker.done(root);
		return psiBuilder.getTreeBuilt();
	}
}
