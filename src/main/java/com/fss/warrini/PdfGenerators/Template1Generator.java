package com.fss.warrini.PdfGenerators;

import com.fss.warrini.dto.EducationDto;
import com.fss.warrini.dto.ExperienceDto;
import com.fss.warrini.dto.ResumeDto;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.core.io.ClassPathResource;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Template1Generator {
    private static PDDocument document;
    private static ResumeDto resumeDto;
    @SuppressWarnings("unchecked")
    public static byte[] generate(ResumeDto input) {
        resumeDto = input;
        try {

            document = new PDDocument();
            ClassPathResource fontResource = new ClassPathResource("fonts/Lato/Lato-Black.ttf");
            InputStream fontStream = fontResource.getInputStream();

            PDType0Font latoFont = PDType0Font.load(document, fontStream);

            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            PDFont headerFont = PDType1Font.HELVETICA_BOLD;
            PDFont subHeaderFont = PDType1Font.HELVETICA;
            PDFont textFont = PDType1Font.HELVETICA;

            contentStream.setNonStrokingColor(Color.decode("#323b4c"));
            writeText(contentStream, page, resumeDto.getName(), 30, 18, headerFont);
            writeText(contentStream, page, resumeDto.getProfession(), 55, 14, subHeaderFont);

            drawContactInfo(contentStream, textFont);

            drawSection(contentStream, "PROFILE INFO", 30, 650, latoFont, textFont, true, 525);
            drawSectionContent(false, false, contentStream, resumeDto.getSummary(), 40, 630, textFont, 12, 500);
            contentStream.moveTo(220, 550);
            contentStream.lineTo(220, 30);
            contentStream.stroke();
            drawSection(contentStream, "EDUCATION", 30, 540, headerFont, textFont, false, 70);
            int currTy = 520;
            for (EducationDto edu : resumeDto.getEducations()) {
                currTy -= drawSectionContent(false, false, contentStream, edu.getDegree(), 40, currTy, latoFont, 12,
                        150) * 15;
                currTy -= drawSectionContent(false, true, contentStream, edu.getInstitution(), 53, currTy, textFont,
                        10, 150) * 15;
                drawSectionContent(false, false, contentStream, edu.getDuration(), 53, currTy, textFont, 8, 150);
                currTy -= 40;
            }
            drawSection(contentStream, "LANGUAGES", 30, 330, headerFont, textFont, false, 75);
            currTy = 310;
            for (String lang : resumeDto.getLanguages()) {
                currTy -= drawSectionContent(false, true, contentStream, lang, 53, currTy, textFont, 12, 150)
                        * 15;

            }
            drawSection(contentStream, "SKILLS", 30, currTy-50, headerFont, textFont, false, 42);
            currTy -= 70;
            for (String lang : resumeDto.getSkills()) {
                currTy -= drawSectionContent(false, true, contentStream, lang, 53, currTy, textFont, 12, 150)
                        * 15;

            }
            drawSection(contentStream, "PROFESSIONAL EXPERIENCE", 250, 540, headerFont, textFont, false, 175);
            currTy = 520;
            for (ExperienceDto exp : resumeDto.getExperiences()) {
                currTy -= drawSectionContent(true, false, contentStream,
                        (String) exp.getName() + " (" + (String) exp.getDuration() + ")", 260, currTy, latoFont, 12,
                        500) * 15;
                currTy -= drawSectionContent(false, true, contentStream, (String) exp.getPlace(), 273, currTy,
                        textFont, 10,
                        500) * 15;
                for (String task : (List<String>) exp.getTasks()) {
                    currTy -= drawSectionContent(false, false, contentStream, "- " + task, 273, currTy, textFont, 8, 500)
                            * 15;
                }
                currTy -= 40;
            }

            drawSection(contentStream, "CERTIFICATES AND TRAINING COURSES", 250, currTy-30, headerFont, textFont, false, 100);
            currTy -= 50;
            for (String certif : resumeDto.getCertifications()) {
                currTy -= drawSectionContent(false, true, contentStream, certif, 273, currTy, textFont, 12, 290)
                        * 15;

            }
            contentStream.close();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            document.save(outputStream);
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    private static void drawContactInfo(PDPageContentStream contentStream, PDFont font) throws IOException {
        ClassPathResource gpsAssetResource = new ClassPathResource("assets/gps.png");
        ClassPathResource phoneAssetResource = new ClassPathResource("assets/phone.png");
        ClassPathResource mailAssetResource = new ClassPathResource("assets/mail.png");

        drawImage(document, contentStream, gpsAssetResource.getInputStream(), 55, 710, 10, 10);
        int lines = writeWords(false, contentStream, resumeDto.getAddress(), font, 10, 150, 70, 715);
        contentStream.setStrokingColor(0, 0, 0);
        contentStream.addRect(30, 715-15*lines, 530, 15+15*lines);
        contentStream.stroke();
        drawImage(document, contentStream, phoneAssetResource.getInputStream(), 250, 715-(15*lines/2), 10, 10);
        writeWords(false, contentStream, resumeDto.getPhone(), font, 10, 100, 270, 722-(15*lines/2));
        drawImage(document, contentStream, mailAssetResource.getInputStream(), 405, 715-(15*lines/2), 10, 10);
        writeWords(false, contentStream, resumeDto.getEmail(), font, (int) (225/resumeDto.getEmail().length()), 200, 425, 722-(15*lines/2));


    }

    private static void drawImage(PDDocument document, PDPageContentStream contentStream, InputStream imageStream, float x,
                                  float y, float width, float height) throws IOException {
        // Load the image as a PDImageXObject
        byte[] imageBytes = imageStream.readAllBytes();

        PDImageXObject image = PDImageXObject.createFromByteArray(document, imageBytes, "image");

        contentStream.drawImage(image, x, y, width, height);
    }

    private static int writeWords(boolean containsDate, PDPageContentStream contentStream, String text, PDFont font,
                                  float fontSize,
                                  float maxWidth, int tx, int ty) throws IOException {
        int nbLines = 1;
        String dateSection = "";
        if (containsDate) {
            int fIndex = text.indexOf('(', 0);
            int lIndex = text.indexOf(')', 0);
            dateSection = text.substring(fIndex, lIndex + 1);
            text = text.replace(dateSection, "");
        }
        contentStream.setFont(font, fontSize);
        contentStream.setLeading(fontSize * 1.2f);
        contentStream.beginText();
        Boolean isBigger = false;
        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();

        for (String word : words) {
            String testLine = line + word + " ";
            float textWidth = font.getStringWidth(testLine) / 1000 * fontSize;

            if (textWidth > maxWidth) {
                nbLines ++;
                line = new StringBuilder(word + " ");
            } else {
                line.append(word).append(" ");
            }
        }

        if (!isBigger)
            contentStream.newLineAtOffset(tx, ty - 5);

        line = new StringBuilder();
        for (String word : words) {
            String testLine = line + word + " ";
            float textWidth = font.getStringWidth(testLine) / 1000 * fontSize;

            if (textWidth > maxWidth) {
                contentStream.showText(line.toString().trim());
                contentStream.newLine();
                line = new StringBuilder(word + " ");
            } else {
                line.append(word).append(" ");
            }
        }

        contentStream.showText(line.toString().trim());
        if (containsDate) {
            contentStream.setNonStrokingColor(Color.GRAY);
            contentStream.setFont(font, fontSize - 2);
            contentStream.showText("\s\s\s\s" + dateSection);
            contentStream.setNonStrokingColor(Color.decode("#323b4c"));
        }
        contentStream.endText();
        return nbLines;

    }

    private static void drawBulletPoint(PDPageContentStream contentStream, float centerX, float centerY, float radius)
            throws IOException {
        final float magicNumber = 0.552284749831f;

        float x0 = centerX;
        float y0 = centerY - radius;
        float x1 = centerX + radius * magicNumber;
        float y1 = centerY - radius;
        float x2 = centerX + radius;
        float y2 = centerY - radius * magicNumber;
        float x3 = centerX + radius;
        float y3 = centerY;

        float x4 = centerX + radius;
        float y4 = centerY + radius * magicNumber;
        float x5 = centerX + radius * magicNumber;
        float y5 = centerY + radius;
        float x6 = centerX;
        float y6 = centerY + radius;

        float x7 = centerX - radius * magicNumber;
        float y7 = centerY + radius;
        float x8 = centerX - radius;
        float y8 = centerY + radius * magicNumber;
        float x9 = centerX - radius;
        float y9 = centerY;

        float x10 = centerX - radius;
        float y10 = centerY - radius * magicNumber;
        float x11 = centerX - radius * magicNumber;
        float y11 = centerY - radius;
        float x12 = centerX;
        float y12 = centerY - radius;

        contentStream.moveTo(x0, y0);
        contentStream.curveTo(x1, y1, x2, y2, x3, y3);
        contentStream.curveTo(x4, y4, x5, y5, x6, y6);
        contentStream.curveTo(x7, y7, x8, y8, x9, y9);
        contentStream.curveTo(x10, y10, x11, y11, x12, y12);
        contentStream.closePath();
        contentStream.fill();
    }

    private static void drawSection(PDPageContentStream contentStream, String title, float x, float y,
                                    PDFont headerFont, PDFont textFont, boolean isProfile, int lineLength) throws IOException {
        contentStream.beginText();
        contentStream.setFont(headerFont, 12);
        contentStream.newLineAtOffset(x, y);
        contentStream.showText(title);
        contentStream.endText();

        if (isProfile) {
            contentStream.moveTo(x + 100, y + 3);
            contentStream.lineTo(x + lineLength, y + 3);
        } else {
            contentStream.moveTo(x, y - 5);
            contentStream.lineTo(x + lineLength, y - 5);
        }
        contentStream.stroke();
    }

    private static int drawSectionContent(boolean containsDate, boolean sub, PDPageContentStream contentStream,
                                          String content, int x,
                                          int y, PDFont font, float fontSize, float maxWidth) throws IOException {

        if (sub) {
            drawBulletPoint(contentStream, x - 10, y, 2);
        }
        return writeWords(containsDate, contentStream, content, font, fontSize, maxWidth, x, y);

    }

    private static void writeText(PDPageContentStream contentStream, PDPage page, String text, int marginTop,
                                  float fontSize, PDFont font) throws IOException {
        String name = text;
        float titleWidth = font.getStringWidth(name) / 1000 * fontSize;

        float titleHeight = font.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * fontSize;
        contentStream.beginText();
        contentStream.setFont(font, fontSize);

        contentStream.newLineAtOffset((page.getMediaBox().getWidth() - titleWidth) / 2,
                page.getMediaBox().getHeight() - marginTop - titleHeight);
        contentStream.drawString(name);
        contentStream.endText();
    }
}
