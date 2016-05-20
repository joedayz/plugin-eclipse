package pe.gob.sunat.projects.wizards;

import java.io.File;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import pe.gob.sunat.projects.Activator;

public class NewGradleProjectWizardPage extends WizardPage {

	private Combo projectTypeCombo;
	private Text projectLocationText;
	private Text projectNameText;
	private Text projectVersionText;
	private Text megaProcessText;
	private Text macroProcessText;
	private Text processText;
	private Text subProcessText;
	private ISelection selection;

	public NewGradleProjectWizardPage(ISelection selection) {
		// TODO Auto-generated method stub
		super("NewGradleProjectWizardPage");

		this.selection = selection;
		this.setTitle("Proyecto Gradle");
		this.setDescription("Este asistente le ayudará a crear un nuevo proyecto Gradle");
		this.setImageDescriptor(Activator
				.getImageDescriptor("icons/gradle-project-large.png"));
	}

	@Override
	public void createControl(Composite parent) {
		// TODO Auto-generated method stub
		Composite container = new Composite(parent, SWT.NULL);

		createProjectParameters(container);
		createProjectSettingsGroup(container);

		setControl(container);
		setPageComplete(false);
	}

	public int getProjectTypeIndex() {
		return this.projectTypeCombo.getSelectionIndex();
	}

	public String getProjectType() {
		String projectType = "";

		int index = this.projectTypeCombo.getSelectionIndex();
		switch (index) {
		case 0:
			projectType = "webapp";
			break;
		case 1:
			projectType = "backend";
			break;
		case 2:
			projectType = "batch";
			break;
		case 3:
			projectType = "sharedlib";
			break;
		default:
			break;
		}

		return projectType;
	}

	public String getProjectLocation() {
		return projectLocationText.getText();
	}

	public String getProjectName() {
		return projectNameText.getText();
	}

	public String getProjectVersion() {
		return projectVersionText.getText();
	}

	public String getMegaProcess() {
		return megaProcessText.getText();
	}

	public String getMacroProcess() {
		return macroProcessText.getText();
	}

	public String getProcess() {
		return processText.getText();
	}

	public String getSubProcess() {
		return subProcessText.getText();
	}

	public String getInstallPath() {
		String path = "";

		try {
			path = Activator.getInstallLocation();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return path;
	}

	private void createProjectParameters(Composite parent) {
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		gridLayout.makeColumnsEqualWidth = false;
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		GridData newGridData = new GridData(GridData.FILL_HORIZONTAL);
		newGridData.horizontalSpan = 2;

		parent.setLayout(gridLayout);

		Label projectTypeLabel = new Label(parent, SWT.NULL);
		projectTypeLabel.setText("&Tipo de Proyecto:");
		projectTypeCombo = new Combo(parent, SWT.BORDER | SWT.SINGLE);
		projectTypeCombo.setLayoutData(newGridData);
		projectTypeCombo.setItems(new String[] { "Proyecto Web",
				"Proyecto Back-End", "Proyecto Batch", "Proyecto Library" });
		projectTypeCombo.select(0);
		projectTypeCombo.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				dialogChanged();
			}

		});

		Label projectLocationLabel = new Label(parent, SWT.NULL);
		projectLocationLabel.setText("&Ubicación del Proyecto:");
		projectLocationText = new Text(parent, SWT.BORDER | SWT.SINGLE);
		projectLocationText.setLayoutData(gridData);
		projectLocationText.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}

		});
		Button projectLocationButton = new Button(parent, SWT.PUSH);
		projectLocationButton.setText("Seleccionar...");
		projectLocationButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				handleBrowseLocation();
			}

		});

		Label projectNameLabel = new Label(parent, SWT.NULL);
		projectNameLabel.setText("&Nombre del Proyecto:");
		projectNameText = new Text(parent, SWT.BORDER | SWT.SINGLE);
		projectNameText.setLayoutData(newGridData);
		projectNameText.setEditable(false);
	}

	private void createProjectSettingsGroup(Composite parent) {
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		gridLayout.makeColumnsEqualWidth = false;
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 3;
		GridData newGridData = new GridData(GridData.FILL_HORIZONTAL);

		Group projectSettingsGroup = new Group(parent, SWT.NONE);
		projectSettingsGroup.setText("Configuración del Proyecto");
		projectSettingsGroup.setLayout(gridLayout);
		projectSettingsGroup.setLayoutData(gridData);

		Label megaProcessLabel = new Label(projectSettingsGroup, SWT.NULL);
		megaProcessLabel.setText("&Mega proceso:");
		megaProcessText = new Text(projectSettingsGroup, SWT.BORDER | SWT.SINGLE);
		megaProcessText.setLayoutData(newGridData);
		megaProcessText.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}

		});

		Label macroProcessLabel = new Label(projectSettingsGroup, SWT.NULL);
		macroProcessLabel.setText("&Macro proceso:");
		macroProcessText = new Text(projectSettingsGroup, SWT.BORDER | SWT.SINGLE);
		macroProcessText.setLayoutData(newGridData);
		macroProcessText.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}

		});

		Label processLabel = new Label(projectSettingsGroup, SWT.NULL);
		processLabel.setText("&Proceso:");
		processText = new Text(projectSettingsGroup, SWT.BORDER | SWT.SINGLE);
		processText.setLayoutData(newGridData);
		processText.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}

		});

		Label subProcessLabel = new Label(projectSettingsGroup, SWT.NULL);
		subProcessLabel.setText("&Sub-Proceso:");
		subProcessText = new Text(projectSettingsGroup, SWT.BORDER | SWT.SINGLE);
		subProcessText.setLayoutData(newGridData);

		Label projectVersionLabel = new Label(projectSettingsGroup, SWT.NULL);
		projectVersionLabel.setText("&Versión del Proyecto:");
		projectVersionText = new Text(projectSettingsGroup, SWT.BORDER | SWT.SINGLE);
		projectVersionText.setLayoutData(newGridData);
		projectVersionText.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}

		});
	}

	private void handleBrowseLocation() {
		Shell parentShell = new Shell(SWT.DIALOG_TRIM);
		DirectoryDialog dlg = new DirectoryDialog(parentShell, SWT.OPEN);
		dlg.setText("Browse folder");
		dlg.setMessage("Select the location directory");
		dlg.setFilterPath(projectLocationText.getText());

		String folderName = dlg.open();
		if (folderName != null)
			projectLocationText.setText(folderName);
	}

	private void dialogChanged() {
		String projectLocation = getProjectLocation();
		if (projectLocation.length() == 0) {
			updateStatus("La ubicación del proyecto debe ser especificada");
			return;
		}

		File location = new File(projectLocation);
		if (!location.exists() || !location.isDirectory()) {
			updateStatus("La ubicación del proyecto no es válida");
			return;
		}

		String megaProcess = getMegaProcess();
		if (megaProcess.length() == 0) {
			updateStatus("Mega proceso debe ser especificado");
			return;
		}

		String macroProcess = getMacroProcess();
		if (macroProcess.length() == 0) {
			updateStatus("Macro proceso debe ser especificado");
			return;
		}

		String process = getProcess();
		if (process.length() == 0) {
			updateStatus("Proceso debe ser especificado");
			return;
		}

		String projectVersion = getProjectVersion();
		if (projectVersion.length() == 0) {
			updateStatus("La versión del proyecto debe ser especificada");
			return;
		}

		projectNameText.setText(megaProcess + "-" + macroProcess + "-"
				+ process + "-" + getProjectType());
		updateStatus(null);
	}

	private void updateStatus(String message) {
		setErrorMessage(message);

		setPageComplete(message == null);
	}

}
