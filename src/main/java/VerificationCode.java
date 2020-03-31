import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class VerificationCode {
    /*
    * 设置动态生成图片
    */
    public void fun() throws IOException {
        /**
         *创建图片缓冲区
         * 设置宽高
         * 得到画笔
         * 保存
         */
        BufferedImage bi = new BufferedImage(70,35,BufferedImage.TYPE_INT_RGB);
        //得到画笔
        Graphics2D g = (Graphics2D) bi.getGraphics();
        //设置绘制的颜色
        g.setColor(Color.RED);
        //填充矩形(从（0,0）开始)
        g.fillRect(0,0,70,35);
        //向图片中写入字符串
        g.drawString("Hello",2,35-2);

        ImageIO.write(bi,"JPG",new FileOutputStream("F:/java文件/JavaWebTest/Image"));
    }
}
