package fr.gde.formation.projetcompetences.niveaucompetences;

public enum Niveau {

    ZERO("Aucune Connaissance"),
    UN("Connait le nom de la compétence"),
    DEUX("Connait le nom de la compétence et sa définition"),
    TROIS("Connait le nom de la compétence, sa définition et ce qu'elle fait"),
    QUATRE("Connait le nom de la compétence, sa définition, ce qu'elle fait et comment elle fonctionne"),
    CINQ("Sait utiliser de manière basique la compétence"),
    SIX("Sait utiliser de manière intermédiaire la compétence"),
    SEPT("Sait utiliser de manière avancée la compétence"),
    HUIT("Sait utiliser de manière experte la compétence"),
    NEUF("Maitrise la compétence"),
    DIX("Sait enseigner la compétence a la personne qui la créé.");


    private final String label;

    Niveau(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
