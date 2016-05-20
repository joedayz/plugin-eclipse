package pe.gob.sunat.projects.wizards;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import pe.gob.sunat.projects.helper.GradleModuleUtil;

public class NewGradleModuleWizard extends Wizard implements INewWizard {

	private NewGradleModuleWizardPage newGradleModuleWizardPage;
	private ISelection selection;
	private String projectName;
	private String projectLocation;

	public NewGradleModuleWizard(String projectName, String projectLocation) {
		// TODO Auto-generated constructor stub
		super();

		setWindowTitle("Nuevo Módulo Gradle");
		setNeedsProgressMonitor(true);
		this.projectName = projectName;
		this.projectLocation = projectLocation;
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub
		this.selection = selection;
	}

	@Override
	public void addPages() {
		super.addPages();

		newGradleModuleWizardPage = new NewGradleModuleWizardPage(selection,
				this.projectName, this.projectLocation);
		addPage(newGradleModuleWizardPage);
	}

	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		String installPath = newGradleModuleWizardPage.getInstallPath();
		String moduleName = newGradleModuleWizardPage.getModuleName();

		switch (newGradleModuleWizardPage.getModuleTypeIndex()) {
		case 0:
			GradleModuleUtil.createJarModule(installPath, this.projectName,
					this.projectLocation, moduleName);
			break;
		case 1:
			GradleModuleUtil.createWarModule(installPath, this.projectName,
					this.projectLocation, moduleName);
			break;
		default:
			break;
		}

		return true;
	}

}
