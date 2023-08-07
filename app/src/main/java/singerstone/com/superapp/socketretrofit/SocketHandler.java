package singerstone.com.superapp.socketretrofit;

import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import singerstone.com.superapp.log.AppLog;

public class SocketHandler implements Runnable {

    private Socket socket;

    SocketHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader bufferedReader = null;
        PrintWriter writer = null;
        try {
            System.out.println("thread:\t" + Thread.currentThread().getName());
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //true,表示自动刷新，不需要人为触发
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            String userInput;
            while ((userInput = bufferedReader.readLine()) != null) {
                if ("exit".equals(userInput)) {
                    System.out.println("退出连接通信\t");
                    break;
                }
                System.out.println("接收内容：\t" + userInput);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    String result =
                            "服务器时间：" + LocalDateTime.now() + "\t" + new StringBuilder(userInput)
                                    .reverse();
                    writer.println(result);
                    AppLog.i("yogachen",result);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                socket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
