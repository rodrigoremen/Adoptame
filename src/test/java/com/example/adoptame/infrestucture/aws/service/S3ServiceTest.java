package com.example.adoptame.infrestucture.aws.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class S3ServiceTest {

    @Mock
    private AmazonS3Client s3Client;

    @InjectMocks
    private S3Service s3Service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPutObject() throws IOException {
        // Crea un archivo de prueba
        byte[] content = "Test content".getBytes();
        String originalFilename = "test.txt";
        String contentType = "text/plain";
        MultipartFile file = new MockMultipartFile(originalFilename, originalFilename, contentType, content);

        // Configura el comportamiento del mock
        String key = UUID.randomUUID().toString();
        when(s3Client.putObject(any(PutObjectRequest.class))).thenReturn(new PutObjectResult());
        when(s3Client.getUrl(anyString(), anyString())).thenReturn(null);

        // Ejecuta el método a probar
        String result = s3Service.putObject(file);

        // Verifica el resultado
        assertNotNull(result);
        assertTrue(result.contains(key));
        verify(s3Client, times(1)).putObject(any(PutObjectRequest.class));
    }
}
