package pe.gob.sunat.common.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public final class FileUtil {

	protected FileUtil() {
		// TODO Auto-generated constructor stub
		throw new UnsupportedOperationException();
	}

	public static boolean createFolder(String path) {
		boolean created = false;
		try {
			File folder = new File(path);
			if (!folder.exists()) {
				created = folder.mkdir();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return created;
	}

	public static String readFileToString(String filepath) {
		String content = "";
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(filepath));
			StringBuilder stringBuilder = new StringBuilder();
			String line = bufferedReader.readLine();
			while (line != null) {
				stringBuilder.append(line);
				stringBuilder.append("\n");

				line = bufferedReader.readLine();
			}

			content = stringBuilder.toString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (Exception ex) {
				// TODO: handle exception
				ex.printStackTrace();
			}
		}

		return content;
	}

	public static boolean writeStringToFile(String filepath, String content) {
		boolean created = false;
		FileWriter file = null;
		try {
			file = new FileWriter(filepath);
			file.write(content);

			created = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (file != null) {
					file.close();
				}
			} catch (Exception ex) {
				// TODO: handle exception
				ex.printStackTrace();
			}
		}

		return created;
	}

}
