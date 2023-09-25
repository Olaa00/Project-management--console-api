//package pl.ttpsc.javaupdate.project;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import pl.ttpsc.javaupdate.project.persistence.QueryBuild;
//import pl.ttpsc.javaupdate.project.persistence.QuerySpec;
//import pl.ttpsc.javaupdate.project.persistence.sql.PersistenceManagerSQL;
//import pl.ttpsc.javaupdate.project.utility.QuerySpecTrans;
//
//public class LogTest {
//    private static final Logger LOGGER = LoggerFactory.getLogger(LogTest.class.getName());
//
//    public static void main(String[] args) {
//        PersistenceManagerSQL manager = new PersistenceManagerSQL();
//
//
//
//        QuerySpec spec = new QueryBuild()
//                .tableName("projects")
//                .addCondition("id", 1)
//                .addCondition("name", "OlaB")
//                .build();
//
//        System.out.println(QuerySpecTrans.translateQuerySpec(spec));
//    }
//
//}
