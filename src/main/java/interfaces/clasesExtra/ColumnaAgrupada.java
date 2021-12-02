package main.java.interfaces.clasesExtra;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class ColumnaAgrupada {

    protected TableCellRenderer renderer;

    protected List<TableColumn> columns;
    protected List<ColumnaAgrupada> groups;

    protected String text;
    protected int margin = 0;

    public ColumnaAgrupada(String text) {
        this(text, null);
    }

    public ColumnaAgrupada(String text, TableCellRenderer renderer) {
        this.text = text;
        this.renderer = renderer;
        this.columns = new ArrayList<TableColumn>();
        this.groups = new ArrayList<ColumnaAgrupada>();
    }

    public void add(TableColumn column) {
        columns.add(column);
    }

    public void add(ColumnaAgrupada group) {
        groups.add(group);
    }

    /**
     * @param column
     *            TableColumn
     */
    public List<ColumnaAgrupada> getColumnGroups(TableColumn column) {
        if (!contains(column)) {
            return Collections.emptyList();
        }
        List<ColumnaAgrupada> result = new ArrayList<ColumnaAgrupada>();
        result.add(this);
        if (columns.contains(column)) {
            return result;
        }
        for (ColumnaAgrupada columnGroup : groups) {
            result.addAll(columnGroup.getColumnGroups(column));
        }
        return result;
    }

    private boolean contains(TableColumn column) {
        if (columns.contains(column)) {
            return true;
        }
        for (ColumnaAgrupada group : groups) {
            if (group.contains(column)) {
                return true;
            }
        }
        return false;
    }

    public TableCellRenderer getHeaderRenderer() {
        return renderer;
    }

    public void setHeaderRenderer(TableCellRenderer renderer) {
        this.renderer = renderer;
    }

    public String getHeaderValue() {
        return text;
    }

    public Dimension getSize(JTable table) {
        TableCellRenderer renderer = this.renderer;
        if (renderer == null) {
            renderer = table.getTableHeader().getDefaultRenderer();
        }
        Component comp = renderer.getTableCellRendererComponent(table, getHeaderValue() == null || getHeaderValue().trim().isEmpty() ? " "
                : getHeaderValue(), false, false, -1, -1);
        int height = comp.getPreferredSize().height;
        int width = 0;
        for (ColumnaAgrupada columnGroup : groups) {
            width += columnGroup.getSize(table).width;
        }
        for (TableColumn tableColumn : columns) {
            width += tableColumn.getWidth();
            width += margin;
        }
        return new Dimension(width, height);
    }

    public void setColumnMargin(int margin) {
        this.margin = margin;
        for (ColumnaAgrupada columnGroup : groups) {
            columnGroup.setColumnMargin(margin);
        }
    }

}
  
