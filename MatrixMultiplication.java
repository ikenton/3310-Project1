//import java.util.Scanner;
//import java.math.*;
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

    public static Integer[][] stitch(Integer[][] matrixA, Integer[][] matrixB){
        
    }

    public static Integer[][] strassens(Integer[][] A, Integer[][] B){
        Integer[][] answer = new Integer[A.length][A.length];
        if(A.length <= 1){
            answer[0][0] = A[0][0]*B[0][0];
            return answer;
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

        
        Integer[][] p1A = arrayAdd(a, d);
        Integer[][] p1B = arrayAdd(e, h);
//d     Integer[][] p2A = d
        Integer[][] p2B = arraySub(g, e);
        Integer[][] p3A = arrayAdd(a, b);
        //Integer[][] p3B = h
        Integer[][] p4A = arraySub(b, d);
        Integer[][] p4B = arrayAdd(g, h);
        //Integer[][] p5A = a
        Integer[][] p5B = arraySub(f, h);
        Integer[][] p6A = arrayAdd(c, d);
//e     Integer[][] p6B = new Integer[a.length][a.length];
        Integer[][] p7A = arraySub(a,c);
        Integer[][] p7B = arrayAdd(a,d);

        
        //call recursively 7 times 
        Integer[][] p1 = strassens(p1A, p1A);
        Integer[][] p2 = strassens(d, p2B);
        Integer[][] p3 = strassens(p3A, h);
        Integer[][] p4 = strassens(p4A, p4B);
        Integer[][] p5 = strassens(a, p5B);
        Integer[][] p6 = strassens(p6A, e);
        Integer[][] p7 = strassens(p7A, p7B);
        
        //stitch it all back together
        /* c11 = p1 + p2 - p3 + p4
         * c12 = p5 + p3
         * c21 = p6 + p2
         * c22 = p5 + p1 - p6 - p7 
        */
    }

    public static Integer[][] arrayAdd(Integer[][] A, Integer[][] B){
        Integer[][] C = new Integer[A.length][A.length];
        for(int i = 0; i < A.length; i++){
            for(int j = 0; i < A.length; j++){
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }

    public static Integer[][] arraySub(Integer[][] A, Integer[][] B){
        Integer[][] C = new Integer[A.length][A.length];
        for(int i = 0; i < A.length; i++){
            for(int j = 0; i < A.length; j++){
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }

    public static Integer[][] naive(Integer[][] matrixA, Integer[][] matrixB){
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

            //8 recursive calls
            
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