import java.util.Random;
import java.util.*;

public class Matrix implements Comparable
{
    // fields:

    private double[][] mat;

    // basic methods:

    public Matrix(Matrix m)
    {
        this.mat = new double[m.getMat().length][m.getMat().length];
        for (int i = 0; i < this.mat.length; i++)
        {
            for (int j = 0; j < this.mat[i].length; j++)
            {
                mat[i][j] = m.getMat()[i][j];
            }
        }
    }

    public Matrix(int n)
    {
        this.mat = new double[n][n];
        for (int i = 0; i < this.mat.length; i++)
        {
            for (int j = 0; j < this.mat[i].length; j++)
            {
                mat[i][j] = 0;
            }
        }
    }

    public void inItRand()
    {
        for (int i = 0; i < this.mat.length; i++)
        {
            for (int j = 0; j < this.mat[i].length; j++)
            {
                mat[i][j] = Math.ceil(new Random().nextDouble() * 10.0) / 10.0;
            }
        }
    }

    public double[][] getMat() // getter
    {
        return mat;
    }

    // functions:

    public void sum(Matrix m)
    {
        if (m.getMat().length != this.mat.length || m.getMat()[0].length != this.mat[0].length)
            throw new IllegalArgumentException("matrix size isn't matches this matrix size");
        for (int i = 0; i < this.mat.length; i++)
        {
            for (int j = 0; j < this.mat.length; j++)
            {
                this.mat[i][j] += m.getMat()[i][j];
            }
        }
    }

    public void substract(Matrix m)
    {
        if (m.getMat().length != this.mat.length || m.getMat()[0].length != this.mat[0].length)
            throw new IllegalArgumentException("matrix size isn't matches this matrix size");
        for (int i = 0; i < this.mat.length; i++)
        {
            for (int j = 0; j < this.mat.length; j++)
            {
                this.mat[i][j] -= m.getMat()[i][j];
            }
        }
    }

    public Matrix multiply(Matrix m)
    {
        if (m.getMat().length != this.mat.length || m.getMat()[0].length != this.mat[0].length)
            throw new IllegalArgumentException("matrix size isn't matches this matrix size");
        Matrix c = new Matrix(this.mat.length);
        for (int i = 0; i < this.mat.length; i++)
        {
            for (int j = 0; j < m.getMat().length; j++)
            {
                for (int k = 0; k < this.mat.length; k++)
                {
                    c.getMat()[i][j] += this.mat[i][k] * m.getMat()[k][j];
                }
            }
        }
        return c;
    }
    public double determinant()
    {
        if (this.mat.length == 2)
            return (this.mat[0][0] * this.mat[1][1]) - (this.mat[1][0] * this.mat[0][1]);
        double sum = 0;
        for (int i = 0; i < this.mat.length; i++)
        {
            if (i % 2 == 0)
                sum += Math
                        .ceil((this.mat[i][0] * this.removeColAndRow(i, 0).determinant()) * 1000.0)
                        / 1000.0;
            else
                sum -= Math
                        .ceil((this.mat[i][0] * this.removeColAndRow(i, 0).determinant()) * 1000.0)
                        / 1000.0;
        }
        return sum;
    }

    public Matrix removeColAndRow(int row, int col)
    {
        Matrix removed = new Matrix(this.mat.length - 1);
        for (int i = 0; i < this.mat.length; i++)
        {
            for (int j = 0; j < this.mat.length; j++)
            {
                if (i != row && j != col)
                {
                    if (i > row)
                    {
                        if (j > col)
                            removed.getMat()[i - 1][j - 1] = this.mat[i][j];
                        else
                            removed.getMat()[i - 1][j] = this.mat[i][j];
                    } else
                    {
                        if (j > col)
                            removed.getMat()[i][j - 1] = this.mat[i][j];
                        else
                            removed.getMat()[i][j] = this.mat[i][j];
                    }

                }
            }
        }
        return removed;
    }


    @Override
    public int compareTo(Object o)
    {
        double count = 0;
        if (!(o instanceof Matrix))
            throw new IllegalArgumentException("wrong type to check");
        Matrix mm = (Matrix) o;
        if (mm.getMat().length != this.mat.length || mm.getMat()[0].length != this.mat[0].length)
            throw new IllegalArgumentException("matrix size isn't matches this matrix size");
        for (int i = 0; i < this.mat.length; i++)
        {
            for (int j = 0; j < this.mat.length; j++)
            {
                count = count + (this.mat[i][j] - mm.getMat()[i][j]);
            }
        }
        if (count > 0)
            return 1;
        if (count < 0)
            return -1;
        return 0;
    }

  
    

    @Override
    public String toString()
    {
        String str = "";
        for (int i = 0; i < this.mat.length; i++)
        {
            if (i == 0 || i == this.mat.length - 1)
                str += String.format("%s\n",
                        String.join("", Arrays.toString(this.mat[i]).split("")));
            else
                str += String.format("%s\n",
                        String.join("", Arrays.toString(this.mat[i]).split("")));
        }
        return str;
    }

    public static void main(String[] args)
    {
        Matrix m = new Matrix(4);
        m.inItRand();
        System.out.println(m.toString());
        System.out.println(m.determinant());

    }
}

