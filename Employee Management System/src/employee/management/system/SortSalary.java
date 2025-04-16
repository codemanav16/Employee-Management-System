package employee.management.system;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SortSalary {
    public static void main(String[] args) {
        ArrayList<Integer> salaries = new ArrayList<>();
        
        // Fetch salaries from database
        try {
            Conn c = new Conn();
            String query = "SELECT salary FROM employee";
            ResultSet rs = c.s.executeQuery(query);
            
            while (rs.next()) {
                salaries.add(rs.getInt("salary"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Convert ArrayList to Array for sorting
        int[] salaryArray = salaries.stream().mapToInt(i -> i).toArray();
        
        // Sorting salaries using Merge Sort
        mergeSort(salaryArray, 0, salaryArray.length - 1);
        
        // Display sorted salaries
        System.out.println("Sorted Salaries:");
        System.out.println(Arrays.toString(salaryArray));
    }

    // Merge Sort Implementation
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        for (int i = 0; i < n1; i++) leftArr[i] = arr[left + i];
        for (int i = 0; i < n2; i++) rightArr[i] = arr[mid + 1 + i];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }

        while (i < n1) arr[k++] = leftArr[i++];
        while (j < n2) arr[k++] = rightArr[j++];
    }
}