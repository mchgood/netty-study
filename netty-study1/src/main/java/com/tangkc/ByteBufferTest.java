package com.tangkc;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *  bytebuffer 测试
 */
@Slf4j
public class ByteBufferTest {

    public static void main(String[] args) {
        try (FileChannel channel = new FileInputStream("G:\\Person\\IDEA\\netty-study\\netty-study1\\src\\main\\data.txt").getChannel()){
            // 准备缓冲区大小
            ByteBuffer allocate = ByteBuffer.allocate(10);
            while (true){
                // 从 channel 读取数据到缓冲区，返回读取到的数据长度，没读取到返回 -1
                int read = channel.read(allocate);
                log.info("长度为{}",read);
                if(read == -1){
                    break;
                }
                // 切换到写模式
                allocate.flip();

                while (allocate.hasRemaining()){
                    char b = (char) allocate.get();
                    log.info("读取到的字符{}",b);
                }
                // 清空 buffer
                allocate.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
