package mvc;

public class FileContext {
	
	private FileStrategy fileStrategy;
	
	public FileContext(FileStrategy fileStrategy) {
		this.fileStrategy = fileStrategy;
	}
	
	public void saveData(String filePath, DrawingController controller) {
		fileStrategy.saveData(filePath, controller);
	}
	
	public void loadData(String filePath, DrawingController controller) {
		fileStrategy.loadData(filePath, controller);
	}
}
