package pe.gob.sunat.common.helper;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.FileInfoMatcherDescription;
import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceFilterDescription;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.JavaRuntime;

public final class ProjectUtil {

	protected ProjectUtil() {
		// TODO Auto-generated constructor stub
		throw new UnsupportedOperationException();
	}

	// https://github.com/droolsjbpm/droolsjbpm-tools
	// https://github.com/droolsjbpm/droolsjbpm-tools/blob/master/drools-eclipse/org.drools.eclipse/src/main/java/org/drools/eclipse/wizard/project/NewDroolsProjectWizard.java
	// http://www.javased.com/index.php?source_dir=droolsjbpm-tools/drools-eclipse/org.drools.eclipse/src/main/java/org/drools/eclipse/wizard/project/NewDroolsProjectWizard.java
	// http://www.javased.com/?api=org.eclipse.core.resources.IFolder
	// http://www.programcreek.com/java-api-examples/index.php?class=org.eclipse.jdt.core.JavaCore&method=newSourceEntry
	// http://alvinalexander.com/java/jwarehouse/eclipse/org.eclipse.jdt.ui.tests/ui/org/eclipse/jdt/ui/tests/wizardapi/NewJavaProjectWizardTest.java.shtml
	public static void createParentProject(String projectName,
			String projectLocation, String templateFolder,
			boolean defaultConfiguration) {
		validateProjectName(projectName);
		createProject(projectName, projectLocation + "/" + projectName);

		String[] natureIds = { JavaCore.NATURE_ID,
				"org.eclipse.jem.workbench.JavaEMFNature" };
		addNatureToProject(projectName, natureIds);

		String[] buildCommands = { "org.eclipse.jdt.core.javabuilder" };
		// addBuildCommandToProject(projectName, buildCommands);

		if (defaultConfiguration) {
			setDefaultClasspath(projectName);
			setJRELibrary(projectName);

			Map<String, String> container1 = new HashMap<String, String>();
			container1.put("containerPath",
					"org.springsource.ide.eclipse.gradle.classpathcontainer");
			container1.put("isExported", "true");
			List<Map<String, String>> containers = Arrays.asList(container1);
			setContainerEntry(projectName, containers);

			String ouputLocation = "build";
			setOutputLocation(projectName, ouputLocation);
		}

		createJavaSettingsFiles(projectName, templateFolder + "/settings");
	}

	public static void createJavaProject(String projectName,
			String projectLocation, String templateFolder) {
		createParentProject(projectName, projectLocation, templateFolder, false);

		try {
			setDefaultClasspath(projectName);

			Map<String, String> sourceFolder1 = new HashMap<String, String>();
			sourceFolder1.put("path", "src/main/java");
			sourceFolder1.put("specificOutputLocation", "target/classes");

			Map<String, String> sourceFolder2 = new HashMap<String, String>();
			sourceFolder2.put("path", "src/main/resources");
			sourceFolder2.put("inclusionPatterns", "**/*.java");
			sourceFolder2.put("specificOutputLocation", "target/classes");

			Map<String, String> sourceFolder3 = new HashMap<String, String>();
			sourceFolder3.put("path", "src/test/java");
			sourceFolder3.put("specificOutputLocation", "target/test-classes");

			Map<String, String> sourceFolder4 = new HashMap<String, String>();
			sourceFolder4.put("path", "src/test/resources");
			sourceFolder4.put("inclusionPatterns", "**/*.java");
			sourceFolder4.put("specificOutputLocation", "target/test-classes");

			List<Map<String, String>> sourceFolders = Arrays.asList(
					sourceFolder1, sourceFolder2, sourceFolder3, sourceFolder4);
			setSourceFolders(projectName, sourceFolders);

			setJRELibrary(projectName);

			Map<String, String> container1 = new HashMap<String, String>();
			container1.put("containerPath",
					"org.springsource.ide.eclipse.gradle.classpathcontainer");
			container1.put("isExported", "true");
			List<Map<String, String>> containers = Arrays.asList(container1);
			setContainerEntry(projectName, containers);

			String ouputLocation = "target/classes";
			setOutputLocation(projectName, ouputLocation);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createWebProject(String projectName,
			String projectLocation, String templateFolder,
			boolean includeWebFolders) {
		createJavaProject(projectName, projectLocation, templateFolder);

		Map<String, String> wbResource1 = new HashMap<String, String>();
		wbResource1.put("deployPath", "/");
		wbResource1.put("sourcePath", "/src/main/webapp");
		Map<String, String> wbResource2 = new HashMap<String, String>();
		wbResource2.put("deployPath", "/WEB-INF/classes");
		wbResource2.put("sourcePath", "/src/main/java");
		Map<String, String> wbResource3 = new HashMap<String, String>();
		wbResource3.put("deployPath", "/WEB-INF/classes");
		wbResource3.put("sourcePath", "/src/main/resources");

		List<Map<String, String>> wbResources = Arrays.asList(wbResource1,
				wbResource2, wbResource3);
		createWebSettingsFiles(projectName, templateFolder + "/settings",
				wbResources);

		try {
			String[] natureIds = {
					"org.eclipse.wst.common.modulecore.ModuleCoreNature",
					"org.eclipse.wst.common.project.facet.core.nature",
					"org.eclipse.wst.jsdt.core.jsNature" };
			addNatureToProject(projectName, natureIds);

			String[] projectFolders = { "src/main/webapp/WEB-INF",
					"src/main/webapp/WEB-INF/lib" };
			if (includeWebFolders) {
				projectFolders = new String[] { "src/main/webapp/css",
						"src/main/webapp/js", "src/main/webapp/WEB-INF",
						"src/main/webapp/WEB-INF/html",
						"src/main/webapp/WEB-INF/lib" };
			}
			addFolderToProject(projectName, projectFolders);

			String content = FileUtil.readFileToString(templateFolder
					+ "/files/WEB-INF/web.xml");
			content = content.replaceAll("projectName",
					projectName.replaceAll(".war", ""));
			addFileToProject(projectName, "/src/main/webapp/WEB-INF",
					"web.xml", content);

			Map<String, String> filePath = new HashMap<String, String>();
			filePath = new HashMap<String, String>();
			filePath.put("destFilename", "index.html");
			filePath.put("oriFilepath", templateFolder + "/files/index.html");
			List<Map<String, String>> filePaths = Arrays.asList(filePath);
			copyFilesToProject(projectName, "/src/main/webapp", filePaths);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createEnterpriseProject(String projectName,
			String projectLocation, String templateFolder) {
		createParentProject(projectName, projectLocation, templateFolder, false);

		try {
			setDefaultClasspath(projectName);

			Map<String, String> sourceFolder = new HashMap<String, String>();
			sourceFolder.put("path", "src/main/application");
			sourceFolder.put("specificOutputLocation", "target/classes");
			List<Map<String, String>> sourceFolders = Arrays
					.asList(sourceFolder);
			setSourceFolders(projectName, sourceFolders);

			setJRELibrary(projectName);

			Map<String, String> container1 = new HashMap<String, String>();
			container1.put("containerPath",
					"org.springsource.ide.eclipse.gradle.classpathcontainer");
			container1.put("isExported", "true");
			List<Map<String, String>> containers = Arrays.asList(container1);
			setContainerEntry(projectName, containers);

			String ouputLocation = "target/classes";
			setOutputLocation(projectName, ouputLocation);

			String[] projectFolders = { "src/main/application/META-INF" };
			addFolderToProject(projectName, projectFolders);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void addNatureToProject(String projectName, String[] natureIds) {
		try {
			IProject project = findProject(projectName);

			IProjectDescription description = project.getDescription();
			String[] prevNatureIds = description.getNatureIds();
			for (String natureId : natureIds) {
				if (!project.hasNature(natureId)) {
					String[] newNatureIds = new String[prevNatureIds.length + 1];
					System.arraycopy(prevNatureIds, 0, newNatureIds, 0,
							prevNatureIds.length);
					newNatureIds[prevNatureIds.length] = natureId;

					prevNatureIds = new String[newNatureIds.length];
					System.arraycopy(newNatureIds, 0, prevNatureIds, 0,
							newNatureIds.length);
				}
			}
			description.setNatureIds(prevNatureIds);

			project.setDescription(description, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void addBuildCommandToProject(String projectName,
			String[] buildCommands) {
		try {
			IProject project = findProject(projectName);

			IProjectDescription description = project.getDescription();
			ICommand[] prevBuildSpec = description.getBuildSpec();
			for (String buildCommand : buildCommands) {
				if (!project.hasBuildConfig(buildCommand)) {
					ICommand[] newBuildSpec = new ICommand[prevBuildSpec.length + 1];
					System.arraycopy(prevBuildSpec, 0, newBuildSpec, 0,
							prevBuildSpec.length);
					ICommand iCommand = description.newCommand();
					iCommand.setBuilderName(buildCommand);
					newBuildSpec[prevBuildSpec.length] = iCommand;

					prevBuildSpec = new ICommand[newBuildSpec.length];
					System.arraycopy(newBuildSpec, 0, prevBuildSpec, 0,
							newBuildSpec.length);
				}
			}
			description.setBuildSpec(prevBuildSpec);

			project.setDescription(description, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void addFilteredProjects(String projectName,
			String[] filteredProjects) {
		try {
			IProject project = findProject(projectName);

			// IPath parentPath =
			// project.getLocation().toFile().getAbsolutePath();

			List<FileInfoMatcherDescription> matcherList = new ArrayList<FileInfoMatcherDescription>(
					filteredProjects.length);
			for (String filteredProject : filteredProjects) {
				IProject filteredIProject = ResourcesPlugin.getWorkspace()
						.getRoot().getProject(filteredProject);
				if (filteredIProject.exists()) {
					// IPath childPath =
					// filteredIProject.getLocation().toFile().getAbsolutePath();

					// if (parentPath.isPrefixOf(childPath)) {
					FileInfoMatcherDescription matcher = new FileInfoMatcherDescription(
							"org.eclipse.ui.ide.multiFilter",
							"1.0-projectRelativePath-equals-true-false-"
									+ filteredProject);

					matcherList.add(matcher);
					// }
				}
			}

			if (matcherList.size() > 0) {
				project.createFilter(
						IResourceFilterDescription.EXCLUDE_ALL
								| IResourceFilterDescription.FOLDERS
								| IResourceFilterDescription.INHERITABLE,
						new FileInfoMatcherDescription(
								"org.eclipse.ui.ide.orFilterMatcher",
								matcherList
										.toArray(new FileInfoMatcherDescription[matcherList
												.size()])),
						IResource.BACKGROUND_REFRESH, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String[] removeFilteredProjects(String projectName) {
		List<String> filteredProjects = new ArrayList<String>();
		try {
			IProject project = findProject(projectName);

			IResourceFilterDescription[] filters = project.getFilters();
			for (IResourceFilterDescription filter : filters) {
				FileInfoMatcherDescription matcher = filter
						.getFileInfoMatcherDescription();
				if (matcher != null
						&& "org.eclipse.ui.ide.orFilterMatcher".equals(matcher
								.getId())
						&& matcher.getArguments() instanceof FileInfoMatcherDescription[]) {
					FileInfoMatcherDescription[] argumentsMatcher = (FileInfoMatcherDescription[]) matcher
							.getArguments();
					for (FileInfoMatcherDescription argumentMatcher : argumentsMatcher) {
						if (argumentMatcher != null
								&& "org.eclipse.ui.ide.multiFilter"
										.equals(argumentMatcher.getId())
								&& argumentMatcher.getArguments() instanceof String) {
							String filteredProject = (String) argumentMatcher
									.getArguments();

							filteredProjects
									.add(filteredProject
											.replaceAll(
													"1.0-projectRelativePath-equals-true-false-",
													""));
						}
					}

					filter.delete(IResource.BACKGROUND_REFRESH, null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return filteredProjects.toArray(new String[filteredProjects.size()]);
	}

	public static void addPackageToProject(String projectName,
			String sourceFolder, String[] packageNames) {
		try {
			IProject project = findProject(projectName);

			IJavaProject javaProject = JavaCore.create(project);
			IFolder srcFolder = javaProject.getProject()
					.getFolder(sourceFolder);
			createFolder(srcFolder);

			IPackageFragmentRoot iPackageFragmentRoot = javaProject
					.getPackageFragmentRoot(srcFolder);
			for (String packageName : packageNames) {
				iPackageFragmentRoot.createPackageFragment(packageName, false,
						null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void addFolderToProject(String projectName,
			String[] projectPaths) {
		try {
			IProject project = findProject(projectName);

			for (String projectPath : projectPaths) {
				IFolder folder = project.getFolder(projectPath);

				createFolder(folder);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void addFileToProject(String projectName, String folder,
			String filename, String content) {
		Assert.isNotNull(projectName);
		Assert.isNotNull(folder);
		Assert.isNotNull(filename);
		Assert.isNotNull(content);

		try {
			IProject project = findProject(projectName);

			IFolder projectFolder = project.getFolder(folder);
			createFolder(projectFolder);

			IFile projectFile = projectFolder.getFile(filename);
			if (!projectFile.exists()) {
				projectFile.create(
						new ByteArrayInputStream(content.getBytes()), true,
						null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void removeFileFromProject(String projectName, String folder,
			String filename) {
		Assert.isNotNull(projectName);
		Assert.isNotNull(folder);
		Assert.isNotNull(filename);

		try {
			IProject project = findProject(projectName);

			IFolder projectFolder = project.getFolder(folder);
			if (projectFolder.exists()) {
				IFile projectFile = projectFolder.getFile(filename);

				if (projectFile.exists()) {
					projectFile.delete(true, true, null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void copyFilesToProject(String projectName, String folder,
			List<Map<String, String>> filePaths) {
		Assert.isNotNull(projectName);
		Assert.isNotNull(folder);

		try {
			IProject project = findProject(projectName);

			IFolder projectFolder = project.getFolder(folder);
			createFolder(projectFolder);

			for (Map<String, String> filePath : filePaths) {
				IFile projectFile = projectFolder.getFile(filePath
						.get("destFilename"));
				if (!projectFile.exists()) {
					String content = FileUtil.readFileToString(filePath
							.get("oriFilepath"));

					projectFile.create(
							new ByteArrayInputStream(content.getBytes()), true,
							null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void refreshProject(String projectName) {
		try {
			IProject project = findProject(projectName);

			if (!project.isOpen()) {
				project.open(null);
			}

			project.refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void buildProject(String projectName) {
		try {
			IProject project = findProject(projectName);

			if (!project.isOpen()) {
				project.open(null);
			}

			project.build(IncrementalProjectBuilder.FULL_BUILD, null); // new
																		// NullProgressMonitor()
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void validateProjectName(String projectName) {
		Assert.isNotNull(projectName);
		Assert.isTrue(projectName.trim().length() > 0);
	}

	private static void createProject(String projectName, String projectFolder) {
		try {
			IProject project = ResourcesPlugin.getWorkspace().getRoot()
					.getProject(projectName);

			URI pathProjectFolder = (new File(projectFolder)).toURI();
			URI pathFolder = pathProjectFolder;
			if (pathProjectFolder != null
					&& pathProjectFolder.equals(ResourcesPlugin.getWorkspace()
							.getRoot().getLocationURI())) {
				pathFolder = null;
			}

			IProjectDescription projectDescription = project.getWorkspace()
					.newProjectDescription(project.getName());
			projectDescription.setLocationURI(pathFolder);
			project.create(projectDescription, null);
			if (!project.isOpen()) {
				project.open(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static IProject findProject(String projectName) {
		Assert.isNotNull(projectName);

		IProject project = ResourcesPlugin.getWorkspace().getRoot()
				.getProject(projectName);
		Assert.isTrue(project.exists());

		return project;
	}

	private static void setDefaultClasspath(String projectName) {
		try {
			IProject project = findProject(projectName);

			IJavaProject javaProject = JavaCore.create(project);
			javaProject.setRawClasspath(new IClasspathEntry[0], null);
			// javaProject.save(null, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void setSourceFolders(String projectName,
			List<Map<String, String>> sourceFolders) {
		try {
			IProject project = findProject(projectName);
			IJavaProject javaProject = JavaCore.create(project);

			List<IClasspathEntry> classpathEntryList = new ArrayList<IClasspathEntry>();
			classpathEntryList.addAll(Arrays.asList(javaProject
					.getRawClasspath()));
			for (Map<String, String> sourceFolder : sourceFolders) {
				IFolder srcFolder = javaProject.getProject().getFolder(
						sourceFolder.get("path"));
				createFolder(srcFolder);
				IPackageFragmentRoot iPackageFragmentRoot = javaProject
						.getPackageFragmentRoot(srcFolder);

				// IClasspathEntry iClasspathEntry = JavaCore
				// .newSourceEntry(iPackageFragmentRoot.getPath());

				IPath[] inclusionPatterns = new IPath[0];
				if (sourceFolder.get("inclusionPatterns") != null) {
					inclusionPatterns = new IPath[1];
					inclusionPatterns[0] = new Path(
							sourceFolder.get("inclusionPatterns"));
				}

				IPath[] exclusionPatterns = new IPath[0];
				if (sourceFolder.get("exclusionPatterns") != null) {
					exclusionPatterns = new IPath[1];
					exclusionPatterns[0] = new Path(
							sourceFolder.get("exclusionPatterns"));
				}

				IFolder outputFolder = project.getFolder(sourceFolder
						.get("specificOutputLocation"));
				createFolder(outputFolder);

				IClasspathEntry iClasspathEntry = JavaCore.newSourceEntry(
						iPackageFragmentRoot.getPath(), inclusionPatterns,
						exclusionPatterns, outputFolder.getFullPath());
				classpathEntryList.add(iClasspathEntry);
			}

			javaProject.setRawClasspath((IClasspathEntry[]) classpathEntryList
					.toArray(new IClasspathEntry[classpathEntryList.size()]),
					null);
			// javaProject.save(null, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void setJRELibrary(String projectName) {
		try {
			IProject project = findProject(projectName);
			IJavaProject javaProject = JavaCore.create(project);

			List<IClasspathEntry> classpathEntryList = new ArrayList<IClasspathEntry>();
			classpathEntryList.addAll(Arrays.asList(javaProject
					.getRawClasspath()));
			classpathEntryList.add(JavaRuntime.getDefaultJREContainerEntry());

			javaProject.setRawClasspath((IClasspathEntry[]) classpathEntryList
					.toArray(new IClasspathEntry[classpathEntryList.size()]),
					null);
			// javaProject.save(null, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void setContainerEntry(String projectName,
			List<Map<String, String>> containers) {
		try {
			IProject project = findProject(projectName);
			IJavaProject javaProject = JavaCore.create(project);

			List<IClasspathEntry> classpathEntryList = new ArrayList<IClasspathEntry>();
			classpathEntryList.addAll(Arrays.asList(javaProject
					.getRawClasspath()));
			for (Map<String, String> container : containers) {
				Path containerPath = null;
				if (container.get("containerPath") != null) {
					containerPath = new Path(container.get("containerPath"));
				}

				boolean isExported = false;
				if (container.get("isExported") != null) {
					isExported = Boolean.valueOf(container.get("isExported"));
				}

				IClasspathEntry iClasspathEntry = JavaCore.newContainerEntry(
						containerPath, isExported);
				classpathEntryList.add(iClasspathEntry);
			}

			javaProject.setRawClasspath((IClasspathEntry[]) classpathEntryList
					.toArray(new IClasspathEntry[classpathEntryList.size()]),
					null);
			// javaProject.save(null, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void setOutputLocation(String projectName,
			String ouputLocation) {
		try {
			IProject project = findProject(projectName);

			IFolder outputFolder = project.getFolder(ouputLocation);
			createFolder(outputFolder);

			IJavaProject javaProject = JavaCore.create(project);
			IPath outputFolderPath = outputFolder.getFullPath();
			javaProject.setOutputLocation(outputFolderPath, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void createJavaSettingsFiles(String projectName,
			String templateFolder) {
		try {
			IProject project = findProject(projectName);

			IFolder settingsFolder = project.getFolder(".settings");
			createFolder(settingsFolder);

			String settingsFile = "org.eclipse.jdt.core.prefs";
			IFile jdtCorePrefsFile = settingsFolder.getFile(settingsFile);
			if (!jdtCorePrefsFile.exists()) {
				String content = FileUtil.readFileToString(templateFolder + "/"
						+ settingsFile);

				jdtCorePrefsFile.create(
						new ByteArrayInputStream(content.getBytes()), true,
						null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void createWebSettingsFiles(String projectName,
			String templateFolder, List<Map<String, String>> wbResources) {
		try {
			IProject project = findProject(projectName);

			IFolder settingsFolder = project.getFolder(".settings");
			createFolder(settingsFolder);

			String[] settingsFiles = { ".jsdtscope",
					"org.eclipse.wst.common.component",
					"org.eclipse.wst.common.project.facet.core.xml",
					"org.eclipse.wst.jsdt.ui.superType.container",
					"org.eclipse.wst.jsdt.ui.superType.name" };
			for (String settingsFile : settingsFiles) {
				IFile jdtCorePrefsFile = settingsFolder.getFile(settingsFile);
				if (!jdtCorePrefsFile.exists()) {
					if (".jsdtscope".equals(settingsFile)) {
						settingsFile = settingsFile.replaceAll("\\.", "");
					}
					String content = FileUtil.readFileToString(templateFolder
							+ "/" + settingsFile);
					if ("org.eclipse.wst.common.component".equals(settingsFile)) {
						content = content
								.replaceAll("projectName", projectName);

						StringBuilder wbResourcesSb = new StringBuilder();
						for (Map<String, String> wbResource : wbResources) {
							wbResourcesSb.append("\n");
							wbResourcesSb.append("\t\t");
							wbResourcesSb.append("<wb-resource deploy-path=\""
									+ wbResource.get("deployPath")
									+ "\" source-path=\""
									+ wbResource.get("sourcePath") + "\"/>");
						}
						content = content.replaceAll("wbResources",
								wbResourcesSb.toString());

						String outputPath = "target/classes";
						content = content.replaceAll("outputPath", outputPath);
					}

					jdtCorePrefsFile.create(
							new ByteArrayInputStream(content.getBytes()), true,
							null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void createFolder(IFolder folder) throws CoreException {
		IContainer parent = folder.getParent();
		if (parent != null && !parent.exists() && (parent instanceof IFolder)) {
			createFolder((IFolder) parent);
		}

		if (!folder.exists()) {
			folder.create(false, true, null);
		}
	}

}
