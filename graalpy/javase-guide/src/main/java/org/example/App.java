package org.example;

import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class App {
    public static void main(String[] args) throws IOException {
        try (var context = GraalPy.createPythonContext(System.getProperty("graalpy.resources"))) { // ①
            QRCode qrCode = context.eval("python", "import qrcode; qrcode").as(QRCode.class); // ②
            IO io = context.eval("python", "import io; io").as(IO.class);

            IO.BytesIO bytesIO = io.BytesIO(); // ③
            qrCode.make("Hello from GraalPy on JDK " + System.getProperty("java.version")).save(bytesIO);

            var qrImage = ImageIO.read(new ByteArrayInputStream(bytesIO.getvalue().toByteArray())); // ④
            JFrame frame = new JFrame("QR Code");
            frame.getContentPane().add(new JLabel(new ImageIcon(qrImage)));
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setSize(400, 400);
            frame.setVisible(true);
        }
    }
}
