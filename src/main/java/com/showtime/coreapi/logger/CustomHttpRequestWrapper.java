package com.showtime.coreapi.logger;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import lombok.SneakyThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CustomHttpRequestWrapper extends HttpServletRequestWrapper {

    private byte[] requestBody;


    @SneakyThrows
    public CustomHttpRequestWrapper(HttpServletRequest request) {
        super(request);
        InputStream inputStream = request.getInputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, bytesRead);
        }

        this.requestBody = byteArrayOutputStream.toByteArray();

    }

    @Override
    public ServletInputStream getInputStream() throws IOException {

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.requestBody);

        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return byteArrayInputStream.available() == 0;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };

    }


    public byte[] getRequestBody(){
        return this.requestBody;
    }

}
