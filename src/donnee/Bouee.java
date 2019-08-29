package donnee;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Bouee {
	private Integer numero;
	private Integer no_seq;
	private String description;
	private Long miseEnFonction;
	private Double longitude;
	private Double latitude;
	
	private ValeursInitiales valeursInitiales;
	
	private ArrayList<PointDonnee> listePointDonnee;
	private Random generateurDeHasard;

	private float[][] tabTempMinMax;
	
	public Bouee(Integer numero, String description) {
		generateurDeHasard = new Random();
		
		valeursInitiales = new ValeursInitiales();
		
		longitude = valeursInitiales.getLongitude();
		latitude = valeursInitiales.getLatitude();
		this.numero = numero;
		this.description = description;
		miseEnFonction = new Date().getTime();
		no_seq = 0;
		
		listePointDonnee = new ArrayList<PointDonnee>();

		tabTempMinMax = remplirTabTemp();
	}
	
	public void demarrerCollecte(Integer intervale) {
		ScheduledExecutorService executor =
			    Executors.newSingleThreadScheduledExecutor();
		
		Runnable periodicTask = new Runnable() {
		    public void run() {
		        // Invoke method(s) to do the work
		    	listePointDonnee.add(genererPointDonnee());
		    }
		};

		executor.scheduleAtFixedRate(periodicTask, 0, intervale, TimeUnit.SECONDS);
	}
	
	public ArrayList<PointDonnee> lireDonnees(){
		ArrayList<PointDonnee> templist = (ArrayList<PointDonnee>)listePointDonnee.clone();
		listePointDonnee.clear();
		return templist;
	}
	
	private PointDonnee genererPointDonnee() {
		PointDonnee p = new PointDonnee();
		
		p.setId_bouee(numero);
		p.setMoment(new Date().getTime());

		//p.setTemperature(valeursInitiales.getTemperature() - 10 + (generateurDeHasard.nextFloat()*10));
		p.setTemperature(generateurDeHasard.nextFloat() * ((tabTempMinMax[0][1] - tabTempMinMax[0][0]) + 1) + tabTempMinMax[0][0]);


		p.setSalinite(valeursInitiales.getSalinite() - 5 + (generateurDeHasard.nextFloat() * 5));
		p.setDebit(valeursInitiales.getDebit() - 1 + (generateurDeHasard.nextFloat() * 2));
		p.setLongitude(valeursInitiales.getLongitude() + ((-0.5 + generateurDeHasard.nextFloat())/10) );
		p.setLatitude(valeursInitiales.getLatitude() + ((-0.5 + generateurDeHasard.nextFloat())/10) );
		p.setNo_seq(++no_seq);
		
		return p;
	}
	
	private PointDonnee genererPointDonnee(Date moment) {
		PointDonnee p = new PointDonnee();
		
		p.setId_bouee(numero);
		p.setMoment(moment.getTime());
		p.setTemperature(valeursInitiales.getTemperature() - 10 + (generateurDeHasard.nextFloat()*10));
		p.setSalinite(valeursInitiales.getSalinite() - 5 + (generateurDeHasard.nextFloat() * 5));
		p.setDebit(valeursInitiales.getDebit() - 1 + (generateurDeHasard.nextFloat() * 2));
		p.setLongitude(valeursInitiales.getLongitude() + ((-0.5 + generateurDeHasard.nextFloat())/10) );
		p.setLatitude(valeursInitiales.getLatitude() + ((-0.5 + generateurDeHasard.nextFloat())/10) );
		p.setNo_seq(++no_seq);
		
		return p;
	}
	
	
	public ArrayList<PointDonnee> genererNombrePointDonnee(Integer nombre){
		Calendar cal = Calendar.getInstance();

		ArrayList<PointDonnee> listePointDonneeLocale = new ArrayList<PointDonnee>();
		for(int i = 0 ; i < nombre ; i++) {
			cal.add(Calendar.SECOND, 1);
			listePointDonneeLocale.add(this.genererPointDonnee(cal.getTime()));
		}
		return listePointDonneeLocale;
	}

	public float[][] remplirTabTemp(){
		float[][] tab = new float[12][2];

		tab[0][0] = 0;
		tab[0][1] = 5;

		tab[1][0] = 0;
		tab[1][1] = 3;

		tab[2][0] = 3;
		tab[2][1] = 5;

		tab[3][0] = 5;
		tab[3][1] = 7;

		tab[4][0] = 7;
		tab[4][1] = 17;

		tab[5][0] = 15;
		tab[5][1] = 20;

		tab[6][0] = 20;
		tab[6][1] = 25;

		tab[7][0] = 23;
		tab[7][1] = 25;

		tab[8][0] = 17;
		tab[8][1] = 23;

		tab[9][0] = 10;
		tab[9][1] = 20;

		tab[10][0] = 7;
		tab[10][1] = 15;

		tab[11][0] = 3;
		tab[11][1] = 10;

		return tab;
	}
}
