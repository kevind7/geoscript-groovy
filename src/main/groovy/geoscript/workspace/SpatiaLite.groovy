package geoscript.workspace

import org.geotools.data.DataStore
import org.geotools.data.spatialite.SpatiaLiteDataStoreFactory
import org.geotools.jdbc.JDBCDataStore

/**
 * A SpatiaLite Workspace connects to a SpatiaLite database.
 * <p><blockquote><pre>
 * SpatiaLite spatialite = new SpatiaLite("db.sqlite", "databases")
 * </pre></blockquote></p>
 * @author Jared Erickson
 */
class SpatiaLite extends Database {

    /**
     * Create a new SpatiaLite Workspace from a name and directory
     * @param name The name of the database
     * @param dir The File directory containing the database
     */
    SpatiaLite(String name, File dir) {
        super(createDataStore(name, dir))
    }

    /**
     * Create a new SpatiaLite Workspace from a name and directory
     * @param name The name of the database
     * @param dir The directory name containing the database
     */
    SpatiaLite(String name, String dir) {
        this(name, new File(dir).absoluteFile)
    }

    /**
     * Create a new SpatiaLite Workspace from a GeoTools JDBCDataStore
     * @param ds The GeoTools JDBCDataStore
     */
    SpatiaLite(JDBCDataStore ds) {
        super(ds)
    }

    /**
     * Get the format
     * @return The workspace format name
     */
    String getFormat() {
        return "SpatiaLite"
    }

    /**
     * Create a new SpatiaLite DataStore from a name and directory
     */
    private static DataStore createDataStore(String name, File dir) {
        Map params = [:]
        params.put("database", new File(dir,name).absolutePath)
        params.put("dbtype", "spatialite")
        SpatiaLiteDataStoreFactory f = new SpatiaLiteDataStoreFactory()
        f.createDataStore(params)
    }
}