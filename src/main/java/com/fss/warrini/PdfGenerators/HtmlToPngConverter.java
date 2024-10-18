package com.fss.warrini.PdfGenerators;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.Clip;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import com.microsoft.playwright.options.ScreenshotType;

public class HtmlToPngConverter {
    public static File convertHtmlToPng(String htmlContent, String outputFilePath) throws IOException {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(true));
            BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                    .setViewportSize(1920, 1080)
                    .setDeviceScaleFactor(2.0)
            );
            Page page = context.newPage();

            page.setContent(htmlContent);

            ArrayList<Object> boundingBox = (ArrayList<Object>) page.evaluate("() => { " +
                    "const body = document.body;" +
                    "const rect = body.getBoundingClientRect();" +
                    "return [rect.x, rect.y, rect.width, rect.height];" +
                    "}");

            double x = ((Number) boundingBox.get(0)).doubleValue();
            double y = ((Number) boundingBox.get(1)).doubleValue();
            double width = ((Number) boundingBox.get(2)).doubleValue();
            double height = ((Number) boundingBox.get(3)).doubleValue();

            Path path = Paths.get(outputFilePath);

            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(path)
                    .setFullPage(true)
                    .setType(ScreenshotType.PNG)
                    .setClip(new Clip((int) x, (int) y, (int) width, (int) height)));

            browser.close();

            return path.toFile();
        }
    }
}
