package pe.gob.sunat.projects.wizards;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import pe.gob.sunat.projects.helper.GradleApplicationUtil;

public class NewGradleProjectWizard extends Wizard implements INewWizard {

	private NewGradleProjectWizardPage newGradleProjectWizardPage;
	private ISelection selection;

	public NewGradleProjectWizard() {
		// TODO Auto-generated constructor stub
		super();

		setWindowTitle("Nuevo Proyecto Gradle");
		setNeedsProgressMonitor(true);
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub
		this.selection = selection;
	}

	@Override
	public void addPages() {
		super.addPages();

		newGradleProjectWizardPage = new NewGradleProjectWizardPage(selection);
		addPage(newGradleProjectWizardPage);
	}

	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		String installPath = newGradleProjectWizardPage.getInstallPath();

		String projectLocation = newGradleProjectWizardPage
				.getProjectLocation();
		String projectName = newGradleProjectWizardPage.getProjectName();
		String projectType = newGradleProjectWizardPage.getProjectType();
		String megaProcess = newGradleProjectWizardPage.getMegaProcess();
		String macroProcess = newGradleProjectWizardPage
				.getMacroProcess();
		String process = newGradleProjectWizardPage.getProcess();
		String subProcess = newGradleProjectWizardPage.getSubProcess();
		if (subProcess == null) {
			subProcess = "";
		}
		if (!"".equals(subProcess)) {
			subProcess = "-" + subProcess;
		}
		String projectVersion = newGradleProjectWizardPage
				.getProjectVersion();
		switch (newGradleProjectWizardPage.getProjectTypeIndex()) {
		case 0:
			GradleApplicationUtil.createWebApplication(installPath,
					projectType, projectName, projectLocation, megaProcess,
					macroProcess, process, subProcess, projectVersion);
			break;
		case 1:
			GradleApplicationUtil.createBackEndApplication(installPath,
					projectType, projectName, projectLocation, megaProcess,
					macroProcess, process, subProcess, projectVersion);
			break;
		case 2:
			GradleApplicationUtil.createBatchApplication(installPath,
					projectType, projectName, projectLocation, megaProcess,
					macroProcess, process, subProcess, projectVersion);
			break;
		case 3:
			GradleApplicationUtil.createSharedLibApplication(installPath,
					projectType, projectName, projectLocation, megaProcess,
					macroProcess, process, subProcess, projectVersion);
			break;
		default:
			break;
		}

		return true;
	}

}
