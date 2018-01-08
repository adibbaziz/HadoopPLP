/**
 * 
 */
package DisplayCsv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yassine
 *
 */
public abstract class Tree{

	float[] geopoint;
	int arrondissement;
	String genre;
	public String espece;
	String famille;
	int annee;
	public float hauteur;
	float circonference;
	String adresse;
	String nom_commun;
	String variete;
	int objectid;
	String nom_ev;

	Tree(String line){
		List<String> list = new ArrayList<String>(Arrays.asList(line.split(";")));
		List<String> geo_point = new ArrayList<String>(Arrays.asList(list.get(0).split(",")));
		this.geopoint = new float[2];
		this.geopoint[0] = Float.parseFloat(geo_point.get(0).substring(1));
		this.geopoint[1] = Float.parseFloat(geo_point.get(1).substring(0,geo_point.get(1).length()-1));
		this.arrondissement = Integer.parseInt(list.get(1));
		this.genre = list.get(2);
		this.espece = list.get(3);
		this.famille = list.get(4);
		if(!list.get(5).isEmpty()){
			this.annee = Integer.parseInt(list.get(5));
		}
		if(!list.get(6).isEmpty()){
			this.hauteur = Float.parseFloat(list.get(6));
		}
		if(!list.get(7).isEmpty()){
			this.circonference = Float.parseFloat(list.get(7));
		}
		this.adresse = list.get(8);
		this.nom_commun = list.get(9);
		this.variete = list.get(10);
		this.objectid = Integer.parseInt(list.get(11));
		this.nom_ev = list.get(12);
	}

	void displayYearHeight(){
		System.out.println("Year: "+ this.annee + ", Height: " + this.hauteur);
	}
}
