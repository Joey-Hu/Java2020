package com.hh.www.test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

/**
 * @author: huhao
 * @time: 2020/4/14 9:07
 * @desc:
 */
public class SortAlgos {

    public static void main(String[] args) {
        int[] nums = {3, 2, 5, 1, 4};

        SortAlgos sortAlgos = new SortAlgos();
//        sortAlgos.bubleSort(nums);
        System.out.println(Arrays.toString(nums));
        sortAlgos.selectionSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 算法描述：
     * 重复遍历要排序的数组，比较相邻的两个元素的大小，如果顺序错误就交换，知道没有在需要交换的元素
     * @param nums
     */
    public void bubleSort(int[] nums){

        int length = nums.length;

        for(int i=0; i<length-1; i++){
            for(int j=1; j<=length-1-i;j++){
                if(nums[j]<nums[j-1]){
                    swap(nums, j, j-1);
                }
            }
        }

    }

    /**
     * 算法描述：
     * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，
     * 然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
     * @param nums
     */
    public void selectionSort(int[] nums){

        int length = nums.length;
        int minIndex;

        for(int i=0; i<length-1; i++){
            minIndex = i;
            for(int j=i+1; j<length; j++){
                if(nums[j]<nums[minIndex]){
                    minIndex = j;
                }
            }
            swap(nums, minIndex, i);
        }
    }

    private void swap(int[] nums, int i, int j) {

            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
    }
}
