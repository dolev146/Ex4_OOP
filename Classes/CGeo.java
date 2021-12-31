package Classes;

import Interfaces.GeoLocation;

import java.util.Objects;

/**
 * Geo_Class
 */
public class CGeo implements GeoLocation {
    private double x, y, z;

    /**
     * Deep copy constructor.
     * 
     * @param other - geo_location
     */
    public CGeo(GeoLocation other) {
        this.x = other.x();
        this.y = other.y();
        this.z = other.z();

    }

    public CGeo(double x1, double y1, double z1) {
        this.x = x1;
        this.y = y1;
        this.z = z1;
    }

    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {
        return this.z;
    }

    /**
     * Returns the distance between two geoLocations points.
     * 
     * @param g - geo_location
     * @return the distance between this and g.
     */
    @Override
    public double distance(GeoLocation g) {
        double nx = Math.pow(this.x - g.x(), 2);
        double ny = Math.pow(this.y - g.y(), 2);
        double nz = Math.pow(this.z - g.z(), 2);
        double distance = Math.sqrt(nx + ny + nz);
        return distance;
    }

    @Override
    public String toString() {
        return this.x + ", " + this.y + ", " + this.z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CGeo cGeo = (CGeo) o;
        return Double.compare(cGeo.x, x) == 0 && Double.compare(cGeo.y, y) == 0 && Double.compare(cGeo.z, z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}