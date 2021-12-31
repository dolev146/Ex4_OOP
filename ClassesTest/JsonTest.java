package ClassesTest;

import Classes.GA;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class JsonTest {
    private static GA G1Json;

    @BeforeAll
    static void beforeAll() {
        G1Json = new GA();
        G1Json.load("./json_data/G1.json");
    }

    @Test
    void getGrapg() {
    }

    @Test
    void getGrapgAlgo() {
    }

    @Test
    void runGUI() {
    }

    @Test
    void main() {

    }
}