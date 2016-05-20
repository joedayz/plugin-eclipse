package pe.gob.sunat.projects.palette;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import pe.gob.sunat.common.widgets.pshelf.PShelf;
import pe.gob.sunat.common.widgets.pshelf.PShelfItem;
import pe.gob.sunat.common.widgets.pshelf.render.PaletteShelfRenderer;
import pe.gob.sunat.common.widgets.pshelf.render.RedmondShelfRenderer;
import pe.gob.sunat.common.widgets.pshelf.render.base.AbstractRenderer;
import pe.gob.sunat.projects.Activator;
import pe.gob.sunat.projects.palette.dnd.PaletteDragListener;

public class Html5Palette extends ViewPart {

	private PShelf shelf;

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		createPaletteGroup(parent);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
	}

	private void createPaletteGroup(Composite parent) {
		int style = SWT.NONE;
		style |= SWT.BORDER;

		AbstractRenderer renderer = new RedmondShelfRenderer();
		renderer = new PaletteShelfRenderer();
		Image folderImage = Activator.getImage("icons/palette_folder.png");

		this.shelf = new PShelf(parent, style);
		this.shelf.setRenderer(renderer);

		PShelfItem componentsItem = new PShelfItem(this.shelf, SWT.NONE);
		componentsItem.setText("Componentes");
		componentsItem.setImage(folderImage);
		componentsItem.getBody().setLayout(new FillLayout());

		Html5TableViewer componentsTableViewer = new Html5TableViewer(
				componentsItem.getBody());
		int operations = DND.DROP_COPY | DND.DROP_MOVE;
		Transfer[] transferTypes = new Transfer[] { TextTransfer.getInstance() };
		componentsTableViewer.addDragSupport(operations, transferTypes,
				new PaletteDragListener(componentsTableViewer));

		List<Html5Component> componentsList = new ArrayList<Html5Component>();
		componentsList.add(new Html5Component("components.html5.menu", "Menu",
				"icons/palette_control.png"));
		componentsList.add(new Html5Component("components.html5.ubicacion",
				"Rastro de ubicación", "icons/palette_control.png"));
		componentsList.add(new Html5Component("components.html5.calendario",
				"Calendario", "icons/palette_control.png"));
		componentsList.add(new Html5Component("components.html5.listaValores",
				"Lista de Valores", "icons/palette_control.png"));
		componentsList.add(new Html5Component("components.html5.listaPaginada",
				"Lista Paginada", "icons/palette_control.png"));
		componentsList.add(new Html5Component("components.html5.mensajeError",
				"Mensaje de Error", "icons/palette_control.png"));
		componentsList.add(new Html5Component("components.html5.captcha",
				"Captcha", "icons/palette_control.png"));
		componentsList.add(new Html5Component(
				"components.html5.numeroDocumento", "Número de documento",
				"icons/palette_control.png"));
		componentsList.add(new Html5Component("components.html5.codigoBarras",
				"Código de Barras", "icons/palette_control.png"));
		componentsList.add(new Html5Component("components.html5.codigoQr",
				"Código QR", "icons/palette_control.png"));
		componentsList.add(new Html5Component(
				"components.html5.arbolJerarquico", "Árbol Jerárquico",
				"icons/palette_control.png"));
		componentsList.add(new Html5Component(
				"components.html5.arbolAccesible", "Árbol Accesible",
				"icons/palette_control.png"));
		componentsList.add(new Html5Component("components.html5.pestanias",
				"Pestañas", "icons/palette_control.png"));
		componentsList.add(new Html5Component("components.html5.combo",
				"Combo de valores", "icons/palette_control.png"));
		componentsList.add(new Html5Component("components.html5.panelAyuda",
				"Panel Ayuda", "icons/palette_control.png"));
		componentsList.add(new Html5Component("components.html5.validaciones",
				"Validaciones", "icons/palette_control.png"));
		componentsList.add(new Html5Component("components.html5.googleMaps",
				"Google Maps", "icons/palette_control.png"));
		componentsTableViewer.setInput(componentsList);

		PShelfItem formsItem = new PShelfItem(this.shelf, SWT.NONE);
		formsItem.setText("Formularios");
		formsItem.setImage(folderImage);
		formsItem.getBody().setLayout(new FillLayout());

		Html5TableViewer formsTableViewer = new Html5TableViewer(
				formsItem.getBody());
		formsTableViewer.addDragSupport(operations, transferTypes,
				new PaletteDragListener(formsTableViewer));

		List<Html5Component> formsList = new ArrayList<Html5Component>();
		formsList.add(new Html5Component("forms.html5.ruc", "Consulta RUC",
				"icons/palette_control.png"));
		formsList.add(new Html5Component("forms.html5.dni", "Consulta DNI",
				"icons/palette_control.png"));
		formsList.add(new Html5Component("forms.html5.ubigeo",
				"Consulta Ubigeo", "icons/palette_control.png"));
		formsTableViewer.setInput(formsList);
	}

}
