package abeczkowska.utility;

import abeczkowska.persistence.QuerySpec;

import java.util.Map;

public class QuerySpecTrans {

    //obiekt QuerySpec na zapytanie SQL
    public static String translateQuerySpec(QuerySpec querySpec) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        // WHICH COLUMNS
        if(querySpec.getSelectedColumns() == null || querySpec.getSelectedColumns().isEmpty()) {
            sb.append("*"); //wybiera wszystkie kolumny, jeśli nie określono
        }
        else {
            String columns = String.join(", ", querySpec.getSelectedColumns());
            sb.append(columns); //określone kolumny
        }

        sb.append(" FROM " + querySpec.getTableName()); //// Tabela, z której wybieramy

        if(!querySpec.getWhereCondition().isEmpty() || querySpec.getWhereCondition() != null) {
            sb.append(" WHERE ");

            int counter = 0;

            for(Map.Entry<String, Object> entry : querySpec.getWhereCondition().entrySet()) {
                if(counter > 0) sb.append(" AND ");

                sb.append(entry.getKey()).append(" = ").append(entry.getValue().toString()).append(" "); //warunek "kolumna = wartość"
            }
        }


        return sb.toString().trim(); //// Zwraca przetłumaczone zapytanie SQL
    }

}
