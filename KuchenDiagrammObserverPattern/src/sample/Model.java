package sample;


import java.util.Observable;

/**
 * Created by Nico on 03.07.2017.
 */



public class Model extends Observable
{
    private double par1;
    private double par2;
    private double par3;

    public Model(int par1, int par2, int par3)
    {
        this.par1 = par1;
        this.par2 = par2;
        this.par3 = par3;
    }

    public double getPar1()
    {
        return par1;
    }

    public void setPar1(double par1)
    {
            double hilf=this.par1-par1;
            this.par1 = par1;
            this.par2+=hilf/2;
            this.par3+=hilf/2;
    }

    public double getPar2()
    {
        return par2;
    }

    public void setPar2(double par2)
    {
        double hilf=this.par2-par2;
        this.par2 = par2;
        this.par1+=hilf/2;
        this.par3+=hilf/2;
    }

    public double getPar3()
    {
        return par3;
    }

    //public void setPar3(double par3)
    {
        //this.par3 = par3;
    }

    public void changeSomething()
    {

        setChanged();
        notifyObservers();
    }

}
