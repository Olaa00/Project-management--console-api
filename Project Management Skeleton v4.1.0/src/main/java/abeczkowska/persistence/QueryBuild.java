package abeczkowska.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


public class QueryBuild {

    private QuerySpec querySpec;

    public QueryBuild() {
        querySpec = new QuerySpec();
    }

    //ustawia nazwę tabeli w specyfikacji zapytania.
    public QueryBuild tableName(String name) {
        querySpec.setTableName(name);
        return this;
    }

    //ustawiaja wybrane kolumny w specyfikacji zapytania
    public QueryBuild columns(List<String> columns) {
        //lista, w której każdy element zostaje przycięty
        List<String> trimmedColumns = columns.stream()
                .map(String::trim)
                .collect(Collectors.toList());
    // przycięte kolumny do obiektu querySpec
        querySpec.setSelectedColumns(trimmedColumns);
        return this;
    }


    //warunek do specyfikacji zapytania
    public QueryBuild addCondition(String key, Object value) {

        if(querySpec.getWhereCondition() == null) {
            querySpec.setWhereCondition(new HashMap<>());
        }

        querySpec.getWhereCondition().put(key, value);
        return this;
    }


    //zbudowana specyfikację zapytania
    public QuerySpec build() {
        return this.querySpec;
    }
}
