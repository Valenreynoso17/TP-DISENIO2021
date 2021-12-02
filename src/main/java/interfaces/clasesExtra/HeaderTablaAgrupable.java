package main.java.interfaces.clasesExtra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

@SuppressWarnings("serial")
public class HeaderTablaAgrupable extends JTableHeader {

    @SuppressWarnings("unused")
    private static final String uiClassID = "GroupableTableHeaderUI";

    protected List<ColumnaAgrupada> columnGroups = new ArrayList<ColumnaAgrupada>();

    public HeaderTablaAgrupable(TableColumnModel model) {
        super(model);
        setUI(new HeaderTablaAgrupableUI());
        setReorderingAllowed(false);
        // setDefaultRenderer(new MultiLineHeaderRenderer());
    }

    @Override
    public void updateUI() {
        setUI(new HeaderTablaAgrupableUI());
    }

    @Override
    public void setReorderingAllowed(boolean b) {
        super.setReorderingAllowed(false);
    }

    public void addColumnGroup(ColumnaAgrupada g) {
        columnGroups.add(g);
    }

    public List<ColumnaAgrupada> getColumnGroups(TableColumn col) {
        for (ColumnaAgrupada group : columnGroups) {
            List<ColumnaAgrupada> groups = group.getColumnGroups(col);
            if (!groups.isEmpty()) {
                return groups;
            }
        }
        return Collections.emptyList();
    }

    public void setColumnMargin() {
        int columnMargin = getColumnModel().getColumnMargin();
        for (ColumnaAgrupada group : columnGroups) {
            group.setColumnMargin(columnMargin);
        }
    }

}
