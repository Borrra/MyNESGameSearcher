
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JLabel;

public class NumberedListCellRenderer extends DefaultListCellRenderer {
	
	@Override
	
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		
		JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

		label.setText(String.format("%03d. %s", index + 1, value.toString()));

		return label;
	}

}
