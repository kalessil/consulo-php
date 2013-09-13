package org.consulo.php.completion.insert;

import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.openapi.editor.Editor;
import net.jay.plugins.php.completion.PhpLookupElement;
import net.jay.plugins.php.lang.documentation.params.PhpParameterInfoHandlerUtil;
import net.jay.plugins.php.lang.psi.elements.Function;
import org.consulo.php.lang.psi.elements.PhpNamedElement;

/**
 * @author jay
 * @date Jun 24, 2008 4:51:54 PM
 */
public class PhpMethodInsertHandler implements InsertHandler
{

	private static PhpMethodInsertHandler instance = null;

	public static PhpMethodInsertHandler getInstance()
	{
		if(instance == null)
		{
			instance = new PhpMethodInsertHandler();
		}
		return instance;
	}

	protected PhpMethodInsertHandler()
	{

	}

	/**
	 * Invoked inside atomic action.
	 */
	public void handleInsert(InsertionContext context, LookupElement lookupElement)
	{
		Editor editor = context.getEditor();
		if(lookupElement.getObject() instanceof PhpLookupElement)
		{
			final Function method = getMethod(editor, lookupElement);
			if(!PhpInsertHandlerUtil.isStringAtCaret(editor, "("))
			{
				PhpInsertHandlerUtil.insertStringAtCaret(editor, "()");
				if(method != null)
				{
					if(method.getParameters().length > 0)
					{
						editor.getCaretModel().moveCaretRelatively(-1, 0, false, false, true);
						PhpParameterInfoHandlerUtil.showParameterInfo(editor);
					}
				}
			}
			else
			{
				if(PhpInsertHandlerUtil.isStringAtCaret(editor, "()"))
				{
					editor.getCaretModel().moveCaretRelatively(2, 0, false, false, true);
				}
				else
				{
					editor.getCaretModel().moveCaretRelatively(1, 0, false, false, true);
					PhpParameterInfoHandlerUtil.showParameterInfo(editor);
				}
			}
		}
	}

	protected Function getMethod(Editor editor, LookupElement element)
	{
		final PhpNamedElement psiElement = ((PhpLookupElement) element).element;
		if(psiElement instanceof Function)
		{
			return (Function) psiElement;
		}
		return null;
	}

}