package controller;

import additional.AppController;
import display.GamePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;

public class GameController implements ActionListener {
    private GamePanel view;

    private final String iconsPath = "images\\icons";
    private ArrayList<String> iconsPathList = new ArrayList<>();



    private int rowsNumber = 3;
    private int colsNumber = 4;

    public GameController(GamePanel view) {
        this.view = view;
    }


    public int getRowsNumber() {
        return rowsNumber;
    }

    public int getColsNumber() {
        return colsNumber;
    }



    /**
     * Obtenir la liste des chemins des icons
     * @return Une {@code ArrayList<String>} contenant les chemins de chaque icon.
     *          Retourne {@code null} si une erreur s'est produite
     */
    public ArrayList<String> getIconsPathList() {
        if (iconsPathList.isEmpty()) {
            loadIconsPath();
        }
        return iconsPathList;
    }

    /**
     * Recherche tous les icons du dossier icons et les stockent dans {@code iconsPathList} contenant leurs chemins
     *
     */
    public void loadIconsPath() {
        try {
            File iconsDir = new File(System.getProperty("user.dir") + "\\" + iconsPath);
            String[] iconsList = iconsDir.list(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.matches("icon_[0-9]+.png");
                }
            });

            if (iconsList == null) return;

            iconsPathList = new ArrayList<>(Arrays.asList(iconsList));
            for (int i = 0; i < iconsPathList.size(); i++) {
                iconsPathList.set(i, iconsPath + "\\" +  iconsPathList.get(i));
            }

        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
