package org.consulo.php.ide.projectView;

import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.projectView.ViewSettings;
import com.intellij.ide.projectView.impl.nodes.AbstractPsiBasedNode;
import com.intellij.ide.util.treeView.AbstractTreeNode;
import org.consulo.php.lang.psi.*;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author VISTALL
 * @since 19.09.13.
 */
public class PhpFileTreeNode extends AbstractPsiBasedNode<PhpFile> {
	public static List<AbstractTreeNode> fillToTreeNodes(PhpMemberHolder memberHolder, ViewSettings settings) {
		List<AbstractTreeNode> list = new ArrayList<AbstractTreeNode>();
		for (PhpClass phpClass : memberHolder.getClasses()) {
			list.add(new PhpClassTreeNode(phpClass, settings));
		}
		for (PhpFunction phpMethod : memberHolder.getFunctions()) {
			list.add(new PhpFunctionTreeNode(phpMethod, settings));
		}
		for (PhpField phpField : memberHolder.getFields()) {
			list.add(new PhpFieldTreeNode(phpField, settings));
		}
		return list.isEmpty() ? Collections.<AbstractTreeNode>emptyList() : list;
	}

	public PhpFileTreeNode(PhpFile phpFile, ViewSettings viewSettings) {
		super(phpFile.getProject(), phpFile, viewSettings);
	}

	@Nullable
	@Override
	protected com.intellij.psi.PsiElement extractPsiFromValue() {
		return getValue();
	}

	@Nullable
	@Override
	protected Collection<AbstractTreeNode> getChildrenImpl() {
		if(!getSettings().isShowMembers()) {
			return Collections.emptyList();
		}
		PhpFile value = getValue();

		return fillToTreeNodes(value, getSettings());
	}

	@Override
	protected void updateImpl(PresentationData presentationData) {
		presentationData.setPresentableText(getValue().getName());
	}
}