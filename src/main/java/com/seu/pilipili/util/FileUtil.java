package com.seu.pilipili.util;

import com.seu.pilipili.entity.Comment;
import com.seu.pilipili.entity.CommentDetails;
import com.seu.pilipili.entity.User;
import org.springframework.beans.factory.annotation.Value;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class FileUtil {
    @Value("${pilipili.profilePath.WIN}")
    private static String WINDOWS_PROFILES_PATH;
    @Value("${pilipili.profilePath.MAC}")
    private static String MAC_PROFILES_PATH;

    public static byte[] imageToByte(File img) throws Exception {
        byte[] bytes = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            BufferedImage bi;
            bi = ImageIO.read(img);
            ImageIO.write(bi, "png", baos);
            bytes = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            baos.close();
        }
        return bytes;
    }

    public static String fileToBase64(String relativePath) throws IOException {
        String OSName = System.getProperty("os.name");
        String profilesPath = OSName.toLowerCase().startsWith("win") ? WINDOWS_PROFILES_PATH
                : MAC_PROFILES_PATH;
        byte[] b = Files.readAllBytes(Paths.get(profilesPath + relativePath));
        return Base64.getEncoder().encodeToString(b);
    }
}
