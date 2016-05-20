package pe.gob.sunat.projects.wizards;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import pe.gob.sunat.projects.Activator;

public class NewGradleModuleWizardPage extends WizardPage {

	private Text projectNameText;
	private Text projectLocationText;
	private Combo moduleTypeCombo;
	private Text moduleNameText;
	private String projectName;
	private String projectLocation;
	private ISelection selection;

	public NewGradleModuleWizardPage(ISelection selection, String projectName,
			String projectLocation) {
		// TODO Auto-generated method stub
		super("NewGradleModuleWizardPage");

		this.projectName = projectName;
		this.projectLocation = projectLocation;
		this.selection = selection;
		this.setTitle("Módulo Gradle");
		this.setDescription("Este asistente le ayudará a crear un nuevo módulo Gradle");
		this.setImageDescriptor(Activator
				.getImageDescriptor("icons/gradle-project-large.png"));
	}

	@Override
	public void createControl(Composite parent) {
		// TODO Auto-generated method stub
		Composite container = new Composite(parent, SWT.NULL);

		container.setLayout(new GridLayout());
		createProjectDataGroup(container);
		createModuleDataGroup(container);

		setControl(container);
		setPageComplete(false);
	}

	public int getModuleTypeIndex() {
		return this.moduleTypeCombo.getSelectionIndex();
	}

	public String getModuleType() {
		String moduleType = "";

		int index = this.moduleTypeCombo.getSelectionIndex();
		switch (index) {
		case 0:
			moduleType = "jar";
			break;
		case 1:
			moduleType = "war";
			break;
		default:
			break;
		}

		return moduleType;
	}

	public String getModuleName() {
		return moduleNameText.getText();
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

	private void createProjectDataGroup(Composite parent) {
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		gridLayout.makeColumnsEqualWidth = false;
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);

		Group projectValuesGroup = new Group(parent, SWT.NONE);
		projectValuesGroup.setText("Datos del Proyecto");
		projectValuesGroup.setLayout(gridLayout);
		projectValuesGroup.setLayoutData(gridData);

		Label projectNameLabel = new Label(projectValuesGroup, SWT.NULL);
		projectNameLabel.setText("&Nombre del Proyecto:");
		projectNameText = new Text(projectValuesGroup, SWT.BORDER | SWT.SINGLE);
		projectNameText.setLayoutData(gridData);
		projectNameText.setText(this.projectName);
		projectNameText.setEditable(false);

		Label projectLocationLabel = new Label(projectValuesGroup, SWT.NULL);
		projectLocationLabel.setText("&Ubicación del Proyecto:");
		projectLocationText = new Text(projectValuesGroup, SWT.BORDER
				| SWT.SINGLE);
		projectLocationText.setLayoutData(gridData);
		projectLocationText.setText(this.projectLocation);
		projectLocationText.setEditable(false);
	}

	private void createModuleDataGroup(Composite parent) {
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		gridLayout.makeColumnsEqualWidth = false;
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);

		Group moduleSettingsGroup = new Group(parent, SWT.NONE);
		moduleSettingsGroup.setText("Datos del Módulo");
		moduleSettingsGroup.setLayout(gridLayout);
		moduleSettingsGroup.setLayoutData(gridData);

		Label moduleTypeLabel = new Label(moduleSettingsGroup, SWT.NULL);
		moduleTypeLabel.setText("&Tipo de Módulo:");
		moduleTypeCombo = new Combo(moduleSettingsGroup, SWT.BORDER
				| SWT.SINGLE);
		moduleTypeCombo.setLayoutData(gridData);
		moduleTypeCombo.setItems(new String[] { "JAR", "WAR" });
		moduleTypeCombo.select(0);
		moduleTypeCombo.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				dialogChanged();
			}

		});

		Label moduleNameLabel = new Label(moduleSettingsGroup, SWT.NULL);
		moduleNameLabel.setText("&Nombre del Módulo:");
		moduleNameText = new Text(moduleSettingsGroup, SWT.BORDER | SWT.SINGLE);
		moduleNameText.setLayoutData(gridData);
		moduleNameText.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}

		});
		moduleNameText.setFocus();
	}

	private void dialogChanged() {
		String moduleName = getModuleName();
		if (moduleName.length() == 0) {
			updateStatus("Nombre del Módulo debe ser especificado");
			return;
		}

		updateStatus(null);
	}

	private void updateStatus(String message) {
		setErrorMessage(message);

		setPageComplete(message == null);
	}

}
