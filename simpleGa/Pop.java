package simpleGa;
import javax.swing.JFrame;
import java.awt.*;

public class Pop extends JFrame{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final int popSize = 64;
	Indivual[] ind = new Indivual[popSize];
	
	int goalX = 200;
	int goalY = 500;
	Indivual[] getPop()
	{
		return this.ind;
	}
	public static void printHLB(Indivual[] g)//prints Hidden Layer Biases for all individuals in the population
	{
		for(int i = 0; i < g.length; i++)
		{
			System.out.print(g[i].getID()+":");
			print1D(g[i].getHLB());
		}
	}
	public static void print1D(double mat[])//prints a 1d array
    {
    	for (int j = 0; j < mat.length; j++) {
                System.out.print(mat[j] + " ");
    	}
    	System.out.println();
    }
	public void genPop()//creates a new population by instantiates all individuals
	{
		for(int i = 0; i < popSize; i++)
		{
			Indivual newIndividual = new Indivual();
			newIndividual.buildGenes();
			ind[i] = newIndividual;
		}
	}
	public static void printFit(Indivual[] indz)//prints out the fitness of all individuals
	{
		for(int i = 0; i < indz.length;i++)
		{
			System.out.print(indz[i].getID() + ":" + indz[i].getFit()+ "-" + indz[i].getX() + "-" +indz[i].getY()+ ", ");
		}
		System.out.println();
	}
	public Indivual getIndividual(int index) {//gets a specific individual at a certain index
        return ind[index];
    }
	public void calcFit()//calculates the fitness of all individuals
	{
		for(int i = 0; i < popSize;i++)
		{
			ind[i].getFitness(goalX, goalY);
		}
		quicksort(ind,0,ind.length-1);
	}
	public void getNewPop()//gets a new population based on the fitness of the previous generation
	{
		quicksort(ind,0,ind.length - 1);
		int amount = (int)(popSize/5);
		for(int i = amount; i < popSize - amount; i++)
		{
			int max = amount;
			int min = 0;
			int a = (int) (Math.random()*(max-min+1)+min);
			int b = (int) (Math.random()*(max-min+1)+min);
			int c = (int) (Math.random()*(max-min+1)+min);
			int d = (int) (Math.random()*(max-min+1)+min);
			ind[i].set(getIndividual(a).getHLB(),getIndividual(b).getOLB(),getIndividual(c).getHLW(),getIndividual(d).getOLW());
			ind[i].mutate();
			
		}
	}
	
	public void movePop(Graphics g,int a)
	{
		for(int j = 0; j < popSize; j++)
		{
			ind[j].resetCord();
			ind[j].resetFit();
		}
		for(int i = 0; i < 200; i++)//i is the amount of moves each individual can do in a generation
		{
			for(int j = 0; j < popSize; j++)//moves everyone in population
			{
				ind[j].moves();
			}
			paint(g,a);
		}
	}
	static void quicksort(Indivual[] arr, int low, int high){//quicksort algorithm for sorting individuals by fitness
	    if(low < high){
	        int p = partition(arr, low, high);
	        quicksort(arr, low, p-1);
	        quicksort(arr, p+1, high);
	    }
	}
	static void swap(Indivual[] arr, int low, int pivot){
	    Indivual tmp = arr[low];
	    arr[low] = arr[pivot];
	    arr[pivot] = tmp;
	}
	static int partition(Indivual[] arr, int low, int high){
	    int p = low, j;
	    for(j=low+1; j <= high; j++)
	    {
	        if(arr[j].getFit() < arr[low].getFit()) {
	            swap(arr, ++p, j);
	        }
	    }
	    swap(arr, low, p);
	    return p;
	}
	public void paint(Graphics g,int gen) {//paints the algorithm on a JFrame
		g.setColor(Color.red);
		g.fillOval(200, 500, 5, 5);
		g.drawString("Generation: " + gen, 400, 200);
		for(int i = 0; i < popSize/3;i++)
		{
			if( i == 0)
			{
				g.setColor(Color.BLUE);
			}
			else
			{
				g.setColor(new Color(0,0,0,20));
			}
			g.fillOval(ind[i].getX(),ind[i].getY(), 2, 2);
		}
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
	
	public static void main(String[] args) {
		/*Pop a = new Pop();//this was all just for testing
		a.genPop();
		a.calcFit();
		printHLB(a.getPop());
		printFit(a.getPop());
		a.getNewPop();
		for(int i = 0; i < 1000; i++)
		{
			a.getNewPop();
			a.calcFit();
			printFit(a.getPop());
		}
		printHLB(a.getPop());*/
	}
}
