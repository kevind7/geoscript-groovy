GeoScript Groovy
================
GeoScript Groovy is the `Groovy <http://groovy.codehaus.org/>`_ implementation of `GeoScript <http://geoscript.org>`_.  GeoScript is a geospatial scripting API for the JVM that contains one API and four implementations (`Python <https://github.com/jdeolive/geoscript-py>`_, `JavaScript <https://github.com/tschaub/geoscript-js>`_, `Scala <https://github.com/dwins/geoscript.scala>`_, and `Groovy <https://github.com/jericks/geoscript-groovy>`_).

GeoScript is built on the shoulders of giants and essentially wraps the `Java Topology Suite <http://tsusiatsoftware.net/jts/main.html>`_ and the `GeoTools <http://geotools.org/>`_ libraries.

GeoScript provides several modules that includes geometry, projection, features, layers, workspaces, styling and rendering.

Build
-----
Building GeoScript Groovy is quite easy.  You will need to have git, Java, Maven, and Ant installed.

Use git to clone the repository::

    git clone git://github.com/jericks/geoscript-groovy.git

Use maven to build, test, and package::

    mvn clean install

The distribution can be found in target/geoscript-groovy-${version}-app/geoscript-groovy-${version}.

Use
---
To use GeoScript Groovy you need Java and Groovy installed and on your PATH.  Next, download the `latest stable release <https://github.com/jericks/geoscript-groovy/releases>`_ ,
the `latest in development build <http://ares.opengeo.org/geoscript/groovy/>`_, or build the code yourself.  Then put the GeoScript Groovy bin directory on your PATH.  You are now ready to use GeoScript Groovy!

GeoScript Groovy has three commands:

1. geoscript-groovy (which can run Groovy files)
2. geoscript-groovysh (which starts a REPL shell)
3. geoscript-groovyConsole (which starts a graphical editor/mini IDE)

Buffering a Point::

    import geoscript.geom.Point

    def point = new Point(0,0)
    def poly = point.buffer(10)
    println(poly.wkt)

Project a Geomemtry::

    import geoscript.geom.Point
    import geoscript.proj.Projection

    def p1 = new Point(-111.0, 45.7)
    def p2 = Projection.transform(p, "EPSG:4326", "EPSG:26912")

Read a Shapefile::

    import geoscript.layer.Shapefile
    import geoscript.geom.Bounds

    def shp = new Shapefile("states.shp")
    int count = shp.count
    Bounds bounds = shp.bounds
    shp.features.each {f->
        println(f)
    }

Drawing a Shapefile::

    import geoscript.layer.Shapefile
    import geoscript.style.Stroke
    import static geoscript.render.Draw.draw

    def shp = new Shapefile("states.shp")
    shp.style = new Stroke("#999999", 0.1)
    draw(shp)

Reading a Raster::

    import geoscript.layer.GeoTIFF

    def format = new GeoTIFF(new File("raster.tif"))
    def raster = format.read()

    println "Format = ${raster.format}"
    println "Proj EPSG = ${raster.proj.id}"
    println "Proj WKT = ${raster.proj.wkt}"
    println "Bounds = ${raster.bounds.geometry.wkt}"
    println "Size = ${raster.size}"
    println "Block Size = ${raster.blockSize}"
    println "Pixel Size = ${raster.pixelSize}"
    println "Band:"
    raster.bands.eachWithIndex{b,i ->
        println "   ${i}). ${b}"
    }

See the `web site <http://geoscript.org>`_ or the `examples directory <https://github.com/jericks/geoscript-groovy/tree/master/examples>`_ for more examples.

You can also use GeoScript Groovy as a library. If you use Maven you will need to add the Boundless Maven Repository::

    <repositories>
        <repository>
            <id>boundless</id>
            <name>Boundless Maven Repository</name>
            <url>http://repo.boundlessgeo.com/main</url>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
        </repository>
    </repositories>

and then include the GeoScript Groovy dependency::

    <dependency>
        <groupId>org.geoscript</groupId>
        <artifactId>geoscript-groovy</artifactId>
        <version>1.4.0</version>
    </dependency>

Versions
--------

+-----------+----------+-----------+------+--------+
| GeoScript | GeoTools | GeoServer | JTS  | Groovy |
+-----------+----------+-----------+------+--------+
| 1.5       | 13       | 2.7       | 1.13 | 2.3.7  |
+-----------+----------+-----------+------+--------+
| 1.4       | 12       | 2.6       | 1.13 | 2.2.2  |
+-----------+----------+-----------+------+--------+
| 1.3       | 11       | 2.5       | 1.13 | 2.1.9  |
+-----------+----------+-----------+------+--------+
| 1.2       | 10       | 2.4       | 1.13 | 2.1.6  |
+-----------+----------+-----------+------+--------+
| 1.1       | 9        | 2.3       | 1.13 | 1.8.9  |
+-----------+----------+-----------+------+--------+
| 1.0       | 8        | 2.2       | 1.12 | 1.8.8  |
+-----------+----------+-----------+------+--------+

Presentations
-------------
`GeoScript: The GeoSpatial Swiss Army Knife (FOSS4G 2014) <http://geoscript.github.io/foss4g2014-talk/#/>`_

`Using GeoScript Groovy (CUGOS 2014) <http://www.slideshare.net/JaredErickson/using-geoscript-groovy>`_

`Rendering Maps in GeoScript (CUGOS 2012) <http://www.slideshare.net/JaredErickson/geo-scriptstylerendering>`_

`Scripting GeoServer (CUGOS 2012) <http://www.slideshare.net/JaredErickson/scripting-geoserver>`_

`GeoScript: Spatial Capabilities for Scripting Languages (FOSS4G 2011) <http://www.slideshare.net/jdeolive/geoscript-spatial-capabilities-for-scripting-languages>`_

License
-------
GeoScript Groovy is open source and licensed under the MIT license.

.. image:: https://travis-ci.org/geoscript/geoscript-groovy.svg?branch=master
    :target: https://travis-ci.org/geoscript/geoscript-groovy
