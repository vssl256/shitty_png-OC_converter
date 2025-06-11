import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Stream;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.ArrayUtils;

public class App {

    static int extractInt(String s) {
    String num = s.replaceAll("\\D", "");
    return num.isEmpty() ? 0 : Integer.parseInt(num);
    }

    public static void main(String[] args) throws Exception {
        
        boolean isAnime = false;
        String output; 
        Scanner sc = new Scanner(System.in);
        System.out.println("\nSpecify input file (or folder, if you want to convert animation).\n     Example: C:/input_folder/image.png,\n     For animation: C:/input_folder/\nNotice, that for animation, you must have correspondive number to the frame in picture names.\n     For example: image1.png, image2.png\n");
        String input = sc.nextLine();
        System.out.println("Specify output folder (leave it blank, if you want to save it into the same folder as this converter)");
        output = sc.nextLine();
        if (output.isEmpty()) output = "out.txt";
        //String currentDir = System.getProperty("user.dir");
        File outFile = new File(output);
        outFile.createNewFile();
        Path out = Paths.get(output);
        List<String> start = Arrays.asList("component = require(\"component\")\ncolors = require(\"colors\")\nos = require(\"os\")\n\ngpu = component.gpu\n\nfunction draw(a,x,y)\ngpu.setBackground(a)\ngpu.set(x,y,\" \")\nend\n\nfunction clear()\ngpu.setBackground(0)\nos.execute(\"cls\")\nend\n\nfunction fps(f)");
        Files.write(out,start,StandardOpenOption.APPEND);
        boolean toggle = true;
        int counter = 1;
        String fileName = null;
        String[]    hexRedTrue =   { "00", "33", "66", "99", "CC", "FF" };
        String[]    hexGreenTrue = { "00", "24", "49", "6D", "92", "B6", "DB", "FF" };
        String[]    hexBlueTrue =  { "00", "40", "80", "C0", "FF" };

        int[]       redTrue =         { 0, 51, 102, 153, 204, 255 };
        int[]       greenTrue =       { 0, 36, 73, 109, 146, 182, 219, 255 };
        int[]       blueTrue =        { 0, 64, 128, 192, 255 };
        ArrayUtils.contains(redTrue, 2);

    for(int num = 1; num <= counter; num++) {
        File file = new File(input);
        if (!input.contains(".png")&&toggle) {
            isAnime = true;
            File[] fileList = file.listFiles();
            String[] fileNames = new String[fileList.length];
            for (int i = 0; i < fileList.length; i++) {
                int b = extractInt(fileList[i].getName());
                fileNames[b-1] = fileList[i].getName();
                fileName = fileNames[b-1].replace(Integer.toString(b), "");
            }
            toggle = false;
            counter = fileList.length;
            System.out.println(Arrays.toString(fileNames));
            System.out.println(fileName);
        }
        String Sline = "";
        BufferedImage img = null; 
        if (!isAnime) img = ImageIO.read(file);
        if (isAnime) {
            File tfile = new File(input+fileName.replace(".png","")+num+".png");
            System.out.println(input+fileName.replace(".png","")+num+".png");
            img = ImageIO.read(tfile);
        };
        Raster raster = img.getData();
        int[] pixels = new int[3];
        for(int y = 0; y < raster.getHeight(); y++) { 
            for (int x = 0; x < raster.getWidth(); x++) {
                raster.getPixel(x, y, pixels);
                int r = pixels[0];
                int g = pixels[1];
                int b = pixels[2];
                String hexR = Integer.toHexString(r);
                String hexB = Integer.toHexString(b);
                String hexG = Integer.toHexString(g);
                String color = "0x"+hexR+hexG+hexB;
                if ((r+g+b)!=0)Sline += String.format("draw(%s,%s,%s) ", color, x+1, y+1);
            }
        }
        List<String> line = Arrays.asList(Sline+"\nos.sleep(f)\nclear()");
        Files.write(out,line,StandardOpenOption.APPEND);
        //}
    }
    List<String> line = Arrays.asList("end\n\nwhile true do\nfps(0.1)\nend");
        Files.write(out,line,StandardOpenOption.APPEND);
    }
}

