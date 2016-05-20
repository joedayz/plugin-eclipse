package pe.gob.sunat.projects.palette.util;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public final class ComponentsResourceUtil {

	private static final ResourceBundle resourceBundle = ResourceBundle
			.getBundle("pe.gob.sunat.projects.palette.resource.components");

	protected ComponentsResourceUtil() {
		// TODO Auto-generated constructor stub
		throw new UnsupportedOperationException();
	}

	public static String getProperty(String propertyName) {
		if (getResourceBundle() != null) {
			try {
				return getResourceBundle().getString(propertyName);
			} catch (MissingResourceException e) {
			}
		}

		return "!" + propertyName + "!";
	}

	public static String getProperty(String propertyName, String[] parameters) {
		if (getResourceBundle() != null) {
			try {
				String value = getResourceBundle().getString(propertyName);
				return MessageFormat.format(value, parameters);
			} catch (MissingResourceException localMissingResourceException) {
			}
		}

		return "!" + propertyName + "!";
	}

	private static ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

}
