package org.example;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DockerClientBuilder;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyExample {

    public static void main(String[] args) throws IOException {
        // Data
        // todo 修改地址
        String imageId = "api-gateway-center:1.0.0";
        String containerName = "api-gateway-center";
        String containerFile = "/nginx.conf";
        String hostFile = "/Users/94461/Desktop/api/api-center/doc/data/nginx/nginx.conf";

        // Docker client
        DockerClient dockerClient = DockerClientBuilder.getInstance().build();

        // Create container
//        try (CreateContainerCmd createContainer = dockerClient
//                .createContainerCmd(imageId).withName(containerName)) {
//            createContainer.withTty(true);
//            createContainer.exec();
//        }
//
//        // Start container
//        dockerClient.startContainerCmd(containerName).exec();

        // Copy file from container
        try (TarArchiveInputStream tarStream = new TarArchiveInputStream(
                dockerClient.copyArchiveFromContainerCmd(containerName,
                        containerFile).exec())) {
            unTar(tarStream, new File(hostFile));
        }

        // Stop container
        // dockerClient.killContainerCmd(containerName).exec();

        // Remove container
        // dockerClient.removeContainerCmd(containerName).exec();
    }

    public static void unTar(TarArchiveInputStream tis, File destFile)
            throws IOException {
        TarArchiveEntry tarEntry = null;
        while ((tarEntry = tis.getNextTarEntry()) != null) {
            if (tarEntry.isDirectory()) {
                if (!destFile.exists()) {
                    destFile.mkdirs();
                }
            } else {
                FileOutputStream fos = new FileOutputStream(destFile);
                IOUtils.copy(tis, fos);
                fos.close();
            }
        }
        tis.close();
    }

}




