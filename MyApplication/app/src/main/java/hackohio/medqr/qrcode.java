package hackohio.medqr;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

/**
 * Created by Blaise on 11/14/2015.
 */
public class qrcode {

        com.google.zxing.Writer writer = new QRCodeWriter();
        String content = "Is this real life? or is this fantasy?";
        int QR_width = 150;
        int QR_height = 150;
        public Bitmap qrBitmap(String content, int QR_width, int QR_height) {
            this.content = content;
            this.QR_width = QR_width;
            this.QR_height = QR_height;
            Bitmap ImageBitmap = null;

            try {
                BitMatrix bm = writer.encode(content, BarcodeFormat.QR_CODE, QR_width, QR_height);
                ImageBitmap = Bitmap.createBitmap(QR_width, QR_height, Bitmap.Config.ARGB_8888);

                for (int i = 0; i < QR_width; i++) {
                    for (int j = 0; j < QR_height; j++) {
                        ImageBitmap.setPixel(i, j, bm.get(i, j) ? Color.BLACK : Color.WHITE);
                    }
                }


            } catch (WriterException e) {
                e.printStackTrace();
            }
            return ImageBitmap;
        }







    }

