package org.example;

import java.util.ArrayList;
import java.util.Arrays;

public class App 
{

    public static void main( String[] args )
    {
        Feelings feelings = new Feelings();
        ArrayList<Integer> array = new ArrayList<Integer>(Arrays.asList(-1,-1,0,0,1,1,-1,1,0,-1,1,0,1,1,-1, 0,1,1));

        System.out.print(feelings.BeHappy(array));
    }

}
