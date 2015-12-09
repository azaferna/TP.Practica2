package tp.pr1.logic.celula;

import tp.pr1.logic.Casilla;
import tp.pr1.logic.Superficie;

public class CelulaCompleja extends Celula{

	private int explota;
	public static final int MAX_COMER = 3;
	
	public CelulaCompleja()
	{
		this.explota = MAX_COMER;
	}
	
	public String ejecutaMovimiento(Casilla casV, Superficie superficie) {
		
		StringBuilder builder = new StringBuilder();
		Casilla casN = this.generarCasillaVacia(casV, superficie);
		boolean come = false;
		if(superficie.casillaLlena(casN))
			come = superficie.casillaComastible(casN);
		
		if( come && this.moverCelula(casV, casN, superficie) )
		{	
			if(this.menosComer())
				builder.append("Celula Compleja en" + casV.toString() + "se mueve a" + casN.toString() + "--COME--" + '\n');
			else
			{
				builder.append("Explota la celula " + casV.toString() + '\n');
				superficie.eliminarCelula(casV);
			}
			
		}
		else if(!come && this.moverCelula(casV, casN, superficie))
			
			builder.append("Celula Compleja en" + casV.toString() + "se mueve a" + casN.toString() + "--NO COME--"+ '\n');
		else
			builder.append("Celula Compleja no se ha movido"+ '\n');
		return builder.toString();
	}
	
	protected Casilla generarCasillaVacia(Casilla casilla, Superficie superficie)
	{
		Casilla cas;
		int f = -1, c = -1;
		do
		{
			f = (int) (Math.random()*superficie.getFil());
			c = (int) (Math.random()*superficie.getCol());
		}while(f == casilla.getFila() || c == casilla.getColumna());
		
		cas = new Casilla(f, c);
		
		return cas;
	}

	@Override
	public boolean esComestible() {
		return false;
	}
	
	protected boolean moverCelula(Casilla casV, Casilla casN, Superficie superficie)
	{
		boolean ok = false;
		
		if(!superficie.casillaLlena(casN) || superficie.casillaComastible(casN) )
		{ 	
			superficie.modificarCasilla(casV, casN);				
			superficie.eliminarCelula(casV);	
			
			ok = true;
		}	
		return ok;
	}

	private boolean menosComer()
	{
		boolean ok = false;
		if (this.explota > 0)
		{
			this.explota --;
			ok = true;
		}
		return ok;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("  * ");
		/*builder.append(" ");
		builder.append("  " + this.explota + " ");
		*/
		return builder.toString();
	}

}