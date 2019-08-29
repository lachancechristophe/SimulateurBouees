package controle;

import java.util.ArrayList;

import donnee.Bouee;
import donnee.Constantes;
import modele.Simulateur;
import presentation.AffichageConsole;
import presentation.SauvegardeFichier;

public class ControlleurPrincipal {
	private Simulateur simulateur;
	
	public ControlleurPrincipal() {
		simulateur = new Simulateur();
	}
	
	public void demarrerCollecte() {
		simulateur.procUnParSeconde();
	}
	
	public void genererNombrePoints(Integer nombre) {
		//AffichageConsole.afficher(simulateur.procGenererPoints(nombre));
		SauvegardeFichier.sauvegarder("test", simulateur.procGenererPoints(nombre));
	}

	public void attendre(Integer ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void afficherConsole() {
		AffichageConsole.afficher(simulateur.lireDonnees());
		
	}
}

