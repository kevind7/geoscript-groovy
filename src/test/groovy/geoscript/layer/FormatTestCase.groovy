package geoscript.layer

import org.junit.Test
import static org.junit.Assert.*

/**
 * The Format Unit Test
 * @author Jared Erickson
 */
class FormatTestCase {

    @Test void getFormat() {
        // Existing file
        File file = new File(getClass().getClassLoader().getResource("alki.tif").toURI())
        Format format = Format.getFormat(file)
        assertNotNull(format)
        assertNotNull(format.stream)
        assertTrue(format instanceof GeoTIFF)

        // Existing NetCDF
        file = new File(getClass().getClassLoader().getResource("O3-NO2.nc").toURI())
        format = Format.getFormat(file)
        assertNotNull(format)
        assertNotNull(format.stream)
        assertTrue(format instanceof NetCDF)

        // New file
        file = new File("doesnotexist.png")
        format = Format.getFormat(file)
        assertNotNull(format)
        assertNotNull(format.stream)
        assertTrue(format instanceof WorldImage)

        // Not a Raster form a new file
        file = new File("states.shp")
        format = Format.getFormat(file)
        assertNull(format)

        // Not a Raster from a file
        file = new File(getClass().getClassLoader().getResource("states.shp").toURI())
        format = Format.getFormat(file)
        assertNull(format)
    }

}
