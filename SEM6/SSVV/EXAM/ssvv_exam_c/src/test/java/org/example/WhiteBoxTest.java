package org.example;

import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;


public class WhiteBoxTest extends TestCase {
    Feelings feelings;

    @BeforeEach
    public void setUp() {
        this.feelings = new Feelings();
    }

    @Test
    public void testNotSadFeeling() {
        ArrayList<Integer> array = new ArrayList<Integer>(Arrays.asList(-1, 1, 0));
        assertTrue(this.feelings.CheckNeighbours(array, 1));
    }

    @Test
    public void testNeighborsValid() {
        ArrayList<Integer> array = new ArrayList<Integer>(Arrays.asList(1, -1, 1));
        assertTrue(this.feelings.CheckNeighbours(array, 1));
    }

    @Test
    public void testLeftNeighborBranchInvalid() {
        ArrayList<Integer> array = new ArrayList<Integer>(Arrays.asList(1, -1, -1));
        assertFalse(this.feelings.CheckNeighbours(array, 1));
    }

    @Test
    public void testRightNeighborBranchInvalid() {
        ArrayList<Integer> array = new ArrayList<Integer>(Arrays.asList(-1, -1, 1));
        assertFalse(this.feelings.CheckNeighbours(array, 1));
    }
}
