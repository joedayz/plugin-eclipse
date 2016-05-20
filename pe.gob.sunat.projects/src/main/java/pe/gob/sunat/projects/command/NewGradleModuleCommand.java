package pe.gob.sunat.projects.command;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.internal.resources.Project;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

import pe.gob.sunat.projects.wizards.NewGradleModuleWizard;

@SuppressWarnings("restriction")
public class NewGradleModuleCommand extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		IStructuredSelection selection = (IStructuredSelection) HandlerUtil
				.getCurrentSelectionChecked(event);
		Object object = selection.getFirstElement();
		if (object == null || !(object instanceof Project)) {
			System.err.println("Element selected is not valid");
			return null;
		}

		String projectName = "";
		String projectLocation = "";
		try {
			IProject project = ((Project) object).getProject();
			if (object != null) {
				projectName = project.getName();
				projectLocation = project.getLocation().toFile()
						.getAbsolutePath();

				projectLocation = projectLocation.substring(0,
						projectLocation.length() - projectName.length() - 1);
			}

			Wizard wizard = new NewGradleModuleWizard(projectName,
					projectLocation);
			WizardDialog dialog = new WizardDialog(new Shell(), wizard);
			dialog.open();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

}
