package ClassesTest;

import Classes.CEdge;
import Interfaces.EdgeData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CEdgeTest {


    @Test
    void getSrc() {
        EdgeData e = new CEdge(1, 2, 3);
        assertEquals(1, e.getSrc());
    }

    @Test
    void getDest() {
        EdgeData e = new CEdge(1, 2, 3);
        assertEquals(2, e.getDest());
    }

    @Test
    void getWeight() {
        EdgeData e = new CEdge(1, 2, 3);
        assertEquals(3.0, e.getWeight());
    }

    @Test
    void getInfo() {
        EdgeData e = new CEdge(1, 2, 3);
        assertEquals("white", e.getInfo());
    }

    @Test
    void setInfo() {
        EdgeData e = new CEdge(1, 2, 3);
        assertEquals("white", e.getInfo());
        e.setInfo("gray");
        assertEquals("gray", e.getInfo());
        e.setInfo("black");
        assertEquals("black", e.getInfo());
    }

    @Test
    void getTag() {
        EdgeData e = new CEdge(1, 2, 3);
        assertEquals(-1, e.getTag());
    }

    @Test
    void setTag() {
        EdgeData e = new CEdge(1, 2, 3);
        assertEquals(-1, e.getTag());
        e.setTag(0);
        assertEquals(0, e.getTag());
        e.setTag(1);
        assertEquals(1, e.getTag());
    }

    @Test
    void testToString() {
        EdgeData e = new CEdge(1, 2, 3);
        assertEquals("CEdge{ " +
                "src=" + 1 +
                ", w=" + 3.0 +
                ", dest=" + 2 +
                '}', e.toString());
    }

    @Test
    void testEquals() {
        EdgeData e1 = new CEdge(1, 2, 3);
        EdgeData e2 = new CEdge(1, 2, 3);
        assertEquals(e1, e2);
        assertFalse(e1 == e2);
        assertTrue(e1.equals(e2));
    }

    @Test
    void testHashCode() {
        EdgeData e1 = new CEdge(1, 2, 3);
        EdgeData e2 = new CEdge(1, 2, 3);
        assertEquals(e1.hashCode(), e2.hashCode());
    }
}