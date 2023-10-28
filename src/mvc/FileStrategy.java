package mvc;

public interface FileStrategy {

	void saveData(String filePath, DrawingController controller);
	void loadData(String filePath, DrawingController controller);
}
