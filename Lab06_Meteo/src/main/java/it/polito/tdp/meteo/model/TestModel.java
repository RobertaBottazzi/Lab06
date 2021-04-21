package it.polito.tdp.meteo.model;

public class TestModel {

	public static void main(String[] args) {
		
		Model m = new Model();
		
		/*System.out.println(m.getUmiditaMedia(1));
		Integer[] numeri= {79, 66, 75, 81, 86, 82, 72, 73, 72, 64, 63, 74, 64, 76, 55, 37, 34, 77, 73, 76, 63, 75, 64, 56, 53, 60, 78, 73, 81, 80, 97, 97, 89, 89, 73, 82, 99, 99, 90, 91, 97, 89, 95, 95, 94, 81, 88, 77, 91, 100, 98, 96, 93, 88, 93, 82, 98, 98, 93, 100, 100, 73, 85, 73, 60, 51, 52, 65, 96, 94, 88, 93, 99, 89, 89, 90, 68, 48, 45, 72, 98, 91, 80, 86, 83, 75, 86, 82, 91, 86, 81, 73};
		int somma=0;
		int size=0;
		double media=0;
		for(Integer i:numeri) {
			somma+=i;
			size++;
		}
		media=(double)somma/size;
		System.out.println(media);*/
		
		//System.out.println(m.trovaSequenza(1));
		for(int i=1; i<=12; i++) {
			System.out.println(m.trovaSequenza(i)+" "+i+"\n");
		}
		
		
	}

}
