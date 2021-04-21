package it.polito.tdp.meteo.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.meteo.DAO.MeteoDAO;

public class Model {
	
	private final static int COST = 100;
	private final static int NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN = 3;
	private final static int NUMERO_GIORNI_CITTA_MAX = 6;
	private final static int NUMERO_GIORNI_TOTALI = 15;
	private List<Citta> citta; 
	private List<Citta> soluzioneMigliore;
	private List<Citta> parziale;
	private double costoMigliore;
	
	private MeteoDAO meteoDao;
	
	public Model() {
		meteoDao= new MeteoDAO();
	}

	// of course you can change the String output with what you think works best
	public String getUmiditaMedia(int mese) {
		List<Integer> umidita= meteoDao.getAllUmiditaMese(mese);
		Integer somma=0;
		double media=0;
		for(Integer i: umidita) {
			somma+=i;			
		}
		media=(double)somma/umidita.size();
		
		return String.valueOf(media) ;
	}
	
	// of course you can change the String output with what you think works best
	public List<Citta> trovaSequenza(int mese) {
		citta= new ArrayList<>();
		citta.add(new Citta("genova",meteoDao.getAllRilevamentiLocalitaMese(mese, "Genova")));
		citta.add(new Citta("milano",meteoDao.getAllRilevamentiLocalitaMese(mese, "Milano")));
		citta.add(new Citta("torino",meteoDao.getAllRilevamentiLocalitaMese(mese, "Torino")));
		
		parziale= new ArrayList<>();
		//soluzioneMigliore= new ArrayList<>();
		costoMigliore=Double.MAX_VALUE;
		cerca(parziale,0);		
		return soluzioneMigliore;
	}

	private void cerca(List<Citta> parziale, int livello) {
		//caso terminale
		if(parziale.size()==this.NUMERO_GIORNI_TOTALI) {
			double costo=0;
			//calcolo costo
			for(int i=0; i<parziale.size(); i++) {
				costo+=parziale.get(i).getRilevamenti().get(i).getUmidita();
				if(i>0) {
					if(!(parziale.get(i).getNome().equals(parziale.get(i-1).getNome())))
						costo+=this.COST;
				}	
			}
			if(costo<costoMigliore) {
				soluzioneMigliore= new ArrayList<>(parziale);
				costoMigliore=costo;				
			}			
			return;
		}
		for(Citta c: citta) {
		if(c.getCounter()<this.NUMERO_GIORNI_CITTA_MAX) {
			//inizializzazione
			boolean nuova=false;
			Citta cittaPrecedente1=null;
			Citta cittaPrecedente2=null;
			
			parziale.add(c);
			c.increaseCounter();							
									
			if(livello<3 && livello>0) {
				//controllo che siano uguali e se lo sono va bene, se no return
				if(!(parziale.get(livello).getNome().equals(parziale.get(livello-1).getNome())))
					return;
			}
			if(livello>=3) {
				//controllo se ho cambiato città, se non l'ho cambiata va bene, se l'ho cambiata va bene 
				if(!(parziale.get(livello).getNome().equals(parziale.get(livello-1).getNome()) && !nuova)) {
					nuova=true;
					cittaPrecedente1=parziale.get(livello);	
					cittaPrecedente2=null;
				}
					
				else if(nuova && cittaPrecedente2==null)
					if(parziale.get(livello).getNome().equals(cittaPrecedente1.getNome()))
						cittaPrecedente2=parziale.get(livello);
					else
						return;
						
				else if(cittaPrecedente2!=null) {
					if(parziale.get(livello).getNome().equals(cittaPrecedente2.getNome()))
						nuova=false;
					else
						return;
					}
			}
			cerca(parziale,livello+1);
			parziale.remove(c);
			c.setCounter(c.getCounter()-1);
		}	
	}
	}
}
	


