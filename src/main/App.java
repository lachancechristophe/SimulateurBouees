package main;

import controle.ControlleurPrincipal;
import donnee.PointDonnee;
import modele.Simulateur;
import presentation.AffichageConsole;

public class App {

	public static void main(String[] args) {
		/*
		long time1 = new Date().getTime();

		
		
		
		long time = new Date().getTime() - time1;
		System.out.println("Processed in " + time + "ms.");
		
		for(PointDonnee p : listePointsDonnees) {
			System.out.println(p.getId_bouee() + ": " + p.getMoment() + ": " + p.getSalinite() + ": " + p.getTemperature() + ": " + p.getDebit());
		}
		*/
		
		ControlleurPrincipal controleur = new ControlleurPrincipal();
		
		controleur.attendre(6500);
		
		controleur.afficherConsole();
		
		controleur.attendre(6500);
		
		controleur.afficherConsole();
	}

}