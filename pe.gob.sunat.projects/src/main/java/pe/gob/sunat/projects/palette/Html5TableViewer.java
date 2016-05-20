package pe.gob.sunat.projects.palette;

import java.util.List;

import org.eclipse.jface.viewers.BaseLabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import pe.gob.sunat.projects.Activator;

public class Html5TableViewer extends TableViewer {

	public Html5TableViewer(Composite parent) {
		super(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		// TODO Auto-generated constructor stub

		setContentProvider(new TableContentProvider());
		setLabelProvider(new TableLabelProvider());
	}

	class TableContentProvider implements IStructuredContentProvider {

		@Override
		public void dispose() {
			// TODO Auto-generated method stub

		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			// TODO Auto-generated method stub

		}

		@Override
		public Object[] getElements(Object inputElement) {
			// TODO Auto-generated method stub
			List<Html5Component> componentList = (List<Html5Component>) inputElement;

			return componentList.toArray();
		}

	}

	class TableLabelProvider extends BaseLabelProvider implements
			ITableLabelProvider {

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			// TODO Auto-generated method stub
			Html5Component component = (Html5Component) element;
			switch (columnIndex) {
			case 0:
				return Activator.getImage(component.getImagePath());
			}

			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			// TODO Auto-generated method stub
			Html5Component component = (Html5Component) element;
			switch (columnIndex) {
			case 0:
				return component.getDescription();
			}

			return "Not supported";
		}

	}

}
