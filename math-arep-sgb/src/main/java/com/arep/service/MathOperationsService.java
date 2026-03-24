package com.arep.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class MathOperationsService {

    private static ArrayList<Integer> beautifyString(String list) {
        ArrayList<Integer> returnList = new ArrayList<Integer>();
        if (list != null && !list.trim().isEmpty()) {
            String[] parts = list.split("\\s*,\\s*"); 
            ArrayList<String> newList = new ArrayList<>(Arrays.asList(parts));
            for (String item : newList) {
                returnList.add(Integer.parseInt(item));
            }
        }
        return returnList;
    }

    private static int binarySearch(ArrayList<Integer> arr, int target) {
        int left = 0;
        int right = arr.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr.get(mid) == target) {
                return mid;
            } else if (arr.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public int linearSearch(String list, int value) {
        ArrayList<Integer> local = beautifyString(list);
        for(int i = 0 ; i < local.size() ; i++){
            if(local.get(i) == value){
                return i;
            }
        }
        return -1;
    }

    public int binarySearch(String list, int value) {
        ArrayList<Integer> local = beautifyString(list);
        return binarySearch(local, value);
    }
}