import java.util.ArrayList;
public class l003_ArrayQ {
    // equvi sum of 2 subset
    public static int equviSum(int[] arr,int idx, int sum1, int sum2, String set1,String set2){
        if(idx==arr.length){
            if(sum1==sum2){
               System.out.println(set1+" = "+set2);
               return 1;
            }
            return 0;
        }
        int count=0;
        count+=equviSum(arr, idx+1, sum1+arr[idx], sum2, set1+" "+arr[idx], set2);
        count+=equviSum(arr, idx+1, sum1, sum2+arr[idx], set1, set2+" "+arr[idx]);
        return count;
    }
    public static void equalSet() {
        int[] arr = { 10, 20, 30, 40, 50, 60, 70, 80 };
        if(arr.length==1){
            return;
        }
        // es call se mirror image bi aa jyenge    **********************
        // System.out.println(equviSum(arr,0, 0, 0, " ", ""));

        // Starting element ko value ko fix kr denge ek set m.
        System.out.println(equviSum(arr, 1, arr[0], 0, arr[0] + " ", ""));
    }

//============================================================================================
    //permutation in String {if unique elements in Araay}
    public static int permutation(String str,ArrayList<String> ans, String asf){
         if(str.length()==0){
            System.out.println(asf);
            return 1;
         }

         int count=0, n=str.length();
         for(int i=0;i<n;i++){
              String str1=str.substring(0,i)+str.substring(i+1,n);
              count+=permutation(str1, ans, asf+str.charAt(i));
         } 
        return count;
    }
    public static void perm(){
        ArrayList<String> ans=new ArrayList<>();
        System.out.println(permutation("abc", ans, ""));
    }

    // permutation for duplicate elements :: Sol1 {using boolean array}
public int permutationUnique(String str, String ans){
    if (str.length() == 0){
        System.out.println(ans);
        return 1;
    }
    boolean []vis=new boolean[26];
    int count = 0;
    for (int i = 0; i < str.length(); i++){
        char ch = str.charAt(i);
        if (!vis[ch - 'a']){
            vis[ch - 'a'] = true;
            String ros = str.substring(0, i) + str.substring(i + 1);S            count += permutationUnique(ros, ans + ch);
        }
    }
    return count;
}

// permutation for duplicate elements :: Sol1 {Sort string using count freq method then apply}
int permutationUnique2(String str, String ans){
    if (str.length() == 0){
        System.out.println(ans);
        return 1;
    }
    char prev = '$';
    int count = 0;
    for (int i = 0; i < str.length(); i++){
        char ch = str.charAt(i);
        if (prev != ch){
            String ros = str.substring(0, i) + str.substring(i + 1);
            count += permutationUnique2(ros, ans + ch);
        }
        prev = ch;
    }
    return count;
}

//=========================================================================================
    // leetcode 46 :: permutation on ARRAY
    public List<List<Integer>> permute(int[] nums) {
        return null;
    }

    public static void main(String[] args){
        // equalSet();
        perm();
    }
}
