package jd.ide.intellij;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.psi.ClsFileDecompiledPsiFileProvider;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.PsiJavaFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Provider extension that builds a PsiFile from the JD decompiled text, other wise IDEA uses
 * the default decompiled text to build the PsiFile which results in wrong references.
 *
 * @see <a href="http://youtrack.jetbrains.com/issue/IDEA-100029">http://youtrack.jetbrains.com/issue/IDEA-100029</a>
 *
 */
public class JavaClassDecompiledPsiFileProvider implements ClsFileDecompiledPsiFileProvider {
    private final CachingJavaDecompilerService javaDecompilerService;

    public JavaClassDecompiledPsiFileProvider() {
        javaDecompilerService = ServiceManager.getService(CachingJavaDecompilerService.class);
    }



    @Nullable
    @Override
    public PsiFile getDecompiledPsiFile(@NotNull PsiJavaFile clsFile) {
        String decompiledText = javaDecompilerService.decompile(clsFile);

        PsiFile fileFromDecompiledText = PsiFileFactory.getInstance(clsFile.getProject()).createFileFromText(
                clsFile.getName(),
                clsFile.getLanguage(),
                decompiledText,
                false,
                true
        );
        return fileFromDecompiledText;
    }
}
