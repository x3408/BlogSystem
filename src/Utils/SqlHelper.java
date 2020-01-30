package Utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;

public class SqlHelper {
    private static SqlHelper instance;
    private static ComboPooledDataSource cpds = new ComboPooledDataSource();

    public static SqlHelper getInstance() {
        if (instance == null) {
            instance = new SqlHelper();
        }
        return instance;
    }

    public DataSource getDataSource() {
        return cpds;
    }
}
