package net.jay.plugins.php.lang.highlighter.hierarchy;

import java.util.List;

import net.jay.plugins.php.cache.psi.LightPhpElement;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;

/**
 * @author jay
 * @date Jun 25, 2008 1:52:38 AM
 */
abstract public class PhpGutterInfo extends PhpLineMarkerInfo
{

	protected Project project;
	protected List<? extends LightPhpElement> elements;

	public PhpGutterInfo(final int startOffset, @NotNull Project project, @NotNull List<? extends LightPhpElement> elements)
	{
		super(startOffset);
		this.project = project;
		assert elements.size() > 0;
		this.elements = elements;
	}

	public Project getProject()
	{
		return project;
	}

	public List<? extends LightPhpElement> getElements()
	{
		return elements;
	}

	abstract public String getTitle();
}
