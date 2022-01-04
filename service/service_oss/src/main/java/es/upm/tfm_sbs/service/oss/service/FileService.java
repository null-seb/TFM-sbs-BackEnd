package es.upm.tfm_sbs.service.oss.service;

import java.io.InputStream;

public interface FileService {
    String upload(InputStream inputStream, String module, String originalFilename);
}
