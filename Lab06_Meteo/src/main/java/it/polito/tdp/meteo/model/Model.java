package it.polito.tdp.meteo.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.meteo.DAO.MeteoDAO;

public class Model {
	
	private final static int COST = 100;
	private final static int NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN = 3;
	private final static int NUMERO_GIORNI_CITTA_MAX = 6;
	private final static int NUMERO_GIORNI_TOTALI = 15;
	
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
	public String trovaSequenza(int mese) {
		return "TODO!";
	}
	

}
