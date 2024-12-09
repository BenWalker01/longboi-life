package com.spacecomplexity.longboilife.headless;

import com.spacecomplexity.longboilife.game.utils.Vector2Int;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2IntTests extends AbstractHeadlessGdxTest {
    @Test
    public void testBlankVector() {
        Vector2Int v1 = new Vector2Int();
        assertEquals(new Vector2Int(0, 0), v1);
    }

    @Test
    public void testEqualsMethod() {
        Vector2Int v1 = new Vector2Int(1, 2);
        Vector2Int v2 = new Vector2Int(1, 2);
        assertTrue(v1.equals(v2), "Vectors are equal");
        assertTrue(v1.equals(v1), "Vectors are the same");
        Vector2Int v3 = new Vector2Int(1, 3);
        assertFalse(v1.equals(v3), "Vectors are not equal");
        assertFalse(v1.equals(10), "Vectors are not equal");
    }

    @Test
    public void testHashCodeMethod() {
        Vector2Int v1 = new Vector2Int(1, 2);
        Vector2Int v2 = new Vector2Int(1, 2);
        assertEquals(v1.hashCode(), v2.hashCode());
    }

    @Test
    public void testAddMethod() {
        Vector2Int v1 = new Vector2Int(1, 2);
        Vector2Int v2 = new Vector2Int(1, 2);
        assertEquals(v1.add(v2), v2.add(v1));
        assertNotEquals(v1.add(v2), v1);
    }

    @Test
    public void testSubtractMethod() {
        Vector2Int v1 = new Vector2Int(1, 2);
        Vector2Int v2 = new Vector2Int(1, 2);
        assertEquals(v1.subtract(v2), v2.subtract(v1));
        assertNotEquals(v1.subtract(v2), v1);
    }

    @Test
    public void testMag2Method() {
        Vector2Int v1 = new Vector2Int(1, 2);
        assertSame(5, v1.mag2());
        assertNotEquals(0, v1.mag2());
    }

    @Test
    public void testMagMethod() {
        Vector2Int v1 = new Vector2Int(3, 4);
        assertEquals(5.0f, v1.mag());
        assertNotEquals(0, v1.mag());
    }

}
