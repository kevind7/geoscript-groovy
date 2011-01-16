package geoscript.geom

import org.junit.Test
import static org.junit.Assert.*
import geoscript.proj.Projection
import org.geotools.geometry.jts.ReferencedEnvelope

/**
 * The Bounds unit test
 */
class BoundsTestCase {
	
    @Test void constructors() {
        ReferencedEnvelope e = new ReferencedEnvelope(1,3,2,4,null)
        Bounds b1 = new Bounds(e)
        assertEquals "(1.0,2.0,3.0,4.0)", b1.toString()
		
        Bounds b2 = new Bounds(1,2,3,4, new Projection("EPSG:2927"))
        assertEquals "(1.0,2.0,3.0,4.0,EPSG:2927)", b2.toString()
		
        Bounds b3 = new Bounds(1,2,3,4)
        assertEquals "(1.0,2.0,3.0,4.0)", b3.toString()

        Bounds b4 = new Bounds(1,2,3,4, "EPSG:2927")
        assertEquals "(1.0,2.0,3.0,4.0,EPSG:2927)", b4.toString()
    }
	
    @Test void l() {
        Bounds b = new Bounds(1,2,3,4)
        assertEquals 1.0, b.l, 0.0
    }

    @Test void west() {
        Bounds b = new Bounds(1,2,3,4)
        assertEquals 1.0, b.west, 0.0
    }

    @Test void b() {
        Bounds b = new Bounds(1,2,3,4)
        assertEquals 2.0, b.b, 0.0
    }

    @Test void south() {
        Bounds b = new Bounds(1,2,3,4)
        assertEquals 2.0, b.south, 0.0
    }

    @Test void r() {
        Bounds b = new Bounds(1,2,3,4)
        assertEquals 3.0, b.r, 0.0
    }

    @Test void east() {
        Bounds b = new Bounds(1,2,3,4)
        assertEquals 3.0, b.east, 0.0
    }
	
    @Test void t() {
        Bounds b = new Bounds(1,2,3,4)
        assertEquals 4.0, b.t, 0.0
    }

    @Test void north() {
        Bounds b = new Bounds(1,2,3,4)
        assertEquals 4.0, b.north, 0.0
    }

    @Test void getWidth() {
        Bounds b = new Bounds(1,2,3,4)
        assertEquals 2.0, b.width, 0.0
    }

    @Test void getHeight() {
        Bounds b = new Bounds(1,2,3,5)
        assertEquals 3.0, b.height, 0.0
    }

    @Test void expandBy() {
        Bounds b = new Bounds(1,2,3,4)
        Bounds b2 = b.expandBy(10)
        assertEquals b, b2
        assertEquals(-9, b.west, 0.0)
        assertEquals(-8, b.south, 0.0)
        assertEquals(13, b.east, 0.0)
        assertEquals(14, b.north, 0.0)
    }

    @Test void expand() {
        Bounds b1 = new Bounds(1,1,4,4)
        Bounds b2 = new Bounds(8,8,20,20)
        Bounds b3 = b1.expand(b2)
        assertEquals b1, b3
        assertEquals(1, b3.west, 0.0)
        assertEquals(1, b3.south, 0.0)
        assertEquals(20, b3.east, 0.0)
        assertEquals(20, b3.north, 0.0)
    }

    @Test void scale() {
        Bounds b1 = new Bounds(5,5,10,10)
        Bounds b2 = b1.scale(2)
        assertEquals(2.5, b2.west, 0.0)
        assertEquals(2.5, b2.south, 0.0)
        assertEquals(12.5, b2.east, 0.0)
        assertEquals(12.5, b2.north, 0.0)

    }

    @Test void getGeometry() {
        Bounds b = new Bounds(1,2,3,4)
        Geometry g = b.geometry
        assertEquals "POLYGON ((1 2, 1 4, 3 4, 3 2, 1 2))", g.wkt
    }

    @Test void getPolygon() {
        Bounds b = new Bounds(1,2,3,4)
        Geometry g = b.polygon
        assertEquals "POLYGON ((1 2, 1 4, 3 4, 3 2, 1 2))", g.wkt
    }

    @Test void reproject() {
        Bounds b1 = new Bounds(-111, 44.7, -110, 44.9, "EPSG:4326")
        println(b1.polygon.wkt)
        Bounds b2 = b1.reproject("EPSG:26912")
        assertEquals(499999, b2.west as int)
        assertEquals(4949624, b2.south as int)
        assertEquals(579224, b2.east as int)
        assertEquals(4972327, b2.north as int)
    }

    @Test void string() {
        ReferencedEnvelope e = new ReferencedEnvelope(1,3,2,4,null)
        Bounds b1 = new Bounds(e)
        assertEquals "(1.0,2.0,3.0,4.0)", b1.toString()
		
        Bounds b2 = new Bounds(1,2,3,4, new Projection("EPSG:2927"))
        assertEquals "(1.0,2.0,3.0,4.0,EPSG:2927)", b2.toString()
		
        Bounds b3 = new Bounds(1,2,3,4)
        assertEquals "(1.0,2.0,3.0,4.0)", b3.toString()
    }

    @Test void getAt() {
        Bounds b = new Bounds(1,2,3,4)
        assertEquals(1, b[0], 0)
        assertEquals(2, b[1], 0)
        assertEquals(3, b[2], 0)
        assertEquals(4, b[3], 0)
        assertNull(b[4])
        def (w,s,e,n) = b
        assertEquals(1, w, 0)
        assertEquals(2, s, 0)
        assertEquals(3, e, 0)
        assertEquals(4, n, 0)
    }
}