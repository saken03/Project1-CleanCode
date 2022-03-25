public class Test extends Question {
    String [] options = new String [4];
    final int numOfOptions = 4; // constant
    char[] labels = {'A', 'B', 'C', 'D'};

    public Test() {
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public String getOptionAt(int x) {
        return options[x];
    }

    public String toString() {
        return getDescription();
    }
}