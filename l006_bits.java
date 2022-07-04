public class l006_bits {
    
    public static void leftShift(){
        int n=5;
        for(int i=1;i<6;i++){
           System.out.println((n<<i));
        }
    }
    public static void rightShift(){
        int n=160;
        for(int i=1;i<6;i++){
           System.out.println((n>>i));
        }
    }

    public static void offToOn(int n, int k){
        int x= (1<< k);
        n=n | x;
        System.out.println(n);
    }
    public static void onToOff(int n, int k){
        int x=(~(1<<k));
        n =n & x;
        System.out.println(n);
    }

    // leetcode 191 
    public int hammingWeight_01(int n) {
        int count=0;
        for(int i=0;i<32;i++){
            if((n &(1<<i)) != 0){
                count++;
            }
        }
        return count;
    }

    public int hammingWeight_02(int n) {
        int count=0;
        while(n != 0){
            if((n & 1) != 0){
                count++;
            }
            
            n=(n>>>1);
        }
        return count;
    }

    //log(n)
    public int hammingWeight_03(int n) {
        int count=0;
        while(n != 0){
            count++;
            n=n & (n-1);
        }
        return count;
    }

    // leetcode 338
    public int[] countBits(int n) {
        int [] output=new int[n+1];
        
        for(int i=1;i<=n;i++){
            output[i]=output[i & (i-1)]+1;
        }
        return output;
    }

    // leetcode 231 
    public boolean isPowerOfTwo(int n){
        return (n>0 && (n &(n-1)) ==0);
    }

    // leetcode 342
    public boolean isPowerOfFour(int n) {
        if(n<=0 || (n &(n-1)) !=0) return false; // must be power of 2
        int count=0;
        while(n !=0){
            if((n & 1) == 0){
                count++;
            }
            
            n=n>>>1;
        }
        return (count & 1) == 0; 
    }

    // leetcode 136
    public int singleNumber(int[] nums) {
        int ans=0;
        for(int ele : nums){
            ans ^=ele;
        }
        return ans;
    }

    // leetcode 137
    public int singleNumber_II(int[] nums) {
        int ans=0;
        
        return ans;
    }

    //leetcode 260
    public int singleNumber_III(int[] nums) {
        int ans=0;
        
        return ans;
    }

    // swap two numbers using XOR
    public void swap(int a, int b){
        a=a^b;
        b=a^b;
        a=a^b;
    }

    // for N , Calculate XOR b/w (1-N) :: pattern of 4 -> O(1)
    public int XOR(int n){
        if(n%4==0){
           return n;
        } else if(n%4==1){
            return 1;
        } else if(n%4==2){
            return n+1;
        } else{
            return 0;
        }
    }

    // N Queen using bit manipulation
    static int row=0, col=0,diag=0,aDiag=0;
    public static int nqueen_combi_bits(int n,int m, int tnq, int r, String ans){
        if (tnq == 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int c =0 ; c < m; c++)
        {
            if( (col & (1<<c))==0 && (diag & (1<<r-c+m-1)) ==0 && (aDiag & (1<<r+c))==0 ){

                col ^=(1<<c);
                diag ^= (1<<r-c+m-1);
                aDiag ^= (1<<r+c);
                count += nqueen_combi_bits(n,m, tnq - 1, r + 1, ans + "(" + r + "," + c + ") ");
                col ^=(1<<c);
                diag ^= (1<<r-c+m-1);
                aDiag ^= (1<<r+c);
            }
        }
        return count;
    }


    public static void main(String[] args){
        // leftShift();
        // rightShift();

        // int n =10;
        // int k=2;
        // offToOn(n, k);
        // onToOff(n, k);

        System.out.println(nqueen_combi_bits(4,4,4,0,""));
    }

}
