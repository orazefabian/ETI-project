package ab1.impl.Sandner_Kleewein_Oraze;

import ab1.RM;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RMImpl implements RM {

    private int[] memoryContent;
    private Map<String, Integer> labels;

    public RMImpl() {
        this.memoryContent = new int[0];
        this.labels = new HashMap<>();
    }

    @Override
    public void setInitialMemoryContent(int[] content) {
        this.memoryContent = content;
    }

    @Override
    public boolean execute(List<String> programm) {
        // initial loop to determine labels
        for (int i = 0; i < programm.size(); i++) {
            checkForLabel(programm, i);
        }
        if (this.memoryContent.length == 0) {
            this.memoryContent = new int[]{0};
        }
        for (int i = 0; i < programm.size(); i++) {
            i = checkCommand(programm.get(i), i, programm.size());
        }
        return true;
    }

    private void checkForLabel(List<String> program, int currPos) {
        if (program.get(currPos).contains(":")) {
            String[] arr = program.get(currPos).split(":");
            String label = arr[0];
            String cmd = arr[1].substring(1);
            this.labels.put(label, currPos);
            program.set(currPos, cmd);
        }
    }

    private int checkCommand(String x, int index, int amountOfCommands) {
        String[] temp = x.split(" ");
        String command = temp[0];

        if (command.equalsIgnoreCase("end")) {
            return amountOfCommands;
        }
        String param = temp[1];

        switch (command.toLowerCase()) { //lowerCase to ignore case sensitivity
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
        try {
            if (param.startsWith("#")) {
                param = param.substring(1);
                return Integer.parseInt(param);
            } else if (param.startsWith("*")) {
                param = param.substring(1);
                return memoryContent[memoryContent[Integer.parseInt(param)]];
            }
            return memoryContent[Integer.parseInt(param)];
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0;
        }
    }

    private void execLoad(String param) {

        memoryContent[0] = getParamValue(param);
    }

    private void execStore(String param) {
        int paramValue = getParamValueStore(param);
        if (paramValue > memoryContent.length - 1) {
            int[] newArr = new int[paramValue + 1];
            for (int i = 0; i < memoryContent.length; i++) {
                newArr[i] = memoryContent[i];
            }
            for (int i = memoryContent.length; i < newArr.length; i++) {
                newArr[i] = 0;
            }
            newArr[paramValue] = memoryContent[0];
            memoryContent = newArr;
        } else {
            memoryContent[paramValue] = memoryContent[0];
        }
    }

    private int getParamValueStore(String param) {
        if (param.startsWith("*")) {
            param = param.substring(1);
            return memoryContent[Integer.parseInt(param)];
        }
        return Integer.parseInt(param);
    }

    private void execAdd(String param) {
        memoryContent[0] += getParamValue(param);
    }

    private void execSub(String param) {
        int par = getParamValue(param);
        if (memoryContent[0] < par) {
            memoryContent[0] = 0;
        } else {
            memoryContent[0] -= getParamValue(param);
        }
    }

    private void execMult(String param) {
        memoryContent[0] *= getParamValue(param);
    }

    private void execDiv(String param) {
        memoryContent[0] /= getParamValue(param);
    }

    private int execJzero(String param, int index) {
        if (memoryContent[0] == 0) {
            if (this.labels.containsKey(param)) {
                return this.labels.get(param) - 1;
            }
            return Integer.parseInt(param) - 2;
        }
        return index;
    }

    private int execGoto(String param) {
        if (this.labels.containsKey(param)) {
            return this.labels.get(param) - 1;
        }
        return Integer.parseInt(param) - 2;
    }

}
