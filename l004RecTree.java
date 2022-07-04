public class l004RecTree {

    public static int permuatationInfinityCoins(int[] arr, int tar, String psf){
        if(tar==0){
            System.out.println(psf);
            return 1;
        }
        int count=0;
        for(int i=0;i<arr.length;i++){
            if(tar-arr[i]>=0){
                count+=permuatationInfinityCoins(arr, tar-arr[i],psf+arr[i]+" ");
            }
        }
        return count;
    }
    public static int combinationInfinityCoins(int[] arr, int tar,int idx, String psf){
        if(tar==0){
            System.out.println(psf);
            return 1;
        }
        int count=0;
        for(int i=idx;i<arr.length;i++){
            if(tar-arr[i]>=0){
                count+=combinationInfinityCoins(arr, tar-arr[i],i,psf+arr[i]+" ");
            }
        }
        return count;
    }
    public static int combinationSingleCoins(int[] arr, int tar,int idx, String psf){
        if(tar==0){
            System.out.println(psf);
            return 1;
        }
        int count=0;
        for(int i=idx;i<arr.length;i++){
            if(tar-arr[i]>=0){
                count+=combinationSingleCoins(arr, tar-arr[i],i+1,psf+arr[i]+" ");
            }
        }
        return count;
    }

    public static int permuatationSingleCoins(int[] arr, int tar, String psf){
        if(tar==0){
            System.out.println(psf);
            return 1;
        }
        int count=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]>0 && tar-arr[i]>=0){
                // block
                int val= arr[i];
                arr[i]=-val;
                count+=permuatationSingleCoins(arr, tar-val,psf+val+" ");
                //unblock
                arr[i]=val;
            }
        }
        return count;
    }
    public static void coinsProblem(){
        int[] arr={2,3,5,7};
        int tar=10;
        // System.out.println(permuatationInfinityCoins(arr,tar,""));
        // System.out.println(combinationInfinityCoins(arr,tar,0,""));
        // System.out.println(combinationSingleCoins(arr,tar,0,""));
        // System.out.println(permuatationSingleCoins(arr,tar,""));
    }

    //=================================Subsequence Way===========================================
    public static int combinationSingleCoins_sub(int[] arr, int tar,int idx, String psf){
        if(tar==0 || idx== arr.length){
            if(tar==0){
                System.out.println(psf);
                return 1;
            }
            return 0;
        }
        int count=0;
        if(tar-arr[idx]>=0){
            count+=combinationSingleCoins_sub(arr, tar-arr[idx],idx+1,psf+arr[idx]+" ");
        }
        count+=combinationSingleCoins_sub(arr, tar,idx+1,psf);
        return count;
    }

    public static int combinationInfinityCoins_sub(int[] arr, int tar,int idx, String psf){
        if(tar==0 || idx==arr.length){
            if(tar==0){
                System.out.println(psf);
                return 1;
            }
            return 0;
        }
        int count=0;
        if(tar-arr[idx]>=0){
            count+=combinationInfinityCoins_sub(arr, tar-arr[idx],idx,psf+arr[idx]+" ");
        }
        count+=combinationInfinityCoins_sub(arr, tar,idx+1,psf);

        return count;
    }

    public static int permuatationInfinityCoins_sub(int[] arr, int tar,int idx, String psf){
        if(tar==0 || idx==arr.length){
            if(tar==0){
                System.out.println(psf);
                return 1;
            }
            return 0;
        }
        int count=0;
        if(tar-arr[idx]>=0){
            count+=permuatationInfinityCoins_sub(arr, tar-arr[idx],0,psf+arr[idx]+" ");
        }
        count+=permuatationInfinityCoins_sub(arr, tar,idx+1,psf);
        return count;
    }

    public static int permuatationSingleCoins_sub(int[] arr, int tar,int idx, String psf){
        if(tar==0 || idx==arr.length){
            if(tar==0){
                System.out.println(psf);
                return 1;
            }
            return 0;
        }
        int count=0;
        if(arr[idx]>0 && tar-arr[idx]>=0){
            // block
            int val= arr[idx];
            arr[idx]=-val;
            count+=permuatationSingleCoins_sub(arr, tar-val,0,psf+val+" ");
            //unblock
            arr[idx]=val;
        }
        count+=permuatationSingleCoins_sub(arr, tar,idx+1,psf);
        return count;
    }

    public static void coinsProblem_sub(){
        int[] arr={2,3,5,7};
        int tar=10;
        // System.out.println(combinationSingleCoins_sub(arr,tar,0,""));
        // System.out.println(combinationInfinityCoins_sub(arr,tar,0,""));
        // System.out.println(permuatationInfinityCoins_sub(arr,tar,0,""));
        System.out.println(permuatationSingleCoins_sub(arr,tar,0,""));
    }


    public static void main(String[] args){
        // coinsProblem();
        coinsProblem_sub();
    }
}
