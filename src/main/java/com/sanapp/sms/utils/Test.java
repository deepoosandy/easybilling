package com.sanapp.sms.utils;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
        Integer[] A={1, 3, 6, 4, 1, 2};
        // write your code in Java SE 8
        Map<Integer, Integer> map=new TreeMap<>();

        for(int i=0;i<A.length;i++){
            map.put(A[i],A[i]);
        }

        for(int i=1;i<A.length;i++){
            if(!map.containsKey(i)){
                System.out.println(i);
                return;
            }
        }
    }
}
