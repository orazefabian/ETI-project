package ab1.impl.Sandner_Kleewein_Oraze;

import ab1.RM;

import java.util.Arrays;
import java.util.List;

public class RMImpl implements RM {

    private int[] memoryContent;

    public RMImpl() {
        this.memoryContent = new int[0];
    }

    @Override
    public void setInitialMemoryContent(int[] content) {
        this.memoryContent = content;
    }

    @Override
    public boolean execute(List<String> programm) {

        for (int i = 0; i < programm.size(); i++) {
            i = checkCommand(programm.get(i), i);
            //System.out.println("current index: " + i);
            //System.out.println(Arrays.toString(memoryContent)); //TODO: REMOVE!!
        }
        return true;
    }

    private int checkCommand(String x, int index) {
        String[] temp = x.split(" ");
        String command = temp[0];

        if (command.toLowerCase().equals("end")) {
            return index;
        }
        String param = temp[1]; //TODO: check null and if its END

        //to ignore case sensitivity
        switch (command.toLowerCase()) {
            case "load":
                execLoad(param);
                break;
            case "store":
                execStore(param);
                break;
            case "add":
                execAdd(param);
                break;
            case "sub":
                execSub(param);
                break;
            case "mult":
                execMult(param);
                break;
            case "div":
                execDiv(param);
                break;
            case "jzero":
                index = execJzero(param, index);
                break;
            case "goto":
                index = execGoto(param);
                break;
        }

        return index;
    }


    @Override
    public int[] getMemoryContent() {
        int count = 0;
        for (int i = memoryContent.length - 1; i >= 0; i--) {
            if (memoryContent[i] == 0) {
                count++;
            } else {
                break;
            }
        }
        int[] newArr = new int[memoryContent.length - count];
        for (int i = 0; i < newArr.length; i++) {
            newArr[i] = memoryContent[i];
        }
        return newArr;
    }

    private int getParamValue(String param) {
        if (param.contains("#")) {
            param = param.substring(1);
            return Integer.parseInt(param);
        }
        return memoryContent[Integer.parseInt(param)];

    }

    private void execLoad(String param) {

        memoryContent[0] = getParamValue(param);
    }

    private void execStore(String param) {
        int paramvalue = Integer.parseInt(param); //TODO: with * aswell?
        if (paramvalue > memoryContent.length - 1) {
            int[] newArr = new int[paramvalue + 1];
            for (int i = 0; i < memoryContent.length; i++) {
                newArr[i] = memoryContent[i];
            }
            for (int i = memoryContent.length; i < newArr.length; i++) {
                newArr[i] = 0;
            }
            newArr[paramvalue] = memoryContent[0];
            memoryContent = newArr;
        } else {
            memoryContent[paramvalue] = memoryContent[0];
        }
    }

    private void execAdd(String param) {
        memoryContent[0] += getParamValue(param);
    }

    private void execSub(String param) {
        memoryContent[0] -= getParamValue(param);
    }

    private void execMult(String param) {
        memoryContent[0] *= getParamValue(param);
    }

    private void execDiv(String param) {
        memoryContent[0] /= getParamValue(param);
    }

    private int execJzero(String param, int index) {
        if (memoryContent[0] == 0) {
            return Integer.parseInt(param) - 1;
        }
        return index;
    }

    private int execGoto(String param) {
        return Integer.parseInt(param) - 2;
    }

}
