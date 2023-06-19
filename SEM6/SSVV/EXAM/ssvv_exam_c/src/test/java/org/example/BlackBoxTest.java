package org.example;

import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;


public class BlackBoxTest extends TestCase {
    Feelings feelings;

    @BeforeEach
    public void setUp() {
        this.feelings = new Feelings();
    }

    @Test
    public void testNoSadFeeling() {
        ArrayList<Integer> array = new ArrayList<Integer>(Arrays.asList(1));
        assertEquals(this.feelings.FindSadFeeling(array, 0), -1);
    }

    @Test
    public void testInvalidPositionLower() {
        ArrayList<Integer> array = new ArrayList<Integer>(Arrays.asList(1));
        assertThrows(IndexOutOfBoundsException.class, () -> this.feelings.CheckNeighbours(array, -1));
    }

    @Test
    public void testInvalidPositionGreater() {
        ArrayList<Integer> array = new ArrayList<Integer>(Arrays.asList(1, -1, 1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> this.feelings.CheckNeighbours(array, 4));
    }

    @Test
    public void testInvalidPositionSizeMinusTwo() {
        ArrayList<Integer> array = new ArrayList<Integer>(Arrays.asList(1, -1, 0, 1, 0));
        assertEquals(this.feelings.FindSadFeeling(array, 3), -1);
    }

    @Test
    public void testStartingPositionSadFeeling() {
        ArrayList<Integer> array = new ArrayList<Integer>(Arrays.asList(-1));
        assertEquals(this.feelings.FindSadFeeling(array, 0), 0);
    }

    @Test
    public void testSadFeelingPastStartingPosition() {
        ArrayList<Integer> array = new ArrayList<Integer>(Arrays.asList(-1, 1));
        assertEquals(this.feelings.FindSadFeeling(array, 1), -1);
    }

    @Test
    public void testMultipleSadFeelings() {
        ArrayList<Integer> array = new ArrayList<Integer>(Arrays.asList(-1, 1, 1, 0, -1, -1));
        assertEquals(this.feelings.FindSadFeeling(array, 1), 4);
    }
}
