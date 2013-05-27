package net.jay.plugins.php.lang.annotator;

import net.jay.plugins.php.lang.psi.resolve.types.PhpTypeAnnotatorVisitor;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiElement;

/**
 * @author jay
 * @date Apr 11, 2008 10:43:12 AM
 */
public class PhpAnnotator implements Annotator
{

	public void annotate(PsiElement element, AnnotationHolder annotationHolder)
	{
		PhpAnnotatorVisitor visitor = new PhpAnnotatorVisitor(annotationHolder);
		element.accept(visitor);

		element.accept(PhpTypeAnnotatorVisitor.getInstance());
	}
}
