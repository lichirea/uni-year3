package com.company;




import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class Colors {

    private int colorsNumber;
    private Map<Integer, String> codesToColors;

    public Colors(int colorsNumber){
        this.colorsNumber = colorsNumber;

        codesToColors = new HashMap<>();

        for(int code=0; code< colorsNumber; code++)
        {
            codesToColors.put(code,"");

        }
    }

    public void addCodeToColor(int code, String color){
        codesToColors.put(code,color);
    }

    public Map<Integer,String> getNodesToColors(List<Integer> codes){
        Map<Integer, String> map = new HashMap<>();

        for(int index=0;index<codes.size();index++)
        {
            String color = codesToColors.get(codes.get(index));
            map.put(index,color);
        }

        return map;
    }
}
