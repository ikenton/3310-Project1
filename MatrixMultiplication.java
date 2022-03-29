import java.util.Scanner;
import java.math.*;
public class MatrixMultiplication {
    public static void main(String[] args){
        //set up the matrix 
        //nxn where n is a power of 2
        //Scanner kb = new Scanner(System.in);
       
        Integer[][] matrixA = {{2,0,-1,6}, {3,7,8,0}, {-5,1,6,-2}, {8,0,2,7}};
        Integer[][] matrixB = {{0,1,6,3}, {-2,8,7,1}, {2,0,-1,0}, {9,1,6,-2}};
        //bruteForce(matrixA,matrixB);
        divide(matrixA);
        conquer(matrixA, matrixB);
    }
    
    public static Integer[][] loadMatrix(Integer[][] matrixes){

    }

    public static Integer[][] strassens(Integer[][] A, Integer[][] B){
        if(A.length <=2){
            return bruteForce(A, B);
        }
        Integer[][][] aThree = divide(A);
        Integer[][][] bThree = divide(B);

        Integer[][] a = aThree[0];   
        Integer[][] b = aThree[1];
        Integer[][] c = aThree[2];
        Integer[][] d = aThree[3];

        Integer[][] e = bThree[0];
        Integer[][] f = bThree[1];
        Integer[][] g = bThree[2];
        Integer[][] h = bThree[3];

        //call recursively 7 times
        //a+b 
        Integer[][] p1A = new Integer[a.length][a.length];
        Integer[][] p1B = new Integer[a.length][a.length];
//d     Integer[][] p2A = new Integer[a.length][a.length];
        Integer[][] p2B = new Integer[a.length][a.length];
        Integer[][] p3A = new Integer[a.length][a.length];
//h     Integer[][] p3B = new Integer[a.length][a.length];
        Integer[][] p4A = new Integer[a.length][a.length];
        Integer[][] p4B = new Integer[a.length][a.length];
//a     Integer[][] p5A = new Integer[a.length][a.length];
        Integer[][] p5B = new Integer[a.length][a.length];
        Integer[][] p6A = new Integer[a.length][a.length];
//e     Integer[][] p6B = new Integer[a.length][a.length];
        Integer[][] p7A = new Integer[a.length][a.length];
        Integer[][] p7B = new Integer[a.length][a.length];

        for(int i = 0; i < A.length; i++){
            for(int j = 0; i < A.length; j++){
                p1A[i][j] = a[i][j] + d[i][j];
                p1B[i][j] = e[i][j] + h[i][j];
                p2B[i][j] = g[i][j] - e[i][j];
                p3A[i][j] = a[i][j] + b[i][j];
                p4A[i][j] = b[i][j] - d[i][j];
                p4B[i][j] = g[i][j] + h[i][j];
                p5B[i][j] = f[i][j] - h[i][j];
                p6A[i][j] = c[i][j] + d[i][j];
                p7A[i][j] = a[i][j] - c[i][j];
                p7A[i][j] = e[i][j] + f[i][j];
            }
        }
    }

    public static Integer[][] conquer(Integer[][] matrixA, Integer[][] matrixB){
        //base case
        if(matrixA.length <= 2 || matrixB.length <= 2){
            return bruteForce(matrixA, matrixB);
        }else {
            Integer[][][] aThree = divide(matrixA);
            Integer[][][] bThree = divide(matrixB);
    
            Integer[][] a = aThree[0];   
            Integer[][] b = aThree[1];
            Integer[][] c = aThree[2];
            Integer[][] d = aThree[3];
    
            Integer[][] e = bThree[0];
            Integer[][] f = bThree[1];
            Integer[][] g = bThree[2];
            Integer[][] h = bThree[3];
            
            
        }

    }

    public static Integer[][][] divide(Integer[][] matrixes){
        //4 new matrixes
        int size = matrixes.length;

        int div = size/2;

        Integer[][] a = new Integer[div][div];
        Integer[][] b = new Integer[div][div];
        Integer[][] c = new Integer[div][div];
        Integer[][] d = new Integer[div][div];
        for(int i = 0; i < div; i++){
            for(int j = 0; j< div;j++){
                a[i][j] = matrixes[i][j];
                b[i][j] = matrixes[i][j+div];
                c[i][j] = matrixes[i + div][j];
                d[i][j] = matrixes[i+div][j+div];
                System.out.println("a: "+a[i][j]);
                System.out.println("b: "+b[i][j]);
                System.out.println("c: "+c[i][j]);
                System.out.println("d: "+d[i][j]);
            }
        }

        Integer[][][] temp = {a,b,c,d};
        return temp;
    }

    public static Integer[][] combine(Integer[][] A, Integer[][] B){
        //stitch the matrixes back together.
    }
    public static Integer[][] bruteForce(Integer[][] A, Integer[][] B){
        //first row times all collumns
        //2nd row times all collumns
        int size = A.length;
        
        Integer[][] answer = new Integer[size][size];
        for(int i = 0; i < size; i++){
            //controls A's rows
            System.out.print(i +"  :  ");
            for(int j = 0; j < size; j++){
                int sum = 0; 
                //B's columns
                for(int x = 0; x < size; x++){
                    //a's columns and b's rows
                    sum += A[i][x] * B[x][j];
                    //System.out.println("sum: "+ sum);
                    //System.out.print("\ti: "+i+" j: "+j+" x: "+x);
                }
                answer[i][j] = sum;
                System.out.print(answer[i][j]+" ");
            }  
            System.out.println("");
        }
        return answer;
    }
}