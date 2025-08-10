package com.dsa.dynamic.programming;

import java.util.ArrayList;
import java.util.List;

public class LongestCommonSubSequence {

  public static void main(String[] args) {
    String s1 = "ABCBDAB";
    String s2 =  "BDCAB";

    List<String> subsequences1 = generateSubSequence(s1);
    List<String> subsequences2 = generateSubSequence(s2);
    System.out.println(subsequences1);
    System.out.println(subsequences2);
    int maxLength = 0;

    for (String ss1 : subsequences1) {
      for (String ss2 : subsequences2) {
        if (ss1.equals(ss2) && ss2.length() > maxLength) {
          maxLength = ss1.length();
        }
      }
    }

    System.out.println("Longest Common Subsequence Length is : " + maxLength);

  }

  private static List<String> generateSubSequence(String str) {
    List<String> result = new ArrayList<>();
    util(str, 0, "", result);
    return result;
  }

  static void util(String str, int index, String subsequence, List<String> result) {
    if (index == str.length()) {
      result.add(subsequence);
      return;
    }

    // pick
    util(str, index + 1,  subsequence + str.charAt(index), result);

    // not pick
    util(str, index + 1, subsequence, result);
  }


}
