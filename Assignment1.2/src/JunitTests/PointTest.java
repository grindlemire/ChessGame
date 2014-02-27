

package JunitTests;

import static org.junit.Assert.*;

import Model.Model.BoardClasses.Point;
import org.junit.Test;

public class PointTest {

    Point testPoint = new Point();


    @Test
    public void test() {

        assertNotNull("X not initialized", testPoint.getX());
        assertNotNull("Y not initialized",testPoint.getY());
        assertEquals("X not initialized",testPoint.getX(), -1);
        assertEquals("Y not initialized",testPoint.getY(), -1);

        testPoint.setPoint(2,3);
        assertEquals(2,testPoint.getX());
        assertEquals(3,testPoint.getY());


    }

}
