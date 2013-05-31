package com.wasoft.util;

public class ArraySupport
{
	
	/**
	 * 如果value在数组中返回ture
	 * @param array
	 * @param value
	 * @return
	 */
    public static boolean Contains(String[] array, String value) 
    {
        boolean isIncluded = false;

        if (array == null || value == null) {
            return false;
        }
        for (int i = 0; i < array.length; i++) {
            if (value.equals(array[i])) {
                isIncluded = true;
                break;
            }
        }
        return isIncluded;
    }
    
    public static boolean Contains(int[] array, int value)
    {
        boolean isIncluded = false;

        if (array == null) 
        {
            return false;
        }
        for (int i = 0; i < array.length; i++) 
        {
            if (array[i] == value) 
            {
                isIncluded = true;
                break;
            }
        }
        return isIncluded;
    }
    
	public static void main(String[] args) throws Exception 
	{
		int [] arr = new int[]{1,2,3,4,5,127,128,22};
		
		for (int i = 0; i < arr.length; i++)
		{			
			System.out.println(Contains(arr, 128));
		}
		
	}
}
