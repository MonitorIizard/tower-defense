package helperMethod;

import objects.PathPoint;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoadSave {

    public static ArrayList<PathPoint> GetLevelPathPoints(String name) {
        File lvlFIie = new File("resources/" + name + ".txt");

        if( lvlFIie.exists() ) {
            ArrayList<Integer> list = ReadFromFile(lvlFIie);
            System.out.println(list.size());
            ArrayList<PathPoint> points = new ArrayList<>();
            points.add( new PathPoint( list.get(200), list.get(201) ));
            points.add( new PathPoint( list.get(202), list.get(203) ));

            return  points;
        } else {

            System.out.println("File : " + lvlFIie + "not exist!");
            return null;
        }
    }
    public static BufferedImage getSpriteAtlas() {

        BufferedImage img = null;
        InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("sprite-atlas.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e)  {
            e.printStackTrace();
        }

        return img;
    }


    public static void CreateLevel(String fileName, int[] idArr ) {
        File newLevel = new File("resources/" + fileName + ".txt");

        if( newLevel.exists() ) {
            System.out.println("File : " + fileName + "already exist!");
            return;
        } else {
            try {
                newLevel.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        WriteToFile(newLevel, idArr, new PathPoint(0,0), new PathPoint(0, 0));
    }

    private static void WriteToFile(File f, int[] idArr, PathPoint start, PathPoint end) {
        try {
            PrintWriter pw = new PrintWriter(f);

            for( Integer i : idArr ) {
                pw.println(i);
            }

            pw.println(start.getxCord());
            pw.println(start.getyCord());
            pw.println(end.getxCord());
            pw.println(end.getyCord());

            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static  ArrayList<Integer> ReadFromFile(File file) {
        ArrayList<Integer> list = new ArrayList<>();

        try {
            Scanner sc = new Scanner(file);

            while(sc.hasNextLine()) {
                list.add(Integer.parseInt(sc.nextLine()));
            }


            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static int[][] GetLevelData(String name) {
        File lvlFile = new File("resources/" + name + ".txt");

        if ( lvlFile.exists() ) {
            ArrayList<Integer> list = ReadFromFile(lvlFile);
            return Utilz.ArrayListTo2Dint(list, 10, 20);
        } else {
            System.out.println("file" + name + "does not exists!");
            return null;
        }
    }

    public static void SaveLevel(String name, int [][] idArr, PathPoint start, PathPoint end) {
        File levelFile = new File("resources/" + name + ".txt");
        if( levelFile.exists()) {
            WriteToFile(levelFile, Utilz.TwoDto1Dint(idArr), start, end);

//            printMatrix(idArr);

        } else {
            System.out.println("File : " + name + " does not exists!");
        }
    }

    public static void printMatrix(int[][] matrix) {
        for ( int i = 0; i < matrix.length; i++ ) {
            for ( int j = 0; j < matrix[i].length; j++ ) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
