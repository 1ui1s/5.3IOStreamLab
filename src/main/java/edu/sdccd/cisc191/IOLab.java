package edu.sdccd.cisc191;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * References:
 * Morelli, R., & Walde, R. (2016). Java, Java, Java: Object-Oriented Problem Solving.
 * Retrieved from https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 *
 */
public class IOLab
{
    public static final String RESOURCES_PATH = "src/test/resources/";
    private static Pattern dateTimePattern = Pattern.compile("\"datetime\":\"(.*?)\"");

    public static String readTestResults(String s) {

        StringBuilder sb = new StringBuilder();
        File file = new File(RESOURCES_PATH + "/" +s);

        if (!file.exists() || !file.isFile()) {
            System.out.println("File does not exist");
        }

        String line;
        try {
            //InputStream is = Files.newInputStream(file.toPath());
            InputStream is = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                }
                else {
                    sb.append(System.lineSeparator());
                }
            }
            is.close();
            isr.close();
            br.close();


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return sb.toString();
    }

    public static void appendTestResult(String s, String s1) {
        File file = new File(RESOURCES_PATH + "/" +s);
        try {
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);

            if(file.createNewFile()) {
                System.out.println(s + "doesn't yet exist, created");
            } else {
                System.out.println(s + " does exist, appending");
                bw.append(System.lineSeparator());
            }

            bw.append(s1);
            bw.close();
            fw.close();


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String readDateTime(String s) {
        try {
            URL url = new URL(s);
            InputStream is = new BufferedInputStream(url.openStream());
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String result = br.readLine();
            Matcher dateTimeMatcher = dateTimePattern.matcher(result);

        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}