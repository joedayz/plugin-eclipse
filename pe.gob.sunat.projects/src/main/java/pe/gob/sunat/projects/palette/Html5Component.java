package pe.gob.sunat.projects.palette;

public class Html5Component {

	private String componentId;
	private String description;
	private String imagePath;

	public Html5Component(String componentId, String description,
			String imagePath) {
		// TODO Auto-generated constructor stub
		this.componentId = componentId;
		this.description = description;
		this.imagePath = imagePath;
	}

	public String getComponentId() {
		return componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

}
