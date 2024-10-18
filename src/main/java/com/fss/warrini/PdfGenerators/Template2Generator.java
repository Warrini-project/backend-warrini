package com.fss.warrini.PdfGenerators;

import com.fss.warrini.dto.EducationDto;
import com.fss.warrini.dto.ExperienceDto;
import com.fss.warrini.dto.ResumeDto;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.lowagie.text.Document;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Template2Generator {
    private static ResumeDto resumeDto;

    public static byte[] generate(ResumeDto input, String profileImage){

        resumeDto = input;
        try {
            Resource resource = new ClassPathResource("templates/template2.html");
            Path path = resource.getFile().toPath();
            String htmlBaseContent = new String(Files.readAllBytes(path));

            String modifiedHtml = htmlBaseContent.toString()
                    .replace("{{name}}", resumeDto.getName())
                    .replace("{{profileImage}}", "\"" + profileImage + "\"")
                    .replace("{{profileInfo}}", resumeDto.getSummary())
                    .replace("{{profession}}", resumeDto.getProfession())
                    .replace("{{phone}}", resumeDto.getPhone())
                    .replace("{{email}}", resumeDto.getEmail())
                    .replace("{{address}}", resumeDto.getAddress())
                    .replace("{{github}}", resumeDto.getGithub())
                    .replace("{{linkedin}}", resumeDto.getLinkedin())
                    .replace("{{skills}}", fillSkills(resumeDto.getSkills()))
                    .replace("{{languages}}", fillLanguages(resumeDto.getLanguages()))
                    .replace("{{educations}}", fillEducations(resumeDto.getEducations()))
                    .replace("{{experiences}}", fillExperiences(resumeDto.getExperiences()));

            return convertor(modifiedHtml);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }




    private static byte[] convertor(String htmlContent) {
        Path sharedDir = Paths.get("./shared");
        try {
            Files.createDirectories(sharedDir);
            Path tempHtmlPath = Files.createTempFile(sharedDir, "resume", ".html");
            Files.write(tempHtmlPath, htmlContent.getBytes());
            File pngFile = HtmlToPngConverter.convertHtmlToPng(htmlContent,"./shared/output.png");

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);
            document.open();
            com.lowagie.text.Image img = com.lowagie.text.Image.getInstance(pngFile.getAbsolutePath());
            img.scaleToFit(PageSize.A4.getWidth() - document.leftMargin() - document.rightMargin(),
                    PageSize.A4.getHeight() - document.topMargin() - document.bottomMargin());
            img.setAlignment(com.lowagie.text.Image.ALIGN_CENTER | com.lowagie.text.Image.ALIGN_MIDDLE);

            document.add(img);
            document.close();
            deleteDirectory(sharedDir.toString());
            return outputStream.toByteArray();
        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            deleteDirectory(sharedDir.toString());
        }

        return null;

    }

    public static void deleteDirectory(String dirPath) {
        String command;

        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            command = "cmd /c rmdir /s /q \"" + dirPath + "\"";
        } else {
            command = "rm -rf \"" + dirPath + "\"";
        }

        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
        } catch (IOException | InterruptedException e) {
        }
    }


    private static String fillSkills(List<String> skills) {
        String result = "";
        for (String skill : skills) {
            result += "<li>" +
                    skill +
                    "</li><br/>";
        }
        return result;

    }

    private static String fillLanguages(List<String> languages) {
        String result = "";
        for (String lang : languages) {
            result += "<li style='font-size: 18px;'>" +
                    lang +
                    "</li>";
        }
        return result;

    }

    private static String fillEducations(List<EducationDto> educations) {
        String result = "";
        for (EducationDto educ : educations) {
            result += "<li>" +
                    "<div class=\"jobPosition\">" +
                    "<span style=\"font-size: 20px; font-weight: bold;\">" +
                    educ.getDegree() +
                    "</span>" +
                    "<span>" +
                    educ.getDuration() +
                    "</span>" +
                    " </div>" +
                    "<p>" +
                    educ.getInstitution() +
                    "</p>" +
                    "</li>";
        }
        return result;

    }

    @SuppressWarnings("unchecked")
    private static String fillExperiences(List<ExperienceDto> experiences) {
        String result = "";
        for (ExperienceDto exp : experiences) {
            result += "<li>" +
                    "<div class=\"jobPosition\">" +
                    "<span class=\"bolded\" style=\"text-decoration: underline;\">" +
                    exp.getName() +
                    "</span>" +
                    "<span>" +
                    exp.getDuration() +
                    "</span>" +
                    " </div>" +
                    "<div class=\"projectName bolded\">" +
                    " <span>" +
                    exp.getPlace() +
                    "</span>" +
                    "</div>" +
                    "<div class=\"smallText\">" +
                    "<ul>";
            for (String task : exp.getTasks()) {
                result += " <li>" +
                        task +
                        " </li>";
            }
            result += " </ul>" +
                    "</div>" +
                    "</li>";
        }
        return result;
    }

}