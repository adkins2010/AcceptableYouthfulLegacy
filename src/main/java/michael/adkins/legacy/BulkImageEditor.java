package michael.adkins.legacy;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    static File cropImage(File inputFile, String outputFolder) throws IOException {
        BufferedImage inputImage = ImageIO.read(new File("input.jpg"));

        // Get the dimensions of the input image
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        // Calculate the dimensions of the cropped image
        int croppedWidth = width;
        int croppedHeight = height - (height / 4);

        // Create a new BufferedImage for the cropped image
        BufferedImage croppedImage = new BufferedImage(croppedWidth, croppedHeight, inputImage.getType());

        // Copy the bottom right portion of the input image to the cropped image
        croppedImage.getGraphics().drawImage(inputImage, 0, 0, croppedWidth, croppedHeight, width - croppedWidth, height - croppedHeight, width, height, null);

        String inputFileName = inputFile.getName();
        String regex = "([0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]T[0-9]+\\\\.[0-9]+)";
        Matcher m = Pattern.compile(regex).matcher(inputFileName);
        String outputFileName = null;
        if(m.find()) {
            outputFileName = m.group(1);
        } else {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-ddThhmmss.v");
            outputFileName = dateFormat.format(Calendar.getInstance().getTime());
        }
        File outputFile = new File(new StringBuilder(outputFolder).append("\\").append(outputFileName).toString());
        ImageIO.write(croppedImage, "png", outputFile);
        return outputFile;
    }

    static File writeBlackBoxToImage(File inputFile, String outputFolder) throws IOException {
        BufferedImage inputImage = ImageIO.read(inputFile);

        // Create a new image with the same dimensions as the input image
        BufferedImage outputImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), BufferedImage.TYPE_INT_ARGB);

        // Draw the input image onto the output image
        Graphics2D graphics = outputImage.createGraphics();
        graphics.drawImage(inputImage, 0, 0, null);

        // Draw a black rectangle on the bottom right of the output image
        int rectWidth = 274;
        int rectHeight = 22;
        int rectX = outputImage.getWidth() - rectWidth;
        int rectY = outputImage.getHeight() - rectHeight;
        graphics.setColor(Color.WHITE);
        graphics.fillRect(rectX, rectY, rectWidth, rectHeight);
        File outputFile = new File(new StringBuilder(outputFolder).append("\\").append(inputFile.getName()).toString());
        ImageIO.write(outputImage, "png", outputFile);
        return outputFile;
    }


    static boolean fileCondition(File file) {
        return file.isFile() && file.getName().toLowerCase(Locale.ROOT).contains("png") && (file.getName().toLowerCase(Locale.ROOT).contains("image") || file.getName().toLowerCase(Locale.ROOT).contains("generated") || file.getName().toLowerCase(Locale.ROOT).contains("ecu hottie"));
    }

    public static void main(String[] args) throws IOException {

        String inputPath = "C:\\Users\\User\\Pictures\\AI\\";
        String outputPath = "C:\\Users\\User\\Pictures\\AI\\out";
        if (args != null && args.length >= 1) {
            inputPath = args[1];
            outputPath = args[2];
        }
        // Save the output image to a file
        File[] filesInFolder = getFilesInFolder(inputPath);
        String finalOutputPath = outputPath;
        Flux.fromArray(filesInFolder).subscribeOn(Schedulers.parallel()).filter(BulkImageEditor::fileCondition).mapNotNull(file -> {
            File out = file;
            BufferedImage image = null;
            try {
                image = ImageIO.read(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            List<Integer> dimensions = List.of(384, 512, 640, 768, 1024);
//            if (image != null && (!dimensions.contains(image.getWidth()) || !dimensions.contains(image.getHeight()))) {
//                if(file.delete()) {
//                    return file;
//                }
//            }
            if(image != null && (dimensions.contains(image.getHeight()) && dimensions.contains(image.getWidth()))) {
                try {
                    out = writeBlackBoxToImage(out, finalOutputPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
//            if(file.getName().matches("([0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]T[0-9]+\\.[0-9]+)")) {
//                if(file.getName().matches("^.+([0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]).+$")) {
//                    try {
//                        out = cropImage(file, finalOutputPath);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
            }
            return out;
        }).blockLast();
    }
}
