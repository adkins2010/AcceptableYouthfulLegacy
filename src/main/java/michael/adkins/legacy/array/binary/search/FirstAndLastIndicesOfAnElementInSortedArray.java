package michael.adkins.legacy.array.binary.search;

import java.util.Arrays;
class FirstAndLastIndicesOfAnElementInSortedArray {
  public static int[] getRange(int[] arr, int target) throws InterruptedException {
    return new int[] {binarySearch(arr, 0, arr.length - 1, target, true), binarySearch(arr, 0, arr.length - 1, target, false)};
  }
  
  public static int binarySearch(int [] arr, int low, int high, int target) {
    int index = -1;
      while(low < high) {
        int mid = low + (high - low) / 2;
        if(target == arr[mid]) {
          index = mid;
          break;
        } else if(target < arr[mid]) {
          high = mid-1;
        } else {
          low = mid+1;
        }
      }
    return index;
  }

  public static int binarySearch(int [] arr, int low, int high, int target, boolean firstOccurence) throws InterruptedException {
    int index = -1;
    System.out.printf("Low: %d%nHigh: %d%n", low, high);
      while(low < high) {
        int mid = low + (high - low) / 2;
        System.out.printf("Low: %d%nMid: %d%nHigh: %d%n", low, mid, high);
        if(firstOccurence) {
          if((mid == 0 || target > arr[mid-1])  && target == arr[mid]) {
            index = mid;
            break;
          } else if(target < arr[mid]) {
            high = mid-1;
          } else {
            low = mid-1;
          }
        } else {
          if((mid == arr.length-1 || target <= arr[mid+1]) && target == arr[mid]) {
            index = mid;
            break;
          } else if(target > arr[mid]) {
            low = mid+1;
          } else {
            high = mid+1;
          }
        }
      }
    System.out.println("Index: " + index);
    Thread.sleep(1000L);
    return index;
  }
  public static void main(String[] args) {
    // System.out.println(args);
    int[] test = new int[]{1, 3, 3, 5, 7, 9, 9, 9, 15};
    int target = 9;
    System.out.println(Arrays.toString(test));
    // System.out.printf("Index of target %d at %d %n", target, binarySearch(test, 0, test.length-1, target));
    try {
      System.out.printf("Range of target %d at %s %n", target, Arrays.toString(getRange(test, target)));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}