package mvc;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import geometry.Shape;

public class FileHandler implements FileStrategy {

	@Override
	public void saveData(String filePath, DrawingController controller) {
		saveLogger(filePath, controller);
		saveSerialize(filePath, controller);
	}

	@Override
	public void loadData(String filePath, DrawingController controller) {
		if(filePath.endsWith(".txt")) {
			loadLogger(filePath, controller);
		} else {
			loadSerializable(filePath, controller);
		}
	}
	
	private void saveLogger(String filePath, DrawingController controller) {
		try(PrintWriter printWriter = new PrintWriter(filePath + ".txt")) {
			List<Object> loggerText = Arrays.asList(controller.getFrame().getDefaultList().toArray());
			for(Object text : loggerText) {
				printWriter.println(text);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void saveSerialize(String filePath, DrawingController controller) {
		try(FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(controller.getModel());
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void loadLogger(String filePath, DrawingController controller) {
		try {
			controller.setNextNumber(0);
			controller.setLog(Files.readAllLines(Paths.get(filePath)));
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void loadSerializable(String filePath, DrawingController controller) {
		try(FileInputStream fileInputStream = new FileInputStream(filePath)) {
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			DrawingModel model = (DrawingModel)objectInputStream.readObject();
			for(Shape shape : model.getShapes()) {
				controller.getModel().add(shape);
			}
			controller.getFrame().repaint();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
