import java.util.ArrayList;
public class l001Basic{
    public static void printIncreasing(int a, int b){
        if(a>b){
            return;
        }
        System.out.println(a);
        printIncreasing(a+1, b);
    }
    public static void printDecersing(int a, int b){
        if(a>b){
            return;
        }
        printIncreasing(a+1, b);
        System.out.println(a);
    }
    public static void printIncreasingDecersing(int a, int b){
        if(a>b){
            return;
        }
        System.out.println(a);
        printIncreasing(a+1, b);
        System.out.println(a);
    }
    public static void printOddEven(int a, int b){
        if(a>b){
            return;
        }
        if(a%2 == 1){
            System.out.println(a);
        }
        printIncreasing(a+1, b);
        if(a%2 == 0){
            System.out.println(a);
        }
    }

    public static int factorial(int n){
        return n==0 ? 1 : n*factorial(n-1);
    }

    public static int power_Linear(int x, int n){
        return n==0 ? 1 : x*power_Linear(x,n-1);
    }

   // all above questions[ Recursion in One Call ] --> Time: O(N) , Space: O(N)


    //time : O(logN), Space : O(logN)a
    public static int power_Log(int x, int n){
        if(n==0){
            return 1;
        }
        int nDiv2= power_Log(x, n/2);
        int myPow=nDiv2*nDiv2;
        if(n%2 == 1){
           myPow*=x;
        }
        return myPow;
    }

// ==================================================================================
   // Recursion in Array --> TIME :O(N), SPACE:O(N)
   public static void Display(int arr[], int idx){
       if(idx==arr.length){
           return;
       }
       System.out.println(arr[idx]);
       Display(arr, idx+1);
   }

   public static void Display_Reverse(int arr[], int idx){
    if(idx==arr.length){
        return;
    }
    Display_Reverse(arr, idx+1);
    System.out.println(arr[idx]);
   }

   public static int max(int arr[], int idx){
       if(idx==arr.length){
           return -(int)1e9;
       }
       int maxFromidxPO=max(arr,idx+1);
       if(arr[idx]>maxFromidxPO){
         maxFromidxPO=arr[idx];
       }
       return maxFromidxPO;
   }

   public static int min(int arr[], int idx){
    if(idx==arr.length){
        return (int)1e9;
    }
    int minFromidxPO=min(arr,idx+1);
    return Math.min(minFromidxPO, arr[idx]);
   }

   //preOrder
   public static boolean find(int arr[], int idx,int data){
    if(idx==arr.length){
        return false;
    }
    if(arr[idx]==data){
        return true;
    }
    boolean isFromidxPO=find(arr,idx+1,data);
    return isFromidxPO;
    //return arr[idx]==data || find(arr,idx+1, data);
   }

   //preOrder
   public static int firstIdx(int arr[], int idx,int data){
    if(idx==arr.length){
        return -1;
    }
    if(arr[idx]==data){
        return idx;
    }
    return firstIdx(arr,idx+1,data);
   }

   public static int lastIdx(int arr[], int idx,int data){
    if(idx==arr.length){
        return -1;
    }
    int lIdx=lastIdx(arr,idx+1,data);
    if( lIdx == -1){
        return lIdx;
    }
    return arr[idx]==data ? idx : -1;
   }
   

   public static int[] allIndex(int[] arr,int idx, int csf,int data){
       if(idx==arr.length){
           int[] ans=new int[csf];
           return ans;
       }

      int rArr[]=null;
      if(arr[idx]==data){
          rArr=allIndex(arr, idx+1, csf+1, data);
          rArr[csf]=idx; // upr and niche aate huye data same stage pe milta h
      } else{
        rArr=allIndex(arr, idx+1, csf+1, data);
      }
      return rArr;
   }

// Recursion with 2 Calls --> Time : O(2^N), Space : O(logN)
   public static int printTreePath(int n){
    if(n==0 || n==1){
        System.out.println("base :"+ n);
        return n;
    }
    int ans=0;
    System.out.println("Pre :"+ n);

    ans+=printTreePath(n-1);
    System.out.println("In :"+ n);
    ans+=printTreePath(n-2);
    System.out.println("Post :"+ n);

    return ans+3;
}
//===================================================================================== 
   // Recursion on ArrayList :: Time :O(N2), Space:O(N)
   public static ArrayList<String> subSeq(String str,int idx){
        if(idx==str.length()){
            ArrayList<String> base=new ArrayList<>();
            base.add("");
            return base;
        }

       ArrayList<String> list=subSeq(str, idx+1);

       // str.charAt(idx) ni aayega 
       ArrayList<String> myAns=new ArrayList<>(list);
       // str.charAt(idx) aayega 
       for(String s : list){
        myAns.add(str.charAt(idx)+s);
       }
       return myAns;
   }

   //    O(2^h)
   public static int subSeq(String str,int idx,String psf,ArrayList<String> res){
    if(idx==str.length()){
        res.add(psf);
        return 1;
    }
    int count=0;
    count+=subSeq(str, idx+1, psf+str.charAt(idx), res);
    count+=subSeq(str, idx+1, psf, res);

    return count;
}

   // get keypad Combination using recursion
   static String[] pad={".;", "abc","def","ghi","jkl","mno","pqrs","tu","vwx","yz"};
   public static ArrayList<String> getKPC(String str,int idx){
       if(idx==str.length()){
        ArrayList<String> base=new ArrayList<>();
        base.add("");
        return base;
       }
       ArrayList<String> recAns=getKPC(str, idx+1);
       ArrayList<String> myList=new ArrayList<>();
       char ch=str.charAt(idx);
       String strAtch=pad[ch-'0'];
       for(int i=0;i<strAtch.length();i++){
           char c1=strAtch.charAt(i);
           for(String c2 : recAns){
              myList.add(c1+c2);
           }
       }
       return myList;
   }


   // get stair Path using Recursion
public static ArrayList<String> stairPath(int n){
    if(n==0){
      ArrayList<String>base=new ArrayList<>();
      base.add("");
      return base;
    }
  ArrayList<String> list=new ArrayList<>();
  ArrayList<String> res=null;
  if(n-1>=0){
    res=stairPath(n-1);
    for(String s : res){
        list.add(1+s);
    }
  }
  if(n-2>=0){
     res=stairPath(n-2);
     for(String s : res){
         list.add(2+s);
     }
  }
  if(n-3>=0){
     res=stairPath(n-3);
     for(String s : res){
         list.add(3+s);
     }
  }
  return list;
}

    public static void main(String[] args){

    }
}