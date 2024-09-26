package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.util.StringUtils;

import java.sql.PreparedStatement;

public class V2__INDEX_DATABASE extends BaseJavaMigration {
    @Override
    public void migrate(Context context) throws Exception {
        String url = context.getConnection().getMetaData().getURL();
        String jdbcURL = StringUtils.replace(url, "test", "tenant1");
        String tenantName = "tenant1";

        String insertQuery = "INSERT INTO INDEX_DATABASE values ('%s','Active','postgresql','org.postgresql.Driver','%s','test','test','%s','')";

        String query = String.format(insertQuery, tenantName, jdbcURL, tenantName);

        try (PreparedStatement statement = context.getConnection()
                .prepareStatement(query)) {
            statement.execute();
        }

    }
}
