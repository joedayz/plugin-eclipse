package pe.gob.sunat.projects.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.gob.sunat.common.helper.FileUtil;
import pe.gob.sunat.common.helper.ProjectUtil;

public final class GradleApplicationUtil {

	protected GradleApplicationUtil() {
		// TODO Auto-generated constructor stub
		throw new UnsupportedOperationException();
	}

	public static void createWebApplication(String installPath,
			String projectType, String projectName, String projectLocation,
			String megaProcess, String macroProcess, String process,
			String subProcess, String projectVersion) {
		// TODO Auto-generated method stub
		createRootProject(installPath, projectType, projectName,
				projectLocation, megaProcess, macroProcess, process,
				subProcess, projectVersion);
		createGradleSettingsFiles(projectType, projectName, projectLocation,
				megaProcess, macroProcess, process, subProcess, projectVersion,
				installPath + "/templates/projects/java/settings/gradle", true);

		ProjectUtil.createJavaProject(macroProcess + "-" + process
				+ "-controller.jar", projectLocation + "/" + projectName,
				installPath + "/templates/projects/java");
		createGradleSettingsFiles(projectType, macroProcess + "-" + process
				+ "-controller.jar", projectLocation + "/" + projectName,
				megaProcess, macroProcess, process, subProcess, projectVersion,
				installPath + "/templates/projects/java/settings/gradle", false);
		ProjectUtil.addPackageToProject(macroProcess + "-" + process
				+ "-controller.jar", "/src/main/java",
				new String[] { "pe.gob.sunat." + megaProcess + "."
						+ macroProcess + "." + process + ".ws.rest" });
		createJavaProjectFiles(installPath, projectType, macroProcess + "-"
				+ process + "-controller.jar", projectLocation + "/"
				+ projectName, megaProcess, macroProcess, process, subProcess,
				projectVersion);

		ProjectUtil.createWebProject("ia" + process + subProcess + ".war",
				projectLocation + "/" + projectName, installPath
						+ "/templates/projects/web", false);
		createGradleSettingsFiles(projectType, "ia" + process + subProcess
				+ ".war", projectLocation + "/" + projectName, megaProcess,
				macroProcess, process, subProcess, projectVersion, installPath
						+ "/templates/projects/web/settings/gradle", false);
		ProjectUtil.addPackageToProject("ia" + process + subProcess + ".war",
				"/src/main/java", new String[] { "pe.gob.sunat." + megaProcess
						+ "." + macroProcess + "." + process
						+ ".web.controller" });
		createWebProjectFiles(installPath, projectType, "ia" + process
				+ subProcess + ".war", projectLocation + "/" + projectName,
				megaProcess, macroProcess, process, subProcess, projectVersion);

		ProjectUtil.createWebProject("it" + process + subProcess + ".war",
				projectLocation + "/" + projectName, installPath
						+ "/templates/projects/web", true);
		createGradleSettingsFiles(projectType, "it" + process + subProcess
				+ ".war", projectLocation + "/" + projectName, megaProcess,
				macroProcess, process, subProcess, projectVersion, installPath
						+ "/templates/projects/web/settings/gradle", false);
		createWebProjectFiles(installPath, projectType, "it" + process
				+ subProcess + ".war", projectLocation + "/" + projectName,
				megaProcess, macroProcess, process, subProcess, projectVersion);

		ProjectUtil.createEnterpriseProject(megaProcess + "-" + macroProcess
				+ "-" + process + ".ear", projectLocation + "/" + projectName,
				installPath + "/templates/projects/enterprise");
		createGradleSettingsFiles(projectType, megaProcess + "-" + macroProcess
				+ "-" + process + ".ear", projectLocation + "/" + projectName,
				megaProcess, macroProcess, process, subProcess, projectVersion,
				installPath + "/templates/projects/enterprise/settings/gradle",
				false);
		createEnterpriseProjectFiles(installPath, projectType, megaProcess
				+ "-" + macroProcess + "-" + process + ".ear", projectLocation
				+ "/" + projectName, megaProcess, macroProcess, process,
				subProcess, projectVersion);

		addFilteredProjects(installPath, projectType, projectName,
				projectLocation, megaProcess, macroProcess, process,
				subProcess, projectVersion);

		ProjectUtil.refreshProject(projectName);
	}

	public static void createBackEndApplication(String installPath,
			String projectType, String projectName, String projectLocation,
			String megaProcess, String macroProcess, String process,
			String subProcess, String projectVersion) {
		// TODO Auto-generated method stub
		createRootProject(installPath, projectType, projectName,
				projectLocation, megaProcess, macroProcess, process,
				subProcess, projectVersion);
		createGradleSettingsFiles(projectType, projectName, projectLocation,
				megaProcess, macroProcess, process, subProcess, projectVersion,
				installPath + "/templates/projects/java/settings/gradle", true);

		ProjectUtil.createJavaProject(macroProcess + "-" + process
				+ "ws-controller.jar", projectLocation + "/" + projectName,
				installPath + "/templates/projects/java");
		createGradleSettingsFiles(projectType, macroProcess + "-" + process
				+ "ws-controller.jar", projectLocation + "/" + projectName,
				megaProcess, macroProcess, process, subProcess, projectVersion,
				installPath + "/templates/projects/java/settings/gradle", false);
		ProjectUtil.addPackageToProject(macroProcess + "-" + process
				+ "ws-controller.jar", "/src/main/java",
				new String[] { "pe.gob.sunat." + megaProcess + "."
						+ macroProcess + "." + process + ".ws.rest" });
		createJavaProjectFiles(installPath, projectType, macroProcess + "-"
				+ process + "ws-controller.jar", projectLocation + "/"
				+ projectName, megaProcess, macroProcess, process, subProcess,
				projectVersion);

		ProjectUtil.createWebProject("ia" + process + subProcess + "-ws.war",
				projectLocation + "/" + projectName, installPath
						+ "/templates/projects/web", false);
		createGradleSettingsFiles(projectType, "ia" + process + subProcess
				+ "-ws.war", projectLocation + "/" + projectName, megaProcess,
				macroProcess, process, subProcess, projectVersion, installPath
						+ "/templates/projects/web/settings/gradle", false);
		ProjectUtil.addPackageToProject(
				"ia" + process + subProcess + "-ws.war", "/src/main/java",
				new String[] { "pe.gob.sunat." + megaProcess + "."
						+ macroProcess + "." + process + ".ws.rest" });
		createWebProjectFiles(installPath, projectType, "ia" + process
				+ subProcess + "-ws.war", projectLocation + "/" + projectName,
				megaProcess, macroProcess, process, subProcess, projectVersion);

		ProjectUtil.createEnterpriseProject(megaProcess + "-" + macroProcess
				+ "-" + process + "-backend.ear", projectLocation + "/"
				+ projectName, installPath + "/templates/projects/enterprise");
		createGradleSettingsFiles(projectType, megaProcess + "-" + macroProcess
				+ "-" + process + "-backend.ear", projectLocation + "/"
				+ projectName, megaProcess, macroProcess, process, subProcess,
				projectVersion, installPath
						+ "/templates/projects/enterprise/settings/gradle",
				false);
		createEnterpriseProjectFiles(installPath, projectType, megaProcess
				+ "-" + macroProcess + "-" + process + "-backend.ear",
				projectLocation + "/" + projectName, megaProcess, macroProcess,
				process, subProcess, projectVersion);

		addFilteredProjects(installPath, projectType, projectName,
				projectLocation, megaProcess, macroProcess, process,
				subProcess, projectVersion);

		ProjectUtil.refreshProject(projectName);
	}

	public static void createBatchApplication(String installPath,
			String projectType, String projectName, String projectLocation,
			String megaProcess, String macroProcess, String process,
			String subProcess, String projectVersion) {
		// TODO Auto-generated method stub
		createRootProject(installPath, projectType, projectName,
				projectLocation, megaProcess, macroProcess, process,
				subProcess, projectVersion);
		createGradleSettingsFiles(projectType, projectName, projectLocation,
				megaProcess, macroProcess, process, subProcess, projectVersion,
				installPath + "/templates/projects/java/settings/gradle", true);

		ProjectUtil.createJavaProject("ejb" + macroProcess + "-" + process
				+ "mdb.jar", projectLocation + "/" + projectName, installPath
				+ "/templates/projects/java");
		createGradleSettingsFiles(projectType, "ejb" + macroProcess + "-"
				+ process + "mdb.jar", projectLocation + "/" + projectName,
				megaProcess, macroProcess, process, subProcess, projectVersion,
				installPath + "/templates/projects/java/settings/gradle", false);
		ProjectUtil.addPackageToProject("ejb" + macroProcess + "-" + process
				+ "mdb.jar", "/src/main/java",
				new String[] { "pe.gob.sunat." + megaProcess + "."
						+ macroProcess + "." + process + ".ejb.mdb" });
		createJavaProjectFiles(installPath, projectType, "ejb" + macroProcess
				+ "-" + process + "mdb.jar", projectLocation + "/"
				+ projectName, megaProcess, macroProcess, process, subProcess,
				projectVersion);

		ProjectUtil.createEnterpriseProject(megaProcess + "-" + macroProcess
				+ "-" + process + "-batch.ear", projectLocation + "/"
				+ projectName, installPath + "/templates/projects/enterprise");
		createGradleSettingsFiles(projectType, megaProcess + "-" + macroProcess
				+ "-" + process + "-batch.ear", projectLocation + "/"
				+ projectName, megaProcess, macroProcess, process, subProcess,
				projectVersion, installPath
						+ "/templates/projects/enterprise/settings/gradle",
				false);
		createEnterpriseProjectFiles(installPath, projectType, megaProcess
				+ "-" + macroProcess + "-" + process + "-batch.ear",
				projectLocation + "/" + projectName, megaProcess, macroProcess,
				process, subProcess, projectVersion);

		addFilteredProjects(installPath, projectType, projectName,
				projectLocation, megaProcess, macroProcess, process,
				subProcess, projectVersion);

		ProjectUtil.refreshProject(projectName);
	}

	public static void createSharedLibApplication(String installPath,
			String projectType, String projectName, String projectLocation,
			String megaProcess, String macroProcess, String process,
			String subProcess, String projectVersion) {
		// TODO Auto-generated method stub
		createRootProject(installPath, projectType, projectName,
				projectLocation, megaProcess, macroProcess, process,
				subProcess, projectVersion);
		createGradleSettingsFiles(projectType, projectName, projectLocation,
				megaProcess, macroProcess, process, subProcess, projectVersion,
				installPath + "/templates/projects/java/settings/gradle", true);

		ProjectUtil.createJavaProject("dao-ifz.jar", projectLocation + "/"
				+ projectName, installPath + "/templates/projects/java");
		createGradleSettingsFiles(projectType, "dao-ifz.jar", projectLocation
				+ "/" + projectName, megaProcess, macroProcess, process,
				subProcess, projectVersion, installPath
						+ "/templates/projects/java/settings/gradle", false);
		ProjectUtil.addPackageToProject("dao-ifz.jar", "/src/main/java",
				new String[] { "pe.gob.sunat." + megaProcess + "."
						+ macroProcess + "." + process + ".model.dao" });
		createJavaProjectFiles(installPath, projectType, "dao-ifz.jar",
				projectLocation + "/" + projectName, megaProcess, macroProcess,
				process, subProcess, projectVersion);

		ProjectUtil.createJavaProject("dao-imp.jar", projectLocation + "/"
				+ projectName, installPath + "/templates/projects/java");
		createGradleSettingsFiles(projectType, "dao-imp.jar", projectLocation
				+ "/" + projectName, megaProcess, macroProcess, process,
				subProcess, projectVersion, installPath
						+ "/templates/projects/java/settings/gradle", false);
		ProjectUtil.addPackageToProject("dao-imp.jar", "/src/main/java",
				new String[] { "pe.gob.sunat." + megaProcess + "."
						+ macroProcess + "." + process + ".model.dao.jpa" });
		createJavaProjectFiles(installPath, projectType, "dao-imp.jar",
				projectLocation + "/" + projectName, megaProcess, macroProcess,
				process, subProcess, projectVersion);

		ProjectUtil.createJavaProject("model.jar", projectLocation + "/"
				+ projectName, installPath + "/templates/projects/java");
		createGradleSettingsFiles(projectType, "model.jar", projectLocation
				+ "/" + projectName, megaProcess, macroProcess, process,
				subProcess, projectVersion, installPath
						+ "/templates/projects/java/settings/gradle", false);
		ProjectUtil.addPackageToProject("model.jar", "/src/main/java",
				new String[] { "pe.gob.sunat." + megaProcess + "."
						+ macroProcess + "." + process + ".model" });
		createJavaProjectFiles(installPath, projectType, "model.jar",
				projectLocation + "/" + projectName, megaProcess, macroProcess,
				process, subProcess, projectVersion);

		ProjectUtil.createJavaProject("service-ifz.jar", projectLocation + "/"
				+ projectName, installPath + "/templates/projects/java");
		createGradleSettingsFiles(projectType, "service-ifz.jar",
				projectLocation + "/" + projectName, megaProcess, macroProcess,
				process, subProcess, projectVersion, installPath
						+ "/templates/projects/java/settings/gradle", false);
		ProjectUtil.addPackageToProject("service-ifz.jar", "/src/main/java",
				new String[] { "pe.gob.sunat." + megaProcess + "."
						+ macroProcess + "." + process + ".service" });
		createJavaProjectFiles(installPath, projectType, "service-ifz.jar",
				projectLocation + "/" + projectName, megaProcess, macroProcess,
				process, subProcess, projectVersion);

		ProjectUtil.createJavaProject("service-imp.jar", projectLocation + "/"
				+ projectName, installPath + "/templates/projects/java");
		createGradleSettingsFiles(projectType, "service-imp.jar",
				projectLocation + "/" + projectName, megaProcess, macroProcess,
				process, subProcess, projectVersion, installPath
						+ "/templates/projects/java/settings/gradle", false);
		ProjectUtil.addPackageToProject("service-imp.jar", "/src/main/java",
				new String[] { "pe.gob.sunat." + megaProcess + "."
						+ macroProcess + "." + process + ".service" });
		createJavaProjectFiles(installPath, projectType, "service-imp.jar",
				projectLocation + "/" + projectName, megaProcess, macroProcess,
				process, subProcess, projectVersion);

		ProjectUtil.createEnterpriseProject("slb.ear", projectLocation + "/"
				+ projectName, installPath + "/templates/projects/enterprise");
		createGradleSettingsFiles(projectType, "slb.ear", projectLocation + "/"
				+ projectName, megaProcess, macroProcess, process, subProcess,
				projectVersion, installPath
						+ "/templates/projects/enterprise/settings/gradle",
				false);
		createEnterpriseProjectFiles(installPath, projectType, "slb.ear",
				projectLocation + "/" + projectName, megaProcess, macroProcess,
				process, subProcess, projectVersion);

		addFilteredProjects(installPath, projectType, projectName,
				projectLocation, megaProcess, macroProcess, process,
				subProcess, projectVersion);

		ProjectUtil.refreshProject(projectName);
	}

	private static void createRootProject(String installPath,
			String projectType, String projectName, String projectLocation,
			String megaProcess, String macroProcess, String process,
			String subProcess, String projectVersion) {
		// TODO Auto-generated method stub
		ProjectUtil.createParentProject(projectName, projectLocation,
				installPath + "/templates/projects/java", true);

		createGradleProjectFolders(installPath, projectType, projectName,
				megaProcess, macroProcess, process, subProcess, projectVersion);
		createGradleProjectFiles(projectType, projectName, projectLocation,
				megaProcess, macroProcess, process, subProcess, projectVersion,
				installPath + "/templates/applications/" + projectType
						+ "/gradle/files/build.gradle", "/", "build.gradle");
		createGradleProjectFiles(projectType, projectName, projectLocation,
				megaProcess, macroProcess, process, subProcess, projectVersion,
				installPath + "/templates/applications/" + projectType
						+ "/gradle/files/settings.gradle", "/",
				"settings.gradle");
	}

	private static void createGradleSettingsFiles(String projectType,
			String projectName, String projectLocation, String megaProcess,
			String macroProcess, String process, String subProcess,
			String projectVersion, String templateFolder,
			boolean includeCreatedProjects) {
		String[] natureIds = { "org.springsource.ide.eclipse.gradle.core.nature" };
		ProjectUtil.addNatureToProject(projectName, natureIds);

		StringBuilder projectsCreated = new StringBuilder();
		StringBuilder buildFamily = new StringBuilder();
		if (includeCreatedProjects) {
			if ("webapp".equals(projectType)) {
				projectsCreated.append("projects=;");
				projectsCreated.append(macroProcess + "-" + process
						+ "-controller.jar");
				projectsCreated.append(";");
				projectsCreated.append("ia" + process + subProcess + ".war");
				projectsCreated.append(";");
				projectsCreated.append("it" + process + subProcess + ".war");
				projectsCreated.append(";");
				projectsCreated.append(megaProcess + "-" + macroProcess + "-"
						+ process + ".ear");
				projectsCreated.append(";");
				projectsCreated.append("\n");

				buildFamily
						.append("build.family.org.gradle.tooling.model.eclipse.HierarchicalEclipseProject=;");
				buildFamily.append(macroProcess + "-" + process
						+ "-controller.jar");
				buildFamily.append(";");
				buildFamily.append("ia" + process + subProcess + ".war");
				buildFamily.append(";");
				buildFamily.append("it" + process + subProcess + ".war");
				buildFamily.append(";");
				buildFamily.append(megaProcess + "-" + macroProcess + "-"
						+ process + ".ear");
				buildFamily.append(";");
				buildFamily.append("\n");
			} else if ("backend".equals(projectType)) {
				projectsCreated.append("projects=;");
				projectsCreated.append(macroProcess + "-" + process
						+ "ws-controller.jar");
				projectsCreated.append(";");
				projectsCreated.append("ia" + process + subProcess + "-ws.war");
				projectsCreated.append(";");
				projectsCreated.append(megaProcess + "-" + macroProcess + "-"
						+ process + "-backend.ear");
				projectsCreated.append(";");
				projectsCreated.append("\n");

				buildFamily
						.append("build.family.org.gradle.tooling.model.eclipse.HierarchicalEclipseProject=;");
				buildFamily.append(macroProcess + "-" + process
						+ "ws-controller.jar");
				buildFamily.append(";");
				buildFamily.append("ia" + process + subProcess + "-ws.war");
				buildFamily.append(";");
				buildFamily.append(megaProcess + "-" + macroProcess + "-"
						+ process + "-backend.ear");
				buildFamily.append(";");
				buildFamily.append("\n");
			} else if ("batch".equals(projectType)) {
				projectsCreated.append("projects=;");
				projectsCreated.append("ejb" + macroProcess + "-" + process
						+ "mdb.jar");
				projectsCreated.append(";");
				projectsCreated.append(megaProcess + "-" + macroProcess + "-"
						+ process + "-batch.ear");
				projectsCreated.append(";");
				projectsCreated.append("\n");

				buildFamily
						.append("build.family.org.gradle.tooling.model.eclipse.HierarchicalEclipseProject=;");
				buildFamily.append("ejb" + macroProcess + "-" + process
						+ "mdb.jar");
				buildFamily.append(";");
				buildFamily.append(megaProcess + "-" + macroProcess + "-"
						+ process + "-batch.ear");
				buildFamily.append(";");
				buildFamily.append("\n");
			} else if ("sharedlib".equals(projectType)) {
				projectsCreated.append("projects=;");
				projectsCreated.append("dao-ifz.jar");
				projectsCreated.append(";");
				projectsCreated.append("dao-imp.jar");
				projectsCreated.append(";");
				projectsCreated.append("model.jar");
				projectsCreated.append(";");
				projectsCreated.append("service-ifz.jar");
				projectsCreated.append(";");
				projectsCreated.append("service-imp.jar");
				projectsCreated.append(";");
				projectsCreated.append("slb.ear");
				projectsCreated.append(";");
				projectsCreated.append("\n");

				buildFamily
						.append("build.family.org.gradle.tooling.model.eclipse.HierarchicalEclipseProject=;");
				buildFamily.append("dao-ifz.jar");
				buildFamily.append(";");
				buildFamily.append("dao-imp.jar");
				buildFamily.append(";");
				buildFamily.append("model.jar");
				buildFamily.append(";");
				buildFamily.append("service-ifz.jar");
				buildFamily.append(";");
				buildFamily.append("service-imp.jar");
				buildFamily.append(";");
				buildFamily.append("slb.ear");
				buildFamily.append(";");
				buildFamily.append("\n");
			}

			String content = FileUtil.readFileToString(templateFolder
					+ "/org.springsource.ide.eclipse.gradle.core.import.prefs");
			content = content.replaceAll("projectsCreated",
					projectsCreated.toString());
			ProjectUtil.addFileToProject(projectName, ".settings/gradle",
					"org.springsource.ide.eclipse.gradle.core.import.prefs",
					content);
		}

		String content = FileUtil.readFileToString(templateFolder
				+ "/org.springsource.ide.eclipse.gradle.core.prefs");
		if (includeCreatedProjects) {
			content = FileUtil.readFileToString(templateFolder
					+ "/org.springsource.ide.eclipse.gradle.core.prefs-root");

			content = content.replaceAll("buildFamily", buildFamily.toString());
		}
		ProjectUtil.addFileToProject(projectName, ".settings/gradle",
				"org.springsource.ide.eclipse.gradle.core.prefs", content);

		Map<String, String> filePath1 = new HashMap<String, String>();
		filePath1.put("destFilename",
				"org.springsource.ide.eclipse.gradle.refresh.prefs");
		filePath1.put("oriFilepath", templateFolder
				+ "/org.springsource.ide.eclipse.gradle.refresh.prefs");

		List<Map<String, String>> filePaths = Arrays.asList(filePath1);
		ProjectUtil.copyFilesToProject(projectName, ".settings/gradle",
				filePaths);
	}

	private static void createJavaProjectFiles(String installPath,
			String projectType, String projectName, String projectLocation,
			String megaProcess, String macroProcess, String process,
			String subProcess, String projectVersion) {
		// TODO Auto-generated method stub
		if ("batch".equals(projectType)) {
			String content = FileUtil.readFileToString(installPath
					+ "/templates/applications/" + projectType
					+ "/java/files/ejb-jar.xml");
			content = content.replaceAll("macroProcess", macroProcess);
			content = content.replaceAll("process", process);
			ProjectUtil.addFileToProject(projectName,
					"/src/main/resources/META-INF", "ejb-jar.xml", content);

			Map<String, String> filePath1 = new HashMap<String, String>();
			filePath1.put("destFilename", "weblogic-ejb-jar.xml");
			filePath1.put("oriFilepath", installPath
					+ "/templates/applications/" + projectType
					+ "/java/files/weblogic-ejb-jar.xml");
			List<Map<String, String>> filePaths = Arrays.asList(filePath1);
			ProjectUtil.copyFilesToProject(projectName,
					"/src/main/resources/META-INF", filePaths);
		} else if ("sharedlib".equals(projectType)) {
			Map<String, String> filePath1 = new HashMap<String, String>();
			filePath1.put("destFilename", "beans.xml");
			filePath1.put("oriFilepath", installPath
					+ "/templates/applications/" + projectType
					+ "/java/files/beans.xml");
			List<Map<String, String>> filePaths = Arrays.asList(filePath1);
			ProjectUtil.copyFilesToProject(projectName,
					"/src/main/resources/META-INF", filePaths);

			if ("dao-imp.jar".equals(projectName)) {
				String content = FileUtil.readFileToString(installPath
						+ "/templates/applications/" + projectType
						+ "/java/files/persistence.xml");
				content = content.replaceAll("megaProcess", megaProcess);
				content = content.replaceAll("macroProcess", macroProcess);
				content = content.replaceAll("process", process);
				ProjectUtil.addFileToProject(projectName,
						"/src/main/resources/META-INF", "persistence.xml",
						content);
			}
		} else {
			Map<String, String> filePath1 = new HashMap<String, String>();
			filePath1.put("destFilename", process + ".properties");
			filePath1.put("oriFilepath", installPath
					+ "/templates/projects/java/files/proceso.properties");
			List<Map<String, String>> filePaths = Arrays.asList(filePath1);
			ProjectUtil.copyFilesToProject(projectName, "/src/main/resources",
					filePaths);
		}

		String templateFilepath = installPath + "/templates/applications/"
				+ projectType + "/java/gradle/build.gradle";
		if ("sharedlib".equals(projectType)) {
			templateFilepath = installPath + "/templates/applications/"
					+ projectType + "/java/gradle/build_"
					+ projectName.replace(".jar", "") + ".gradle";
		}
		createGradleProjectFiles(projectType, projectName, projectLocation,
				megaProcess, macroProcess, process, subProcess, projectVersion,
				templateFilepath, "/", "build.gradle");
	}

	private static void createWebProjectFiles(String installPath,
			String projectType, String projectName, String projectLocation,
			String megaProcess, String macroProcess, String process,
			String subProcess, String projectVersion) {
		// TODO Auto-generated method stub
		if (projectName.startsWith("it")) {
			Map<String, String> filePath1 = new HashMap<String, String>();
			filePath1.put("destFilename", "bootstrap-datetimepicker.css");
			filePath1
					.put("oriFilepath",
							installPath
									+ "/templates/projects/web/files/angular/css/bootstrap-datetimepicker.css");
			Map<String, String> filePath2 = new HashMap<String, String>();
			filePath2.put("destFilename", "style-map.css");
			filePath2
					.put("oriFilepath",
							installPath
									+ "/templates/projects/web/files/angular/css/style-map.css");
			Map<String, String> filePath3 = new HashMap<String, String>();
			filePath3.put("destFilename", "style.css");
			filePath3.put("oriFilepath", installPath
					+ "/templates/projects/web/files/angular/css/style.css");
			List<Map<String, String>> filePaths = Arrays.asList(filePath1,
					filePath2, filePath3);
			ProjectUtil.copyFilesToProject(projectName, "/src/main/webapp/css",
					filePaths);

			filePath1 = new HashMap<String, String>();
			filePath1.put("destFilename", "angular-google-maps.min.js");
			filePath1
					.put("oriFilepath",
							installPath
									+ "/templates/projects/web/files/angular/js/libs/angular-google-maps.min.js");
			filePath2 = new HashMap<String, String>();
			filePath2.put("destFilename", "angular-qr.js");
			filePath2
					.put("oriFilepath",
							installPath
									+ "/templates/projects/web/files/angular/js/libs/angular-qr.js");
			filePath3 = new HashMap<String, String>();
			filePath3.put("destFilename", "angularjs.min.js");
			filePath3
					.put("oriFilepath",
							installPath
									+ "/templates/projects/web/files/angular/js/libs/angularjs.min.js");
			Map<String, String> filePath4 = new HashMap<String, String>();
			filePath4.put("destFilename", "barcode.js");
			filePath4
					.put("oriFilepath",
							installPath
									+ "/templates/projects/web/files/angular/js/libs/barcode.js");
			Map<String, String> filePath5 = new HashMap<String, String>();
			filePath5.put("destFilename", "bootstrap-datetimepicker.js");
			filePath5
					.put("oriFilepath",
							installPath
									+ "/templates/projects/web/files/angular/js/libs/bootstrap-datetimepicker.js");
			Map<String, String> filePath6 = new HashMap<String, String>();
			filePath6.put("destFilename", "bsDropdown.js");
			filePath6
					.put("oriFilepath",
							installPath
									+ "/templates/projects/web/files/angular/js/libs/bsDropdown.js");
			Map<String, String> filePath7 = new HashMap<String, String>();
			filePath7.put("destFilename", "lodash.underscore.min.js");
			filePath7
					.put("oriFilepath",
							installPath
									+ "/templates/projects/web/files/angular/js/libs/lodash.underscore.min.js");
			Map<String, String> filePath8 = new HashMap<String, String>();
			filePath8.put("destFilename", "moment-with-locales.js");
			filePath8
					.put("oriFilepath",
							installPath
									+ "/templates/projects/web/files/angular/js/libs/moment-with-locales.js");
			Map<String, String> filePath9 = new HashMap<String, String>();
			filePath9.put("destFilename", "qrcode.js");
			filePath9
					.put("oriFilepath",
							installPath
									+ "/templates/projects/web/files/angular/js/libs/qrcode.js");
			Map<String, String> filePath10 = new HashMap<String, String>();
			filePath10.put("destFilename", "services-captcha.js");
			filePath10
					.put("oriFilepath",
							installPath
									+ "/templates/projects/web/files/angular/js/libs/services-captcha.js");
			filePaths = Arrays.asList(filePath1, filePath2, filePath3,
					filePath4, filePath5, filePath6, filePath7, filePath8,
					filePath9, filePath10);
			ProjectUtil.copyFilesToProject(projectName,
					"/src/main/webapp/js/libs", filePaths);

			// Incluir los archivos necesarios por los componentes
			String[] cssFiles = new String[] { "calendario.css",
					"codigo-qr.css", "google-maps.css" };
			filePaths = new ArrayList<Map<String, String>>();
			for (String cssFile : cssFiles) {
				Map<String, String> filePath = new HashMap<String, String>();
				filePath.put("destFilename", cssFile);
				filePath.put("oriFilepath", installPath
						+ "/templates/components/css/" + cssFile);

				filePaths.add(filePath);
			}
			ProjectUtil.copyFilesToProject(projectName, "/src/main/webapp/css",
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
			ProjectUtil.copyFilesToProject(projectName, "/src/main/webapp/js",
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
			ProjectUtil.copyFilesToProject(projectName, "/src/main/webapp",
					filePaths);

			jsFiles = new String[] { "dni.js", "25723525.json",
					"29730310.json", "ubigeo.js" };
			filePaths = new ArrayList<Map<String, String>>();
			for (String jsFile : jsFiles) {
				Map<String, String> filePath = new HashMap<String, String>();
				filePath.put("destFilename", jsFile);
				filePath.put("oriFilepath", installPath
						+ "/templates/forms/js/" + jsFile);

				filePaths.add(filePath);
			}
			ProjectUtil.copyFilesToProject(projectName, "/src/main/webapp/js",
					filePaths);

			htmlFiles = new String[] { "dni-form.html", "ubigeo-form.html" };
			filePaths = new ArrayList<Map<String, String>>();
			for (String htmlFile : htmlFiles) {
				Map<String, String> filePath = new HashMap<String, String>();
				filePath.put("destFilename", htmlFile);
				filePath.put("oriFilepath", installPath
						+ "/templates/forms/html/" + htmlFile);

				filePaths.add(filePath);
			}
			ProjectUtil.copyFilesToProject(projectName, "/src/main/webapp",
					filePaths);
			// Incluir los archivos necesarios por los componentes

			ProjectUtil.removeFileFromProject(projectName, "/src/main/webapp",
					"index.html");

			filePath1 = new HashMap<String, String>();
			filePath1.put("destFilename", "index.html");
			filePath1.put("oriFilepath", installPath
					+ "/templates/projects/web/files/angular/index.html");
			filePaths = Arrays.asList(filePath1);
			ProjectUtil.copyFilesToProject(projectName, "/src/main/webapp",
					filePaths);
		}

		if ("backend".equals(projectType)) {
			// || projectName.startsWith("ia")
			ProjectUtil.removeFileFromProject(projectName, "/src/main/webapp",
					"index.html");
			ProjectUtil.removeFileFromProject(projectName,
					"/src/main/webapp/WEB-INF", "web.xml");

			String content = FileUtil.readFileToString(installPath
					+ "/templates/applications/" + projectType
					+ "/web/files/web.xml");
			content = content.replaceAll("projectName",
					projectName.replaceAll(".war", ""));
			ProjectUtil.addFileToProject(projectName,
					"/src/main/webapp/WEB-INF", "web.xml", content);

			// if ("backend".equals(projectType)) {
			Map<String, String> filePath1 = new HashMap<String, String>();
			filePath1.put("destFilename", "beans.xml");
			filePath1.put("oriFilepath", installPath
					+ "/templates/applications/" + projectType
					+ "/web/files/beans.xml");
			List<Map<String, String>> filePaths = Arrays.asList(filePath1);
			ProjectUtil.copyFilesToProject(projectName,
					"/src/main/webapp/WEB-INF", filePaths);
			// }
		}

		String content = FileUtil.readFileToString(installPath
				+ "/templates/applications/" + projectType
				+ "/web/files/weblogic.xml");
		content = content.replaceAll("projectNameUpperCase", projectName
				.replaceAll(".war", "").replaceAll("-", "").toUpperCase());
		content = content.replaceAll("projectName",
				projectName.replaceAll(".war", ""));
		ProjectUtil.addFileToProject(projectName, "/src/main/webapp/WEB-INF",
				"weblogic.xml", content);

		createGradleProjectFiles(projectType, projectName, projectLocation,
				megaProcess, macroProcess, process, subProcess, projectVersion,
				installPath + "/templates/applications/" + projectType
						+ "/web/gradle/build.gradle", "/", "build.gradle");
	}

	private static void createEnterpriseProjectFiles(String installPath,
			String projectType, String projectName, String projectLocation,
			String megaProcess, String macroProcess, String process,
			String subProcess, String projectVersion) {
		// TODO Auto-generated method stub
		String content = FileUtil.readFileToString(installPath
				+ "/templates/applications/" + projectType
				+ "/enterprise/files/weblogic-application.xml");
		content = content.replaceAll("megaProcess", megaProcess);
		content = content.replaceAll("macroProcess", macroProcess);
		content = content.replaceAll("process", process);
		ProjectUtil.addFileToProject(projectName,
				"/src/main/application/META-INF", "weblogic-application.xml",
				content);

		createGradleProjectFiles(projectType, projectName, projectLocation,
				megaProcess, macroProcess, process, subProcess, projectVersion,
				installPath + "/templates/applications/" + projectType
						+ "/enterprise/gradle/build.gradle", "/",
				"build.gradle");
	}

	private static void createGradleProjectFolders(String installPath,
			String projectType, String projectName, String megaProcess,
			String macroProcess, String process, String subProcess,
			String projectVersion) {
		// TODO Auto-generated method stub
		String[] projectFolders = { ".gradle/2.6/taskArtifacts",
				".gradle/2.12/taskArtifacts", "dependencias" };
		ProjectUtil.addFolderToProject(projectName, projectFolders);

		String content = FileUtil.readFileToString(installPath
				+ "/templates/applications/" + projectType
				+ "/gradle/settings/dependencias.gradle");
		ProjectUtil.addFileToProject(projectName, "/dependencias",
				"dependencias.gradle", content);
	}

	private static void createGradleProjectFiles(String projectType,
			String projectName, String projectLocation, String megaProcess,
			String macroProcess, String process, String subProcess,
			String projectVersion, String templateFilepath, String folder,
			String filename) {
		// TODO Auto-generated method stub
		String content = FileUtil.readFileToString(templateFilepath);
		content = content.replaceAll("megaProcess", megaProcess);
		content = content.replaceAll("macroProcess", macroProcess);
		content = content.replaceAll("subProcess", subProcess);
		content = content.replaceAll("process", process);

		if ("/".equals(folder)) {
			FileUtil.writeStringToFile(projectLocation + "/" + projectName
					+ folder + filename, content);
		} else {
			ProjectUtil
					.addFileToProject(projectName, folder, filename, content);
		}
	}

	private static void addFilteredProjects(String installPath,
			String projectType, String projectName, String projectLocation,
			String megaProcess, String macroProcess, String process,
			String subProcess, String projectVersion) {
		String[] filteredProjects = new String[] {};
		if ("webapp".equals(projectType)) {
			filteredProjects = new String[] {
					macroProcess + "-" + process + "-controller.jar",
					"ia" + process + subProcess + ".war",
					"it" + process + subProcess + ".war",
					megaProcess + "-" + macroProcess + "-" + process + ".ear" };
		} else if ("backend".equals(projectType)) {
			filteredProjects = new String[] {
					macroProcess + "-" + process + "ws-controller.jar",
					"ia" + process + subProcess + "-ws.war",
					megaProcess + "-" + macroProcess + "-" + process
							+ "-backend.ear" };
		} else if ("batch".equals(projectType)) {
			filteredProjects = new String[] {
					"ejb" + macroProcess + "-" + process + "mdb.jar",
					megaProcess + "-" + macroProcess + "-" + process
							+ "-batch.ear" };
		} else if ("sharedlib".equals(projectType)) {
			filteredProjects = new String[] { "dao-ifz.jar", "dao-imp.jar",
					"model.jar", "service-ifz.jar", "service-imp.jar",
					"slb.ear" };
		}

		ProjectUtil.addFilteredProjects(projectName, filteredProjects);
	}

}
