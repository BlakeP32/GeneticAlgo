package simpleGa;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Indivual {
	private double fitness = 0.0;
	double mutationRate = 0.15;
	double mutationCrazy = 1.0;
	final int id = ThreadLocalRandom.current().nextInt(10000, 100000);
	int inputLayerLength = 2;
	int HiddenLayerLength = 4;
	int OutputLayerLength = 4;
	private double input[] = new double[inputLayerLength];
	private double HiddenLayerW[][] = new double[inputLayerLength][HiddenLayerLength];
	private double OutputLayerW[][] = new double[HiddenLayerLength][OutputLayerLength];
	private double HiddenLayerb[] = new double[HiddenLayerLength];
	private double OutputLayerb[] = new double[OutputLayerLength];
	private int Xpos = 300;
	private int Ypos = 600;
	int getID()//all get functions are for getting a private value
	{
		return this.id;
	}
	double getFit()
	{
		return fitness;
	}
	void increaseX()
	{
		this.Xpos++;
	}
	void decreaseX()
	{
		this.Xpos--;
	}
	void increaseY()
	{
		this.Ypos++;
	}
	void resetCord()//resets the starting cords for each generation
	{
		this.Xpos = 300;
		this.Ypos = 600;
	}
	void resetFit() {//puts the fitness back to 0 for each generation 
		this.fitness = 0.0;
	}
	public double[][] getHLW()
	{
		return this.HiddenLayerW;
	}
	public double[][] getOLW()
	{
		return this.OutputLayerW;
	}
	public double[] getINP()
	{
		return this.input;
	}
	public double[] getHLB()
	{
		return this.HiddenLayerb;
	}
	public double[] getOLB()
	{
		return this.OutputLayerb;
	}
	void decreaseY()
	{
		this.Ypos--;
	}
	public int getX()
	{
		return Xpos;
	}
	public int getY()
	{
		return Ypos;
	}
	public void set(double[] a, double[] b, double[][] c, double[][] d)//sets the Neurual Net of an individual
	{
		for(int i = 0; i < HiddenLayerLength; i++)
		{
			HiddenLayerb[i] = a[i];
		}
		for(int i = 0; i < OutputLayerLength; i++)
		{
			OutputLayerb[i] = b[i];
		}
		for(int i = 0; i < inputLayerLength; i++)
		{
			for(int j = 0; j < HiddenLayerLength; j++)
			{
				HiddenLayerW[i][j] = c[i][j];
			}
		}
		for(int i = 0; i < HiddenLayerLength; i++)
		{
			for(int j = 0; j < OutputLayerLength; j++)
			{
				OutputLayerW[i][j] = d[i][j];
			}
		}
	}
	private void setHLW(int i, int j)//mutates individuals NN values based on mutationCrazy which is a value that is the maximum amount a value can change 
	{
		double max = 0;
		double min = -mutationCrazy;
		this.HiddenLayerW[i][j] = Math.random()*(max-min+mutationCrazy)+min;
	}
	private void setOLW(int i, int j)//mutates individuals NN values based on mutationCrazy
	{
		double max = 0;
		double min = -mutationCrazy;
		this.OutputLayerW[i][j] = Math.random()*(max-min+mutationCrazy)+min;
	}
	private void setHLB(int i)//mutates individuals NN values based on mutationCrazy
	{
		double max = 0;
		double min = -mutationCrazy;
		this.HiddenLayerb[i] = Math.random()*(max-min+mutationCrazy)+min;
	}
	private void setOLB(int i)//mutates individuals NN values based on mutationCrazy
	{
		double max = 0;
		double min = -mutationCrazy;
		this.OutputLayerb[i] = Math.random()*(max-min+mutationCrazy)+min;
	}

	public void buildGenes()//generates genes for a new individual
	{
		int max = 0;
		int min = -1;

		for(int i = 0; i < HiddenLayerLength; i++)
		{
			HiddenLayerb[i] = Math.random()*(max-min+1)+min;
		}
		for(int i = 0; i < OutputLayerLength; i++)
		{
			OutputLayerb[i] = Math.random()*(max-min+1)+min;
		}
		for(int i = 0; i < inputLayerLength; i++)
		{
			for(int j = 0; j < HiddenLayerLength; j++)
			{
				HiddenLayerW[i][j] = Math.random()*(max-min+1)+min;
			}
		}
		for(int i = 0; i < HiddenLayerLength; i++)
		{
			for(int j = 0; j < OutputLayerLength; j++)
			{
				OutputLayerW[i][j] = Math.random()*(max-min+1)+min;
			}
		}
	}
	
	void mutate() {//mutates a value in NN based on MutationRate

		for(int i = 0; i < HiddenLayerLength; i++)
		{
			if (Math.random() <= mutationRate) {
				this.setHLB(i);}
		}
		for(int i = 0; i < OutputLayerLength; i++)
		{
			if (Math.random() <= mutationRate) {
				this.setOLB(i);;}
		}
		for(int i = 0; i < inputLayerLength; i++)
		{
			for(int j = 0; j < HiddenLayerLength; j++)
			{
				if (Math.random() <= mutationRate) {
					this.setHLW(i, j);}
			}
		}
		for(int i = 0; i < HiddenLayerLength; i++)
		{
			for(int j = 0; j < OutputLayerLength; j++)
			{
				if (Math.random() <= mutationRate) {
					this.setOLW(i, j);}
			}
		}
    }
	
	public static boolean getRandomBoolean() {//gets random Bool
	    Random random = new Random();
	    return random.nextBoolean();
	    
	    
	}
    public static void print2D(double mat[][])//prints 2d array
    {
        // Loop through all rows
        for (int i = 0; i < mat.length; i++) {
 
            // Loop through all elements of current row
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + " ");
        	}
            System.out.print(" | ");
        }
        System.out.println();
    }

    public static void print1D(double mat[])//prints 1d array
    {
    	for (int j = 0; j < mat.length; j++) {
                System.out.print(mat[j] + " ");
    	}
    	System.out.println();
    }
    public static void print(Indivual a)//prints an individuals neural net in console
    {
    	System.out.println(a.Xpos+ " "+a.Ypos);
    	print1D(a.getINP());
    	print2D(a.getHLW());
    	print1D(a.getHLB());
    	print2D(a.getOLW());
    	print1D(a.getOLB());
    	
    }
	public void updateInput()//Updates the position of the individual and puts those values into the input layer
	{
		double Xrel = ((double)Xpos - 500.0)/100.0;
		double Yrel = ((double)Ypos - 500.0)/100.0;
		input[0] = sigmoid(Xrel);//500 is half grid length
		input[1] = sigmoid(Yrel);//500 is half of grid height
		return;
	}
	public double[] calc()//calculates the output of the neural net
	{
		double HLvals [] = new double[HiddenLayerLength];
		for(int i = 0; i < HiddenLayerLength; i++)
		{
			for(int k = 0; k < inputLayerLength; k++)
			{
				HLvals[i] += input[k] * HiddenLayerW[k][i];
			}
			HLvals[i] += HiddenLayerb[i];
			HLvals[i] = sigmoid(HLvals[i]);
		}
		double outputVals [] = new double[OutputLayerLength];
		for(int i = 0; i < OutputLayerLength; i++)
		{
			for(int k = 0; k < HiddenLayerLength; k++)
			{
				outputVals[i] += HLvals[k] * OutputLayerW[k][i];
			}
			outputVals[i] += OutputLayerb[i];
		}
		return outputVals;
	}
	public void moveCalc(double[] a)//checks which direction individual wants to move in
	{
		if(a[0] > a[1] && a[0] > a[2] && a[0] > a[3] && a[0] > 0.75)
		{
			this.increaseX();
		}
		else if(a[1] > a[2] && a[1] > a[3] && a[1] > 0.75)
		{
			this.decreaseX();
		}
		else if(a[2] > a[3] && a[2] > 0.75)
		{
			this.increaseY();
		}
		else if(a[3] > 0.75)
		{
			this.decreaseY();
		}
	}
	public void moves()
	{
		updateInput();//updates inputs based on x and y
		moveCalc(calc());//moves individual based on output of calc
	}
	public void getFitness(int x, int y)//gives fitness of individual
	{
		int xDif = getX() - x;
		double squaredX = Math.pow(xDif,2);
		int yDif = getY() - y;
		double squaredY = Math.pow(yDif,2);
		this.fitness = Math.sqrt(squaredY + squaredX);
	}
	public double sigmoid(double x) {//sigmoid function to normalize values around -1 and 1
		    return ((1/( 1 + Math.pow(Math.E,(-1*x)))) * 2) - 1;
	}
	public static void main(String[] args) {
	
	}
}
