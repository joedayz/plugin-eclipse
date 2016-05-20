package pe.gob.sunat.projects.palette.dnd;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.TextTransfer;

import pe.gob.sunat.common.helper.FileUtil;
import pe.gob.sunat.common.helper.ProjectUtil;
import pe.gob.sunat.projects.Activator;
import pe.gob.sunat.projects.palette.Html5Component;
import pe.gob.sunat.projects.palette.util.ComponentsResourceUtil;

public class PaletteDragListener implements DragSourceListener {

	private final StructuredViewer viewer;

	public PaletteDragListener(StructuredViewer viewer) {
		// TODO Auto-generated constructor stub
		this.viewer = viewer;
	}

	@Override
	public void dragStart(DragSourceEvent event) {
		// TODO Auto-generated method stub
		IStructuredSelection selection = (IStructuredSelection) viewer
				.getSelection();
		event.doit = (selection.size() > 0) ? true : false;
	}

	@Override
	public void dragSetData(DragSourceEvent event) {
		// TODO Auto-generated method stub
		if (TextTransfer.getInstance().isSupportedType(event.dataType)) {
			IStructuredSelection selection = (IStructuredSelection) viewer
					.getSelection();
			Html5Component firstElement = (Html5Component) selection
					.getFirstElement();

			String installPath = getInstallPath();
			//String projectName = getProjectName();

			String htmlFilepath = ComponentsResourceUtil
					.getProperty(firstElement.getComponentId() + ".html");
			String htmlTag = "";
			if (!"".equals(htmlFilepath)) {
				htmlTag = FileUtil.readFileToString(installPath + htmlFilepath);
			}

			/*String jsFilepathStr = ComponentsResourceUtil
					.getProperty(firstElement.getComponentId() + ".js");
			if (!"".equals(jsFilepathStr)) {
				String[] jsFilepathArr = jsFilepathStr.split(";");
				for (String jsFilepath : jsFilepathArr) {
					String filename = jsFilepath.substring(jsFilepath
							.lastIndexOf("/"));
					String content = FileUtil.readFileToString(installPath
							+ jsFilepath);

					ProjectUtil.addFileToProject(projectName,
							"/src/main/webapp/js", filename, content);
				}
			}

			String cssFilepathStr = ComponentsResourceUtil
					.getProperty(firstElement.getComponentId() + ".css");
			if (!"".equals(cssFilepathStr)) {
				String[] cssFilepathArr = cssFilepathStr.split(";");
				for (String cssFilepath : cssFilepathArr) {
					String filename = cssFilepath.substring(cssFilepath
							.lastIndexOf("/"));
					String content = FileUtil.readFileToString(installPath
							+ cssFilepath);

					ProjectUtil.addFileToProject(projectName,
							"/src/main/webapp/css", filename, content);
				}
			}

			String htmlFilesFilepathStr = ComponentsResourceUtil
					.getProperty(firstElement.getComponentId() + ".html.files");
			if (!"".equals(htmlFilesFilepathStr)) {
				String[] htmlFilesFilepathArr = htmlFilesFilepathStr.split(";");
				for (String htmlFileFilepath : htmlFilesFilepathArr) {
					String filename = htmlFileFilepath
							.substring(htmlFileFilepath.lastIndexOf("/"));
					String content = FileUtil.readFileToString(installPath
							+ htmlFileFilepath);

					ProjectUtil.addFileToProject(projectName,
							"/src/main/webapp", filename, content);
				}
			}*/

			event.data = htmlTag;
		}
	}

	@Override
	public void dragFinished(DragSourceEvent event) {
		// TODO Auto-generated method stub
	}

	private String getInstallPath() {
		String path = "";

		try {
			path = Activator.getInstallLocation();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return path;
	}

	private String getProjectName() {
		String projectName = "iainfo.war";

		//ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);

		return projectName;
	}

}
