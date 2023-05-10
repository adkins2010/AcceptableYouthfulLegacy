package michael.adkins.legacy;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.imageio.ImageIO;

public class BulkImageEditor {
    static File[] getFilesInFolder(String folderPath) {
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();

        assert listOfFiles != null;
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                System.out.println("File " + listOfFile.getName());
            } else if (listOfFile.isDirectory()) {
                System.out.println("Directory " + listOfFile.getName());
            }
        }
        return listOfFiles;
    }

    static File writeBlackBoxToImage(File inputFile) throws IOException {
        BufferedImage inputImage = ImageIO.read(inputFile);

        // Create a new image with the same dimensions as the input image
        BufferedImage outputImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), BufferedImage.TYPE_INT_ARGB);

        // Draw the input image onto the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, null);

        // Draw a black rectangle on the bottom right of the output image
        int rectWidth = 275;
        int rectHeight = 23;
        int rectX = outputImage.getWidth() - rectWidth;
        int rectY = outputImage.getHeight() - rectHeight;
        g2d.setColor(Color.BLACK);
        g2d.fillRect(rectX, rectY, rectWidth, rectHeight);
        File outputFile = new File(inputFile.getAbsolutePath());
        ImageIO.write(outputImage, "png", outputFile);
        return inputFile;
    }


    static boolean fileCondition(File file) {
        return file.isFile() && file.getName().toLowerCase(Locale.ROOT).contains("png") && (file.getName().toLowerCase(Locale.ROOT).contains("image") || file.getName().toLowerCase(Locale.ROOT).contains("generated"));
    }

    public static void main(String[] args) throws IOException {

//        File inputFile = new File("C:\\Users\\User\\Downloads\\temp\\download (1).png");
        String folder = "C:\\Users\\User\\Downloads\\temp\\";
        if(args != null && args.length >= 1) {
            folder = args[1];
        }
        // Save the output image to a file
        File[] filesInFolder = getFilesInFolder(folder);
        Flux.fromArray(filesInFolder).subscribeOn(Schedulers.parallel()).filter(BulkImageEditor::fileCondition).mapNotNull(file -> {
            try {
                return writeBlackBoxToImage(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            BufferedImage image = null;
            try {
                image = ImageIO.read(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            List<Integer> dimensions = List.of(512, 768, 1024);
            if (image != null && (dimensions.contains(image.getWidth()) || dimensions.contains(image.getHeight()))) {
                boolean delete = file.delete();
            }
            return file;
        }).collectList().block();
    }
}
