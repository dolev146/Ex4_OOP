package ClassesTest;

import Classes.CGeo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CGeoTest {


    @Test
    void x() {
        var g1 = new CGeo(1, 2, 3);
        assertEquals(1.0, g1.x());
    }

    @Test
    void y() {
        var g1 = new CGeo(1, 2, 3);
        assertEquals(2.0, g1.y());
    }

    @Test
    void z() {
        var g1 = new CGeo(1, 2, 3);
        assertEquals(3.0, g1.z());
    }

    @Test
    void distance() {
        var g1 = new CGeo(1, 2, 3);
        var g2 = new CGeo(1, 2, 3);
        assertEquals(0.0, g1.distance(g2));
    }

    @Test
    void testToString() {
        assertEquals(1.0 + ", " + 2.0 + ", " + 3.0, new CGeo(1, 2, 3).toString());
    }
}