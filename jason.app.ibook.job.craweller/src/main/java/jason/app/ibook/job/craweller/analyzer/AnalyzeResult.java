package jason.app.ibook.job.craweller.analyzer;

import java.util.List;

public class AnalyzeResult {
    private String type;
    private List<Item> result;
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public List<Item> getResult() {
        return result;
    }
    public void setResult(List<Item> result) {
        this.result = result;
    }
}
