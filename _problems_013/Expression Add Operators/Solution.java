import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Solution {
    private ScriptEngine engine = (new ScriptEngineManager()).getEngineByName("js");

    public List<String> addOperators(String num, int target) {
        ArrayList<String> result = new ArrayList<String>();
        if (!num.isEmpty())
            doAdd(result, num.substring(0, 1), num, 1, target);
        return result;
    }

    private void doAdd(List<String> result, String path, String num, int start, int target) {
        if (start == num.length()) {
            try {
                if ((Long) engine.eval(path) == target)
                    result.add(path);
            } catch (ScriptException e) {
                e.printStackTrace();
            }
            return;
        }

        doAdd(result, path +  num.charAt(start), num, start + 1, target);
        doAdd(result, path + '+' + num.charAt(start), num, start + 1, target);
        doAdd(result, path + '-' + num.charAt(start), num, start + 1, target);
        doAdd(result, path + '*' + num.charAt(start), num, start + 1, target);
    }
}
