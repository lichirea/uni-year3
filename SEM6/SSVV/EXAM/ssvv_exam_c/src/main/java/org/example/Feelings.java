package org.example;

import java.util.ArrayList;

public class Feelings {

    public int FindSadFeeling(ArrayList<Integer> array, int start) {
        for (int i = start; i < array.size(); i++) {
            if (array.get(i) == -1) {
                return i;
            }
        }
        return -1;
    }

    public boolean CheckNeighbours(ArrayList<Integer> array, int position) {
        if (array.get(position) != -1) {
            return true;
        }
        else {
            if(position - 1 >= 0 && array.get(position - 1) == 1) {
                if(position + 1 < array.size() && array.get(position + 1) == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Integer> InsertHappyFeelings(ArrayList<Integer> array, int position) {
        if(position - 1 < 0) {
            array.add(position, 1);
            position++;
        }
        else {
            if(array.get(position - 1) != 1) {
                array.add(position, 1);
                position++;
            }
        }

        if(position + 1 >= array.size()) {
            array.add(position + 1, 1);
        }
        else {
            if(array.get(position + 1) != 1) {
                array.add(position + 1, 1);
            }
        }

        return array;
    }

    public ArrayList<Integer> BeHappy(ArrayList<Integer> array) {
        int sad = -1;

        for(int i = 0; i < array.size(); i++) {
            sad = FindSadFeeling(array, i);
            if(sad != -1) {
                if(!CheckNeighbours(array, sad)) {
                    array = InsertHappyFeelings(array, sad);
                }
            }
        }
        return array;
    }
}
