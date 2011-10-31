package geoscript.layer

import geoscript.feature.Feature
import org.geotools.feature.FeatureIterator
import org.opengis.feature.simple.SimpleFeature
import org.opengis.feature.simple.SimpleFeatureType
import org.geotools.feature.FeatureCollection

/**
 * A Cursor is a Iterator over a Feature objects.
 * <p>Most often you will get a Cursor by calling the getCursor() method of a Layer</p>
 * <code><pre>
 * Shapefile shp = new Shapefile('states.shp')
 * Cursor c = shp.cursor
 * while(c.hasNext()) {
 *      Feature f = c.next()
 * }
 * c.close()
 * </pre></code>
 * @author Jared Erickson
 */
class Cursor {

    /**
     * The Layer
     */
    private Layer layer

    /**
     * The GeoTools FeatureIterator
     */
    private FeatureIterator<SimpleFeature> iter
    
    /**
     * The GeoTools FeatureCollection
     */
    private FeatureCollection<SimpleFeatureType, SimpleFeature> col
    
    /**
     * Create a new Cursor with a FeatureCollection and a Layer
     * @param col The GeoTools FeatureCollection
     * @param layer The Geoscript Layer
     */
    Cursor(FeatureCollection<SimpleFeatureType, SimpleFeature> col, Layer layer) {
        this.col = col
        this.iter = col.features()
        this.layer = layer
    }

    /**
     * Get the next Feature
     * @return The next Feature
     */
    Feature next() {
        Feature f = new Feature(iter.next())
        f.layer = layer
        f
    }

    /**
     * Read n features into a List
     * @param n The number of features to read
     * @return A List of Features
     */
    List<Feature> read(int n) {
        List<Feature> features = []
        (0..n).each {
            features.append(next())
        }
        features
    }

    /**
     * Whether there are Features remaining
     * @return Whether there are Features remaining
     */
    boolean hasNext() {
        iter.hasNext()
    }

    /**
     * Closes the Cursor.  This should always be called.
     */
    void close() {
        iter.close()
    }

}