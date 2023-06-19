package org.example;

import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;


public class IntegrationTest extends TestCase {
    Feelings feelings;

    @BeforeEach
    public void setUp() {
        this.feelings = new Feelings();
    }

    @Test
    public void testCheckNeighbors() {
        ArrayList<Integer> array = new ArrayList<Integer>(Arrays.asList(1, -1, 1));
        assertTrue(this.feelings.CheckNeighbours(array, 1));
    }

    @Test
    public void testFindSadFeeling() {
        ArrayList<Integer> array = new ArrayList<Integer>(Arrays.asList(-1, 1, 1, 0, -1, -1));
        assertEquals(this.feelings.FindSadFeeling(array, 1), 4);
    }

    @Test
    public void testInsertHappyFeelings() {
        ArrayList<Integer> array = new ArrayList<Integer>(Arrays.asList(-1, 1));
        assertEquals(this.feelings.InsertHappyFeelings(array, 0), new ArrayList<Integer>(Arrays.asList(1, -1, 1)));
    }

    @Test
    public void testHappyArray() {
        ArrayList<Integer> array = new ArrayList<Integer>(Arrays.asList(1));
        assertEquals(this.feelings.BeHappy(array), array);
    }

    @Test
    public void testUnhappyArray() {
        ArrayList<Integer> array = new ArrayList<Integer>(Arrays.asList(-1));
        assertEquals(this.feelings.BeHappy(array), new ArrayList<Integer>(Arrays.asList(1, -1, 1)));
    }

    @Test
    public void testSeverelyUnhappyArray() {
        ArrayList<Integer> array = new ArrayList<Integer>(Arrays.asList(-1, -1, -1));
        assertEquals(this.feelings.BeHappy(array), new ArrayList<Integer>(Arrays.asList(1, -1, 1, -1, 1, -1, 1)));
    }

}
