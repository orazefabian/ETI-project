package ab1.impl.Sandner_Kleewein_Oraze;

import ab1.RM;

import java.util.List;

public class RMImpl implements RM {

	private int[] memoryContent;
	private int accumulator;

	public RMImpl(){
		this.memoryContent = new int[0];
	}

	@Override
	public void setInitialMemoryContent(int[] content) {
		this.memoryContent = content;
	}

	@Override
	public boolean execute(List<String> programm) {
		return false;
	}

	@Override
	public int[] getMemoryContent() {
		int count = 0;
		for(int i = memoryContent.length-1; i>=0; i--){
			if(memoryContent[i] == 0){
				count++;
			}else{
				break;
			}
		}
		int[] newArr = new int[memoryContent.length - count];
		for(int i = 0; i<newArr.length; i++){
			newArr[i] = memoryContent[i];
		}
		return newArr;
	}
}
