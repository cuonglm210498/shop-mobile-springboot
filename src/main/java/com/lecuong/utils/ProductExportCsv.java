package com.lecuong.utils;

import com.lecuong.modal.response.product.ProductResponse;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ProductExportCsv {

    // Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    // CSV file header
    private static final String FILE_HEADER = "Id,Name,Color,Batterry capacity,Operation system,Screen size,Ram,Warranty,CPU,Memory,Thumbnail,Real camera,Front camera,Status,Quantity,Price,Provider name,Category name";

    public static void writeCsvFile(List<ProductResponse> productResponses, String fileName) {
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(fileName, StandardCharsets.UTF_8);

            // Write the CSV file header
            fileWriter.append(FILE_HEADER);

            // Add a new line separator after the header
            fileWriter.append(NEW_LINE_SEPARATOR);

            // Write a new ProductResponse object list to the CSV file
            for (ProductResponse productResponse : productResponses) {

                fileWriter.append(String.valueOf(productResponse.getId()));
                fileWriter.append(COMMA_DELIMITER);

                fileWriter.append(productResponse.getName());
                fileWriter.append(COMMA_DELIMITER);

                fileWriter.append(productResponse.getColor());
                fileWriter.append(COMMA_DELIMITER);

                fileWriter.append(productResponse.getBatteryCapacity());
                fileWriter.append(COMMA_DELIMITER);

                fileWriter.append(productResponse.getOperationSystem());
                fileWriter.append(COMMA_DELIMITER);

                fileWriter.append(productResponse.getScreenSize());
                fileWriter.append(COMMA_DELIMITER);

                fileWriter.append(productResponse.getRam());
                fileWriter.append(COMMA_DELIMITER);

                fileWriter.append(productResponse.getWarranty());
                fileWriter.append(COMMA_DELIMITER);

                fileWriter.append(productResponse.getCpu());
                fileWriter.append(COMMA_DELIMITER);

                fileWriter.append(productResponse.getMemory());
                fileWriter.append(COMMA_DELIMITER);

                fileWriter.append(productResponse.getThumbnail());
                fileWriter.append(COMMA_DELIMITER);

                fileWriter.append(productResponse.getRealCamera());
                fileWriter.append(COMMA_DELIMITER);

                fileWriter.append(productResponse.getFrontCamera());
                fileWriter.append(COMMA_DELIMITER);

                fileWriter.append(productResponse.getStatus());
                fileWriter.append(COMMA_DELIMITER);

                fileWriter.append(String.valueOf(productResponse.getQuantity()));
                fileWriter.append(COMMA_DELIMITER);

                fileWriter.append(String.valueOf(productResponse.getPrice()));
                fileWriter.append(COMMA_DELIMITER);

                fileWriter.append(productResponse.getProviderName());
                fileWriter.append(COMMA_DELIMITER);

                fileWriter.append(productResponse.getCategoryName());
                fileWriter.append(NEW_LINE_SEPARATOR);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
