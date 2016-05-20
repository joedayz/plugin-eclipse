package pe.gob.sunat.projects.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.gob.sunat.common.helper.FileUtil;
import pe.gob.sunat.common.helper.ProjectUtil;

public final class GradleModuleUtil {

	protected GradleModuleUtil() {
		// TODO Auto-generated constructor stub
		throw new UnsupportedOperationException();
	}

	public static void createJarModule(String installPath, String projectName,
			String projectLocation, String moduleName) {
		// TODO Auto-generated method stub
		ProjectUtil.createJavaProject(moduleName, projectLocation + "/"
				+ projectName, installPath + "/templates/projects/java");
		createGradleSettingsFiles(moduleName, installPath
				+ "/templates/projects/java/settings/gradle");
		// ProjectUtil.addPackageToProject(moduleName, "/src/main/java",
		// new String[] { "pe.gob.sunat." + megaProcess + "."
		// + macroProcess + "." + process + ".ws.rest" });
		createJavaModuleFiles(moduleName, projectLocation + "/" + projectName,
				installPath + "/templates/projects/java/files");

		addFilteredProjects(projectName, moduleName);

		ProjectUtil.refreshProject(projectName);
	}

	public static void createWarModule(String installPath, String projectName,
			String projectLocation, String moduleName) {
		// TODO Auto-generated method stub
		ProjectUtil.createWebProject(moduleName, projectLocation + "/"
				+ projectName, installPath + "/templates/projects/web", true);
		createGradleSettingsFiles(moduleName, installPath
				+ "/templates/projects/web/settings/gradle");
		createWebModuleFiles(moduleName, projectLocation + "/" + projectName,
				installPath + "/templates/projects/web/files");

		addFilteredProjects(projectName, moduleName);

		ProjectUtil.refreshProject(projectName);
	}

	private static void createGradleSettingsFiles(String moduleName,
			String templateFolder) {
		String[] natureIds = { "org.springsource.ide.eclipse.gradle.core.nature" };
		ProjectUtil.addNatureToProject(moduleName, natureIds);

		String content = FileUtil.readFileToString(templateFolder
				+ "/org.springsource.ide.eclipse.gradle.core.prefs");
		ProjectUtil.addFileToProject(moduleName, ".settings/gradle",
				"org.springsource.ide.eclipse.gradle.core.prefs", content);

		Map<String, String> filePath1 = new HashMap<String, String>();
		filePath1.put("destFilename",
				"org.springsource.ide.eclipse.gradle.core.prefs");
		filePath1.put("oriFilepath", templateFolder
				+ "/org.springsource.ide.eclipse.gradle.core.prefs");
		Map<String, String> filePath2 = new HashMap<String, String>();
		filePath2.put("destFilename",
				"org.springsource.ide.eclipse.gradle.refresh.prefs");
		filePath2.put("oriFilepath", templateFolder
				+ "/org.springsource.ide.eclipse.gradle.refresh.prefs");

		List<Map<String, String>> filePaths = Arrays.asList(filePath1,
				filePath2);
		ProjectUtil.copyFilesToProject(moduleName, ".settings/gradle",
				filePaths);
	}

	private static void createJavaModuleFiles(String moduleName,
			String moduleLocation, String templateFolder) {
		// TODO Auto-generated method stub
		Map<String, String> filePath1 = new HashMap<String, String>();
		filePath1.put("destFilename", moduleName + ".properties");
		filePath1.put("oriFilepath", templateFolder + "/proceso.properties");
		List<Map<String, String>> filePaths = Arrays.asList(filePath1);
		ProjectUtil.copyFilesToProject(moduleName, "/src/main/resources",
				filePaths);

		createGradleModuleFiles(moduleName, moduleLocation, templateFolder);
	}

	private static void createWebModuleFiles(String moduleName,
			String moduleLocation, String templateFolder) {
		// TODO Auto-generated method stub
		Map<String, String> filePath1 = new HashMap<String, String>();
		filePath1.put("destFilename", "bootstrap-datetimepicker.css");
		filePath1.put("oriFilepath", templateFolder
				+ "/angular/css/bootstrap-datetimepicker.css");
		Map<String, String> filePath2 = new HashMap<String, String>();
		filePath2.put("destFilename", "style-map.css");
		filePath2.put("oriFilepath", templateFolder
				+ "/angular/css/style-map.css");
		Map<String, String> filePath3 = new HashMap<String, String>();
		filePath3.put("destFilename", "style.css");
		filePath3.put("oriFilepath", templateFolder + "/angular/css/style.css");
		List<Map<String, String>> filePaths = Arrays.asList(filePath1,
				filePath2, filePath3);
		ProjectUtil.copyFilesToProject(moduleName, "/src/main/webapp/css",
				filePaths);

		filePath1 = new HashMap<String, String>();
		filePath1.put("destFilename", "angular-google-maps.min.js");
		filePath1.put("oriFilepath", templateFolder
				+ "/angular/js/libs/angular-google-maps.min.js");
		filePath2 = new HashMap<String, String>();
		filePath2.put("destFilename", "angular-qr.js");
		filePath2.put("oriFilepath", templateFolder
				+ "/angular/js/libs/angular-qr.js");
		filePath3 = new HashMap<String, String>();
		filePath3.put("destFilename", "angularjs.min.js");
		filePath3.put("oriFilepath", templateFolder
				+ "/angular/js/libs/angularjs.min.js");
		Map<String, String> filePath4 = new HashMap<String, String>();
		filePath4.put("destFilename", "barcode.js");
		filePath4.put("oriFilepath", templateFolder
				+ "/angular/js/libs/barcode.js");
		Map<String, String> filePath5 = new HashMap<String, String>();
		filePath5.put("destFilename", "bootstrap-datetimepicker.js");
		filePath5.put("oriFilepath", templateFolder
				+ "/angular/js/libs/bootstrap-datetimepicker.js");
		Map<String, String> filePath6 = new HashMap<String, String>();
		filePath6.put("destFilename", "bsDropdown.js");
		filePath6.put("oriFilepath", templateFolder
				+ "/angular/js/libs/bsDropdown.js");
		Map<String, String> filePath7 = new HashMap<String, String>();
		filePath7.put("destFilename", "lodash.underscore.min.js");
		filePath7.put("oriFilepath", templateFolder
				+ "/angular/js/libs/lodash.underscore.min.js");
		Map<String, String> filePath8 = new HashMap<String, String>();
		filePath8.put("destFilename", "moment-with-locales.js");
		filePath8.put("oriFilepath", templateFolder
				+ "/angular/js/libs/moment-with-locales.js");
		Map<String, String> filePath9 = new HashMap<String, String>();
		filePath9.put("destFilename", "qrcode.js");
		filePath9.put("oriFilepath", templateFolder
				+ "/angular/js/libs/qrcode.js");
		Map<String, String> filePath10 = new HashMap<String, String>();
		filePath10.put("destFilename", "services-captcha.js");
		filePath10.put("oriFilepath", templateFolder
				+ "/angular/js/libs/services-captcha.js");
		filePaths = Arrays.asList(filePath1, filePath2, filePath3, filePath4,
				filePath5, filePath6, filePath7, filePath8, filePath9,
				filePath10);
		ProjectUtil.copyFilesToProject(moduleName, "/src/main/webapp/js/libs",
				filePaths);

		// Incluir los archivos necesarios por los componentes
		String installPath = templateFolder + "/../../../";
		String[] cssFiles = new String[] { "calendario.css", "codigo-qr.css",
				"google-maps.css" };
		filePaths = new ArrayList<Map<String, String>>();
		for (String cssFile : cssFiles) {
			Map<String, String> filePath = new HashMap<String, String>();
			filePath.put("destFilename", cssFile);
			filePath.put("oriFilepath", installPath
					+ "/templates/components/css/" + cssFile);

			filePaths.add(filePath);
		}
		ProjectUtil.copyFilesToProject(moduleName, "/src/main/webapp/css",
				filePaths);

		String[] jsFiles = new String[] { "calendario.js", "captcha.js",
				"codigo-barras.js", "codigo-qr.js", "combo.js",
				"google-maps.js", "lista-paginada.js", "lista-valores.js",
				"arbol-accesible.js", "arbol-accesible-data.json",
				"arbol-jerarquico.js", "arbol-jerarquico-data.json" };
		filePaths = new ArrayList<Map<String, String>>();
		for (String jsFile : jsFiles) {
			Map<String, String> filePath = new HashMap<String, String>();
			filePath.put("destFilename", jsFile);
			filePath.put("oriFilepath", installPath
					+ "/templates/components/js/" + jsFile);

			filePaths.add(filePath);
		}
		ProjectUtil.copyFilesToProject(moduleName, "/src/main/webapp/js",
				filePaths);

		String[] htmlFiles = new String[] { "arbol-accesible-1.html",
				"arbol-accesible-11.html", "arbol-accesible-111.html",
				"arbol-accesible-2.html", "arbol-accesible-21.html",
				"arbol-accesible-3.html" };
		filePaths = new ArrayList<Map<String, String>>();
		for (String htmlFile : htmlFiles) {
			Map<String, String> filePath = new HashMap<String, String>();
			filePath.put("destFilename", htmlFile);
			filePath.put("oriFilepath", installPath
					+ "/templates/components/html/" + htmlFile);

			filePaths.add(filePath);
		}
		ProjectUtil.copyFilesToProject(moduleName, "/src/main/webapp",
				filePaths);

		jsFiles = new String[] { "dni.js", "25723525.json", "29730310.json",
				"ubigeo.js" };
		filePaths = new ArrayList<Map<String, String>>();
		for (String jsFile : jsFiles) {
			Map<String, String> filePath = new HashMap<String, String>();
			filePath.put("destFilename", jsFile);
			filePath.put("oriFilepath", installPath + "/templates/forms/js/"
					+ jsFile);

			filePaths.add(filePath);
		}
		ProjectUtil.copyFilesToProject(moduleName, "/src/main/webapp/js",
				filePaths);

		htmlFiles = new String[] { "dni-form.html", "ubigeo-form.html" };
		filePaths = new ArrayList<Map<String, String>>();
		for (String htmlFile : htmlFiles) {
			Map<String, String> filePath = new HashMap<String, String>();
			filePath.put("destFilename", htmlFile);
			filePath.put("oriFilepath", installPath + "/templates/forms/html/"
					+ htmlFile);

			filePaths.add(filePath);
		}
		ProjectUtil.copyFilesToProject(moduleName, "/src/main/webapp",
				filePaths);
		// Incluir los archivos necesarios por los componentes

		ProjectUtil.removeFileFromProject(moduleName, "/src/main/webapp",
				"index.html");

		filePath1 = new HashMap<String, String>();
		filePath1.put("destFilename", "index.html");
		filePath1.put("oriFilepath", templateFolder + "/angular/index.html");
		filePaths = Arrays.asList(filePath1);
		ProjectUtil.copyFilesToProject(moduleName, "/src/main/webapp",
				filePaths);

		String content = FileUtil.readFileToString(templateFolder
				+ "/application/weblogic.xml");
		content = content.replaceAll("moduleNameUpperCase", moduleName
				.replaceAll(".war", "").replaceAll("-", "").toUpperCase());
		content = content.replaceAll("moduleName",
				moduleName.replaceAll(".war", ""));
		ProjectUtil.addFileToProject(moduleName, "/src/main/webapp/WEB-INF",
				"weblogic.xml", content);

		createGradleModuleFiles(moduleName, moduleLocation, templateFolder);
	}

	private static void createGradleModuleFiles(String moduleName,
			String moduleLocation, String templateFolder) {
		// TODO Auto-generated method stub
		String content = FileUtil.readFileToString(templateFolder
				+ "/gradle/build.gradle");

		FileUtil.writeStringToFile(moduleLocation + "/" + moduleName
				+ "/build.gradle", content);
	}

	private static void addFilteredProjects(String projectName,
			String moduleName) {
		String[] prevFilteredProjects = ProjectUtil
				.removeFilteredProjects(projectName);

		String[] newFilteredProjects = new String[prevFilteredProjects.length + 1];
		System.arraycopy(prevFilteredProjects, 0, newFilteredProjects, 0,
				prevFilteredProjects.length);
		newFilteredProjects[prevFilteredProjects.length] = moduleName;

		ProjectUtil.addFilteredProjects(projectName, newFilteredProjects);
	}

}
