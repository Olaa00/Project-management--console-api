package abeczkowska.persistence;

import java.util.List;
import java.util.Map;

public class QuerySpec {
    // FROM
    private String tableName;

    // WHICH COLUMNS
    private List<String> selectedColumns; //lista wybranych kolumn, które powinny być zwrócone

    // WHERE key == value
    private Map<String, Object> whereCondition; // mapa warunków WHERE, gdzie klucz reprezentuje nazwę kolumny, a wartość reprezentuje wartość, do której ta kolumna powinna pasować.

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<String> getSelectedColumns() {
        return selectedColumns;
    }

    public void setSelectedColumns(List<String> selectedColumns) {
        this.selectedColumns = selectedColumns;
    }

    public Map<String, Object> getWhereCondition() {
        return whereCondition;
    }

    public void setWhereCondition(Map<String, Object> whereCondition) {
        this.whereCondition = whereCondition;
    }
}