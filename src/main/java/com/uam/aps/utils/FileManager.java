package com.uam.aps.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class FileManager {

	public static void InitializeFiles() throws IOException {

		Writer FIRST_SERVER_CLIENT = new BufferedWriter(new FileWriter(Path.FIRST_SERVER_CLIENT, false));
		FIRST_SERVER_CLIENT.write("[]");
		FIRST_SERVER_CLIENT.close();

		Writer SECOND_SERVER_CLIENT = new BufferedWriter(new FileWriter(Path.SECOND_SERVER_CLIENT, false));
		SECOND_SERVER_CLIENT.write("[]");
		SECOND_SERVER_CLIENT.close();

		Writer SECOND_SERVER_EMPLOYEE = new BufferedWriter(new FileWriter(Path.SECOND_SERVER_EMPLOYEE, false));
		SECOND_SERVER_EMPLOYEE.write("[]");
		SECOND_SERVER_EMPLOYEE.close();

		Writer THIRD_SERVER_EMPLOYEE = new BufferedWriter(new FileWriter(Path.THIRD_SERVER_EMPLOYEE, false));
		THIRD_SERVER_EMPLOYEE.write("[]");
		THIRD_SERVER_EMPLOYEE.close();

		Writer FIRST_SERVER_PRODUCT = new BufferedWriter(new FileWriter(Path.FIRST_SERVER_PRODUCT, false));
		FIRST_SERVER_PRODUCT.write("[]");
		FIRST_SERVER_PRODUCT.close();

		Writer THIRD_SERVER_PRODUCT = new BufferedWriter(new FileWriter(Path.THIRD_SERVER_PRODUCT, false));
		THIRD_SERVER_PRODUCT.write("[]");
		THIRD_SERVER_PRODUCT.close();
	}

}
