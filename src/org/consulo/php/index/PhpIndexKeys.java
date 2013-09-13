package org.consulo.php.index;

import com.intellij.psi.stubs.StubIndexKey;
import net.jay.plugins.php.lang.psi.elements.PhpClass;

/**
 * @author VISTALL
 * @since 16.07.13.
 */
public interface PhpIndexKeys {
	StubIndexKey<String, PhpClass> CLASSES = StubIndexKey.createIndexKey("php.classes");
}