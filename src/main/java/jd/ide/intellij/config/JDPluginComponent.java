package jd.ide.intellij.config;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.IconLoader;
import org.apache.commons.lang.StringUtils;
import org.jdom.Element;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Configuration component for Java Decompiler.
 * <p/>
 * Holds the configuration of the plugin application wise.
 */
@State(
        name = JDPluginConfigurationPane.COMPONENT_NAME,
        storages = {@Storage(value = "$APP_CONFIG$/java.decompiler.xml")}
)
public class JDPluginComponent implements ApplicationComponent, Configurable, PersistentStateComponent<Element> {

    public static final String ESCAPE_UNICODE_CHARACTERS = "escapeUnicodeCharacters";
    public static final String OMIT_PREFIX_THIS = "omitPrefixThis";
    public static final String REALIGN_LINE_NUMBERS = "realignLineNumbers";
    public static final String SHOW_METADATA_ATTRIBUTE = "displayMetadata";
    public static final String SHOW_LINE_NUMBERS_ATTRIBUTE = "displayLineNumbers";
    public static final String SHOW_DEFAULT_CONSTRUCTOR = "displayDefaultConstructor";
    public static final String JD_CONFIGURATION_CONFIG_ELEMENT = "jd-configuration";
    public static final String JD_INTELLIJ_ID = "jd-intellij";

    private JDPluginConfigurationPane configPane;
    private boolean escapeUnicodeCharactersEnabled;
    private boolean omitPrefixThisEnabled;
    private boolean realignLineNumbersEnabled;
    private boolean showLineNumbersEnabled;
    private boolean showMetadataEnabled;
    private boolean showDefaultConstructorEnabled;

    @Override
    public void initComponent() {
    } // nop

    @Override
    public void disposeComponent() {
    } // nop

    @NotNull
    @Override
    public String getComponentName() {
        return "Java Decompiler plugin";
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "Java Decompiler";
    }

    //@Override
    public Icon getIcon() {
        return IconLoader.getIcon("main/resources/icons/jd_64.png");
    }

    @Override
    public String getHelpTopic() {
        return null;
    } // nop

    @Override
    public Element getState() {
        Element jdConfiguration = new Element(JD_CONFIGURATION_CONFIG_ELEMENT);
        jdConfiguration.setAttribute(ESCAPE_UNICODE_CHARACTERS, String.valueOf(escapeUnicodeCharactersEnabled));
        jdConfiguration.setAttribute(OMIT_PREFIX_THIS, String.valueOf(omitPrefixThisEnabled));
        jdConfiguration.setAttribute(REALIGN_LINE_NUMBERS, String.valueOf(realignLineNumbersEnabled));
        jdConfiguration.setAttribute(SHOW_LINE_NUMBERS_ATTRIBUTE, String.valueOf(showLineNumbersEnabled));
        jdConfiguration.setAttribute(SHOW_METADATA_ATTRIBUTE, String.valueOf(showMetadataEnabled));
        jdConfiguration.setAttribute(SHOW_DEFAULT_CONSTRUCTOR, String.valueOf(showDefaultConstructorEnabled));
        return jdConfiguration;
    }

    @Override
    public void loadState(Element jdConfiguration) {
        String escapeUnicodeCharactersStr = jdConfiguration.getAttributeValue(ESCAPE_UNICODE_CHARACTERS);
        if (StringUtils.isNotBlank(escapeUnicodeCharactersStr)) {
            escapeUnicodeCharactersEnabled = Boolean.valueOf(escapeUnicodeCharactersStr);
        }
        String omitPrefixThisStr = jdConfiguration.getAttributeValue(OMIT_PREFIX_THIS);
        if (StringUtils.isNotBlank(omitPrefixThisStr)) {
            omitPrefixThisEnabled = Boolean.valueOf(omitPrefixThisStr);
        }
        String realignLineNumbersStr = jdConfiguration.getAttributeValue(REALIGN_LINE_NUMBERS);
        if (StringUtils.isNotBlank(realignLineNumbersStr)) {
            realignLineNumbersEnabled = Boolean.valueOf(realignLineNumbersStr);
        } else {
            realignLineNumbersEnabled = true;
        }
        String showLineNumbersStr = jdConfiguration.getAttributeValue(SHOW_LINE_NUMBERS_ATTRIBUTE);
        if (StringUtils.isNotBlank(showLineNumbersStr)) {
            showLineNumbersEnabled = Boolean.valueOf(showLineNumbersStr);
        } else {
            showLineNumbersEnabled = true;
        }
        String showMetadataStr = jdConfiguration.getAttributeValue(SHOW_METADATA_ATTRIBUTE);
        if (StringUtils.isNotBlank(showMetadataStr)) {
            showMetadataEnabled = Boolean.valueOf(showMetadataStr);
        } else {
            showMetadataEnabled = true;
        }
        String showDefaultConstructorStr = jdConfiguration.getAttributeValue(SHOW_DEFAULT_CONSTRUCTOR);
        if (StringUtils.isNotBlank(showDefaultConstructorStr)) {
            showDefaultConstructorEnabled = Boolean.valueOf(showDefaultConstructorStr);
        }
    }

    @Override
    public JComponent createComponent() {
        if (configPane == null) {
            configPane = new JDPluginConfigurationPane();
        }
        return configPane.getRootPane();
    }

    @Override
    public boolean isModified() {
        return configPane != null && configPane.isModified(this);
    }

    @Override
    public void apply() throws ConfigurationException {
        if (configPane != null) {
            configPane.storeDataTo(this);
        }
    }

    @Override
    public void reset() {
        if (configPane != null) {
            configPane.readDataFrom(this);
        }
    }

    @Override
    public void disposeUIResources() {
        configPane = null;
    }

    public boolean isEscapeUnicodeCharactersEnabled() {
        return escapeUnicodeCharactersEnabled;
    }

    public void setEscapeUnicodeCharactersEnabled(boolean escapeUnicodeCharactersEnabled) {
        this.escapeUnicodeCharactersEnabled = escapeUnicodeCharactersEnabled;
    }

    public boolean isOmitPrefixThisEnabled() {
        return omitPrefixThisEnabled;
    }

    public void setOmitPrefixThisEnabled(boolean omitPrefixThisEnabled) {
        this.omitPrefixThisEnabled = omitPrefixThisEnabled;
    }

    public boolean isRealignLineNumbersEnabled() {
        return realignLineNumbersEnabled;
    }

    public void setRealignLineNumbersEnabled(boolean realignLineNumbersEnabled) {
        this.realignLineNumbersEnabled = realignLineNumbersEnabled;
    }

    public boolean isShowLineNumbersEnabled() {
        return showLineNumbersEnabled;
    }

    public void setShowLineNumbersEnabled(boolean showLineNumbersEnabled) {
        this.showLineNumbersEnabled = showLineNumbersEnabled;
    }

    public boolean isShowMetadataEnabled() {
        return showMetadataEnabled;
    }

    public void setShowMetadataEnabled(boolean showMetadataEnabled) {
        this.showMetadataEnabled = showMetadataEnabled;
    }

    public boolean isShowDefaultConstructorEnabled() {
        return showDefaultConstructorEnabled;
    }

    public void setShowDefaultConstructorEnabled(boolean showDefaultConstructorEnabled) {
        this.showDefaultConstructorEnabled = showDefaultConstructorEnabled;
    }

}
