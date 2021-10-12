package controller;

import display.GamePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class GameController implements ActionListener {
    private GamePanel view;

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
            String localPath = "images" + File.separator + "icons";
            Path path = Paths.get(System.getProperty("user.dir"), localPath);

            if (!Files.exists(path))
                throw new NullPointerException("Invalid path");

            File iconsDir = new File(path.toString());

            File[] iconsList = iconsDir.listFiles((dir, name) -> name.matches("icon_[0-9]+.png"));

            if (iconsList == null)
                throw new NullPointerException("Empty directory");

            iconsPathList = new ArrayList<>();
            for (File file : iconsList) {
                // Add local path
                iconsPathList.add(file.toString());
            }

        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
